package com.github.rx.service;

import com.github.rx.domain.Catalog;
import com.github.rx.repository.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CatalogReactiveServiceImpl implements ICatalogReactiveService {

    @Autowired
    private ICatalogRepository catalogRepository;

    public CatalogReactiveServiceImpl() {
    }

    @Override
    public Flux<Catalog> getAllCatalog() {
        return (Flux<Catalog>) this.catalogRepository.findAll();
    }

    /**
     * TODO
     * @param name
     * @return
     */
    @Override
    public Mono<Catalog> getCatalogByName(String name) {
        return null;
    }

    public Catalog save(Catalog c) {
        return catalogRepository.save(c);
    }
}
