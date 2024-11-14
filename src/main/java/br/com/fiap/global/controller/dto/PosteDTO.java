package br.com.fiap.global.controller.dto;

import br.com.fiap.global.model.Poste;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PosteDTO {

    private Long id;

    @NotBlank(message = "A localização é obrigatória.")
    private String localizacao;

    @Min(value = 0, message = "A potência deve ser positiva.")
    private double potencia;

    @NotBlank(message = "O status é obrigatório.")
    private String status;

    private double consumoAtual;

    private double ultimoConsumo;

    // Construtor que converte um objeto Poste em um PosteDTO
    public PosteDTO(Poste poste) {
        this.id = poste.getId();
        this.localizacao = poste.getLocalizacao();
        this.potencia = poste.getPotencia();
        this.status = poste.getStatus();
        this.consumoAtual = poste.getConsumoAtual();
        this.ultimoConsumo = poste.getUltimoConsumo();
    }

    // Construtor vazio necessário para uso em frameworks como Spring
    public PosteDTO() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getConsumoAtual() {
        return consumoAtual;
    }

    public void setConsumoAtual(double consumoAtual) {
        this.consumoAtual = consumoAtual;
    }

    public double getUltimoConsumo() {
        return ultimoConsumo;
    }

    public void setUltimoConsumo(double ultimoConsumo) {
        this.ultimoConsumo = ultimoConsumo;
    }
}
