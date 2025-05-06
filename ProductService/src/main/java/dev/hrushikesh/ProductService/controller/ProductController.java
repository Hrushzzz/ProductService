package dev.hrushikesh.ProductService.controller;

import dev.hrushikesh.ProductService.dto.FakeStoreProductDTO;
import dev.hrushikesh.ProductService.dto.ProductProjection;
import dev.hrushikesh.ProductService.dto.ProductResponseDTO;
import dev.hrushikesh.ProductService.model.Product;
import dev.hrushikesh.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/v1/product") // every API on this controller would be /v1/API
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/category/{id}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByCategory(@PathVariable("id") int categoryId) {
        List<Product> savedProducts = productService.getAllProductByCategoryId(categoryId);
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

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/v1/product")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        Product savedProduct = productService.getProduct(id);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/product/desc/{description}") // localhost:8080/product/desc/something
    public ResponseEntity<List<Product>> getProductByDescription(@PathVariable("description") String description){
        List<Product> matchedProducts = productService.getProductByDescription(description);
        return ResponseEntity.ok(matchedProducts);
    }

    @GetMapping("/product/projection/{name}") // localhost:8080/product/projection/something
    public ResponseEntity<ProductProjection> getProductProjectionByName(@PathVariable("name") String name){
        ProductProjection projection = productService.getProductProjection(name);
        return ResponseEntity.ok(projection);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable("id") int productId){
        boolean response = productService.deleteProduct(productId);
        return ResponseEntity.ok(response);
    }



//    Practice API's on FakeStore data :::

    @GetMapping("/product")
    public FakeStoreProductDTO[] getAllProductsFake() {
        return productService.getAllProductsFromFakeStore();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<FakeStoreProductDTO> getProduct(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product doesn't exist");
            // return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // Sending ResponseEntity :::
        FakeStoreProductDTO fakeStoreProductDTO = productService.getProductById(id);
        return new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);
    }

    @PostMapping("/product")
    public FakeStoreProductDTO createProduct(@RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        return productService.createProduct(fakeStoreProductDTO);
    }

    @PutMapping("/product/{id}")
    public FakeStoreProductDTO replaceProduct(@PathVariable("id") int id, @RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        return productService.replaceProduct(id, fakeStoreProductDTO);
    }

    @DeleteMapping("/product/{id}")
    public Boolean deleteProduct(@PathVariable("id") int id) {
        return productService.deleteProduct(id);
    }

//    This method will be automatically called whenever IllegalArgumentException is thrown in this controller.
    @ExceptionHandler(IllegalArgumentException.class)
//    Passing multiple Exceptions :::
//    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(NullPointerException.class)
//    public String handleNullPointerExceptions(Exception exception) {
//        return exception.getMessage();
//    }

//    @ExceptionHandler(Exception.class)
//    public String handleAllExceptions(Exception exception) {
//        return exception.getMessage();
//    }

}