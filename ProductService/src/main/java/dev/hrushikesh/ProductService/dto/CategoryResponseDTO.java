package dev.hrushikesh.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CategoryResponseDTO {
    private String categoryName;
    private String categoryDescription;

    public CategoryResponseDTO(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
