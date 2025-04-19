package com.academia.controller;

import com.academia.dao.ProfessorDAO;
import com.academia.model.Professor;
import com.academia.service.ProfessorService;
import com.academia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProfessorController {
    private final ProfessorService professorService;
    private final ConsoleUtils console;

    public ProfessorController(Connection connection) {
        this.professorService = new ProfessorService(new ProfessorDAO(connection));
        this.console = new ConsoleUtils();
    }

    public void gerenciarProfessores() {
        int opcao;
        do {
            console.exibirMenuProfessores();
            opcao = console.lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarProfessor();
                case 2 -> listarProfessores();
                case 3 -> buscarProfessorPorId();
                case 4 -> atualizarProfessor();
                case 5 -> alterarStatusProfessor();
                case 0 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarProfessor() {
        try {
            Professor professor = new Professor();
            professor.setNome(console.lerString("Nome: "));
            professor.setDataNascimento(console.lerData("Data Nascimento (AAAA-MM-DD): "));
            professor.setGenero(console.lerString("Gênero (M/F/NB): "));
            professor.setTelefone(console.lerString("Telefone: "));
            professor.setEmail(console.lerString("Email: "));
            professor.setDataInicio(LocalDate.now());
            professor.setGraduacao(console.lerString("Graduação (Ex: Faixa Preta 3º Grau): "));
            professor.setAnosExperiencia(console.lerInt("Anos de Experiência: "));
            professor.setFaixaPreta(console.lerBoolean("É faixa preta? (S/N): "));
            professor.setObservacoes(console.lerString("Observações: "));
            professor.setAtivo(true);

            professorService.cadastrarProfessor(professor);
            System.out.println("Professor cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    private void listarProfessores() {
        try {
            List<Professor> professores = professorService.listarProfessoresAtivos();
            console.exibirProfessores(professores);
        } catch (SQLException e) {
            System.err.println("Erro ao listar professores: " + e.getMessage());
        }
    }

    private void buscarProfessorPorId() {
        try {
            int id = console.lerInt("ID do Professor: ");
            Professor professor = professorService.buscarPorId(id);
            
            if (professor != null) {
                console.exibirDetalhesProfessor(professor);
            } else {
                System.out.println("Professor não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar professor: " + e.getMessage());
        }
    }

    private void atualizarProfessor() {
        try {
            int id = console.lerInt("ID do Professor: ");
            Professor professor = professorService.buscarPorId(id);
            
            if (professor != null) {
                System.out.println("Deixe em branco para manter o valor atual");
                
                String novoNome = console.lerString("Novo nome [" + professor.getNome() + "]: ");
                if (!novoNome.isEmpty()) professor.setNome(novoNome);
                
                String novoTelefone = console.lerString("Novo telefone [" + professor.getTelefone() + "]: ");
                if (!novoTelefone.isEmpty()) professor.setTelefone(novoTelefone);
                
                String novoEmail = console.lerString("Novo email [" + professor.getEmail() + "]: ");
                if (!novoEmail.isEmpty()) professor.setEmail(novoEmail);
                
                String novaGraduacao = console.lerString("Nova graduação [" + professor.getGraduacao() + "]: ");
                if (!novaGraduacao.isEmpty()) professor.setGraduacao(novaGraduacao);
                
                professorService.atualizarProfessor(professor);
                System.out.println("Professor atualizado com sucesso!");
            } else {
                System.out.println("Professor não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    private void alterarStatusProfessor() {
        try {
            int id = console.lerInt("ID do Professor: ");
            boolean sucesso = professorService.alternarStatus(id);
            
            if (sucesso) {
                System.out.println("Status alterado com sucesso!");
            } else {
                System.out.println("Professor não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar status: " + e.getMessage());
        }
    }
}