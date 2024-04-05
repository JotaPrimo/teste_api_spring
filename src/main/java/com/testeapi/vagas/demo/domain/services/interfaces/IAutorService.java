package com.testeapi.vagas.demo.domain.services.interfaces;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.web.records.autor.AutorCreateDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorResponseDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorUpdateDTO;

import java.util.List;

public interface IAutorService {
    List<Autor> list();

    AutorResponseDTO create(AutorCreateDTO autorCreateDTO);

    void deleteById(Long id);

    AutorResponseDTO update(AutorUpdateDTO autorUpdateDTO, Long id);
}
