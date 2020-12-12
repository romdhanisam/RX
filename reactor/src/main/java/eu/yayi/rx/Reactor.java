package eu.yayi.rx;

import eu.yayi.client.PersonController;
import eu.yayi.config.GenericSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Reactor {
    static Logger logger = LoggerFactory.getLogger(Reactor.class);

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctxt
                     = new ClassPathXmlApplicationContext("beans.xml")) {
            PersonController client = ctxt.getBean(PersonController.class);
            logger.info("Get All persons FROM DB : {}", client.persons());
            client.persons()
                    .subscribe(new GenericSubscriber());

        }
    }

}
