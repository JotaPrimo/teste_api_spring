package com.testeapi.vagas.demo.domain.repositories.jpa;

import com.testeapi.vagas.demo.domain.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId);
}
