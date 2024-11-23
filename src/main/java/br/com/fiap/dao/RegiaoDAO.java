package br.com.fiap.dao;

import br.com.fiap.beans.Regiao;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegiaoDAO {
    private Connection conexao;

    // Create
    public void cadastrar(Regiao regiao) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "INSERT INTO PF0645.REGIOES_SUSTENTAVEIS (ID_REGIAO, NOME) VALUES (?, ?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, regiao.getIdRegiao());
        stmt.setString(2, regiao.getNome());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Read All
    public List<Regiao> listarRegioes() throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        List<Regiao> regioes = new ArrayList<>();
        String sql = "SELECT * FROM PF0645.REGIOES_SUSTENTAVEIS";

        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
        	Regiao regiao = new Regiao(
                rs.getInt("ID_REGIAO"),
                rs.getString("NOME")
            );
            regioes.add(regiao);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return regioes;
    }

    // Read by ID
    public Regiao buscarPorId(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "SELECT * FROM PF0645.REGIOES_SUSTENTAVEIS WHERE ID_REGIAO = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Regiao regiao = null;

        if (rs.next()) {
            regiao = new Regiao(
                rs.getInt("ID_REGIAO"),
                rs.getString("NOME")
            );
        }

        rs.close();
        stmt.close();
        conexao.close();

        return regiao;
    }

    // Update
    public void atualizar(Regiao regiao) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "UPDATE PF0645.REGIOES_SUSTENTAVEIS SET NOME = ? WHERE ID_REGIAO = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, regiao.getNome());
        stmt.setInt(2, regiao.getIdRegiao());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Delete
    public void remover(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "DELETE FROM PF0645.REGIOES_SUSTENTAVEIS WHERE ID_REGIAO = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
}