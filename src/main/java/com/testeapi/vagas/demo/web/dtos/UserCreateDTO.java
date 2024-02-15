package com.testeapi.vagas.demo.web.dtos;

import com.testeapi.vagas.demo.web.validations.interfaces.ICPFValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class UserCreateDTO {

    @NotBlank(message = "Name é um campo obrigatório")
    @Size(min = 5, max = 255, message = "Name deve ter entre 5 e 255 caracteres")
    private String name;

    @NotBlank(message = "CPF é um campo obrigatório")
    @Size(min = 14, max = 14, message = "Name deve ter entre 5 e 255 caracteres")
    @ICPFValidator
    private String cpf;

    @NotBlank(message = "E-mail é um campo obrigatório")
    @Size(min = 14, max = 14, message = "E-mail deve ter entre 5 e 255 caracteres")
    @Email(message = "E-mail inválido")
    private String email;
}
