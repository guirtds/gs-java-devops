package br.com.fiap.global.controller.dto;

import br.com.fiap.global.model.ConsumoEnergetico;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ConsumoEnergeticoDTO {

    private Long id;
    private Long posteId;

    @NotNull(message = "A data e hora do consumo são obrigatórias.")
    private LocalDateTime dataHora;

    @Min(value = 0, message = "O consumo deve ser positivo.")
    private double consumo;

    // Construtor que converte um objeto ConsumoEnergetico em ConsumoEnergeticoDTO
    public ConsumoEnergeticoDTO(ConsumoEnergetico consumoEnergetico) {
        this.id = consumoEnergetico.getId();
        this.dataHora = consumoEnergetico.getDataHora();
        this.consumo = consumoEnergetico.getConsumo();

        // Verifica se o objeto Poste está presente para evitar NullPointerException
        if (consumoEnergetico.getPoste() != null) {
            this.posteId = consumoEnergetico.getPoste().getId();
        }
    }

    // Construtor vazio necessário para uso em frameworks como Spring
    public ConsumoEnergeticoDTO() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPosteId() {
        return posteId;
    }

    public void setPosteId(Long posteId) {
        this.posteId = posteId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }
}
