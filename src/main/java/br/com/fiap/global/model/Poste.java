package br.com.fiap.global.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localizacao;

    private double potencia;

    private String status;

    private double consumoAtual;

    private double ultimoConsumo;

    @OneToMany(mappedBy = "poste", cascade = CascadeType.ALL)
    private List<ConsumoEnergetico> consumos;

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

    public List<ConsumoEnergetico> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<ConsumoEnergetico> consumos) {
        this.consumos = consumos;
    }
}
