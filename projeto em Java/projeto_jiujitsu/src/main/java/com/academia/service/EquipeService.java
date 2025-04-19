package com.academia.service;

import com.academia.dao.EquipeDAO;
import com.academia.model.Equipe;
import java.util.List;

public class EquipeService {
    private EquipeDAO equipeDAO;

    public EquipeService() {
        this.equipeDAO = new EquipeDAO();
    }

    public void cadastrarEquipe(Equipe equipe) {
        equipeDAO.save(equipe);
    }

    public void atualizarEquipe(Equipe equipe) {
        equipeDAO.update(equipe);
    }

    public void excluirEquipe(int equipeId) {
        equipeDAO.delete(equipeId);
    }

    public List<Equipe> listarEquipes() {
        return equipeDAO.findAll();
    }

    public Equipe buscarEquipePorId(int equipeId) {
        return equipeDAO.findById(equipeId);
    }
}
