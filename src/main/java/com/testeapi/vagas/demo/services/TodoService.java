package com.testeapi.vagas.demo.services;

import com.testeapi.vagas.demo.entities.Todo;
import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.exceptions.TodoAlreadyCompletedException;
import com.testeapi.vagas.demo.repositories.TodoRepository;
import com.testeapi.vagas.demo.web.dtos.TodoCreateDTO;
import com.testeapi.vagas.demo.web.dtos.mapper.TodoMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Todo create(TodoCreateDTO todoCreateDTO, User user) {
        try {
            return repository.save(TodoMapper.toModel(todoCreateDTO, user));
        } catch (RuntimeException exception) {
            throw new RuntimeException("Erro ao tentar salvar todo");
        }
    }

    @Transactional(readOnly = true)
    public List<Todo> list() {
        Sort sort = Sort.by(Sort.Direction.ASC, "prioridade");
        return repository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public Todo findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Todo de id #%s", id))
        );
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Todo complete(Long id) {
        Todo todo = findById(id);

        if(todo.isRealizado()) {
            throw new TodoAlreadyCompletedException(String.format("Todo de com id #%s já está completada", id));
        }

        todo.setRealizado(Todo.REALIZADA);
        return repository.save(todo);
    }

}
