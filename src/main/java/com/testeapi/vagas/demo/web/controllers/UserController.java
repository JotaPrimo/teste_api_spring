package com.testeapi.vagas.demo.web.controllers;

import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.path.ApiPaths;
import com.testeapi.vagas.demo.services.UserService;
import com.testeapi.vagas.demo.web.dtos.UserCreateDTO;
import com.testeapi.vagas.demo.web.dtos.UserResponseDTO;
import com.testeapi.vagas.demo.web.dtos.UserUpdateDTO;
import com.testeapi.vagas.demo.web.dtos.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.USER_PATH)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> list() {
        List<User> users = service.getAllSortedByNameAsc();

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListDTO(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> show(@PathVariable Long id) {
        User user = this.service.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> store(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User userCreated = service.store(userCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDTO(userCreated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> inativar(@PathVariable Long id) {
        User user = service.inativar(id);

        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
       User user =  this.service.update(id, userUpdateDTO);

        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
