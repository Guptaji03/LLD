package com.Practice.LLD.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
            super(message);
        }

}
