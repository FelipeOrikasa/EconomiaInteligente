package br.com.fiap.bo;

import br.com.fiap.beans.Usuario;
import br.com.fiap.dao.UsuarioDAO;

import java.sql.SQLException;
import java.util.List;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioBO() throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.listar();
    }

    // Buscar usuário por ID
    public Usuario buscarUsuario(int idUsuario) throws SQLException {
        return usuarioDAO.ler(idUsuario);
    }

    // Inserir novo usuário
    public String inserirUsuario(Usuario usuario) throws SQLException {
        // Regras de negócio podem ser aplicadas aqui
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            return "O nome do usuário não pode estar vazio.";
        }
        if (!usuario.validarEmail()) {
            return "O email informado não é válido.";
        }
        return usuarioDAO.inserir(usuario);
    }

    // Atualizar um usuário existente
    public String atualizarUsuario(Usuario usuario) throws SQLException {
        return usuarioDAO.atualizar(usuario);
    }

    // Deletar usuário por ID
    public String deletarUsuario(int idUsuario) throws SQLException {
        return usuarioDAO.deletar(idUsuario);
    }
}
