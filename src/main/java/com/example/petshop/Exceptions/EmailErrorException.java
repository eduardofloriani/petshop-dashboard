package com.example.petshop.Exceptions;

public class EmailErrorException extends RuntimeException{

    public EmailErrorException (String message) {
        super(message);
    }

}
