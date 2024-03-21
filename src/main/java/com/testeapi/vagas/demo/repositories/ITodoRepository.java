package com.testeapi.vagas.demo.repositories;

import com.testeapi.vagas.demo.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoRepository extends JpaRepository<Todo, Long> {
}
