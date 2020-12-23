package eu.yayi.rx;

import eu.yayi.client.PersonController;
import eu.yayi.config.GenericSubscriber;
import eu.yayi.config.SimpleSubscriber;
import eu.yayi.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "eu.yayi")
public class Reactor {
    static Logger logger = LoggerFactory.getLogger(Reactor.class);

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context
                     = new ClassPathXmlApplicationContext("beans.xml")) {
            List<Person> theTrackList = context.getBean("fakeData", List.class);
            logger.info("Laod context ...");
            logger.info("Laod theTrackList ... {} ",theTrackList);
        }
        SpringApplication.run(Reactor.class, args);
    }

}
