package com.testeapi.vagas.demo.entities;

import com.testeapi.vagas.demo.enums.Prioridade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Table(name = "tb_todos")
@Entity
public class Todo {
    private static final boolean NAO_REALIZADA = false;
    private static final boolean REALIZADA = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 255, message = "Nome deve ter entre 5 e 255 caracteres")
    @NotBlank(message = "Nome é um campo obrigatório")
    @Column(nullable = false, length = 255)
    private String nome;

    @Size(min = 5, max = 2500, message = "Nome deve ter entre 5 e 255 caracteres")
    @NotBlank(message = "Nome é um campo obrigatório")
    @Column(nullable = false, length = 25000)
    private String descricao;

    @Column(name = "realizado", columnDefinition = "boolean default false")
    private boolean realizado;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    public Todo() {
    }

    public Todo(Long id, String nome, String descricao, boolean realizado, Prioridade prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.realizado = realizado;
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
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

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", realizado=" + realizado +
                ", prioridade=" + prioridade +
                '}';
    }
}
