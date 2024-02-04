package com.testeapi.vagas.demo.exceptions;

public class TodoAlreadyCompletedException extends RuntimeException {
    public TodoAlreadyCompletedException(String message) {
        super(message);
    }
}
