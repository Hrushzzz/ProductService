package dev.hrushikesh.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CategoryRequestDTO {
    private String categoryName;
    private String categoryDescription;

    public CategoryRequestDTO(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
