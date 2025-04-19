# 🥋 Academia de Jiu-Jitsu - Sistema de Gerenciamento

## 📋 Descrição

Este projeto é um **sistema de gerenciamento para academias de Jiu-Jitsu**, desenvolvido em **Java** com conexão ao banco de dados **MySQL**. Ele segue a arquitetura **MVC** (Model-View-Controller), e está organizado em camadas para facilitar a manutenção e escalabilidade.

O sistema permite **cadastrar, consultar, atualizar e gerenciar** alunos, professores, turmas, equipes, eventos, faixas e histórico de promoções. Além disso, oferece geração de **relatórios específicos** para análise do perfil dos alunos.

---

## ✅ Funcionalidades Principais

### 👤 Gerenciamento de Alunos
- Cadastro completo com informações pessoais e técnicas
- Indicação de características especiais (ex: autismo, gênero)
- Associação com faixas, graus e equipes
- Atualização de status (ativo/inativo)
- Histórico de promoções (faixa e grau)

### 📊 Relatórios
- Alunos por faixa
- Alunos por equipe
- Alunos com autismo
- Alunas do sexo feminino
- Histórico de promoções

### 📚 Outros Módulos (em desenvolvimento)
- Cadastro e consulta de professores
- Gerenciamento de turmas com faixa mínima, horários e dias da semana
- Cadastro de equipes de treino
- Organização de eventos (competições, seminários etc.)

---

## 🧱 Estrutura do Projeto

Organizado por pacotes seguindo o padrão:  
src/  
    ├── com.academia.controller/ # Controladores da aplicação  
    ├── com.academia.dao/ # DAOs (Acesso ao banco)  
    ├── com.academia.model/ # Modelos de entidades  
    ├── com.academia.service/ # Lógica de negócio  
    ├── com.academia.util/ # Utilitários (Conexão BD, Helpers)  


---

## 💽 Estrutura do Banco de Dados

Principais tabelas:

- `alunos` - Dados pessoais e técnicos
- `professores` - Instrutores da academia
- `equipes` - Equipes de competição ou treino
- `cores_faixa` e `graus_faixa` - Sistema de graduação
- `historico_faixas` - Histórico de promoções
- `turmas` - Informações sobre cada turma (nome, horário, faixa mínima, professor)
- `eventos` - Eventos promovidos pela academia

> O banco de dados utilizado é `academia_jiujitsu`.

---

## 🛠️ Pré-requisitos

- Java JDK 8 ou superior
- MySQL Server 5.7+
- JDBC Driver (MySQL Connector/J)
- IDE (Eclipse, IntelliJ, VSCode) ou terminal

---

## ⚙️ Configuração

1. Clone o repositório ou copie o projeto.
2. Crie o banco de dados MySQL:
```SQL
CREATE DATABASE academia_jiujitsu;
```
3. Configure a conexão no arquivo ConexaoBD.java:
```Java
private static final String URL = "jdbc:mysql://localhost:3306/academia_jiujitsu?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
private static final String USUARIO = "root";
private static final String SENHA = "sua_senha";
```
4. Garanta que o driver mysql-connector-java esteja incluído no classpath.

---

## ▶️ Como Executar
1. Compile todos os arquivos .java:
```
javac -cp . src/com/academia/**/*.java
```
2. Execute o ponto de entrada (ex: AcademiaJiujitsuApp ou outro main):
```
java -cp . com.academia.view.AcademiaJiujitsuApp
```

---

## 🤝 Contribuição
Contribuições são muito bem-vindas!
Você pode:

Reportar problemas  

Sugerir novas funcionalidades  

Melhorar a estrutura do código  

Criar uma interface gráfica (Swing/JavaFX)  

---

## 📝 Licença
Este projeto está licenciado sob a MIT License.  
Consulte o arquivo LICENSE para mais informações.  

---

## 📬 Contato
Para dúvidas, sugestões ou colaborações, entre em contato com o desenvolvedor.  
Email: muniquefeitoz4@gmail.com  
GitHub: Munique-Feitoza  
LinkedIn: https://linkedin.com/in/munique-feitoza-77034b231/  

Desenvolvido com 💻 + 🥋.

