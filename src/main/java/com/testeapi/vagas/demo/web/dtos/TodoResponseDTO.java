package com.testeapi.vagas.demo.web.dtos;

public class TodoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String prioridade;
    private boolean realizado;

    public TodoResponseDTO() {
    }

    public TodoResponseDTO(String nome, String descricao, String prioridade, boolean realizado) {

        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.realizado = realizado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}
