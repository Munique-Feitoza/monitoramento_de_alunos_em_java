package com.academia.service;

import com.academia.dao.AlunoDAO;
import com.academia.model.Aluno;
import java.util.List;

public class AlunoService {
    private AlunoDAO alunoDAO;

    public AlunoService() {
        this.alunoDAO = new AlunoDAO();
    }

    public void cadastrarAluno(Aluno aluno) {
        alunoDAO.save(aluno);
    }

    public void atualizarAluno(Aluno aluno) {
        alunoDAO.update(aluno);
    }

    public void excluirAluno(int alunoId) {
        alunoDAO.delete(alunoId);
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.findAll();
    }

    public Aluno buscarAlunoPorId(int alunoId) {
        return alunoDAO.findById(alunoId);
    }
}
