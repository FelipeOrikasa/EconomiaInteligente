package br.com.fiap.bo;

import br.com.fiap.beans.EmissaoCarbono;
import br.com.fiap.dao.EmissaoCarbonoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class EmissaoCarbonoBO {
    private EmissaoCarbonoDAO dao;

    public EmissaoCarbonoBO() {
        this.dao = new EmissaoCarbonoDAO();
    }

    // Business validation before creating emission
    public void cadastrar(EmissaoCarbono emissao) throws Exception {
        // Validate input
        if (emissao == null) {
            throw new IllegalArgumentException("Emissão de Carbono não pode ser nula");
        }

        // Additional business rules
        if (emissao.getEmissao() < 0) {
            throw new IllegalArgumentException("Emissão de carbono não pode ser negativa");
        }

        // Check if emission is too high
        if (emissao.isHighEmission()) {
            // Log high emission or trigger additional process
            System.out.println("ALERTA: Emissão de carbono muito alta!");
        }

        // Proceed with DAO registration
        dao.cadastrar(emissao);
    }

    // Business validation before updating
    public void atualizar(EmissaoCarbono emissao) throws Exception {
        if (emissao == null) {
            throw new IllegalArgumentException("Emissão de Carbono não pode ser nula");
        }

        // Validate ID exists
        EmissaoCarbono existente = dao.buscarPorId(emissao.getIdEmissao());
        if (existente == null) {
            throw new IllegalArgumentException("Emissão de Carbono não encontrada");
        }

        dao.atualizar(emissao);
    }

    // Additional business logic method
    public List<EmissaoCarbono> listarEmissoesAltas() throws Exception {
        List<EmissaoCarbono> todasEmissoes = dao.listarEmissoes();
        List<EmissaoCarbono> emissoesAltas = new ArrayList<>();

        for (EmissaoCarbono emissao : todasEmissoes) {
            if (emissao.isHighEmission()) {
                emissoesAltas.add(emissao);
            }
        }

        return emissoesAltas;
    }

    // Delegate methods
    public List<EmissaoCarbono> listarEmissoes() throws Exception {
        return dao.listarEmissoes();
    }

    public EmissaoCarbono buscarPorId(int id) throws Exception {
        return dao.buscarPorId(id);
    }

    public void remover(int id) throws Exception {
        // Additional validation before removal
        EmissaoCarbono existente = dao.buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Emissão de Carbono não encontrada");
        }
        
        dao.remover(id);
    }
}