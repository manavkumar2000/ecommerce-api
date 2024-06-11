package dev.manav.ecommerce.exceptions;

public class ProductNotAdded extends RuntimeException{
    public ProductNotAdded(String message) {
        super(message);
    }
}
