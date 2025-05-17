package dev.hrushikesh.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductReqDTO {
    private String name;
    private String description;
    private int categoryId;
    private double price;
    private double rating;
    private int quantity;
}
