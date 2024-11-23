package br.com.fiap.bo;

import br.com.fiap.beans.TipoFonte;
import br.com.fiap.dao.TipoFonteDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TipoFonteBO {
    private TipoFonteDAO dao = new TipoFonteDAO();

    // Métodos de Lógica de Negócio
    public void cadastrarFonte(TipoFonte fonte) throws Exception {
        if (fonte.getNome() == null || fonte.getNome().trim().isEmpty()) {
            throw new Exception("Nome da fonte não pode ser vazio");
        }

        dao.cadastrar(fonte);
    }

    public List<TipoFonte> listarFontesRenovaveis() throws SQLException, ClassNotFoundException {
        return dao.listarFontes().stream()
            .filter(TipoFonte::isRenewableSource)
            .collect(Collectors.toList());
    }

    public int contarFontes() throws SQLException, ClassNotFoundException {
        return dao.listarFontes().size();
    }

    // Novo método para atualizar uma fonte de energia (UPDATE)
    public void atualizarFonte(TipoFonte fonte) throws Exception {
        if (fonte.getIdTipoFonte() <= 0) {
            throw new Exception("ID da fonte inválido");
        }

        if (fonte.getNome() == null || fonte.getNome().trim().isEmpty()) {
            throw new Exception("Nome da fonte não pode ser vazio");
        }

        // Chama o DAO para atualizar a fonte no banco de dados
        dao.atualizar(fonte);
    }
}
