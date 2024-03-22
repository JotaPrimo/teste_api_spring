package com.testeapi.vagas.demo.domain.repositories;

import com.testeapi.vagas.demo.domain.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoRepository extends JpaRepository<Todo, Long> {
}
