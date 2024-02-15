package com.testeapi.vagas.demo.web.dtos.mapper;

import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.web.dtos.UserCreateDTO;
import com.testeapi.vagas.demo.web.dtos.UserResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toModel(UserCreateDTO dto) {
        return new ModelMapper().map(dto, User.class);
    }

    public static UserResponseDTO toDTO(User user) {
        return new ModelMapper().map(user, UserResponseDTO.class);
    }

    public static List<UserResponseDTO> toListDTO(List<User> users) {
        return users.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }
}
