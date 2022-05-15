package com.github.rx.repository.impl;

import com.github.rx.domain.Catalog;
import com.github.rx.persistence.BaseDaoJpa;
import com.github.rx.repository.ICatalog2Repository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Catalog2RepositoryImpl extends BaseDaoJpa<Catalog, Integer> implements ICatalog2Repository {
    public Catalog2RepositoryImpl() {
        super(Catalog.class);
    }

    @Override
    public List<Catalog> findAll() {
        return new ArrayList<>();
    }
}
