package com.github.rx;

import com.github.rx.client.PersonController;
import com.github.rx.config.GenericSubscriber;
import com.github.rx.domain.Catalog;
import com.github.rx.repository.ICatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@SpringBootApplication
@Configuration
//@EnableAutoConfiguration
@EnableJpaRepositories
@ImportResource("classpath:beans.xml")
//"eu.yayi.client",
@ComponentScan(basePackages = {"eu.yayi.service", "eu.yayi.repository"})
public class Reactor  implements CommandLineRunner {
    static Logger logger = LoggerFactory.getLogger(Reactor.class);

    /*
   @Autowired
   private ICatalogReactiveService catalogReactiveService;
   */


    public static void main(String[] args) {
        logger.info("###############################");
        SpringApplication.run(Reactor.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try (ConfigurableApplicationContext ctxt
                     = new ClassPathXmlApplicationContext("beans.xml")) {

            logger.info("#############  PersonController ##################");
            PersonController client1 = ctxt.getBean(PersonController.class);
            logger.info("Get All persons FROM DB : {}", client1.persons());
            client1.persons().subscribe(new GenericSubscriber());

            /*
            logger.info("#############  CatalogController ##################");
            CatalogController client2 = ctxt.getBean(CatalogController.class);
            logger.info("Get All catalogs FROM DB : {}", client2.catalogs());
            client2.catalogs().subscribe(new GenericSubscriber());

            logger.info("#############  Catalog ### management ###############");
            Catalog catalog1 = new Catalog();
            catalog1.setFile("metadata ..");
            Catalog savedCatalog = reactiveCatalogService.save(catalog1);
            logger.info("SAVE Catalog TO DB  id : {}", savedCatalog.getId());
             */
        }
    }

    @Bean
    public CommandLineRunner demo(ICatalogRepository repository) {
        logger.info("#############  Catalog management ###############");
        return (args) -> {
            // save a few catalogs
            Catalog catalog1 = new Catalog();
            catalog1.setFileName("metadata ..");
            catalog1.setFile("<xml/>".getBytes(StandardCharsets.UTF_8));
            Catalog savedCatalog =  repository.save(catalog1);

            // fetch all catalogs
            logger.info("Catalog found with findAll():");
            logger.info("-------------------------------");
            for (Catalog catalogs : repository.findAll()) {
                logger.info(catalogs.toString());
            }
            logger.info("");

            // fetch an individual catalog by ID
            Optional<Catalog> catalog = repository.findById(savedCatalog.getId());
            logger.info("Catalog found with findById(1L):");
            logger.info("--------------------------------");
            logger.info(catalog.get().toString());
            logger.info("");
        };
    }


    /*
    @Bean
    public ICatalogReactiveService getCatalogReactiveService() {
        return new CatalogReactiveServiceImpl(getCatalogRepository());
    }

    @Bean
    public ICatalogRepository getCatalogRepository() {
        return new CatalogRepositoryImpl();
    }
    */

}
