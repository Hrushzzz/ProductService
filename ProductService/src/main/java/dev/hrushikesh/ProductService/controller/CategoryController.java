package dev.hrushikesh.ProductService.controller;

import dev.hrushikesh.ProductService.dto.CategoryRequestDTO;
import dev.hrushikesh.ProductService.dto.CategoryResponseDTO;
import dev.hrushikesh.ProductService.dto.ProductResponseDTO;
import dev.hrushikesh.ProductService.model.Category;
import dev.hrushikesh.ProductService.model.Product;
import dev.hrushikesh.ProductService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        Category savedCategory = categoryService.createCategory(categoryRequestDTO);
        CategoryResponseDTO categoryResponseDTO =
                new CategoryResponseDTO(savedCategory.getName(), savedCategory.getDescription());
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") int id){
        Category savedCategory = categoryService.getCategory(id);
        CategoryResponseDTO categoryResponseDTO =
                new CategoryResponseDTO(savedCategory.getName(), savedCategory.getDescription());
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for(Category category : categories){
            CategoryResponseDTO responseDTO = new CategoryResponseDTO(
                    category.getName(), category.getDescription()
            );
            categoryResponseDTOS.add(responseDTO);
        }
        return ResponseEntity.ok(categoryResponseDTOS);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

    @GetMapping("/product/category/{id}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByCategory(@PathVariable("id") int categoryId) {
        List<Product> savedProducts = categoryService.getAllProductsByCategory(categoryId);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : savedProducts) {
            ProductResponseDTO responseDTO = new ProductResponseDTO(
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getRating()
            );
            productResponseDTOS.add(responseDTO);
        }
        return ResponseEntity.ok(productResponseDTOS);
    }

    @GetMapping("/category/product/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryFromProduct(@PathVariable("id") int productId){
        Category category = categoryService.getCategoryFromProduct(productId);
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(
                category.getName(), category.getDescription()
        );
        return ResponseEntity.ok(categoryResponseDTO);
    }
}
