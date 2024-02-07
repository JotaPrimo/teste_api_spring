package com.testeapi.vagas.demo.web.controllers;

import com.testeapi.vagas.demo.entities.User;
import com.testeapi.vagas.demo.path.ApiPaths;
import com.testeapi.vagas.demo.services.UserService;
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
    public ResponseEntity<List<User>> list(Model model) {
        List<User> users = service.getAllSortedByNameAsc();
        model.addAttribute("users", users);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    public ResponseEntity<User> store(@Valid @RequestBody User user) {
        User userCreated = service.store(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
