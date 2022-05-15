package com.github.rx.persistence;

import com.github.rx.persistence.entities.ModelObject;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author samir
 *
 * @param <T>
 * @param <S>
 */
@Repository
public abstract class BaseDaoJpa<T extends ModelObject<S>, S extends Serializable> implements BaseDao<T, S> {
    private Class<T> clazz;

    public BaseDaoJpa(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    @PersistenceContext
    private EntityManager entityManager;

    public List<T> createQuery(String qlString, Map<String, Object> map, Integer maxResult, Integer firstResult) {

        TypedQuery<T> typedQuery = entityManager.createQuery(qlString, this.clazz);

        for (Entry<String, Object> entry : map.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        if (firstResult != null) {
            typedQuery.setFirstResult(firstResult);
        }
        if (maxResult != null) {
            typedQuery.setMaxResults(maxResult);
        }
        return typedQuery.getResultList();
    }

    public <R extends Object> R getSingleResult(Class<R> clazzR, String qlString, Map<String, Object> map) {
        TypedQuery<R> typedQuery = entityManager.createQuery(qlString, clazzR);
        for (Entry<String, Object> entry : map.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        typedQuery.setMaxResults(1);
        return typedQuery.getSingleResult();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#setEntityManager(javax.
     * persistence .EntityManager)
     */

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Regular method adapted from the EntityManager
     */

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#get(java.lang.Class,
     * java.io.Serializable)
     */

    public T get(final S primaryKey) {
        return entityManager.getReference(clazz, primaryKey);
    }

    public void delete(T entity) {
        this.save(entity);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#findById(java.lang.Class,
     * java.io.Serializable)
     */

    @Override
    public T findById(final S primaryKey) {
        return entityManager.find(clazz, primaryKey);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.atn.commons.persistence.jpa.BaseDao#persist(com.atn.commons.entities
     * .ModelObject)
     */

    public void persist(final T entity) {
        entityManager.persist(entity);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.atn.commons.persistence.jpa.BaseDao#remove(com.atn.commons.entities
     * .ModelObject)
     */

    public void remove(T entity) {
        if (entity != null) {
            if (!entityManager.contains(entity)) {
                // if entity isn't managed by EM, load it into EM
                entity = entityManager.merge(entity);
            }
            // entity is now a managed entity so it can be removed.
            entityManager.remove(entity);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#save(T)
     */

    public T save(T entity) {
        entity = entityManager.merge(entity);
        return entity;
    }

    // JPA support methods

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#flush()
     */

    public void flush() {
        entityManager.flush();

    }

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#clear()
     */

    public void clear() {
        entityManager.clear();
    }

    /**
     * permet de setter les valeurs quand la query
     */

    public enum PredicateType {
        IS_NULL, IS_NOT_NULL;
    }

    public class CriteriaMap {
        final List<String> attrs = new ArrayList<String>();
        final List<Object> values = new ArrayList<Object>();
        final List<PredicateType> predicates = new ArrayList<PredicateType>();
        int currentSize;

        public synchronized void add(final PredicateType predicate, final String attrName, final Object value) {
            predicates.add(predicate);
            attrs.add(attrName);
            values.add(value);
            currentSize++;
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#getCriteriaBuilder()
     */

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.atn.commons.persistence.jpa.BaseDao#findListByCriteria(java.lang.
     * Class, java.util.Map, java.lang.Integer, java.lang.Integer)
     */

    @SuppressWarnings("unchecked")
    public List<T> findListByCriteria(Map<String, Object> map, Integer maxResults, Integer firstResult,
                                      BaseDao.DELETION_STATUS status) {

        // creteriaQuery
        CriteriaQuery<T> criteriaQuery = getCriteriaBuilder().createQuery(clazz);
        Root<T> criteriaFrom = (Root<T>) buildCriteriaQuery(criteriaQuery, clazz, map, status);
        criteriaQuery.select(criteriaFrom);
        criteriaQuery.distinct(true);

        // create a typedQuerry
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(firstResult);
        // if not limited by maxResult ,return all entities have the conditions
        // in the map
        if (maxResults != null) {
            typedQuery.setMaxResults(maxResults);
        }
        List<T> resultList = typedQuery.getResultList();

        return resultList;
    }

    /**
     * build the criteriaQuerry
     *
     * @param query
     * @param clazz
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    private Root<?> buildCriteriaQuery(CriteriaQuery<?> query, Class<?> clazz, Map<String, Object> map,
                                       BaseDao.DELETION_STATUS status) {
        Root<?> from = query.from(clazz);
        List<Predicate> predicateList = new ArrayList<Predicate>();
        // add deletion status predicate

        if (BaseDao.DELETION_STATUS.ACTIVE.equals(status)) {
            predicateList.add(doPredicate("is:deletingDate", "", from));
        } else if (BaseDao.DELETION_STATUS.DELETED.equals(status)) {
            predicateList.add(doPredicate("isN:deletingDate", "", from));
        }

        // add the where predicates
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!"count".equals(entry.getKey()) && entry.getValue() != null) {
                // order by --desc
                if (entry.getKey().endsWith("#D")) {
                    query.orderBy(getCriteriaBuilder()
                            .desc(from.get(entry.getKey().substring(0, entry.getKey().length() - 2))));
                }
                // order by -- asc
                else if (entry.getKey().endsWith("#A")) {
                    query.orderBy(getCriteriaBuilder()
                            .asc(from.get(entry.getKey().substring(0, entry.getKey().length() - 2))));
                    // group by predicates
                } else if (entry.getKey().endsWith("#G")) {
                    query.groupBy(from.get(entry.getKey().substring(0, entry.getKey().length() - 2)));
                    // OR predicates
                } else if (entry.getKey().equals("OR:")) {
                    Predicate predicate = null;
                    HashMap<String, Object> orMap = (HashMap<String, Object>) entry.getValue();
                    for (String orParam : orMap.keySet()) {
                        if (predicate != null) {
                            predicate = getCriteriaBuilder().or(predicate,
                                    doPredicate(orParam, orMap.get(orParam), from));
                        } else {
                            predicate = getCriteriaBuilder().or(doPredicate(orParam, orMap.get(orParam), from));
                        }
                    }
                    predicateList.add(predicate);
                } else {
                    predicateList.add(doPredicate(entry.getKey(), entry.getValue(), from));
                }
            }
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        // the where predicates
        query.where(predicates);
        return from;
    }

    /**
     * build a predicate
     *
     * @param param
     *            : the key of search dataMap
     * @param value
     *            : the value of seacrh param
     * @param from
     *            : the root of querry (from)
     * @return a predicate
     */
    @SuppressWarnings("unchecked")
    private Predicate doPredicate(String param, Object value, Root<?> from) {
        Predicate predicate = null;
        String paramV;

        if (!"count".equals(param) && value != null) {
            // if is null
            if (param.substring(0, 3).equals("is:")) {
                paramV = param.substring(3);
                // if it's a join attribute
                if (paramV.contains(".")) {
                    predicate = doJoin(paramV, from).isNull();
                } else {
                    predicate = from.get(paramV).isNull();
                }
            }
            // if is not null
            else if (param.substring(0, 4).equals("isN:")) {
                paramV = param.substring(4);
                // if it's a join attribute
                if (paramV.contains(".")) {
                    predicate = doJoin(paramV, from).isNotNull();
                } else {
                    predicate = from.get(paramV).isNotNull();
                }

            }
            // if equal
            else if (param.substring(0, 1).equals("=")) {
                paramV = param.substring(1);
                // if it's a join attribute
                if (paramV.contains(".")) {
                    predicate = getCriteriaBuilder().equal(doJoin(paramV, from), value);
                } else {
                    predicate = getCriteriaBuilder().equal(from.get(paramV), value);
                }
            }
            // if not equal
            else if (param.substring(0, 2).equals("!=")) {
                paramV = param.substring(2);
                // if it's a join attribute
                if (paramV.contains(".")) {
                    predicate = getCriteriaBuilder().notEqual(doJoin(paramV, from), value);
                } else {
                    predicate = getCriteriaBuilder().notEqual(from.get(paramV), value);
                }
            }
            // if LIKE %--% ou %-- ou --%
            else if (param.substring(0, 1).equals("%") || param.endsWith("%")) {
                if (param.endsWith("%") && param.substring(0, 1).equals("%")) {
                    paramV = param.substring(1, param.length() - 1);
                    // if it's a join attribute
                    if (paramV.contains(".")) {
                        predicate = getCriteriaBuilder().like(getCriteriaBuilder().upper(doJoin(paramV, from)),
                                "%" + value + "%");
                    } else {
                        predicate = getCriteriaBuilder().like(getCriteriaBuilder().upper(from.<String>get(paramV)),
                                "%" + value + "%");
                    }
                } else if (param.substring(0, 1).equals("%")) {
                    paramV = param.substring(1, param.length());
                    // if it's a join attribute
                    if (paramV.contains(".")) {
                        predicate = getCriteriaBuilder().like(getCriteriaBuilder().upper(doJoin(paramV, from)),
                                "%" + value);
                    } else {
                        predicate = getCriteriaBuilder().like(getCriteriaBuilder().upper(from.<String>get(paramV)),
                                "%" + value);
                    }
                } else {
                    paramV = param.substring(0, param.length() - 1);
                    // if it's a join attribute
                    if (paramV.contains(".")) {
                        predicate = getCriteriaBuilder().like(getCriteriaBuilder().upper(doJoin(paramV, from)),
                                value + "%");
                    } else {
                        predicate = getCriteriaBuilder().like(getCriteriaBuilder().upper(from.<String>get(paramV)),
                                value + "%");
                    }
                }
            }
            // if less than , if less than or equal
            else if (param.substring(0, 1).equals("<")) {
                if (param.substring(0, 2).equals("<=")) {
                    paramV = param.substring(2);
                    // if it's a join attribute
                    if (paramV.contains(".")) {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().lessThanOrEqualTo(doJoin(paramV, from), (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().le(doJoin(paramV, from), (Number) value);
                        }
                    } else {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().lessThanOrEqualTo(from.<Date>get(paramV), (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().le(from.<Number>get(paramV), (Number) value);
                        }
                    }
                } else {
                    paramV = param.substring(1);
                    // if it's a join attribute
                    if (paramV.contains(".")) {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().lessThan(doJoin(paramV, from), (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().lt(doJoin(paramV, from), (Number) value);
                        }
                    } else {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().lessThan(from.<Date>get(paramV), (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().lt(from.<Number>get(paramV), (Number) value);
                        }
                    }
                }
            }
            // if greater than ,if greater than or equal
            else if (param.substring(0, 1).equals(">")) {
                if (param.substring(0, 2).equals(">=")) {
                    paramV = param.substring(2);
                    // if it's a join attribute
                    if (paramV.contains(".")) {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().greaterThanOrEqualTo(doJoin(paramV, from), (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().ge(doJoin(paramV, from), (Number) value);
                        }
                    } else {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().greaterThanOrEqualTo(from.<Date>get(paramV), (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().ge(from.<Number>get(paramV), (Number) value);
                        }
                    }
                } else {
                    paramV = param.substring(1);
                    // if it's a join attribute
                    if (paramV.contains(".")) {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().greaterThan(doJoin(paramV, from), (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().gt(doJoin(paramV, from), (Number) value);
                        }
                    } else {
                        if (value instanceof Date) {
                            predicate = getCriteriaBuilder().greaterThan(from.<Date>get(param.substring(1)),
                                    (Date) value);
                        } else {
                            predicate = getCriteriaBuilder().gt(from.<Number>get(param.substring(1)), (Number) value);
                        }
                    }
                }
            }
            // if in list
            else if (param.substring(0, 3).equals("IN:")) {
                paramV = param.substring(3);
                // if it's a join attribute
                if (paramV.contains(".")) {
                    predicate = doJoin(paramV, from).in((Collection<?>) value);
                } else {
                    predicate = from.get(paramV).in((Collection<?>) value);
                }
            }
            // if not in list
            else if (param.substring(0, 4).equals("!IN:")) {
                paramV = param.substring(4);
                // if it's a join attribute
                if (paramV.contains(".")) {
                    predicate = getCriteriaBuilder().not(doJoin(paramV, from).in((Collection<?>) value));
                } else {
                    predicate = getCriteriaBuilder().not(from.get(paramV).in((Collection<?>) value));
                }
            }
        }
        return predicate;
    }

    /**
     * join with the foreign key
     *
     */
    @SuppressWarnings({ "rawtypes" })
    private Path doJoin(String paramV, Root<?> root) {
        Join join = null;
        while (paramV.contains(".")) {
            if (join != null) {
                join = join.join(paramV.substring(0, paramV.indexOf(".")));
            } else {
                join = root.join(paramV.substring(0, paramV.indexOf(".")));
            }
            paramV = paramV.substring(paramV.indexOf(".") + 1);
        }
        Path path = join.get(paramV);
        return path;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.atn.commons.persistence.jpa.BaseDao#getCount(java.lang.String)
     */
    @Override
    public int getCount(Map<String, Object> map, BaseDao.DELETION_STATUS status) {
        // count Query
        CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(Long.class);
        Root<?> countFrom = buildCriteriaQuery(countQuery, clazz, map, status);
        countQuery.select(getCriteriaBuilder().countDistinct(countFrom));
        Object count = entityManager.createQuery(countQuery).getSingleResult();
        return Integer.parseInt(count.toString());
    }

}