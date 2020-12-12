package eu.yayi.repository;

import eu.yayi.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
