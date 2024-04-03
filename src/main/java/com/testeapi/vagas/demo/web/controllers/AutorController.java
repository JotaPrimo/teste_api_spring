package com.testeapi.vagas.demo.web.controllers;

import com.testeapi.vagas.demo.config.ApiPaths;
import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.services.interfaces.IAutorService;
import com.testeapi.vagas.demo.web.records.autor.AutorCreateDTO;
import com.testeapi.vagas.demo.web.records.autor.AutorResponseDTO;
import com.testeapi.vagas.demo.web.records.user.UserCreateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.AUTOR_PATH)
public class AutorController {

    private IAutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> list() {
        List<Autor> autores = autorService.list();
        return ResponseEntity.status(HttpStatus.OK).body(AutorResponseDTO.toList(autores));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> store(@Valid @RequestBody AutorCreateDTO autorCreateDTO) {
        AutorResponseDTO autorResponseDTO = autorService.create(autorCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorResponseDTO);
    }
}
