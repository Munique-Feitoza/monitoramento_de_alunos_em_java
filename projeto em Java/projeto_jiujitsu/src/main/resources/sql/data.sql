-- Inserir dados de exemplo para Faixas
INSERT INTO faixa (nome) VALUES ('Branca');
INSERT INTO faixa (nome) VALUES ('Azul');
INSERT INTO faixa (nome) VALUES ('Roxa');
INSERT INTO faixa (nome) VALUES ('Preta');

-- Inserir dados de exemplo para GrauFaixa
INSERT INTO grau_faixa (nome, faixa_id) VALUES ('1º Grau', 1);
INSERT INTO grau_faixa (nome, faixa_id) VALUES ('2º Grau', 2);
INSERT INTO grau_faixa (nome, faixa_id) VALUES ('3º Grau', 3);

-- Inserir dados de exemplo para Professores
INSERT INTO professor (nome, email, telefone) VALUES ('Carlos Silva', 'carlos@jiujitsu.com', '999999999');
INSERT INTO professor (nome, email, telefone) VALUES ('Maria Souza', 'maria@jiujitsu.com', '888888888');

-- Inserir dados de exemplo para Equipes
INSERT INTO equipe (nome) VALUES ('Equipe A');
INSERT INTO equipe (nome) VALUES ('Equipe B');

-- Inserir dados de exemplo para Alunos
INSERT INTO aluno (nome, email, telefone, data_nascimento, faixa_id) VALUES ('João Oliveira', 'joao@exemplo.com', '777777777', '1990-05-20', 1);
INSERT INTO aluno (nome, email, telefone, data_nascimento, faixa_id) VALUES ('Lucas Almeida', 'lucas@exemplo.com', '666666666', '1992-07-15', 2);
