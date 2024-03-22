package com.testeapi.vagas.demo.web.records.user;

import com.testeapi.vagas.demo.domain.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record UsuarioResponse(
        Long id,

        String name,

        String cpf,

        String email,

        Boolean ativo,

        LocalDateTime created_at) {

    private static List<UsuarioResponse> userList = new ArrayList<>();

    public static UsuarioResponse userToResponseDto(User user) {
        return new UsuarioResponse(user.getId(), user.getName(), user.getCpf(), user.getEmail(), user.isAtivo(), user.getCreated_at());
    }

    public static List<UsuarioResponse> toListUsers(List<User> listParam) {
        listParam.stream().forEach(user -> userList.add(userToResponseDto(user)));
        return userList;
    }

}
