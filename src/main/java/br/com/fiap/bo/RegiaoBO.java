package br.com.fiap.bo;

import br.com.fiap.beans.Regiao;
import br.com.fiap.dao.RegiaoDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RegiaoBO {
    private RegiaoDAO dao = new RegiaoDAO();

    // Business Logic Methods
    public void cadastrarRegiao(Regiao regiao) throws Exception {
        if (regiao.getNome() == null || regiao.getNome().trim().isEmpty()) {
            throw new Exception("Nome da região não pode ser vazio");
        }

        dao.cadastrar(regiao);
    }

    public void atualizarRegiao(Regiao regiao) throws Exception {
        if (regiao.getNome() == null || regiao.getNome().trim().isEmpty()) {
            throw new Exception("Nome da região não pode ser vazio");
        }

        dao.atualizar(regiao);
    }

    public List<Regiao> listarRegioesMeteopolitanas() throws SQLException, ClassNotFoundException {
        return dao.listarRegioes().stream()
            .filter(Regiao::isMetropolitan)
            .collect(Collectors.toList());
    }

    public int contarRegioes() throws SQLException, ClassNotFoundException {
        return dao.listarRegioes().size();
    }
}