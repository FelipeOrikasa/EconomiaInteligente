package br.com.fiap.dao;

import br.com.fiap.beans.Projeto;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection conexao;

    // Create (Insert)
    public void cadastrar(Projeto projeto) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "INSERT INTO PF0645.PROJETOS_SUSTENTAVEIS " +
                     "(ID_PROJETO, DESCRICAO, CUSTO, STATUS, ID_REGIAO, ID_TIPO_FONTE) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, projeto.getIdProjeto());
        stmt.setString(2, projeto.getDescricao());
        stmt.setDouble(3, projeto.getCusto());
        stmt.setString(4, projeto.getStatus());
        stmt.setInt(5, projeto.getIdRegiao());
        stmt.setInt(6, projeto.getIdTipoFonte());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Read (Select All)
    public List<Projeto> listarProjetos() throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM PF0645.PROJETOS_SUSTENTAVEIS";

        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
        	Projeto projeto = new Projeto(
                rs.getInt("ID_PROJETO"),
                rs.getString("DESCRICAO"),
                rs.getDouble("CUSTO"),
                rs.getString("STATUS"),
                rs.getInt("ID_REGIAO"),
                rs.getInt("ID_TIPO_FONTE")
            );
            projetos.add(projeto);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return projetos;
    }

    // Read (Select by ID)
    public Projeto buscarPorId(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "SELECT * FROM PF0645.PROJETOS_SUSTENTAVEIS WHERE ID_PROJETO = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Projeto projeto = null;

        if (rs.next()) {
            projeto = new Projeto(
                rs.getInt("ID_PROJETO"),
                rs.getString("DESCRICAO"),
                rs.getDouble("CUSTO"),
                rs.getString("STATUS"),
                rs.getInt("ID_REGIAO"),
                rs.getInt("ID_TIPO_FONTE")
            );
        }

        rs.close();
        stmt.close();
        conexao.close();

        return projeto;
    }

    // Update
    public void atualizar(Projeto projeto) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "UPDATE PF0645.PROJETOS_SUSTENTAVEIS " +
                     "SET DESCRICAO = ?, CUSTO = ?, STATUS = ?, ID_REGIAO = ?, ID_TIPO_FONTE = ? " +
                     "WHERE ID_PROJETO = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, projeto.getDescricao());
        stmt.setDouble(2, projeto.getCusto());
        stmt.setString(3, projeto.getStatus());
        stmt.setInt(4, projeto.getIdRegiao());
        stmt.setInt(5, projeto.getIdTipoFonte());
        stmt.setInt(6, projeto.getIdProjeto());

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Delete
    public void remover(int id) throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        String sql = "DELETE FROM PF0645.PROJETOS_SUSTENTAVEIS WHERE ID_PROJETO = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);

        stmt.executeUpdate();
        stmt.close();
        conexao.close();
    }

    // Custom Queries from SQL Examples
    public List<Projeto> listarProjetosSolarEOlica() throws SQLException, ClassNotFoundException {
        ConexaoFactory fabrica = new ConexaoFactory();
        conexao = fabrica.conexao();

        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT ps.ID_PROJETO, ps.DESCRICAO, ps.CUSTO " +
                     "FROM PF0645.PROJETOS_SUSTENTAVEIS ps " +
                     "JOIN PF0645.TIPO_FONTES tf ON ps.ID_TIPO_FONTE = tf.ID_TIPO_FONTE " +
                     "WHERE tf.NOME IN ('Solar', 'Eólica') " +
                     "ORDER BY ps.DESCRICAO ASC";

        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Projeto projeto = new Projeto();
            projeto.setIdProjeto(rs.getInt("ID_PROJETO"));
            projeto.setDescricao(rs.getString("DESCRICAO"));
            projeto.setCusto(rs.getDouble("CUSTO"));
            projetos.add(projeto);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return projetos;
    }
}