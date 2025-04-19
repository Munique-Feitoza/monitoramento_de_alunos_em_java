package com.academia.controller;

import com.academia.dao.*;
import com.academia.model.*;
import com.academia.service.TurmaService;
import com.academia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TurmaController {
    private final TurmaService turmaService;
    private final ProfessorDAO professorDAO;
    private final ConsoleUtils console;

    public TurmaController(Connection connection) {
        this.turmaService = new TurmaService(new TurmaDAO(connection));
        this.professorDAO = new ProfessorDAO(connection);
        this.console = new ConsoleUtils();
    }

    public void gerenciarTurmas() {
        int opcao;
        do {
            console.exibirMenuTurmas();
            opcao = console.lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarTurma();
                case 2 -> listarTurmas();
                case 3 -> buscarTurmaPorId();
                case 4 -> atualizarTurma();
                case 5 -> adicionarAlunoTurma();
                case 6 -> removerAlunoTurma();
                case 7 -> listarAlunosTurma();
                case 0 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarTurma() {
        try {
            Turma turma = new Turma();
            turma.setNome(console.lerString("Nome da Turma: "));
            turma.setDiaSemana(console.lerString("Dia da Semana: "));
            turma.setHorario(console.lerString("Horário: "));
            turma.setNivel(console.lerString("Nível: "));
            turma.setCapacidadeMaxima(console.lerInt("Capacidade Máxima: "));
            
            listarProfessoresAtivos();
            turma.setIdProfessor(console.lerInt("ID do Professor: "));
            
            turmaService.cadastrarTurma(turma);
            System.out.println("Turma cadastrada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar turma: " + e.getMessage());
        }
    }

    private void listarTurmas() {
        try {
            List<Turma> turmas = turmaService.listarTurmasAtivas();
            console.exibirTurmas(turmas);
        } catch (SQLException e) {
            System.err.println("Erro ao listar turmas: " + e.getMessage());
        }
    }

    private void buscarTurmaPorId() {
        try {
            int id = console.lerInt("ID da Turma: ");
            Turma turma = turmaService.buscarPorId(id);
            
            if (turma != null) {
                console.exibirDetalhesTurma(turma);
            } else {
                System.out.println("Turma não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turma: " + e.getMessage());
        }
    }

    private void atualizarTurma() {
        try {
            int id = console.lerInt("ID da Turma: ");
            Turma turma = turmaService.buscarPorId(id);
            
            if (turma != null) {
                System.out.println("Deixe em branco para manter o valor atual");
                
                String novoNome = console.lerString("Novo nome [" + turma.getNome() + "]: ");
                if (!novoNome.isEmpty()) turma.setNome(novoNome);
                
                String novoDia = console.lerString("Novo dia [" + turma.getDiaSemana() + "]: ");
                if (!novoDia.isEmpty()) turma.setDiaSemana(novoDia);
                
                String novoHorario = console.lerString("Novo horário [" + turma.getHorario() + "]: ");
                if (!novoHorario.isEmpty()) turma.setHorario(novoHorario);
                
                turmaService.atualizarTurma(turma);
                System.out.println("Turma atualizada com sucesso!");
            } else {
                System.out.println("Turma não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    private void adicionarAlunoTurma() {
        try {
            int idTurma = console.lerInt("ID da Turma: ");
            int idAluno = console.lerInt("ID do Aluno: ");
            
            boolean sucesso = turmaService.adicionarAluno(idTurma, idAluno);
            
            if (sucesso) {
                System.out.println("Aluno adicionado à turma com sucesso!");
            } else {
                System.out.println("Não foi possível adicionar o aluno à turma");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar aluno à turma: " + e.getMessage());
        }
    }

    private void removerAlunoTurma() {
        try {
            int idTurma = console.lerInt("ID da Turma: ");
            int idAluno = console.lerInt("ID do Aluno: ");
            
            boolean sucesso = turmaService.removerAluno(idTurma, idAluno);
            
            if (sucesso) {
                System.out.println("Aluno removido da turma com sucesso!");
            } else {
                System.out.println("Não foi possível remover o aluno da turma");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover aluno da turma: " + e.getMessage());
        }
    }

    private void listarAlunosTurma() {
        try {
            int idTurma = console.lerInt("ID da Turma: ");
            List<Aluno> alunos = turmaService.listarAlunosTurma(idTurma);
            
            if (alunos != null) {
                console.exibirAlunos(alunos);
            } else {
                System.out.println("Turma não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos da turma: " + e.getMessage());
        }
    }

    private void listarProfessoresAtivos() throws SQLException {
        List<Professor> professores = professorDAO.listarAtivos();
        console.exibirProfessores(professores);
    }
}