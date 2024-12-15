package com.apirest.mitocode.ruben.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String entity) {
        super(String.format("%s not found", entity));
    }
}
