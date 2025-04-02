import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcademiaJiujitsuApp {
    // Configurações de conexão
    private static final String URL = "jdbc:mysql://localhost:3306/academia_jiujitsu?" + 
                                   "useSSL=false&" +
                                   "allowPublicKeyRetrieval=true&" +
                                   "serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "sua_senha";
    
    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Tentando conectar ao banco de dados...");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexão estabelecida com sucesso!");
                System.out.println("Banco de dados: " + connection.getCatalog());
                exibirMenuPrincipal();
            } else {
                System.err.println("Falha ao estabelecer conexão!");
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("\nERRO: Driver JDBC não encontrado!");
            System.err.println("Solução: Adicione o mysql-connector-java-X.X.XX.jar ao classpath");
            System.err.println("Disponível em: https://dev.mysql.com/downloads/connector/j/");
            e.printStackTrace();
            
        } catch (SQLException e) {
            System.err.println("\nERRO NA CONEXÃO:");
            System.err.println("Mensagem: " + e.getMessage());
            System.err.println("Código: " + e.getErrorCode());
            System.err.println("Estado SQL: " + e.getSQLState());
            
            if (e.getMessage().contains("Access denied")) {
                System.err.println("\nDICA: Verifique usuário e senha");
            } else if (e.getMessage().contains("Unknown database")) {
                System.err.println("\nDICA: Banco 'academia_jiujitsu' existe?");
            } else if (e.getMessage().contains("Communications link failure")) {
                System.err.println("\nDICA: MySQL está rodando? (sudo systemctl status mysql)");
            }
            
        } finally {
            if (scanner != null) scanner.close();
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("\nConexão encerrada.");
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }

    public static boolean testarConexao() {
        try (Connection testConn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            return testConn.isValid(2);
        } catch (SQLException e) {
            return false;
        }
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Professores");
            System.out.println("3. Gerenciar Turmas");
            System.out.println("4. Gerenciar Equipes");
            System.out.println("5. Gerenciar Eventos");
            System.out.println("6. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1: gerenciarAlunos(); break;
                case 2: gerenciarProfessores(); break;
                case 3: gerenciarTurmas(); break;
                case 4: gerenciarEquipes(); break;
                case 5: gerenciarEventos(); break;
                case 6: exibirRelatorios(); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void gerenciarAlunos() {
        while (true) {
            System.out.println("\n=== GERENCIAR ALUNOS ===");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Buscar aluno por ID");
            System.out.println("4. Atualizar aluno");
            System.out.println("5. Promover aluno");
            System.out.println("6. Alterar status");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1: cadastrarAluno(); break;
                case 2: listarAlunos(); break;
                case 3: buscarAlunoPorId(); break;
                case 4: atualizarAluno(); break;
                case 5: promoverAluno(); break;
                case 6: alterarStatusAluno(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarAluno() {
        try {
            System.out.println("\n=== CADASTRAR ALUNO ===");
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Data Nascimento (YYYY-MM-DD): ");
            String dataNasc = scanner.nextLine();
            
            System.out.print("Gênero (M/F/NB): ");
            String genero = scanner.nextLine();
            
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Data Entrada (YYYY-MM-DD): ");
            String dataEntrada = scanner.nextLine();
            
            System.out.print("É autista? (true/false): ");
            boolean isAutista = scanner.nextBoolean();
            
            int nivelAutismo = 0;
            if (isAutista) {
                System.out.print("Nível autismo (1-10): ");
                nivelAutismo = scanner.nextInt();
            }
            
            System.out.print("É mulher? (true/false): ");
            boolean isMulher = scanner.nextBoolean();
            
            System.out.print("Peso (kg): ");
            double peso = scanner.nextDouble();
            
            System.out.print("Altura (m): ");
            double altura = scanner.nextDouble();
            scanner.nextLine();
            
            listarEquipes();
            System.out.print("ID Equipe: ");
            int idEquipe = scanner.nextInt();
            
            listarCoresFaixa();
            System.out.print("ID Cor Faixa: ");
            int idCorFaixa = scanner.nextInt();
            
            listarGrausFaixa();
            System.out.print("ID Grau Faixa: ");
            int idGrauFaixa = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Observações: ");
            String obs = scanner.nextLine();
            
            String sql = "INSERT INTO alunos (nome, data_nascimento, genero, telefone, email, " +
                         "data_entrada, is_autista, nivel_autismo, is_mulher, peso, altura, " +
                         "id_equipe, id_cor_faixa, id_grau_faixa, observacoes) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, dataNasc);
            stmt.setString(3, genero);
            stmt.setString(4, telefone);
            stmt.setString(5, email);
            stmt.setString(6, dataEntrada);
            stmt.setBoolean(7, isAutista);
            stmt.setInt(8, nivelAutismo);
            stmt.setBoolean(9, isMulher);
            stmt.setDouble(10, peso);
            stmt.setDouble(11, altura);
            stmt.setInt(12, idEquipe);
            stmt.setInt(13, idCorFaixa);
            stmt.setInt(14, idGrauFaixa);
            stmt.setString(15, obs);
            
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Aluno cadastrado!" : "Falha ao cadastrar");
            
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void listarAlunos() {
        try {
            String sql = "SELECT a.id_aluno, a.nome, a.data_nascimento, a.ativo, " +
                         "cf.nome_cor AS faixa, gf.descricao AS grau, e.nome_equipe " +
                         "FROM alunos a " +
                         "JOIN cores_faixa cf ON a.id_cor_faixa = cf.id_cor " +
                         "JOIN graus_faixa gf ON a.id_grau_faixa = gf.id_grau " +
                         "JOIN equipes e ON a.id_equipe = e.id_equipe";
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.printf("\n%-5s %-30s %-15s %-10s %-15s %-10s %-20s\n", 
                            "ID", "Nome", "Nascimento", "Ativo", "Faixa", "Grau", "Equipe");
            System.out.println("-------------------------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-5d %-30s %-15s %-10s %-15s %-10s %-20s\n",
                                rs.getInt("id_aluno"),
                                rs.getString("nome"),
                                rs.getString("data_nascimento"),
                                rs.getBoolean("ativo") ? "Sim" : "Não",
                                rs.getString("faixa"),
                                rs.getString("grau"),
                                rs.getString("nome_equipe"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
    }

    private static void buscarAlunoPorId() {
        try {
            System.out.print("\nID do aluno: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            String sql = "SELECT a.*, cf.nome_cor AS faixa, gf.descricao AS grau, e.nome_equipe " +
                         "FROM alunos a " +
                         "JOIN cores_faixa cf ON a.id_cor_faixa = cf.id_cor " +
                         "JOIN graus_faixa gf ON a.id_grau_faixa = gf.id_grau " +
                         "JOIN equipes e ON a.id_equipe = e.id_equipe " +
                         "WHERE a.id_aluno = ?";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("\n=== DETALHES ===");
                System.out.println("ID: " + rs.getInt("id_aluno"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Nascimento: " + rs.getString("data_nascimento"));
                System.out.println("Gênero: " + rs.getString("genero"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Entrada: " + rs.getString("data_entrada"));
                System.out.println("Autista: " + (rs.getBoolean("is_autista") ? "Sim" : "Não"));
                if (rs.getBoolean("is_autista")) {
                    System.out.println("Nível: " + rs.getInt("nivel_autismo"));
                }
                System.out.println("Mulher: " + (rs.getBoolean("is_mulher") ? "Sim" : "Não"));
                System.out.println("Faixa: " + rs.getString("faixa") + " " + rs.getString("grau"));
                System.out.println("Peso: " + rs.getDouble("peso") + " kg");
                System.out.println("Altura: " + rs.getDouble("altura") + " m");
                System.out.println("Equipe: " + rs.getString("nome_equipe"));
                System.out.println("Ativo: " + (rs.getBoolean("ativo") ? "Sim" : "Não"));
                System.out.println("Obs: " + rs.getString("observacoes"));
            } else {
                System.out.println("Aluno não encontrado!");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar: " + e.getMessage());
        }
    }

    private static void atualizarAluno() {
        try {
            System.out.print("\nID do aluno: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("\nDeixe em branco para não alterar");
            
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();
            
            System.out.print("Novo email: ");
            String email = scanner.nextLine();
            
            System.out.print("Novo peso (kg): ");
            String pesoStr = scanner.nextLine();
            Double peso = pesoStr.isEmpty() ? null : Double.parseDouble(pesoStr);
            
            System.out.print("Nova altura (m): ");
            String alturaStr = scanner.nextLine();
            Double altura = alturaStr.isEmpty() ? null : Double.parseDouble(alturaStr);
            
            StringBuilder sql = new StringBuilder("UPDATE alunos SET ");
            boolean hasUpdate = false;
            
            if (!nome.isEmpty()) {
                sql.append("nome = ?, ");
                hasUpdate = true;
            }
            if (!telefone.isEmpty()) {
                sql.append("telefone = ?, ");
                hasUpdate = true;
            }
            if (!email.isEmpty()) {
                sql.append("email = ?, ");
                hasUpdate = true;
            }
            if (peso != null) {
                sql.append("peso = ?, ");
                hasUpdate = true;
            }
            if (altura != null) {
                sql.append("altura = ?, ");
                hasUpdate = true;
            }
            
            if (!hasUpdate) {
                System.out.println("Nada para atualizar!");
                return;
            }
            
            sql.delete(sql.length()-2, sql.length());
            sql.append(" WHERE id_aluno = ?");
            
            PreparedStatement stmt = connection.prepareStatement(sql.toString());
            int param = 1;
            
            if (!nome.isEmpty()) stmt.setString(param++, nome);
            if (!telefone.isEmpty()) stmt.setString(param++, telefone);
            if (!email.isEmpty()) stmt.setString(param++, email);
            if (peso != null) stmt.setDouble(param++, peso);
            if (altura != null) stmt.setDouble(param++, altura);
            
            stmt.setInt(param, id);
            
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Atualizado com sucesso!" : "Falha ao atualizar");
            
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    private static void promoverAluno() {
        try {
            System.out.print("\nID do aluno: ");
            int idAluno = scanner.nextInt();
            scanner.nextLine();
            
            String alunoSql = "SELECT id_cor_faixa, id_grau_faixa FROM alunos WHERE id_aluno = ?";
            PreparedStatement alunoStmt = connection.prepareStatement(alunoSql);
            alunoStmt.setInt(1, idAluno);
            ResultSet alunoRs = alunoStmt.executeQuery();
            
            if (!alunoRs.next()) {
                System.out.println("Aluno não encontrado!");
                return;
            }
            
            int idCorAtual = alunoRs.getInt("id_cor_faixa");
            int idGrauAtual = alunoRs.getInt("id_grau_faixa");
            
            System.out.println("\n=== PROMOÇÃO ===");
            listarCoresFaixa();
            System.out.print("Nova cor (ID): ");
            int novaCor = scanner.nextInt();
            
            listarGrausFaixa();
            System.out.print("Novo grau (ID): ");
            int novoGrau = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Data promoção (YYYY-MM-DD): ");
            String dataPromo = scanner.nextLine();
            
            System.out.print("ID professor responsável: ");
            int idProf = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Observações: ");
            String obs = scanner.nextLine();
            
            String updateSql = "UPDATE alunos SET id_cor_faixa = ?, id_grau_faixa = ?, data_promocao_faixa = ? WHERE id_aluno = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setInt(1, novaCor);
            updateStmt.setInt(2, novoGrau);
            updateStmt.setString(3, dataPromo);
            updateStmt.setInt(4, idAluno);
            
            int rows = updateStmt.executeUpdate();
            if (rows > 0) {
                String histSql = "INSERT INTO historico_faixas (id_aluno, id_professor, " +
                                "id_cor_faixa_anterior, id_grau_faixa_anterior, " +
                                "id_cor_faixa_nova, id_grau_faixa_nova, data_promocao, observacoes) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
                PreparedStatement histStmt = connection.prepareStatement(histSql);
                histStmt.setInt(1, idAluno);
                histStmt.setInt(2, idProf);
                histStmt.setInt(3, idCorAtual);
                histStmt.setInt(4, idGrauAtual);
                histStmt.setInt(5, novaCor);
                histStmt.setInt(6, novoGrau);
                histStmt.setString(7, dataPromo);
                histStmt.setString(8, obs);
                
                histStmt.executeUpdate();
                histStmt.close();
                System.out.println("Promoção registrada!");
            } else {
                System.out.println("Falha na promoção");
            }
            
            updateStmt.close();
            alunoRs.close();
            alunoStmt.close();
        } catch (SQLException e) {
            System.err.println("Erro na promoção: " + e.getMessage());
        }
    }

    private static void alterarStatusAluno() {
        try {
            System.out.print("\nID do aluno: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            String checkSql = "SELECT ativo FROM alunos WHERE id_aluno = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("Aluno não encontrado!");
                return;
            }
            
            boolean ativo = rs.getBoolean("ativo");
            System.out.println("Status atual: " + (ativo ? "Ativo" : "Inativo"));
            System.out.print("Alterar status? (s/n): ");
            String conf = scanner.nextLine();
            
            if (conf.equalsIgnoreCase("s")) {
                String updateSql = "UPDATE alunos SET ativo = ? WHERE id_aluno = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setBoolean(1, !ativo);
                updateStmt.setInt(2, id);
                
                int rows = updateStmt.executeUpdate();
                System.out.println(rows > 0 ? "Status alterado!" : "Falha ao alterar");
                
                updateStmt.close();
            }
            
            rs.close();
            checkStmt.close();
        } catch (SQLException e) {
            System.err.println("Erro ao alterar status: " + e.getMessage());
        }
    }

    private static void gerenciarProfessores() {
        System.out.println("\nFuncionalidade em desenvolvimento...");
    }

    private static void gerenciarTurmas() {
        System.out.println("\nFuncionalidade em desenvolvimento...");
    }

    private static void gerenciarEquipes() {
        System.out.println("\nFuncionalidade em desenvolvimento...");
    }

    private static void gerenciarEventos() {
        System.out.println("\nFuncionalidade em desenvolvimento...");
    }

    private static void exibirRelatorios() {
        while (true) {
            System.out.println("\n=== RELATÓRIOS ===");
            System.out.println("1. Alunos por faixa");
            System.out.println("2. Alunos por equipe");
            System.out.println("3. Alunos autistas");
            System.out.println("4. Alunos mulheres");
            System.out.println("5. Histórico de promoções");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1: relatorioAlunosPorFaixa(); break;
                case 2: relatorioAlunosPorEquipe(); break;
                case 3: relatorioAlunosAutistas(); break;
                case 4: relatorioAlunosMulheres(); break;
                case 5: relatorioHistoricoPromocoes(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void relatorioAlunosPorFaixa() {
        try {
            String sql = "SELECT cf.nome_cor AS faixa, COUNT(*) AS total " +
                         "FROM alunos a " +
                         "JOIN cores_faixa cf ON a.id_cor_faixa = cf.id_cor " +
                         "WHERE a.ativo = TRUE " +
                         "GROUP BY cf.nome_cor " +
                         "ORDER BY cf.ordem_graduacao";
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.printf("\n%-15s %-10s\n", "Faixa", "Total");
            System.out.println("---------------------");
            
            while (rs.next()) {
                System.out.printf("%-15s %-10d\n", 
                                rs.getString("faixa"), 
                                rs.getInt("total"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro no relatório: " + e.getMessage());
        }
    }

    private static void relatorioAlunosPorEquipe() {
        try {
            String sql = "SELECT e.nome_equipe, COUNT(*) AS total " +
                         "FROM alunos a " +
                         "JOIN equipes e ON a.id_equipe = e.id_equipe " +
                         "WHERE a.ativo = TRUE " +
                         "GROUP BY e.nome_equipe " +
                         "ORDER BY total DESC";
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.printf("\n%-20s %-10s\n", "Equipe", "Total");
            System.out.println("--------------------------");
            
            while (rs.next()) {
                System.out.printf("%-20s %-10d\n", 
                                rs.getString("nome_equipe"), 
                                rs.getInt("total"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro no relatório: " + e.getMessage());
        }
    }

    private static void relatorioAlunosAutistas() {
        try {
            String sql = "SELECT nome, data_nascimento, nivel_autismo " +
                         "FROM alunos " +
                         "WHERE is_autista = TRUE AND ativo = TRUE " +
                         "ORDER BY nome";
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.printf("\n%-30s %-15s %-15s\n", "Nome", "Nascimento", "Nível");
            System.out.println("--------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-30s %-15s %-15d\n", 
                                rs.getString("nome"), 
                                rs.getString("data_nascimento"),
                                rs.getInt("nivel_autismo"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro no relatório: " + e.getMessage());
        }
    }

    private static void relatorioAlunosMulheres() {
        try {
            String sql = "SELECT a.nome, a.data_nascimento, cf.nome_cor AS faixa, e.nome_equipe " +
                         "FROM alunos a " +
                         "JOIN cores_faixa cf ON a.id_cor_faixa = cf.id_cor " +
                         "JOIN equipes e ON a.id_equipe = e.id_equipe " +
                         "WHERE a.is_mulher = TRUE AND a.ativo = TRUE " +
                         "ORDER BY a.nome";
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.printf("\n%-30s %-15s %-15s %-20s\n", "Nome", "Nascimento", "Faixa", "Equipe");
            System.out.println("-------------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-30s %-15s %-15s %-20s\n", 
                                rs.getString("nome"), 
                                rs.getString("data_nascimento"),
                                rs.getString("faixa"),
                                rs.getString("nome_equipe"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro no relatório: " + e.getMessage());
        }
    }

    private static void relatorioHistoricoPromocoes() {
        try {
            System.out.print("\nID do aluno: ");
            int idAluno = scanner.nextInt();
            scanner.nextLine();
            
            String sql = "SELECT h.data_promocao, p.nome AS professor, " +
                         "cf_ant.nome_cor AS faixa_ant, gf_ant.descricao AS grau_ant, " +
                         "cf_nova.nome_cor AS faixa_nova, gf_nova.descricao AS grau_nova, " +
                         "h.observacoes " +
                         "FROM historico_faixas h " +
                         "JOIN professores p ON h.id_professor = p.id_professor " +
                         "JOIN cores_faixa cf_ant ON h.id_cor_faixa_anterior = cf_ant.id_cor " +
                         "JOIN graus_faixa gf_ant ON h.id_grau_faixa_anterior = gf_ant.id_grau " +
                         "JOIN cores_faixa cf_nova ON h.id_cor_faixa_nova = cf_nova.id_cor " +
                         "JOIN graus_faixa gf_nova ON h.id_grau_faixa_nova = gf_nova.id_grau " +
                         "WHERE h.id_aluno = ? " +
                         "ORDER BY h.data_promocao DESC";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            
            System.out.printf("\n%-15s %-20s %-20s %-20s %-20s\n", 
                            "Data", "Professor", "Faixa Ant", "Faixa Nova", "Obs");
            System.out.println("---------------------------------------------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-15s %-20s %-20s %-20s %-20s\n", 
                                rs.getString("data_promocao"),
                                rs.getString("professor"),
                                rs.getString("faixa_ant") + " " + rs.getString("grau_ant"),
                                rs.getString("faixa_nova") + " " + rs.getString("grau_nova"),
                                rs.getString("observacoes"));
            }
            
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhuma promoção registrada");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erro no relatório: " + e.getMessage());
        }
    }

    private static void listarCoresFaixa() throws SQLException {
        String sql = "SELECT id_cor, nome_cor FROM cores_faixa ORDER BY ordem_graduacao";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        System.out.printf("\n%-5s %-15s\n", "ID", "Cor");
        System.out.println("-----------------");
        
        while (rs.next()) {
            System.out.printf("%-5d %-15s\n", 
                            rs.getInt("id_cor"), 
                            rs.getString("nome_cor"));
        }
        
        rs.close();
        stmt.close();
    }

    private static void listarGrausFaixa() throws SQLException {
        String sql = "SELECT id_grau, descricao FROM graus_faixa ORDER BY id_grau";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        System.out.printf("\n%-5s %-15s\n", "ID", "Grau");
        System.out.println("-----------------");
        
        while (rs.next()) {
            System.out.printf("%-5d %-15s\n", 
                            rs.getInt("id_grau"), 
                            rs.getString("descricao"));
        }
        
        rs.close();
        stmt.close();
    }

    private static void listarEquipes() throws SQLException {
        String sql = "SELECT id_equipe, nome_equipe FROM equipes WHERE ativo = TRUE";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        System.out.printf("\n%-5s %-20s\n", "ID", "Equipe");
        System.out.println("------------------------");
        
        while (rs.next()) {
            System.out.printf("%-5d %-20s\n", 
                            rs.getInt("id_equipe"), 
                            rs.getString("nome_equipe"));
        }
        
        rs.close();
        stmt.close();
    }
}