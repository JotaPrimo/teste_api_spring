package com.testeapi.vagas.demo.domain.services.interfaces;

import com.testeapi.vagas.demo.web.records.livro.LivroResponseDTO;

import java.util.List;

public interface ILivroService {
    List<LivroResponseDTO> getAll();

    LivroResponseDTO getById(Long id);


}
