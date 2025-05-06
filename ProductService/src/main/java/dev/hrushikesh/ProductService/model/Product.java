package dev.hrushikesh.ProductService.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
public class Product extends BaseModel{
    private double price;
    private double rating;
    private int quantity;
}
