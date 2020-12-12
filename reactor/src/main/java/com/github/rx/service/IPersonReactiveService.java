package com.github.rx.service;

import com.github.rx.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonReactiveService {

    Flux<Person> getAllPerson();

    Mono<Person> getPersonByName(String firstName);
}
