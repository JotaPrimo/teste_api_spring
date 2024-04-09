package com.testeapi.vagas.demo.domain.services.implementation;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.domain.repositories.jpa.IAutorRepository;
import com.testeapi.vagas.demo.domain.services.interfaces.IAutorService;
import com.testeapi.vagas.demo.web.exception.ValidationException;
import com.testeapi.vagas.demo.web.records.autor.AutorCreateDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorResponseDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorUpdateDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AutorService implements IAutorService {

    @Autowired
    private IAutorRepository autorRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Autor> list() {
        return autorRepository.findAll();
    }

    @Override
    public AutorResponseDTO getById(Long id) {
        Optional<Autor> optionalAutor = autorRepository.findById(id);

        if (optionalAutor.isPresent()) {
            return AutorResponseDTO.autorToAutorResponse(optionalAutor.get());
        }
        throw new EntityNotFoundException("Autor de id #" +id+ " não encontrado");
    }

    @Override
    @Transactional
    public AutorResponseDTO create(AutorCreateDTO autorCreateDTO) {
        try {
            validarAutorCreateDTO(autorCreateDTO);

            Autor autor = AutorCreateDTO.toAutor(autorCreateDTO);
            autorRepository.save(autor);

            return AutorResponseDTO.autorToAutorResponse(autor);
        }catch (Exception exception) {
            throw new RuntimeException("Ocorreu um erro ao criar autor");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Autor> optionalAutor = autorRepository.findById(id);
        if (optionalAutor.isPresent()) {
            autorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Autor de id #" +id+ " não encontrado");
        }
    }

    @Override
    public AutorResponseDTO update(AutorUpdateDTO autorUpdateDTO, Long id) {
        Optional<Autor> optionalAutor = autorRepository.findById(id);

        if (optionalAutor.isPresent()) {
            Autor autor = AutorUpdateDTO.toAutor(autorUpdateDTO, id);
            autorRepository.save(autor);
            return AutorResponseDTO.autorToAutorResponse(autor);
        } else {
            throw new EntityNotFoundException("Autor de id #" +id+ " não encontrado");
        }
    }

    private void validarAutorCreateDTO(AutorCreateDTO autorCreateDTO) {
        Set<ConstraintViolation<AutorCreateDTO>> violations = validator.validate(autorCreateDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        if (autorCreateDTO.nome() == null || autorCreateDTO.nome().trim().isEmpty()) {
            throw new ValidationException("Nome não pode ser vazio nem nullo");
        }
    }

}
