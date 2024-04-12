package com.testeapi.vagas.demo.web.records.livro;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.domain.enums.EstadoConservacao;

import java.util.Date;

public record LivroUpdateDTO(
        Long id,

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
        livro.setId(id);
        livro.setTitulo(titulo);
        livro.setDescricao(descricao);
        livro.setAnoLancamento(anoLancamento);
        livro.setAutor(autor);
        livro.setEstadoConservacao(estadoConservacao);
        livro.setUnidadesTotais(unidadesTotais);
        livro.setVersaoBolso(versaoBolso);
        livro.setDataAquisicao(dataAquisicao);

        return livro;
    }
}
