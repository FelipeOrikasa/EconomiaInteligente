package br.com.fiap.dao;

import br.com.fiap.beans.Usuario;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection minhaConexao;

    public UsuarioDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO GS_Usuarios (Nome, Email, Senha, TipoUsuario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.execute();
            return "Usuário cadastrado com sucesso!";
        }
    }

    // Ler (por ID)
    public Usuario ler(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM GS_Usuarios WHERE UsuarioID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("UsuarioID"),
                            rs.getString("Nome"),
                            rs.getString("Email"),
                            rs.getString("Senha"),
                            rs.getString("TipoUsuario")
                    );
                }
                return null;
            }
        }
    }

    // Ler (todos os usuários)
    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT * FROM GS_Usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("UsuarioID"),
                        rs.getString("Nome"),
                        rs.getString("Email"),
                        rs.getString("Senha"),
                        rs.getString("TipoUsuario")
                ));
            }
        }
        return usuarios;
    }

    // Atualizar
    public String atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE GS_Usuarios SET Nome = ?, Email = ?, Senha = ?, TipoUsuario = ? WHERE UsuarioID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setInt(5, usuario.getIdUsuario());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Usuário atualizado com sucesso!" : "Usuário não encontrado.";
        }
    }

    // Deletar
    public String deletar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM GS_Usuarios WHERE UsuarioID = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Usuário deletado com sucesso!" : "Usuário não encontrado.";
        }
    }
}
