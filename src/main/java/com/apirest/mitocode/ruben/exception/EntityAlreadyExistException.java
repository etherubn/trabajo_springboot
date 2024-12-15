package com.apirest.mitocode.ruben.exception;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String entity,String field) {
        super(String.format("%s already exists on field %s ",entity,field));
    }
}
