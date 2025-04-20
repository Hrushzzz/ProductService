package dev.hrushikesh.ProductService.client;

import dev.hrushikesh.ProductService.dto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplate restTemplate;

    public FakeStoreProductDTO[] getAllProducts() {
        String getAllProductURL = "https://fakestoreapi.com/products";
        FakeStoreProductDTO[] response = restTemplate.getForObject(getAllProductURL, FakeStoreProductDTO[].class);
        return response;
    }

    public FakeStoreProductDTO getProduct(int productId) {
        String getProduct = "https://fakestoreapi.com/products/" + productId;
        FakeStoreProductDTO response = restTemplate.getForObject(getProduct, FakeStoreProductDTO.class);
        return response;
    }

}
