package com.testeapi.vagas.demo.web.records.autor;

import com.testeapi.vagas.demo.domain.entities.Autor;

import java.util.ArrayList;
import java.util.List;

public record AutorResponseDTO(
        Long id,
        String nome,
        String apresentacao
) {
    public static AutorResponseDTO autorToAutorResponse(Autor autor) {
        return new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getApresentacao());
    }
    public static List<AutorResponseDTO> toList(List<Autor> autores) {
        List<AutorResponseDTO> dtoList = new ArrayList<>();
        autores.stream().forEach(a -> dtoList.add(AutorResponseDTO.autorToAutorResponse(a)));
        return dtoList;
    }
}
