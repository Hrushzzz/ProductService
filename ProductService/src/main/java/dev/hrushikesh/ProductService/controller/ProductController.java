package dev.hrushikesh.ProductService.controller;

import dev.hrushikesh.ProductService.dto.FakeStoreProductDTO;
import dev.hrushikesh.ProductService.dto.ProductProjection;
import dev.hrushikesh.ProductService.dto.ProductReqDTO;
import dev.hrushikesh.ProductService.dto.ProductResponseDTO;
import dev.hrushikesh.ProductService.model.Product;
import dev.hrushikesh.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductReqDTO productReqDTO){
        Product savedProduct = productService.saveProduct(productReqDTO);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/all/product/{pageNumber}/{ascFilter}/{descFilter}")
    public ResponseEntity<Page<Product>> getAllProducts(@PathVariable("pageNumber") int pageNumber,
                                                        @PathVariable("ascFilter") String ascFilter,
                                                        @PathVariable("descFilter") String descFilter ){
        Page<Product> products = productService.getAllProductsPaginated(pageNumber, ascFilter, descFilter);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all/product")
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


//    FakeStore API end points :::

    @GetMapping("/fakestore/product")
    public FakeStoreProductDTO[] getAllProductsFake() {
        return productService.getAllProductsFromFakeStore();
    }

    @GetMapping("fakestore/product/{id}")
    public ResponseEntity<FakeStoreProductDTO> getProduct(@PathVariable("id") int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Product doesn't exist");
            // return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // Sending ResponseEntity :::
        FakeStoreProductDTO fakeStoreProductDTO = productService.getProductById(id);
        return new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);
    }

    @PostMapping("fakestore/product")
    public FakeStoreProductDTO createProduct(@RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        return productService.createProduct(fakeStoreProductDTO);
    }

    @PutMapping("fakestore/product/{id}")
    public FakeStoreProductDTO replaceProduct(@PathVariable("id") int id, @RequestBody FakeStoreProductDTO fakeStoreProductDTO) {
        return productService.replaceProduct(id, fakeStoreProductDTO);
    }

    @DeleteMapping("fakestore/product/{id}")
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