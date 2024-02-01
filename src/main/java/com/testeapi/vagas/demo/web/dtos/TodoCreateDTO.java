package com.testeapi.vagas.demo.web.dtos;

import com.testeapi.vagas.demo.enums.Prioridade;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoCreateDTO {
    @Size(min = 5, max = 255, message = "Nome deve ter entre 5 e 255 caracteres")
    @NotBlank(message = "Nome é um campo obrigatório")
    @Column(nullable = false, length = 255)
    private String nome;

    @Size(min = 5, max = 2500, message = "Nome deve ter entre 5 e 255 caracteres")
    @NotBlank(message = "Nome é um campo obrigatório")
    @Column(nullable = false, length = 25000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    public TodoCreateDTO() {
    }

    public TodoCreateDTO(String nome, String descricao, Prioridade prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }


    @Override
    public String toString() {
        return "TodoCreateDTO{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", prioridade=" + prioridade +
                '}';
    }
}
