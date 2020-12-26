package eu.yayi.service;

import eu.yayi.commons.exceptions.BusinessException;
import eu.yayi.core.entities.Person;

import java.util.List;

public interface IPersonService {

    List<Person> getAllPerson() throws BusinessException;
}
