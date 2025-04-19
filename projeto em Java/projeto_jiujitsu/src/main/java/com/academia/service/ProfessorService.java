package com.academia.service;

import com.academia.dao.ProfessorDAO;
import com.academia.model.Professor;
import java.util.List;

public class ProfessorService {
    private ProfessorDAO professorDAO;

    public ProfessorService() {
        this.professorDAO = new ProfessorDAO();
    }

    public void cadastrarProfessor(Professor professor) {
        professorDAO.save(professor);
    }

    public void atualizarProfessor(Professor professor) {
        professorDAO.update(professor);
    }

    public void excluirProfessor(int professorId) {
        professorDAO.delete(professorId);
    }

    public List<Professor> listarProfessores() {
        return professorDAO.findAll();
    }

    public Professor buscarProfessorPorId(int professorId) {
        return professorDAO.findById(professorId);
    }
}
