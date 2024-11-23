package br.com.fiap.beans;

public class Regiao {
    private int idRegiao;
    private String nome;

    // Constructors
    public Regiao() {}

    public Regiao(int idRegiao, String nome) {
        this.idRegiao = idRegiao;
        this.nome = nome;
    }

    // Getters and Setters
    public int getIdRegiao() { return idRegiao; }
    public void setIdRegiao(int idRegiao) { this.idRegiao = idRegiao; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // Business logic method
    public boolean isMetropolitan() {
        return nome != null && nome.toLowerCase().contains("metropolitana");
    }
}