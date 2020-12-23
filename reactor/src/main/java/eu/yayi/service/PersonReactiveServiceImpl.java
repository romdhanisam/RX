package eu.yayi.service;

import eu.yayi.domain.Person;
import eu.yayi.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonReactiveServiceImpl implements IPersonReactiveService {

    @Autowired
    //@Qualifier(value = "repo")
    private IPersonRepository repo;

    public PersonReactiveServiceImpl(IPersonRepository repo) {
        this.repo = repo;
    }

    @Override
    public Flux<Person> getAllPerson() {
        return this.repo.findAll();
    }

    /**
     * TODO
     *
     * @param firstName
     * @return
     */
    @Override
    public Mono<Person> getPersonByName(String firstName) {
        return null;
    }
}
