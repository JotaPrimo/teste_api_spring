package com.testeapi.vagas.demo.web.dtos;

public class TodoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String prioridade;
    private boolean realizado;

    private Long user_id;

    public TodoResponseDTO() {
    }

    public TodoResponseDTO(Long id, String nome, String descricao, String prioridade, boolean realizado, Long user_id) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.realizado = realizado;
        this.user_id = user_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
