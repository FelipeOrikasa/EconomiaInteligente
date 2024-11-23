package br.com.fiap.dao;

import br.com.fiap.beans.TipoFonte;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoFonteDAO {
    private Connection conexao;

    // Create
    public void cadastrar(TipoFonte fonte) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "INSERT INTO PF0645.TIPO_FONTES (ID_TIPO_FONTE, NOME) VALUES (?, ?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, fonte.getIdTipoFonte());
        stmt.setString(2, fonte.getNome());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Read All
    public List<TipoFonte> listarFontes() throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        List<TipoFonte> fontes = new ArrayList<>();
        String sql = "SELECT * FROM PF0645.TIPO_FONTES";

        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
        	TipoFonte fonte = new TipoFonte(
                rs.getInt("ID_TIPO_FONTE"),
                rs.getString("NOME")
            );
            fontes.add(fonte);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return fontes;
    }

    // Read by ID
    public TipoFonte buscarPorId(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "SELECT * FROM PF0645.TIPO_FONTES WHERE ID_TIPO_FONTE = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        TipoFonte fonte = null;

        if (rs.next()) {
            fonte = new TipoFonte(
                rs.getInt("ID_TIPO_FONTE"),
                rs.getString("NOME")
            );
        }

        rs.close();
        stmt.close();
        conexao.close();

        return fonte;
    }

    // Update
    public void atualizar(TipoFonte fonte) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "UPDATE PF0645.TIPO_FONTES SET NOME = ? WHERE ID_TIPO_FONTE = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, fonte.getNome());
        stmt.setInt(2, fonte.getIdTipoFonte());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Delete
    public void remover(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "DELETE FROM PF0645.TIPO_FONTES WHERE ID_TIPO_FONTE = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
}