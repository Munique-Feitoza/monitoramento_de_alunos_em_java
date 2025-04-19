-- Tabela Aluno
CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(15),
    data_nascimento DATE,
    faixa_id INT,
    FOREIGN KEY (faixa_id) REFERENCES faixa(id)
);

-- Tabela Professor
CREATE TABLE professor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(15)
);

-- Tabela Turma
CREATE TABLE turma (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

-- Tabela Equipe
CREATE TABLE equipe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela Evento
CREATE TABLE evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_evento DATE NOT NULL,
    local VARCHAR(100) NOT NULL
);

-- Tabela Faixa
CREATE TABLE faixa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Tabela GrauFaixa
CREATE TABLE grau_faixa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    faixa_id INT,
    FOREIGN KEY (faixa_id) REFERENCES faixa(id)
);
