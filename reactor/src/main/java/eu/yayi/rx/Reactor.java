package eu.yayi.rx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Reactor
{

    Logger logger = LoggerFactory.getLogger(Reactor.class);
    public static void main(String[] args) {
        SpringApplication.run(Reactor.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            logger.info("Reactor Environment : {}", ctx.getEnvironment().getActiveProfiles());
        };
    }
}
