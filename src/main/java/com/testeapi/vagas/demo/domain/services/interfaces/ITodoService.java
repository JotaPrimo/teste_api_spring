package com.testeapi.vagas.demo.domain.services.interfaces;

import com.testeapi.vagas.demo.domain.entities.Todo;
import com.testeapi.vagas.demo.domain.entities.User;
import com.testeapi.vagas.demo.web.dtos.TodoCreateDTO;

import java.util.List;

public interface ITodoService {
    Todo create(TodoCreateDTO todoCreateDTO, User user);

    List<Todo> list();

    Todo findById(Long id);

    void delete(Long id);

    Todo complete(Long id);

}
