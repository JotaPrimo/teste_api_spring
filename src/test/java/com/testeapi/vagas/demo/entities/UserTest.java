package com.testeapi.vagas.demo.entities;

import com.testeapi.vagas.demo.config.ApiPaths;
import com.testeapi.vagas.demo.web.dtos.UserCreateDTO;
import com.testeapi.vagas.demo.web.dtos.UserResponseDTO;
import com.testeapi.vagas.demo.web.exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/users/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/trucante-table.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void createUserReturnsCreatedUserWitHStatus201() {
        // Arrange
        UserCreateDTO userCreateDTO = new UserCreateDTO("Jailson Santos", "169.916.460-67", "testedaswe@gmail.com");

        // Act
        UserResponseDTO responseBody = webTestClient
                .post()
                .uri(ApiPaths.USER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UserResponseDTO.class)
                .returnResult()
                .getResponseBody();

        // assertion
        Assertions.assertThat(responseBody)
                .as("Response body should not be null")
                .overridingErrorMessage("Response body n�o deveria ser nullo")
                .isNotNull();

        Assertions.assertThat(responseBody.getName())
                .as("O name deve ser igual")
                .isNotNull()
                .overridingErrorMessage("Name n�o deveria ser null")
                .isEqualTo(userCreateDTO.getName());

        Assertions.assertThat(responseBody.getCpf())
                .as("CPF deve ser igual")
                .overridingErrorMessage("CPF n�o deveria ser null")
                .isEqualTo(userCreateDTO.getCpf());

        Assertions.assertThat(responseBody.getEmail())
                .as("E-mail deve ser igual a")
                .overridingErrorMessage("E-mail n�o deveria ser null")
                .isEqualTo("jtoeasdasdal�k�ldasd");
    }

    @Test
    public void createUserReturnsNotCreatedUserWithStatus422() {
        // arrange
        UserCreateDTO userCreateDTO = new UserCreateDTO("", "169.916.460-67", "testedaswe@gmail.com");

        // action
        ErrorMessage responseBody = webTestClient
                .post()
                .uri(ApiPaths.USER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDTO)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        // Assertion
        Assertions.assertThat(responseBody)
                .as("Corpo da resposta n�o deveria ser nullo")
                .isNotNull();

        Assertions.assertThat(responseBody.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

}