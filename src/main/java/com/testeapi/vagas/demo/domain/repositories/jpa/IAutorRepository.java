package com.testeapi.vagas.demo.domain.repositories.jpa;

import com.testeapi.vagas.demo.domain.entities.Autor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findAllByNomeOrdered(Sort.Direction direction);
}
