package com.testeapi.vagas.demo.domain.services.implementation;

import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.domain.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.domain.repositories.jpa.IAutorRepository;
import com.testeapi.vagas.demo.domain.repositories.jpa.ILivroRepository;
import com.testeapi.vagas.demo.domain.services.interfaces.ILivroService;
import com.testeapi.vagas.demo.web.records.livro.LivroResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService implements ILivroService {

    @Autowired
    private ILivroRepository livroRepository;


    @Override
    public List<LivroResponseDTO> getAll() {
        List<Livro> livroList = livroRepository.findAll();
        return LivroResponseDTO.toList(livroList);
    }

    @Override
    public LivroResponseDTO getById(Long id) {
      Optional<Livro> optionalLivro = livroRepository.findById(id);

      if (optionalLivro.isPresent()) {
          Livro livro = optionalLivro.get();
          return LivroResponseDTO.entityToDTO(livro);
      } else {
          throw new EntityNotFoundException("Livro de id #" +id+ "Não encontrado");
      }
    }

}
