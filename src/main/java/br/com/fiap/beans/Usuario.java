package br.com.fiap.beans;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;

    public Usuario() {
        super();
    }

    public Usuario(int idUsuario, String nome, String email, String senha, String tipoUsuario) {
        super();
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // Lógica adicional
    public boolean validarEmail() {
        return email.contains("@");
    }

    public boolean verificarPermissaoAdmin() {
        return tipoUsuario.equalsIgnoreCase("Admin");
    }
}
