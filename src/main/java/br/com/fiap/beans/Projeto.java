package br.com.fiap.beans;

public class Projeto {
    private int idProjeto;
    private String descricao;
    private double custo;
    private String status;
    private int idRegiao;
    private int idTipoFonte;

    // Constructors
    public Projeto() {}

    public Projeto(int idProjeto, String descricao, double custo, String status, int idRegiao, int idTipoFonte) {
        this.idProjeto = idProjeto;
        this.descricao = descricao;
        this.custo = custo;
        this.status = status;
        this.idRegiao = idRegiao;
        this.idTipoFonte = idTipoFonte;
    }

    // Getters and Setters
    public int getIdProjeto() { return idProjeto; }
    public void setIdProjeto(int idProjeto) { this.idProjeto = idProjeto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getCusto() { return custo; }
    public void setCusto(double custo) { this.custo = custo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getIdRegiao() { return idRegiao; }
    public void setIdRegiao(int idRegiao) { this.idRegiao = idRegiao; }

    public int getIdTipoFonte() { return idTipoFonte; }
    public void setIdTipoFonte(int idTipoFonte) { this.idTipoFonte = idTipoFonte; }

    // Additional method to demonstrate business logic
    public boolean isHighCostProject() {
        return this.custo > 500000;
    }
}