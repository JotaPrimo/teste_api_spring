package com.testeapi.vagas.demo.web.controllers;

import com.testeapi.vagas.demo.config.ApiPaths;
import com.testeapi.vagas.demo.domain.entities.Todo;
import com.testeapi.vagas.demo.domain.entities.User;
import com.testeapi.vagas.demo.domain.services.interfaces.ITodoService;
import com.testeapi.vagas.demo.domain.services.interfaces.IUserService;
import com.testeapi.vagas.demo.web.records.todo.TodoCreateDTO;
import com.testeapi.vagas.demo.web.records.todo.TodoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Todos", description = "Operações crud de todo")
@RestController
@RequestMapping(ApiPaths.TODO_PATH)
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @Autowired
    private IUserService userService;

    @Operation(summary = "Listar registros de todo's", description = "Recurso para listar registros de todo's",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recursos listados com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoResponseDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> list() {
        List<Todo> todos = todoService.list();
        return ResponseEntity.status(HttpStatus.OK).body(TodoResponseDTO.toListResponse(todos));
    }

    @Operation(summary = "Ciar todo's", description = "Recurso para criar todo's",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TodoCreateDTO.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Recurso não processado por dados inválidos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TodoCreateDTO.class)))
            })
    @PostMapping
    public ResponseEntity<TodoResponseDTO> create(@Valid @RequestBody TodoCreateDTO todoCreate) {
        User user = userService.findById(todoCreate.user_id());
        TodoResponseDTO todoCreatead = todoService.create(todoCreate, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoCreatead);
    }

    @Operation(summary = "Localizar todo", description = "Recurso para localizar todo pelo id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso encontrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TodoResponseDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json")
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> findById(@PathVariable Long id) {
        Todo todo = todoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(TodoResponseDTO.todoToResponseDto(todo));
    }

    @Operation(summary = "Deletar todo", description = "Recurso para deletar todo", responses = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Recurso deletado com sucesso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Completar todo", description = "Recurso para completar todo's",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso completado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TodoCreateDTO.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Task já completada",
                            content = @Content(mediaType = "application/json"))
            })
    @PatchMapping("/{id}/completed")
    public ResponseEntity<TodoResponseDTO> complete(@PathVariable Long id) {
        Todo todoCompleted = todoService.complete(id);
        return ResponseEntity.status(HttpStatus.OK).body(TodoResponseDTO.todoToResponseDto(todoCompleted));
    }

}
