package com.testeapi.vagas.demo.exceptions;

public class ClassNotIsJpaEntity extends RuntimeException {
    public ClassNotIsJpaEntity(String message) {
        super(message);
    }
}
