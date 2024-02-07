package com.testeapi.vagas.demo.utils;

import com.testeapi.vagas.demo.exceptions.ClassNotIsJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


import java.util.Set;
import java.util.stream.Collectors;

public class TableFieldChecker {

    @PersistenceContext
    private static EntityManager entityManager;

    public static boolean hasFieldInEntity(Class<?> entityClass, String fieldName) {
        if (entityClass.isAnnotationPresent(Entity.class)) {
            Set<String> columnNames = entityManager.getMetamodel().entity(entityClass)
                    .getAttributes()
                    .stream()
                    .map(a -> a.getName())
                    .collect(Collectors.toSet());

            return columnNames.contains(fieldName);
        } else {
            throw new ClassNotIsJpaEntity("Class is not a JPA entity");
        }
    }
}
