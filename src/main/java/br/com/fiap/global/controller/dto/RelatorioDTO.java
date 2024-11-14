package br.com.fiap.global.controller.dto;

import br.com.fiap.global.model.Relatorio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RelatorioDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotNull(message = "A data de geração é obrigatória.")
    private LocalDateTime dataGeracao;

    @NotBlank(message = "O conteúdo é obrigatório.")
    private String conteudo;

    // Construtor que converte um objeto Relatorio em RelatorioDTO
    public RelatorioDTO(Relatorio relatorio) {
        this.id = relatorio.getId();
        this.titulo = relatorio.getTitulo();
        this.dataGeracao = relatorio.getDataGeracao();
        this.conteudo = relatorio.getConteudo();
    }

    // Construtor vazio necessário para uso em frameworks como Spring
    public RelatorioDTO() {
    }

    // Getters e Setters
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

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
