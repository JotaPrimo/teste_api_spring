package com.testeapi.vagas.demo.web.records.autor;

import com.testeapi.vagas.demo.domain.entities.Autor;

public record AutorUpdateDTO(
        String nome,
        String apresentacao
) {
    public static Autor toAutor(AutorUpdateDTO autorUpdateDTO, Long id) {
        Autor autor = new Autor(autorUpdateDTO.nome(), autorUpdateDTO.apresentacao());
        autor.setId(id);
        return autor;
    }
}
