package com.github.rx.service;

import com.github.rx.domain.Catalog;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ICatalogReactiveService {

    Flux<Catalog> getAllCatalog();

    Mono<Catalog> getCatalogByName(String name);

    Catalog save(Catalog c);
}
