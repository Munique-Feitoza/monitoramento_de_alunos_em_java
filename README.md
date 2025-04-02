# Academia de Jiu-Jitsu - Sistema de Gerenciamento
## Descrição
Este projeto é um sistema de gerenciamento para academias de Jiu-Jitsu desenvolvido em Java com conexão a banco de dados MySQL. O sistema permite o cadastro, consulta e gerenciamento de alunos, professores, turmas, equipes e eventos, além da geração de relatórios diversos.

## Funcionalidades Principais
### Gerenciamento de Alunos
- Cadastro completo de alunos com informações pessoais, características especiais (autismo, gênero) e dados técnicos (faixa, grau, equipe)
- Listagem e busca detalhada de alunos
- Atualização de informações
- Promoção de faixas/graus com registro no histórico
- Alteração de status (ativo/inativo)

## Relatórios
- Alunos por faixa
- Alunos por equipe
- Alunos autistas
- Alunos mulheres
- Histórico de promoções

## Outros Módulos (em desenvolvimento)
- Gerenciamento de Professores
- Gerenciamento de Turmas
- Gerenciamento de Equipes
- Gerenciamento de Eventos

## Pré-requisitos
- Java JDK 8 ou superior
- MySQL Server 5.7 ou superior
- MySQL Connector/J (incluído no classpath)
- Banco de dados "academia_jiujitsu" criado com as tabelas necessárias

## Configuração
1. Clone o repositório ou copie o código fonte
2. Configure as informações de conexão no arquivo **AcademiaJiujitsuApp.java**:

```
private static final String URL = "jdbc:mysql://localhost:3306/academia_jiujitsu?" +
                                  "useSSL=false&" +
                                  "allowPublicKeyRetrieval=true&" +
                                  "serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha";
```

3. Certifique-se de ter o driver JDBC do MySQL no classpath.

## Como Executar
1. Compile o projeto:
```
javac AcademiaJiujitsuApp.java
```
2. Execute a aplicação:
```
java AcademiaJiujitsuApp
```

## Estrutura do Banco de Dados
O sistema espera as seguintes tabelas principais (entre outras):
- **alunos** - Cadastro de alunos
- **professores** - Cadastro de instrutores
- **equipes** - Grupos de treinamento
- **cores_faixa** - Cores de faixas disponíveis
- **graus_faixa** - Graus de faixas disponíveis
- **historico_faixas** - Registro de promoções
- **turmas** - Turmas de treino
- **eventos** - Competições e eventos

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar issues
- Sugerir melhorias
- Enviar pull requests

## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

## Contato
Para mais informações, entre em contato com o desenvolvedor.
