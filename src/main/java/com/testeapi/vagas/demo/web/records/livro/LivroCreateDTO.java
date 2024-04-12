package com.testeapi.vagas.demo.web.records.livro;

import com.testeapi.vagas.demo.domain.entities.Autor;
import com.testeapi.vagas.demo.domain.entities.Livro;
import com.testeapi.vagas.demo.domain.enums.EstadoConservacao;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record LivroCreateDTO(
        @NotBlank(message = "Informe o título do livro")
        @Size(min = 5, max = 255, message = "O título deve ter entre {min} e {max} caracteres.")
        String titulo,

        @NotBlank(message = "Informe a descrição do livro")
        @Size(min = 5, max = 500, message = "A descrição deve ter entre {min} e {max} caracteres.")
        String descricao,

        @NotNull(message = "Informe o ano de lançamento")
        @Positive(message = "Ano de lançamento não pode ser anterior a {min}")
        Integer anoLancamento,

        @NotNull(message = "Informe o id do autor")
        @Positive(message = "Id do autor não pode ser menor que 1")
        Long idAutor,

        @NotNull(message = "Informe o estado de conservação")
        EstadoConservacao estadoConservacao,

        @NotNull(message = "Informe a quantidade de exemplares")
        @Positive(message = "Quantidade de exemplares não pode ser menor que 1")
        Integer unidadesTotais,

        @NotNull(message = "Informe se a versão do livro é de bolso")
        Boolean versaoBolso,

        @NotNull(message = "Informe a data de aquisição")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @PastOrPresent
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
        livro.setUnidadesEmprestadas(0);

        return livro;
    }
}
