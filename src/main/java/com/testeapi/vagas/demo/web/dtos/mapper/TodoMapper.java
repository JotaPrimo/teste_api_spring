package com.testeapi.vagas.demo.web.dtos.mapper;

import com.testeapi.vagas.demo.entities.Todo;
import com.testeapi.vagas.demo.web.dtos.TodoCreateDTO;
import com.testeapi.vagas.demo.web.dtos.TodoResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMapper {

    public static Todo toModel(TodoCreateDTO dto) {
        return new ModelMapper().map(dto, Todo.class);
    }

    public static TodoResponseDTO toDTO(Todo todo) {
        String prioridade = todo.getPrioridade().name();
        PropertyMap<Todo, TodoResponseDTO> props = new PropertyMap<Todo, TodoResponseDTO>() {
            @Override
            protected void configure() {
                map().setPrioridade(prioridade);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(props);
        return modelMapper.map(todo, TodoResponseDTO.class);
    }

    public static List<TodoResponseDTO> toListDTO(List<Todo> todos) {
        return todos.stream().map(todo -> toDTO(todo)).collect(Collectors.toList());
    }

}
