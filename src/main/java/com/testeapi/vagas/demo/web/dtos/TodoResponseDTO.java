package com.testeapi.vagas.demo.web.dtos;

public class TodoResponseDTO {
    private String nome;
    private String descricao;
    private String prioridade;

    public TodoResponseDTO() {
    }

    public TodoResponseDTO(String nome, String descricao, String prioridade) {

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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
