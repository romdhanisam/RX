package com.github.rx.persistence;

import com.github.rx.persistence.entities.ModelObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author samir
 *
 * @param <T>
 * @param <S>
 */
public interface BaseDao<T extends ModelObject<S>, S extends Serializable> {
    enum DELETION_STATUS {
        DELETED, ACTIVE, BOTH
    }

    /**
     * Use this method to laod an entity insatnce that MUST exist. An exception
     * will be thrown if the instance is not found.
     *
     * @see javax.persistence.EntityManager#getReference(Class, Object)
     */
    public T get(S primaryKey);

    /**
     * methode qui exécute une requete JPAQL
     *
     * @param qlString
     * @param maxResult
     * @param firstResult
     * @return List of T
     */
    public List<T> createQuery(String qlString, Map<String, Object> map,
                               Integer maxResult, Integer firstResult);

    /**
     * get generic singleResult query
     *
     * @param clazzR
     * @param qlString
     * @param map
     * @return
     */
    public <R extends Object> R getSingleResult(Class<R> clazzR,
                                                String qlString, Map<String, Object> map);

    /**
     * @see javax.persistence.EntityManager#find(Class, Object)
     */
    public T findById(S primaryKey);

    /**
     * @see javax.persistence.EntityManager#persist(Object)
     */
    public void persist(T entity);

    /**
     * @see javax.persistence.EntityManager#remove(Object)
     */
    public void remove(T entity);

    /**
     * @see javax.persistence.EntityManager#merge(Object)
     */
    public T save(T entity);

    /**
     * @see javax.persistence.EntityManager#flush()
     */
    public void flush();

    public void clear();

    public void delete(T entity);

    int getCount(Map<String, Object> map, DELETION_STATUS status);

    public List<T> findListByCriteria(Map<String, Object> map,
                                      Integer maxResults, Integer firstResult, DELETION_STATUS status);

}