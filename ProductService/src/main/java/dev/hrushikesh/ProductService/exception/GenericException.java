package dev.hrushikesh.ProductService.exception;

public class GenericException extends RuntimeException{
    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }
}
