package com.testeapi.vagas.demo.web.records.autor;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.web.records.user.UserCreateDTO;

public record AutorCreateDTO(
        String nome,
        String apresentacao
) {
    public static Autor toAutor(AutorCreateDTO autorCreateDTO) {
        return new Autor(autorCreateDTO.nome, autorCreateDTO.apresentacao);
    }
}
