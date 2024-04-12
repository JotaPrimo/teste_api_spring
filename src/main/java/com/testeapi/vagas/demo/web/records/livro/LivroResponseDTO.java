package com.testeapi.vagas.demo.web.records.livro;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.domain.enums.EstadoConservacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record LivroResponseDTO(
       String titulo,
       String descricao,
       Integer anoLancamento,

       EstadoConservacao estadoConservacao,

       Integer unidadesTotais,

       Integer unidadesDisponiveis,

       Integer unidadesEmprestadas,

       Boolean versaoBolso,

       Autor autor,

       Date dataAquisicao,

       LocalDateTime createdAt
) {

    public static LivroResponseDTO entityToDTO(Livro livro) {
        return new LivroResponseDTO(livro.getTitulo(), livro.getDescricao(), livro.getAnoLancamento(),
                livro.getEstadoConservacao(), livro.getUnidadesTotais(), livro.getUnidadesDisponiveis(),
                livro.getUnidadesEmprestadas(), livro.getVersaoBolso(), livro.getAutor(), livro.getDataAquisicao(),
                livro.getCreatedAt());
    }

    public static List<LivroResponseDTO> toList(List<Livro> livroList) {
        List<LivroResponseDTO> livroResponseDTOS = new ArrayList<>();
        livroList.stream().forEach(livro -> livroResponseDTOS.add(entityToDTO(livro)));
        return livroResponseDTOS;
    }

}
