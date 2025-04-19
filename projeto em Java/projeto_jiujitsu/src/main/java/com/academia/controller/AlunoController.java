package com.academia.controller;

import com.academia.dao.*;
import com.academia.model.*;
import com.academia.service.AlunoService;
import com.academia.util.ConsoleUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AlunoController {
    private final AlunoService alunoService;
    private final ConsoleUtils console;
    private final FaixaDAO faixaDAO;
    private final EquipeDAO equipeDAO;

    public AlunoController(Connection connection) {
        this.alunoService = new AlunoService(new AlunoDAO(connection));
        this.console = new ConsoleUtils();
        this.faixaDAO = new FaixaDAO(connection);
        this.equipeDAO = new EquipeDAO(connection);
    }

    public void gerenciarAlunos() {
        int opcao;
        do {
            console.exibirMenuAlunos();
            opcao = console.lerInt("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> listarAlunos();
                case 3 -> buscarAlunoPorId();
                case 4 -> atualizarAluno();
                case 5 -> promoverAluno();
                case 6 -> alterarStatusAluno();
                case 0 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarAluno() {
        try {
            Aluno aluno = new Aluno();
            aluno.setNome(console.lerString("Nome: "));
            aluno.setDataNascimento(console.lerData("Data Nascimento (AAAA-MM-DD): "));
            aluno.setGenero(console.lerString("Gênero (M/F/NB): "));
            aluno.setTelefone(console.lerString("Telefone: "));
            aluno.setEmail(console.lerString("Email: "));
            aluno.setDataEntrada(LocalDate.now());
            aluno.setAutista(console.lerBoolean("É autista? (S/N): "));
            
            if (aluno.isAutista()) {
                aluno.setNivelAutismo(console.lerInt("Nível autismo (1-10): "));
            }
            
            aluno.setMulher(console.lerBoolean("É mulher? (S/N): "));
            aluno.setPeso(console.lerDouble("Peso (kg): "));
            aluno.setAltura(console.lerDouble("Altura (m): "));
            
            listarEquipes();
            aluno.setIdEquipe(console.lerInt("ID Equipe: "));
            
            listarCoresFaixa();
            aluno.setIdCorFaixa(console.lerInt("ID Cor Faixa: "));
            
            listarGrausFaixa();
            aluno.setIdGrauFaixa(console.lerInt("ID Grau Faixa: "));
            
            aluno.setObservacoes(console.lerString("Observações: "));
            aluno.setAtivo(true);

            alunoService.cadastrarAluno(aluno);
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    private void listarAlunos() {
        try {
            List<Aluno> alunos = alunoService.listarAlunosAtivos();
            console.exibirAlunos(alunos);
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
    }

    private void buscarAlunoPorId() {
        try {
            int id = console.lerInt("ID do Aluno: ");
            Aluno aluno = alunoService.buscarPorId(id);
            
            if (aluno != null) {
                console.exibirDetalhesAluno(aluno);
            } else {
                System.out.println("Aluno não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
        }
    }

    private void atualizarAluno() {
        try {
            int id = console.lerInt("ID do Aluno: ");
            Aluno aluno = alunoService.buscarPorId(id);
            
            if (aluno != null) {
                System.out.println("Deixe em branco para manter o valor atual");
                
                String novoNome = console.lerString("Novo nome [" + aluno.getNome() + "]: ");
                if (!novoNome.isEmpty()) aluno.setNome(novoNome);
                
                // Outros campos...
                
                alunoService.atualizarAluno(aluno);
                System.out.println("Aluno atualizado com sucesso!");
            } else {
                System.out.println("Aluno não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    private void promoverAluno() {
        try {
            int idAluno = console.lerInt("ID do Aluno: ");
            Aluno aluno = alunoService.buscarPorId(idAluno);
            
            if (aluno != null) {
                System.out.println("Promoção de faixa:");
                listarCoresFaixa();
                int novaCor = console.lerInt("Nova cor (ID): ");
                
                listarGrausFaixa();
                int novoGrau = console.lerInt("Novo grau (ID): ");
                
                LocalDate dataPromocao = console.lerData("Data promoção (AAAA-MM-DD): ");
                int idProfessor = console.lerInt("ID Professor responsável: ");
                String obs = console.lerString("Observações: ");
                
                alunoService.promoverAluno(aluno, novaCor, novoGrau, dataPromocao, idProfessor, obs);
                System.out.println("Aluno promovido com sucesso!");
            } else {
                System.out.println("Aluno não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao promover aluno: " + e.getMessage());
        }
    }

    private void alterarStatusAluno() {
        try {
            int id = console.lerInt("ID do Aluno: ");
            boolean sucesso = alunoService.alternarStatus(id);
            
            if (sucesso) {
                System.out.println("Status alterado com sucesso!");
            } else {
                System.out.println("Aluno não encontrado!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao alterar status: " + e.getMessage());
        }
    }

    private void listarEquipes() throws SQLException {
        List<Equipe> equipes = equipeDAO.listarAtivas();
        console.exibirEquipes(equipes);
    }

    private void listarCoresFaixa() throws SQLException {
        List<Faixa> cores = faixaDAO.listarTodas();
        console.exibirCoresFaixa(cores);
    }

    private void listarGrausFaixa() throws SQLException {
        List<GrauFaixa> graus = faixaDAO.listarGraus();
        console.exibirGrausFaixa(graus);
    }
}