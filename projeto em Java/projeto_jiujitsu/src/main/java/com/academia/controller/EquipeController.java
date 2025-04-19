package com.academia.controller;

import com.academia.dao.EquipeDAO;
import com.academia.model.Equipe;
import com.academia.service.EquipeService;
import com.academia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EquipeController {
    private final EquipeService equipeService;
    private final ConsoleUtils console;

    public EquipeController(Connection connection) {
        this.equipeService = new EquipeService(new EquipeDAO(connection));
        this.console = new ConsoleUtils();
    }

    public void gerenciarEquipes() {
        int opcao;
        do {
            console.exibirMenuEquipes();
            opcao = console.lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarEquipe();
                case 2 -> listarEquipes();
                case 3 -> buscarEquipePorId();
                case 4 -> atualizarEquipe();
                case 5 -> alterarStatusEquipe();
                case 6 -> listarAlunosEquipe();
                case 0 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarEquipe() {
        try {
            Equipe equipe = new Equipe();
            equipe.setNome(console.lerString("Nome da Equipe: "));
            equipe.setDescricao(console.lerString("Descrição: "));
            equipe.setDataCriacao(console.lerData("Data de Criação (AAAA-MM-DD): "));
            equipe.setAtivo(true);

            equipeService.cadastrarEquipe(equipe);
            System.out.println("Equipe cadastrada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar equipe: " + e.getMessage());
        }
    }

    private void listarEquipes() {
        try {
            List<Equipe> equipes = equipeService.listarEquipesAtivas();
            console.exibirEquipes(equipes);
        } catch (SQLException e) {
            System.err.println("Erro ao listar equipes: " + e.getMessage());
        }
    }

    private void buscarEquipePorId() {
        try {
            int id = console.lerInt("ID da Equipe: ");
            Equipe equipe = equipeService.buscarPorId(id);
            
            if (equipe != null) {
                console.exibirDetalhesEquipe(equipe);
            } else {
                System.out.println("Equipe não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar equipe: " + e.getMessage());
        }
    }

    private void atualizarEquipe() {
        try {
            int id = console.lerInt("ID da Equipe: ");
            Equipe equipe = equipeService.buscarPorId(id);
            
            if (equipe != null) {
                System.out.println("Deixe em branco para manter o valor atual");
                
                String novoNome = console.lerString("Novo nome [" + equipe.getNome() + "]: ");
                if (!novoNome.isEmpty()) equipe.setNome(novoNome);
                
                String novaDescricao = console.lerString("Nova descrição [" + equipe.getDescricao() + "]: ");
                if (!novaDescricao.isEmpty()) equipe.setDescricao(novaDescricao);
                
                equipeService.atualizarEquipe(equipe);
                System.out.println("Equipe atualizada com sucesso!");
            } else {
                System.out.println("Equipe não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar equipe: " + e.getMessage());
        }
    }

    private void alterarStatusEquipe() {
        try {
            int id = console.lerInt("ID da Equipe: ");
            boolean sucesso = equipeService.alternarStatus(id);
            
            if (sucesso) {
                System.out.println("Status alterado com sucesso!");
            } else {
                System.out.println("Equipe não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar status: " + e.getMessage());
        }
    }

    private void listarAlunosEquipe() {
        try {
            int idEquipe = console.lerInt("ID da Equipe: ");
            List<Aluno> alunos = equipeService.listarAlunosEquipe(idEquipe);
            
            if (alunos != null) {
                console.exibirAlunos(alunos);
            } else {
                System.out.println("Equipe não encontrada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos da equipe: " + e.getMessage());
        }
    }
}