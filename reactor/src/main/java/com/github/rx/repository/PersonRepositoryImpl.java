package com.github.rx.repository;

import com.github.rx.domain.Person;
import reactor.core.publisher.Flux;

import java.util.List;

public class PersonRepositoryImpl implements IPersonRepository {

    private List<Person> data;

    public PersonRepositoryImpl(List<Person> data) {
        this.data = data;
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.fromIterable(data);
    }

}
