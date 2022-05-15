package com.github.rx.repository;

import com.github.rx.domain.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalogRepository extends CrudRepository<Catalog, Integer> {

    List<Catalog> findAll();

}
