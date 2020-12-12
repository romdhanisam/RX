package eu.yayi.repository;

import eu.yayi.domain.Person;
import reactor.core.publisher.Flux;

public interface IPersonRepository {

    Flux<Person> findAll();

}
