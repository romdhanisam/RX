package com.github.rx.client;

import com.github.rx.domain.Catalog;
import com.github.rx.service.ICatalogReactiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Controller
public class CatalogController {

    static Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private ICatalogReactiveService reactiveCatalogService;

    public CatalogController() {
        logger.debug("CatalogController .... ctrs ");
    }

    @GetMapping(value = "/catalogs", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Catalog> catalogs() {
        Flux<Catalog> persons = reactiveCatalogService.getAllCatalog();
        logger.debug("CatalogController .... {} ", persons);
        return persons;
    }
}
