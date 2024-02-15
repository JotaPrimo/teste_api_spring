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

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable Long id) {
        User user = this.service.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<User> store(@Valid @RequestBody User user) {
        User userCreated = service.store(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> inativar(@PathVariable Long id) {
        User user = service.inativar(id);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User userUpdate) {
       User user =  this.service.update(id, userUpdate);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
