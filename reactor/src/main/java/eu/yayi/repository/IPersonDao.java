package eu.yayi.repository;

import eu.yayi.commons.jpa.persistence.BaseDao;
import eu.yayi.core.entities.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonDao extends BaseDao<Person, Long> {

}
