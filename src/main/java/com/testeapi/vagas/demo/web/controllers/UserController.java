package com.testeapi.vagas.demo.web.controllers;

import com.testeapi.vagas.demo.domain.entities.Todo;
import com.testeapi.vagas.demo.domain.entities.User;
import com.testeapi.vagas.demo.config.ApiPaths;
import com.testeapi.vagas.demo.domain.services.implementation.UserService;
import com.testeapi.vagas.demo.domain.services.interfaces.ITodoService;
import com.testeapi.vagas.demo.domain.services.interfaces.IUserService;
import com.testeapi.vagas.demo.web.records.todo.TodoResponseDTO;
import com.testeapi.vagas.demo.web.records.user.UserCreateDTO;
import com.testeapi.vagas.demo.web.records.user.UserResponseDTO;
import com.testeapi.vagas.demo.web.records.user.UserUpdateDTO;
import com.testeapi.vagas.demo.web.records.user.UserWithTodoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.USER_PATH)
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private ITodoService todoService;

    @Operation(summary = "Listar registros de users", description = "Recurso para listar registros de users",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Listando recurso de users com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> list() {
        List<User> users = service.getAllSortedByNameAsc();

        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.toListUsers(users));
    }

    @Operation(summary = "Localizar user", description = "Recurso para localizar user pelo id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso encontrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso n�o encontrado",
                            content = @Content(mediaType = "application/json")
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> show(@PathVariable Long id) {
        User user = this.service.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.userToResponseDto(user));
    }

    @Operation(summary = "Ciar user", description = "Recurso para criar users",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserCreateDTO.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Recurso n�o processado por dados inv�lidos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserCreateDTO.class)))
            })
    @PostMapping
    public ResponseEntity<UserResponseDTO> store(@Valid @RequestBody UserCreateDTO userCreateRequest) {
        UserResponseDTO userCreated = service.store(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @Operation(summary = "Inativar user", description = "Recurso para inativar user", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User inativado com sucesso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso n�o encontrado",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> inativar(@PathVariable Long id) {
        User user = service.inativar(id);

        return ResponseEntity.ok(UserResponseDTO.userToResponseDto(user));
    }

    @Operation(summary = "Atualizar registro de user", description = "Recurso para atualizar informa��es de user", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Registro n�o encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Recurso n�o processado por dados inv�lidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = this.service.update(id, userUpdateDTO);

        return ResponseEntity.ok(UserResponseDTO.userToResponseDto(user));
    }

    @Operation(summary = "Deletar registro de user", description = "Recurso para deletar registro de user", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Registro deletado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Registro n�o encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
            ),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/todos")
    public ResponseEntity<UserWithTodoResponseDTO> getUserWithTodos(@PathVariable Long id) {
        User user = service.findById(id);
        List<Todo> todos = todoService.findByUserId(id);
        UserWithTodoResponseDTO userWithTodoResponseDTO = new UserWithTodoResponseDTO(UserResponseDTO.userToResponseDto(user), TodoResponseDTO.toListResponse(todos));

        return ResponseEntity.ok(userWithTodoResponseDTO);
    }


}
