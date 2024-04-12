package com.testeapi.vagas.demo.domain.services.interfaces;

import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.web.records.livro.LivroCreateDTO;
import com.testeapi.vagas.demo.web.records.livro.LivroResponseDTO;
import com.testeapi.vagas.demo.web.records.livro.LivroUpdateDTO;

import java.util.List;

public interface ILivroService {
    List<LivroResponseDTO> getAll();

    LivroResponseDTO getById(Long id);

    Livro findById(Long id);

    LivroResponseDTO store(LivroCreateDTO livroCreateDTO);

    LivroResponseDTO update(LivroUpdateDTO livroUpdateDTO);

    void delete(Long id);
}
