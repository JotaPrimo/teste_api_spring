package com.testeapi.vagas.demo.web.records.autor;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.web.records.user.UserCreateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorCreateDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 5, max = 50, message = "Deve ter entre {min} e {max} caracteres")
        String nome,
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 5, message = "Deve ter ao menos {min} caracteres")
        String apresentacao
) {
    public static Autor toAutor(AutorCreateDTO autorCreateDTO) {
        return new Autor(autorCreateDTO.nome, autorCreateDTO.apresentacao);
    }
}
