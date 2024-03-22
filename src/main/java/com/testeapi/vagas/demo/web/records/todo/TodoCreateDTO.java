package com.testeapi.vagas.demo.web.records.todo;

import com.testeapi.vagas.demo.domain.entities.Todo;
import com.testeapi.vagas.demo.domain.entities.User;
import com.testeapi.vagas.demo.domain.enums.Prioridade;

public record TodoCreateDTO(
        String nome,
        String descicao,
        Prioridade priordade,
        Long user_id,
        User user) {
    public Todo toEntity(User user) {
        Todo todo = new Todo();
        todo.setNome(nome);
        todo.setDescricao(descicao);
        todo.setPrioridade(priordade);
        todo.setUser(user);
        return todo;
    }
}
