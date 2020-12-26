package eu.yayi.service;

import eu.yayi.commons.exceptions.BusinessException;
import eu.yayi.core.entities.Person;
import eu.yayi.repository.IPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonDao repo;

    public PersonServiceImpl() {
    }

    @Override
    public List<Person> getAllPerson() throws BusinessException {
        try {
            return this.repo.findAll();
        } catch (Exception exception) {
            throw new BusinessException(exception.getMessage());
        }
    }
}
