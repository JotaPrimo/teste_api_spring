package com.testeapi.vagas.demo.web.controllers;

import com.testeapi.vagas.demo.config.ApiPaths;
import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.domain.services.interfaces.ILivroService;
import com.testeapi.vagas.demo.web.records.livro.LivroCreateDTO;
import com.testeapi.vagas.demo.web.records.livro.LivroResponseDTO;
import com.testeapi.vagas.demo.web.records.livro.LivroUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.LIVRO_PATH)
public class LivroController {

    @Autowired
    private ILivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> index() {
        List<LivroResponseDTO> listLivros = livroService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(listLivros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> show(@PathVariable Long id) {
        LivroResponseDTO livroResponseDTO = livroService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(livroResponseDTO);
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> store(@Valid @RequestBody LivroCreateDTO livroCreateDTO) {
        LivroResponseDTO livroResponseDTO = livroService.store(livroCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> update(@Valid @RequestBody LivroUpdateDTO livroUpdateDTO) {
        LivroResponseDTO livroResponseDTO = livroService.update(livroUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(livroResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
