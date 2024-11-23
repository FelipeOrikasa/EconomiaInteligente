package br.com.fiap.beans;

public class EmissaoCarbono {
    private int idEmissao;
    private int idTipoFonte;
    private double emissao;

    // Constructors
    public EmissaoCarbono() {}

    public EmissaoCarbono(int idEmissao, int idTipoFonte, double emissao) {
        this.idEmissao = idEmissao;
        this.idTipoFonte = idTipoFonte;
        this.emissao = emissao;
    }

    // Getters and Setters
    public int getIdEmissao() { return idEmissao; }
    public void setIdEmissao(int idEmissao) { this.idEmissao = idEmissao; }

    public int getIdTipoFonte() { return idTipoFonte; }
    public void setIdTipoFonte(int idTipoFonte) { this.idTipoFonte = idTipoFonte; }

    public double getEmissao() { return emissao; }
    public void setEmissao(double emissao) { this.emissao = emissao; }

    // Business logic method
    public boolean isHighEmission() {
        return this.emissao > 5000;
    }
}