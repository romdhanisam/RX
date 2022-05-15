package com.github.rx.repository;

import com.github.rx.domain.Catalog;
import com.github.rx.persistence.BaseDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalog2Repository extends BaseDao<Catalog, Integer> {
    List<Catalog> findAll();
}
