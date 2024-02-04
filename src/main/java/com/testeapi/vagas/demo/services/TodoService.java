package com.testeapi.vagas.demo.services;

import com.testeapi.vagas.demo.entities.Todo;
import com.testeapi.vagas.demo.exceptions.EntityNotFoundException;
import com.testeapi.vagas.demo.repositories.TodoRepository;
import com.testeapi.vagas.demo.web.dtos.TodoCreateDTO;
import com.testeapi.vagas.demo.web.dtos.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Todo create(TodoCreateDTO todoCreateDTO) {
        try {
            return repository.save(TodoMapper.toModel(todoCreateDTO));
        }catch (RuntimeException exception) {
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
        Optional<Todo> optionalTodo = Optional.ofNullable(findById(id));

        if (optionalTodo != null && optionalTodo.isPresent()) {
            repository.deleteById(id);
            return;
        }

        throw new EntityNotFoundException(String.format("Todo #%s não encontrado", id));
    }
}
