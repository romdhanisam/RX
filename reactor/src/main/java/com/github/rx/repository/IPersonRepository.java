package com.github.rx.repository;

import com.github.rx.domain.Person;
import reactor.core.publisher.Flux;

public interface IPersonRepository {

    Flux<Person> findAll();

}
