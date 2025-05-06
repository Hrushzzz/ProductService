package dev.hrushikesh.ProductService.dto;

public interface ProductProjection {
    String getName();
    String getDescription();
}

// Projections -> interface mimicking/projecting an object containing subset of attributes/columns for a model

// Create an interface, and add get methods for the attributes.
// Convention -> getAttributeName() -> attribute name should match the get method.

// Use this projection as output for your repo methods.
