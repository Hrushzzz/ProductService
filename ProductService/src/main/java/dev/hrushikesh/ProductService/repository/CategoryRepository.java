package dev.hrushikesh.ProductService.repository;

import dev.hrushikesh.ProductService.model.Category;
import dev.hrushikesh.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    Optional<Category> findByProductsIn(List<Product> products);
}
