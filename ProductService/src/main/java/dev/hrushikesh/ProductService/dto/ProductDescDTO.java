package dev.hrushikesh.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProductDescDTO {
    private String productName;
    private String productDesc;

    public ProductDescDTO(String productName, String productDesc) {
        this.productName = productName;
        this.productDesc = productDesc;
    }
}
