package com.github.rx;

import com.github.rx.client.PersonController;
import com.github.rx.config.GenericSubscriber;
import com.github.rx.domain.Catalog;
import com.github.rx.repository.ICatalog2Repository;
import com.github.rx.repository.ICatalogRepository;
import com.github.rx.service.CatalogReactiveServiceImpl;
import com.github.rx.service.ICatalogReactiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ImportResource("classpath:beans.xml")
@ComponentScan({ "com.github.*" })
public class Reactor  implements CommandLineRunner {
    static Logger logger = LoggerFactory.getLogger(Reactor.class);

   @Autowired
   private ICatalogReactiveService catalogReactiveService;

    public static void main(String[] args) {
        logger.info("###############################");
        SpringApplication.run(Reactor.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try (ConfigurableApplicationContext ctxt
                     = new ClassPathXmlApplicationContext("beans.xml")) {
        }
    }


    @Bean
    public CommandLineRunner demo(ICatalog2Repository repository) {
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
            //Optional<Catalog> catalog = repository.findById(savedCatalog.getId());
            Catalog catalog = repository.findById(savedCatalog.getId());
            logger.info("Catalog found with findById(1L):");
            logger.info("--------------------------------");
            logger.info(catalog.toString());
            logger.info("");
        };
    }

    /*

    @Bean
    public ICatalogReactiveService getICatalogReactiveService() {
        return new CatalogReactiveServiceImpl();
    }
    @Bean
    public ICatalogRepository getCatalogRepository() {
        return new CatalogRepositoryImpl();
    }
    */


}
