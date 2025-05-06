package dev.hrushikesh.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductResponseDTO {
    private String productName;
    private String productDescription;
    private double productPrice;
    private double rating;

    public ProductResponseDTO(String productName, String productDescription, double productPrice, double rating) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.rating = rating;
    }
}
