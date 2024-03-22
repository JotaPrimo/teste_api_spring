package com.testeapi.vagas.demo.entities;

import com.testeapi.vagas.demo.domain.enums.Prioridade;
import com.testeapi.vagas.demo.config.path.ApiPaths;
import com.testeapi.vagas.demo.web.dtos.TodoCreateDTO;
import com.testeapi.vagas.demo.web.dtos.TodoResponseDTO;
import com.testeapi.vagas.demo.web.exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // tomcant do teste ser? executado em porta randomica
@Sql(scripts = "/sql/todos/todo-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) // executa sql antes do metodo
@Sql(scripts = "/sql/todos/todo-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) // executa sql ap?s o methodo do teste
public class TodoTestIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createTodoRetornarTodoCriadoComStatus201() {
        TodoResponseDTO responseBody = testClient
                .post()
                .uri(ApiPaths.TODO_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TodoCreateDTO("teste create", "12345678910", Prioridade.ALTA, 1L))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(TodoResponseDTO.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getNome()).isEqualTo("teste create");
        Assertions.assertThat(responseBody.getPrioridade()).isEqualTo(Prioridade.ALTA.name());
    }

    @Test
    public void createTodoComNomeInvalidoRetornarStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri(ApiPaths.TODO_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TodoCreateDTO("", "12345678910", Prioridade.ALTA, 1L))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void createTodoComSenhaInvalidaRetornarStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri(ApiPaths.TODO_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TodoCreateDTO("teste nome valido", "123", Prioridade.ALTA, 1L))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void createTodoComSenhaVaziaInvalidaRetornarStatus422() {
        ErrorMessage responseBody = testClient
                .post()
                .uri(ApiPaths.TODO_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TodoCreateDTO("teste nome valido", "", Prioridade.ALTA, 1L))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    public void buscarTodoComIdInvalidoRetornarStatus404() {
        ErrorMessage responseBody = testClient
                .get()
                .uri(ApiPaths.TODO_PATH + '0')
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NOT_FOUND)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void buscarTodoComIdcalidoRetornarStatus200() {
        TodoResponseDTO responseBody = testClient
                .get()
                .uri("api/v1/todos/1")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .expectBody(TodoResponseDTO.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getNome()).isEqualTo("Lorem ipsum");
        Assertions.assertThat(responseBody.getPrioridade()).isEqualTo(Prioridade.BAIXA.name());
    }

}