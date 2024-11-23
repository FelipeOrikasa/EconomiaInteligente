package br.com.fiap.dao;

import br.com.fiap.beans.EmissaoCarbono;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmissaoCarbonoDAO {
    private Connection conexao;

    // Create
    public void cadastrar(EmissaoCarbono emissao) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "INSERT INTO PF0645.EMISSOES_CARBONO (ID_EMISSAO, ID_TIPO_FONTE, EMISSAO) VALUES (?, ?, ?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, emissao.getIdEmissao());
        stmt.setInt(2, emissao.getIdTipoFonte());
        stmt.setDouble(3, emissao.getEmissao());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Read All
    public List<EmissaoCarbono> listarEmissoes() throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        List<EmissaoCarbono> emissoes = new ArrayList<>();
        String sql = "SELECT * FROM PF0645.EMISSOES_CARBONO";

        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
        	EmissaoCarbono emissao = new EmissaoCarbono(
                rs.getInt("ID_EMISSAO"),
                rs.getInt("ID_TIPO_FONTE"),
                rs.getDouble("EMISSAO")
            );
            emissoes.add(emissao);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return emissoes;
    }

    // Read by ID
    public EmissaoCarbono buscarPorId(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "SELECT * FROM PF0645.EMISSOES_CARBONO WHERE ID_EMISSAO = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        EmissaoCarbono emissao = null;

        if (rs.next()) {
            emissao = new EmissaoCarbono(
                rs.getInt("ID_EMISSAO"),
                rs.getInt("ID_TIPO_FONTE"),
                rs.getDouble("EMISSAO")
            );
        }

        rs.close();
        stmt.close();
        conexao.close();

        return emissao;
    }

    // Update
    public void atualizar(EmissaoCarbono emissao) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "UPDATE PF0645.EMISSOES_CARBONO SET ID_TIPO_FONTE = ?, EMISSAO = ? WHERE ID_EMISSAO = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, emissao.getIdTipoFonte());
        stmt.setDouble(2, emissao.getEmissao());
        stmt.setInt(3, emissao.getIdEmissao());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Delete
    public void remover(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "DELETE FROM PF0645.EMISSOES_CARBONO WHERE ID_EMISSAO = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }
}