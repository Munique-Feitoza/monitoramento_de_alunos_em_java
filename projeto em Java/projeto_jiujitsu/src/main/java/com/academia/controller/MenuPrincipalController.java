package com.academia.controller;

import java.sql.Connection;

public class MenuPrincipalController {
    private final Connection connection;
    private final AlunoController alunoController;
    private final ProfessorController professorController;
    private final TurmaController turmaController;
    private final EquipeController equipeController;
    private final EventoController eventoController;
    private final RelatorioController relatorioController;
    private final ConsoleUtils console;

    public MenuPrincipalController(Connection connection) {
        this.connection = connection;
        this.console = new ConsoleUtils();
        this.alunoController = new AlunoController(connection);
        this.professorController = new ProfessorController(connection);
        this.turmaController = new TurmaController(connection);
        this.equipeController = new EquipeController(connection);
        this.eventoController = new EventoController(connection);
        this.relatorioController = new RelatorioController(connection);
    }

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            console.exibirMenuPrincipal();
            opcao = console.lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> alunoController.gerenciarAlunos();
                case 2 -> professorController.gerenciarProfessores();
                case 3 -> turmaController.gerenciarTurmas();
                case 4 -> equipeController.gerenciarEquipes();
                case 5 -> eventoController.gerenciarEventos();
                case 6 -> relatorioController.exibirRelatorios();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}