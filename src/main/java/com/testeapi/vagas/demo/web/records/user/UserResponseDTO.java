package com.testeapi.vagas.demo.web.records.user;

import com.testeapi.vagas.demo.domain.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record UserResponseDTO(
        Long id,

        String name,

        String cpf,

        String email,

        Boolean ativo,

        LocalDateTime created_at) {

    private static List<UserResponseDTO> userList = new ArrayList<>();

    public static UserResponseDTO userToResponseDto(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getCpf(), user.getEmail(), user.isAtivo(), user.getCreated_at());
    }

    public static List<UserResponseDTO> toListUsers(List<User> listParam) {
        if (userList.isEmpty()) {
            listParam.stream().forEach(user -> userList.add(userToResponseDto(user)));
        }
        return userList;
    }

}
