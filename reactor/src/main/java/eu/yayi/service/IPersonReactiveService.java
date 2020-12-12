package eu.yayi.service;

import eu.yayi.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonReactiveService {

    Flux<Person> getAllPerson();

    Mono<Person> getPersonByName(String firstName);
}
