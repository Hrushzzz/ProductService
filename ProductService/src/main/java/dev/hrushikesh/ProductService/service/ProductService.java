package dev.hrushikesh.ProductService.service;

import dev.hrushikesh.ProductService.repository.ProductRepository;
import dev.hrushikesh.ProductService.client.FakeStoreClient;
import dev.hrushikesh.ProductService.dto.FakeStoreProductDTO;
import dev.hrushikesh.ProductService.dto.ProductProjection;
import dev.hrushikesh.ProductService.exception.ProductNotFoundException;
import dev.hrushikesh.ProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public List<Product> getAllProductByCategoryId(int categoryId){
        List<Product> products = categoryService.getAllProductsByCategory(categoryId);
        return products;
    }

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public boolean deleteProduct(int productId){
        productRepository.deleteById(productId);
        return true;
    }

    public Product getProduct(int productId){
        Optional<Product> productOptional =
                productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id " + productId + " not found");
        } else {
            return productOptional.get();
        }
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductByDescription(String description){
//        List<Product> products = productRepository.findAll();
//        List<Product> matchedProducts = new ArrayList<>();
//        for(Product product : products){
//            if(product.getDescription().equals(description)){
//                matchedProducts.add(product);
//            }
//        }
//        return matchedProducts;
        List<Product> matchedProducts = productRepository.findAllByDescriptionIgnoreCase(description);
        return matchedProducts;
    }

    public Product updateProduct(Product newProduct, int productId){
        Product savedProduct = getProduct(productId);
        newProduct.setId(productId);
        Product updatedProduct = productRepository.save(newProduct);
        return updatedProduct;
    }

    public ProductProjection getProductProjection(String productName){
        return productRepository.findFirstByName(productName);
    }


    // FakeStoreAPI methods :::
    public FakeStoreProductDTO[] getAllProductsFromFakeStore() {
        return fakeStoreClient.getAllProducts();
    }

    public FakeStoreProductDTO getProductById(int productId) {
        return fakeStoreClient.getProduct(productId);
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO input) {
        return fakeStoreClient.createProduct(input);
    }

    public FakeStoreProductDTO replaceProduct(int id, FakeStoreProductDTO input) {
        return fakeStoreClient.replaceProduct(id, input);
    }

//    public Boolean deleteProduct(int id) {
//        return fakeStoreClient.deleteProduct(id);
//    }
}
