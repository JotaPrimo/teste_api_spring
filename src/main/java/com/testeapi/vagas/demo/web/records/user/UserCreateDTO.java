package com.testeapi.vagas.demo.web.records.user;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.entities.User;

public record UserCreateDTO(
        String name,
        String cpf,
        String email
) {
    public User toEntity() {
        User user = new User();
        user.setName(name);
        user.setCpf(cpf);
        user.setEmail(email);

        return user;
    }

}
