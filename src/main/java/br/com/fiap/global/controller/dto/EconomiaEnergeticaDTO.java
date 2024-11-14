package br.com.fiap.global.controller.dto;

import br.com.fiap.global.model.EconomiaEnergetica;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EconomiaEnergeticaDTO {

    private Long id;

    @NotBlank(message = "O período é obrigatório.")
    private String periodo;

    @Min(value = 0, message = "A economia total deve ser positiva.")
    private double economiaTotal;

    // Construtor que converte um objeto EconomiaEnergetica em EconomiaEnergeticaDTO
    public EconomiaEnergeticaDTO(EconomiaEnergetica economiaEnergetica) {
        this.id = economiaEnergetica.getId();
        this.periodo = economiaEnergetica.getPeriodo();
        this.economiaTotal = economiaEnergetica.getEconomiaTotal();
    }

    // Construtor vazio necessário para uso em frameworks como Spring
    public EconomiaEnergeticaDTO() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getEconomiaTotal() {
        return economiaTotal;
    }

    public void setEconomiaTotal(double economiaTotal) {
        this.economiaTotal = economiaTotal;
    }
}
