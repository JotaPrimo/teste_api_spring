package com.testeapi.vagas.demo.domain.entities;

import com.testeapi.vagas.demo.domain.enums.EstadoConservacao;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Integer anoLancamento;

    @Enumerated(EnumType.STRING)
    private EstadoConservacao estadoConservacao;

    private Integer unidadesTotais;

    private Integer unidadesDisponiveis;

    private Integer unidadesEmprestadas;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean versaoBolso;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private Date dataAquisicao;

    private LocalDateTime createdAt;

    public Livro() {
    }

    public Livro(String titulo, String descricao, Integer anoLancamento, EstadoConservacao estadoConservacao,
                 Integer unidadesTotais, Integer unidadesDisponiveis, Integer unidadesEmprestadas, Boolean versaoBolso,
                 Autor autor, Date dataAquisicao, LocalDateTime createdAt) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.anoLancamento = anoLancamento;
        this.estadoConservacao = estadoConservacao;
        this.unidadesTotais = unidadesTotais;
        this.unidadesDisponiveis = unidadesDisponiveis;
        this.unidadesEmprestadas = unidadesEmprestadas;
        this.versaoBolso = versaoBolso;
        this.autor = autor;
        this.dataAquisicao = dataAquisicao;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public Integer getUnidadesTotais() {
        return unidadesTotais;
    }

    public void setUnidadesTotais(Integer unidadesTotais) {
        this.unidadesTotais = unidadesTotais;
    }

    public Integer getUnidadesDisponiveis() {
        return unidadesDisponiveis;
    }

    public void setUnidadesDisponiveis(Integer unidadesDisponiveis) {
        this.unidadesDisponiveis = unidadesDisponiveis;
    }

    public Integer getUnidadesEmprestadas() {
        return unidadesEmprestadas;
    }

    public void setUnidadesEmprestadas(Integer unidadesEmprestadas) {
        this.unidadesEmprestadas = unidadesEmprestadas;
    }

    public Boolean getVersaoBolso() {
        return versaoBolso;
    }

    public void setVersaoBolso(Boolean versaoBolso) {
        this.versaoBolso = versaoBolso;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
