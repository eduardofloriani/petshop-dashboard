package com.example.petshop.Exceptions;

public class RepeatedEmailException extends RuntimeException{

    public RepeatedEmailException(String message) {
        super(message);
    }

}
