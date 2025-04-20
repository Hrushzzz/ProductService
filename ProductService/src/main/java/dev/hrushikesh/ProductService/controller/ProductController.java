package dev.hrushikesh.ProductService.controller;

import dev.hrushikesh.ProductService.dto.FakeStoreProductDTO;
import dev.hrushikesh.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public FakeStoreProductDTO[] getAllProducts() {
        return productService.getAllProductsFromFakeStore();
    }

    @GetMapping("/product/{id}")
    public FakeStoreProductDTO getProduct(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }
}
