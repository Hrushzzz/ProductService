package dev.hrushikesh.ProductService.service;

import dev.hrushikesh.ProductService.dto.ProductReqDTO;
import dev.hrushikesh.ProductService.dto.SortDTO;
import dev.hrushikesh.ProductService.exception.CategoryNotFoundException;
import dev.hrushikesh.ProductService.model.Category;
import dev.hrushikesh.ProductService.repository.CategoryRepository;
import dev.hrushikesh.ProductService.repository.ProductRepository;
import dev.hrushikesh.ProductService.client.FakeStoreClient;
import dev.hrushikesh.ProductService.dto.FakeStoreProductDTO;
import dev.hrushikesh.ProductService.dto.ProductProjection;
import dev.hrushikesh.ProductService.exception.ProductNotFoundException;
import dev.hrushikesh.ProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private CategoryRepository categoryRepository;

    public Product saveProduct(ProductReqDTO productReqDTO) {
        Category savedCategory = categoryRepository.findById(productReqDTO.getCategoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category does not exist"));

        Product product = new Product();
        product.setName(productReqDTO.getName());
        product.setName(productReqDTO.getName());
        product.setDescription(productReqDTO.getDescription());
        product.setPrice(productReqDTO.getPrice());
        product.setQuantity(productReqDTO.getQuantity());
        product.setRating(productReqDTO.getRating());

        Product savedProduct = productRepository.save(product);

        savedCategory.getProducts().add(savedProduct);
        categoryRepository.save(savedCategory);

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

    public Page<Product> getAllProductsPaginated(int pageNumber, String filterAsc, String filterDesc){
        // Sort sort = Sort.by(parameter).ascen().and( Sort....).and(Sort....)
        Sort sort = Sort.by(filterAsc).ascending().and(Sort.by(filterDesc).descending());
        return productRepository.findAll(PageRequest.of(pageNumber, 3, sort));
    }

    public Page<Product> getAllProductsPaginated(int pageNumber, List<SortDTO> sortingDTO){
        Sort sort = Sort.by("price").ascending().and(Sort.by("rating").descending());
        //TODO : add the logic to sort based on the items inside sortingDTO
        return productRepository.findAll(PageRequest.of(pageNumber, 3, sort));
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

}
