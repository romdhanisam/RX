package eu.yayi.client;

import eu.yayi.domain.Person;
import eu.yayi.service.IPersonReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

public class PersonController {

    @Autowired
    private IPersonReactiveService reactiveService;

    @GetMapping(value = "/persons", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> persons() {
        Flux<Person> persons = reactiveService.getAllPerson();
        return persons;
    }
}
