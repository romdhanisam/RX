package eu.yayi.client;

import eu.yayi.domain.Person;
import eu.yayi.rx.Reactor;
import eu.yayi.service.IPersonReactiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
