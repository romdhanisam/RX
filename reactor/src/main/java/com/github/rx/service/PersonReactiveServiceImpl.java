package com.github.rx.service;

import com.github.rx.domain.Person;
import com.github.rx.repository.IPersonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonReactiveServiceImpl implements IPersonReactiveService {

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
     * @param firstName
     * @return
     */
    @Override
    public Mono<Person> getPersonByName(String firstName) {
        return null;
    }
}
