package com.testeapi.vagas.demo.domain.repositories.jpa;

import com.testeapi.vagas.demo.domain.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILivroRepository extends JpaRepository<Livro, Long> {
}
