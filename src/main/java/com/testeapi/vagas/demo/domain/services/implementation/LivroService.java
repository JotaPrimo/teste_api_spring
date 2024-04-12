package com.testeapi.vagas.demo.domain.services.implementation;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.domain.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.domain.repositories.jpa.IAutorRepository;
import com.testeapi.vagas.demo.domain.repositories.jpa.ILivroRepository;
import com.testeapi.vagas.demo.domain.services.interfaces.IAutorService;
import com.testeapi.vagas.demo.domain.services.interfaces.ILivroService;
import com.testeapi.vagas.demo.web.records.livro.LivroCreateDTO;
import com.testeapi.vagas.demo.web.records.livro.LivroResponseDTO;
import com.testeapi.vagas.demo.web.records.livro.LivroUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService implements ILivroService {

    @Autowired
    private ILivroRepository livroRepository;

    @Autowired
    private IAutorRepository autorRepository;


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
            throw new EntityNotFoundException("Livro de id #" + id + "Não encontrado");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Livro findById(Long id) {
        Optional<Livro> optionalLivro = livroRepository.findById(id);

        if (optionalLivro.isPresent()) {
            return optionalLivro.get();
        } else {
            throw new EntityNotFoundException("Livro de id #" + id + "Não encontrado");
        }
    }

    @Override
    @Transactional()
    public LivroResponseDTO store(LivroCreateDTO livroCreateDTO) {
        validar();

        Optional<Autor> optionalAutor = autorRepository.findById(livroCreateDTO.idAutor());

        if (optionalAutor.isPresent()) {
            Livro livro = livroCreateDTO.toEntity(optionalAutor.get());
            livroRepository.save(livro);
            return LivroResponseDTO.entityToDTO(livro);
        }

        throw new EntityNotFoundException("Autor de id #" +livroCreateDTO.idAutor()+ "não foi encontrado");
    }

    @Override
    @Transactional
    public LivroResponseDTO update(LivroUpdateDTO livroUpdateDTO) {
        boolean livroExiste = livroRepository.findById(livroUpdateDTO.id()).isPresent();
        boolean autorExiste = autorRepository.existsById(livroUpdateDTO.idAutor());

        if (!livroExiste) {
            throw new EntityNotFoundException("O livro de id #"+ livroUpdateDTO.id() + "não foi encontrado");
        }

        if (!autorExiste) {
            throw new EntityNotFoundException("O autor de id #"+ livroUpdateDTO.idAutor() + "não foi encontrado");
        }

        Autor autor = autorRepository.findById(livroUpdateDTO.idAutor()).get();
        Livro livro = livroUpdateDTO.toEntity(autor);
        livroRepository.save(livro);
        return LivroResponseDTO.entityToDTO(livro);

    }

    @Override
    @Transactional
    public void delete(Long id) {

        try {
            Livro livro = this.findById(id);
            livroRepository.delete(livro);
        } catch (Exception exception) {
            throw new EntityNotFoundException("O autor de id #"+ id + "não foi encontrado");
        }
    }

    public void validar() {
        return;
    }


}
