package com.testeapi.vagas.demo.domain.exceptions;

public class ClassNotIsJpaEntity extends RuntimeException {
    public ClassNotIsJpaEntity(String message) {
        super(message);
    }
}
