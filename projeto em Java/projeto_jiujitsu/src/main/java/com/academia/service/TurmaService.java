package com.academia.service;

import com.academia.dao.TurmaDAO;
import com.academia.model.Turma;
import java.util.List;

public class TurmaService {
    private TurmaDAO turmaDAO;

    public TurmaService() {
        this.turmaDAO = new TurmaDAO();
    }

    public void cadastrarTurma(Turma turma) {
        turmaDAO.save(turma);
    }

    public void atualizarTurma(Turma turma) {
        turmaDAO.update(turma);
    }

    public void excluirTurma(int turmaId) {
        turmaDAO.delete(turmaId);
    }

    public List<Turma> listarTurmas() {
        return turmaDAO.findAll();
    }

    public Turma buscarTurmaPorId(int turmaId) {
        return turmaDAO.findById(turmaId);
    }
}
