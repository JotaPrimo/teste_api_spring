package com.testeapi.vagas.demo.domain.repositories.jpa;

import com.testeapi.vagas.demo.domain.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
}
