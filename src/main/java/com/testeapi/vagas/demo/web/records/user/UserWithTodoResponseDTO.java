package com.testeapi.vagas.demo.web.records.user;

import com.testeapi.vagas.demo.web.records.todo.TodoResponseDTO;

import java.util.List;

public record UserWithTodoResponseDTO(
        UserResponseDTO userResponseDTO,
        List<TodoResponseDTO> listTodos
) { }
