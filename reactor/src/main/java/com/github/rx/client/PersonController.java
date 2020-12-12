package com.github.rx.client;

import com.github.rx.domain.Person;
import com.github.rx.service.IPersonReactiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

public class PersonController {

    static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private IPersonReactiveService reactiveService;

    public PersonController(IPersonReactiveService reactiveService) {
        logger.debug("PersonController .... ctrs ");
        this.reactiveService = reactiveService;
    }

    @GetMapping(value = "/persons", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> persons() {
        Flux<Person> persons = reactiveService.getAllPerson();
        logger.debug("PersonController .... {} ", persons);
        return persons;
    }
}
