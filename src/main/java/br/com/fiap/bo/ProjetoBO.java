package br.com.fiap.bo;

import br.com.fiap.beans.Projeto;
import br.com.fiap.dao.ProjetoDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProjetoBO {
    private ProjetoDAO dao = new ProjetoDAO();

    // Business Logic Methods
    public void cadastrarProjeto(Projeto projeto) throws Exception {
        // Validate project before inserting
        if (projeto.getCusto() <= 0) {
            throw new Exception("Custo do projeto deve ser maior que zero");
        }
        
        if (projeto.getDescricao() == null || projeto.getDescricao().trim().isEmpty()) {
            throw new Exception("Descrição do projeto não pode ser vazia");
        }

        dao.cadastrar(projeto);
    }

    public List<Projeto> listarProjetosEmAndamento() throws SQLException, ClassNotFoundException {
        return dao.listarProjetos().stream()
            .filter(p -> "Em andamento".equals(p.getStatus()))
            .collect(Collectors.toList());
    }

    public List<Projeto> listarProjetosAltoCusto() throws SQLException, ClassNotFoundException {
        return dao.listarProjetos().stream()
            .filter(Projeto::isHighCostProject)
            .collect(Collectors.toList());
    }

    public double calcularCustoTotalProjetos() throws SQLException, ClassNotFoundException {
        return dao.listarProjetos().stream()
            .mapToDouble(Projeto::getCusto)
            .sum();
    }
}