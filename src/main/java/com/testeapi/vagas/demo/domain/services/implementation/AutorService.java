package com.testeapi.vagas.demo.domain.services.implementation;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.repositories.jpa.IAutorRepository;
import com.testeapi.vagas.demo.domain.services.interfaces.IAutorService;
import com.testeapi.vagas.demo.web.records.autor.AutorCreateDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService implements IAutorService {

    @Autowired
    private IAutorRepository autorRepository;

    @Override
    public List<Autor> list() {
        return autorRepository.findAll();
    }

    @Override
    public AutorResponseDTO create(AutorCreateDTO autorCreateDTO) {
        Autor autor = AutorCreateDTO.toAutor(autorCreateDTO);
        autorRepository.save(autor);
        return   AutorResponseDTO.autorToAutorResponse(autor);
    }


}
