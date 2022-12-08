package com.daymeijroos.iacspring.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String resource, String id) {
        super(String.format("%s with id %s not found.", resource, id));
    }
}