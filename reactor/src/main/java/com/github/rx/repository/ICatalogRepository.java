package com.github.rx.repository;

import com.github.rx.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatalogRepository extends JpaRepository<Catalog, Integer> {

    List<Catalog> findAll();
   // Catalog saveCatalog(Catalog c);

}
