package com.testeapi.vagas.demo.web.records.todo;

import com.testeapi.vagas.demo.domain.entities.Todo;
import com.testeapi.vagas.demo.domain.entities.User;
import com.testeapi.vagas.demo.web.records.user.UsuarioResponse;

import java.util.List;

public record TodoResponseDTO(
        Long id,
        String nome,
        String descricao,
        String prioridade,
        boolean realizado,
        Long user_id
) {
    private static List<TodoResponseDTO> dtoList;
    public static List<TodoResponseDTO> toListResponse(List<Todo> todos) {
        todos.stream().forEach(todo -> dtoList.add(todoToResponseDto(todo)));
        return dtoList;
    }

    public static TodoResponseDTO todoToResponseDto(Todo todo) {
        return new TodoResponseDTO(todo.getId(), todo.getNome(), todo.getDescricao(), todo.getPrioridade().name(), todo.isRealizado(), todo.getUser().getId());
    }
}
