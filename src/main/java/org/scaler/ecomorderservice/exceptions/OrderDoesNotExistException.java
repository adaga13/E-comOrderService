package org.scaler.ecomorderservice.exceptions;

public class OrderDoesNotExistException extends Exception {
    public OrderDoesNotExistException(String message) {
        super(message);
    }
}
