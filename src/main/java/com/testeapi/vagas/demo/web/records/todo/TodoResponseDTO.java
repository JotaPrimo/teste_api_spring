package com.testeapi.vagas.demo.web.records.todo;

import com.testeapi.vagas.demo.domain.entities.Todo;

import java.util.ArrayList;
import java.util.List;

public record TodoResponseDTO(
        Long id,
        String nome,
        String descricao,
        String prioridade,
        boolean realizado,
        Long user_id
) {
    public static List<TodoResponseDTO> toListResponse(List<Todo> todos) {

         List<TodoResponseDTO> dtoList = new ArrayList<>();

        for (Todo todo : todos) {
            dtoList.add(todoToResponseDto(todo));
        }

        return dtoList;
    }

    public static TodoResponseDTO todoToResponseDto(Todo todo) {
        return new TodoResponseDTO(todo.getId(), todo.getNome(), todo.getDescricao(), todo.getPrioridade().name(), todo.isRealizado(), todo.getUser().getId());
    }
}
