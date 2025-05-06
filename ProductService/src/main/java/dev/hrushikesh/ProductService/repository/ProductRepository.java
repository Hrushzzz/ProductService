package dev.hrushikesh.ProductService.repository;

import dev.hrushikesh.ProductService.dto.ProductProjection;
import dev.hrushikesh.ProductService.model.Product;
import dev.hrushikesh.ProductService.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByDescription(String description);
    List<Product> findAllByDescriptionIgnoreCase(String description);
    Product findFirstByDescriptionIgnoreCase(String description);
    ProductProjection findFirstByName(String name);
    ProductProjection findByNameAndDescription(String name, String description);
}

/*
    Extending JpaRepository adds all fundamental CRUD operation methods in ProductRepository interface
    we don't need to implement those methods, Spring Data JPA will do that for us
    We will just use them directly

    Templates:
    findAll
    findBy
    findFirst
    findLast

    findAllBy<ATTRIBUTENAME>IgnoreCase
    findAllByDescriptionIgnoreCase

    findAll     description     ignorecase
    SELECT * FROM TABLE WHERE <ATTRIBUTENAME> = "" IGNORE CASE;
    SELECT * FROM TABLE WHERE <Description> = "" IGNORE CASE;
 */