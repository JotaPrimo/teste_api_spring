package com.testeapi.vagas.demo.domain.exceptions;

public class TodoAlreadyCompletedException extends RuntimeException {
    public TodoAlreadyCompletedException(String message) {
        super(message);
    }
}
