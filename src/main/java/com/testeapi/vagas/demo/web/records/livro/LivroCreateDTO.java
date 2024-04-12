package com.testeapi.vagas.demo.web.records.livro;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.domain.enums.EstadoConservacao;

import java.time.LocalDateTime;
import java.util.Date;

public record LivroCreateDTO(
        String titulo,

        String descricao,

        Integer anoLancamento,

        Long idAutor,

        EstadoConservacao estadoConservacao,

        Integer unidadesTotais,

        Boolean versaoBolso,

        Date dataAquisicao
) {
    public Livro toEntity(Autor autor) {
        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setTitulo(titulo);
        livro.setDescricao(descricao);
        livro.setAnoLancamento(anoLancamento);
        livro.setEstadoConservacao(estadoConservacao);
        livro.setUnidadesTotais(unidadesTotais);
        livro.setVersaoBolso(versaoBolso);
        livro.setDataAquisicao(dataAquisicao);

        return livro;
    }
}
