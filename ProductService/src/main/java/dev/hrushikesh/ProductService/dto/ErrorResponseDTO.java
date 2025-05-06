package dev.hrushikesh.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ErrorResponseDTO {
    private String message;
    private Integer status;

    public ErrorResponseDTO(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}
