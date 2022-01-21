package com.github.rx.service;

import com.github.rx.domain.Catalog;
import com.github.rx.repository.ICatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CatalogReactiveServiceImpl implements ICatalogReactiveService {

    @Autowired
    private ICatalogRepository catalogRepository;

    public CatalogReactiveServiceImpl(ICatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
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
