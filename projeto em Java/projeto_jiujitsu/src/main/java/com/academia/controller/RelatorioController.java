package com.academia.controller;

import com.academia.dao.*;
import com.academia.service.RelatorioService;
import com.academia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RelatorioController {
    private final RelatorioService relatorioService;
    private final ConsoleUtils console;

    public RelatorioController(Connection connection) {
        this.relatorioService = new RelatorioService(
            new AlunoDAO(connection),
            new ProfessorDAO(connection),
            new TurmaDAO(connection),
            new EquipeDAO(connection),
            new EventoDAO(connection)
        );
        this.console = new ConsoleUtils();
    }

    public void exibirRelatorios() {
        int opcao;
        do {
            console.exibirMenuRelatorios();
            opcao = console.lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> relatorioAlunosPorFaixa();
                case 2 -> relatorioAlunosPorEquipe();
                case 3 -> relatorioAlunosAutistas();
                case 4 -> relatorioAlunosMulheres();
                case 5 -> relatorioHistoricoPromocoes();
                case 6 -> relatorioTurmasLotadas();
                case 7 -> relatorioEventosPorPeriodo();
                case 0 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void relatorioAlunosPorFaixa() {
        try {
            Map<String, Integer> relatorio = relatorioService.alunosPorFaixa();
            console.exibirRelatorio("Alunos por Faixa", relatorio);
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioAlunosPorEquipe() {
        try {
            Map<String, Integer> relatorio = relatorioService.alunosPorEquipe();
            console.exibirRelatorio("Alunos por Equipe", relatorio);
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioAlunosAutistas() {
        try {
            List<Map<String, Object>> relatorio = relatorioService.alunosAutistas();
            console.exibirRelatorioDetalhado("Alunos Autistas", relatorio);
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioAlunosMulheres() {
        try {
            List<Map<String, Object>> relatorio = relatorioService.alunosMulheres();
            console.exibirRelatorioDetalhado("Alunos Mulheres", relatorio);
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioHistoricoPromocoes() {
        try {
            int idAluno = console.lerInt("ID do Aluno: ");
            List<Map<String, Object>> relatorio = relatorioService.historicoPromocoes(idAluno);
            
            if (relatorio != null && !relatorio.isEmpty()) {
                console.exibirRelatorioDetalhado("Histórico de Promoções", relatorio);
            } else {
                System.out.println("Nenhuma promoção encontrada para este aluno!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioTurmasLotadas() {
        try {
            List<Map<String, Object>> relatorio = relatorioService.turmasLotadas();
            console.exibirRelatorioDetalhado("Turmas Lotadas", relatorio);
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void relatorioEventosPorPeriodo() {
        try {
            String dataInicio = console.lerString("Data Início (AAAA-MM-DD): ");
            String dataFim = console.lerString("Data Fim (AAAA-MM-DD): ");
            
            List<Map<String, Object>> relatorio = relatorioService.eventosPorPeriodo(dataInicio, dataFim);
            console.exibirRelatorioDetalhado("Eventos por Período", relatorio);
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}