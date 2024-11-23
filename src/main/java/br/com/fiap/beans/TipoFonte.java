package br.com.fiap.beans;

public class TipoFonte {
    private int idTipoFonte;
    private String nome;

    // Constructors
    public TipoFonte() {}

    public TipoFonte(int idTipoFonte, String nome) {
        this.idTipoFonte = idTipoFonte;
        this.nome = nome;
    }

    // Getters and Setters
    public int getIdTipoFonte() { return idTipoFonte; }
    public void setIdTipoFonte(int idTipoFonte) { this.idTipoFonte = idTipoFonte; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // Business logic method
    public boolean isRenewableSource() {
        return nome != null && 
               (nome.equalsIgnoreCase("Solar") || 
                nome.equalsIgnoreCase("Eólica") || 
                nome.equalsIgnoreCase("Hidrelétrica"));
    }
}