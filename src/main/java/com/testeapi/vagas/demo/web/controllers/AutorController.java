package com.testeapi.vagas.demo.web.controllers;

import com.testeapi.vagas.demo.config.ApiPaths;
import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.services.interfaces.IAutorService;
import com.testeapi.vagas.demo.web.records.autor.AutorCreateDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorResponseDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.AUTOR_PATH)
public class AutorController {

    @Autowired
    private IAutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> list() {
        List<Autor> autores = autorService.list();
        return ResponseEntity.status(HttpStatus.OK).body(AutorResponseDTO.toList(autores));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> show(@PathVariable Long id) {
        AutorResponseDTO autorResponseDTO = autorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(autorResponseDTO);
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> store(@Valid @RequestBody AutorCreateDTO autorCreateDTO) {
        AutorResponseDTO autorResponseDTO = autorService.create(autorCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AutorUpdateDTO autorUpdateDTO) {
        AutorResponseDTO autorResponseDTO = autorService.update(autorUpdateDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(autorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
