package com.testeapi.vagas.demo.entities;

import com.testeapi.vagas.demo.path.ApiPaths;
import com.testeapi.vagas.demo.web.dtos.UserCreateDTO;
import com.testeapi.vagas.demo.web.dtos.UserResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

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
                .overridingErrorMessage("Response body não deveria ser nullo")
                .isNotNull();

        Assertions.assertThat(responseBody.getName())
                .as("O name deve ser igual")
                .isNotNull()
                .overridingErrorMessage("Name não deveria ser null")
                .isEqualTo(userCreateDTO.getName());

        Assertions.assertThat(responseBody.getCpf())
                .as("CPF deve ser igual")
                .overridingErrorMessage("CPF não deveria ser null")
                .isEqualTo(userCreateDTO.getCpf());

        Assertions.assertThat(responseBody.getEmail())
                .as("E-mail deve ser igual a")
                .overridingErrorMessage("E-mail não deveria ser null")
                .isEqualTo("jtoeasdasdalçkçldasd");
    }

    @Test
    public void createUserReturnsNotCreatedUserWithStatus422() {
        // arrange
        UserCreateDTO userCreateDTO = new UserCreateDTO("", "169.916.460-67", "testedaswe@gmail.com");

        // action
        UserResponseDTO responseBody = webTestClient
                .post()
                .uri(ApiPaths.USER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDTO)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
                .expectBody(UserResponseDTO.class)
                .returnResult()
                .getResponseBody();

        // Assertion
        Assertions.assertThat(responseBody)
                .as("Corpo da resposta não deveria ser nullo")
                .isNotNull();

        Assertions
                .assertThat(responseBody.getName())
                .as("Id não deveria ser null")
                .isNotNull()
                .isEqualTo(userCreateDTO.getName());
    }

}