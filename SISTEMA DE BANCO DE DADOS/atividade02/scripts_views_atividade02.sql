/*  **** CRIAÇAO DO BANCO DE DADOS ****  */

CREATE DATABASE EmpresaDB;
USE EmpresaDB;

-- Tabela DEPARTAMENTO
CREATE TABLE DEPARTAMENTO (
    codigo_departamento INT NOT NULL AUTO_INCREMENT,
    nome_departamento VARCHAR(100) NOT NULL,
    CONSTRAINT cod_dpto_pkey PRIMARY KEY (codigo_departamento)
);

-- Tabela FUNCIONARIO
CREATE TABLE FUNCIONARIO (
    codigo_funcionario INT NOT NULL AUTO_INCREMENT,
    nome_funcionario VARCHAR(100) NOT NULL,
    quantidade_dependentes INT NOT NULL DEFAULT 0,
    salario DECIMAL(10,2) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    codigo_departamento INT NOT NULL,
    CONSTRAINT cod_func_pkey PRIMARY KEY (codigo_funcionario),
    CONSTRAINT fk_funcionario_departamento FOREIGN KEY (codigo_departamento) 
        REFERENCES DEPARTAMENTO(codigo_departamento)
);
 
/*  **** POPULACAO DO BANCO DE DADOS ****  */	

-- Inserir departamentos

INSERT INTO DEPARTAMENTO (nome_departamento) VALUES 
('Atendimento ao Cliente'),
('Infraestrutura de Rede'),
('Desenvolvimento de Software'),
('Financeiro'),
('Direção Geral'),
('Direção Marketing'),
('Recursos Humanos');

-- Inserir funcionários para o departamento "Atendimento ao Cliente" (5 funcionários)

INSERT INTO FUNCIONARIO (nome_funcionario, quantidade_dependentes, salario, cargo, codigo_departamento) VALUES 
('Ana Silva', 2, 3500.00, 'Atendente', 1),
('Carlos Oliveira', 1, 4000.00, 'Atendente', 1),
('Mariana Santos', 0, 3700.00, 'Supervisor', 1),
('Fernando Souza', 3, 4200.00, 'Gerente', 1),
('Beatriz Lima', 1, 3600.00, 'Analista', 1);

-- Inserir funcionários para o departamento "Infraestrutura de Rede" (8 funcionários)

INSERT INTO FUNCIONARIO (nome_funcionario, quantidade_dependentes, salario, cargo, codigo_departamento) VALUES 
('João Pedro', 2, 5000.00, 'Engenheiro de Redes', 2),
('Ricardo Martins', 0, 4500.00, 'Técnico de Redes', 2),
('Laura Mendes', 1, 5200.00, 'Gerente', 2),
('Gustavo Farias', 2, 4800.00, 'Analista de Redes', 2),
('Paula Rocha', 0, 4300.00, 'Técnico de Suporte', 2),
('Sérgio Almeida', 1, 4600.00, 'Analista de Redes', 2),
('Camila Pereira', 2, 4700.00, 'Técnico de Suporte', 2),
('Roberto Nunes', 0, 4400.00, 'Coordenador de Rede', 2);

-- Inserir funcionários para o departamento "Desenvolvimento de Software" (7 funcionários)

INSERT INTO FUNCIONARIO (nome_funcionario, quantidade_dependentes, salario, cargo, codigo_departamento) VALUES 
('Lucas Silva', 1, 6000.00, 'Desenvolvedor Backend', 3),
('Juliana Costa', 0, 5800.00, 'Desenvolvedora Frontend', 3),
('Pedro Henrique', 2, 6100.00, 'Arquiteto de Software', 3),
('Vanessa Oliveira', 1, 5700.00, 'Desenvolvedora Mobile', 3),
('Tiago Fernandes', 3, 5900.00, 'Analista de Qualidade', 3),
('Alice Souza', 0, 6200.00, 'Coordenador de Projetos', 3),
('Rafael Santos', 1, 6300.00, 'Tech Lead', 3),
('Jose de Souza Santos', 1, 8300.00, 'Gerente', 3);

-- Inserir funcionários para o departamento "Financeiro" (4 funcionários)

INSERT INTO FUNCIONARIO (nome_funcionario, quantidade_dependentes, salario, cargo, codigo_departamento) VALUES 
('Fábio Alves', 0, 5200.00, 'Contador', 4),
('Isabela Moura', 1, 5400.00, 'Analista Financeiro', 4),
('Tatiane Freitas', 2, 5000.00, 'Gerente', 4),
('Hugo Batista', 0, 5300.00, 'Auxiliar Financeiro', 4);

-- Inserir funcionários para o departamento "Direção Geral" (4 funcionários)
INSERT INTO FUNCIONARIO (nome_funcionario, quantidade_dependentes, salario, cargo, codigo_departamento) VALUES 
('Luís Almeida', 1, 10000.00, 'Gerente', 5),
('Patrícia Ferreira', 0, 8500.00, 'Assistente Executivo', 5),
('Bruno Cavalcanti', 2, 9500.00, 'PMO', 5),
('Silvia Moreira', 3, 9000.00, 'Consultora Estratégica', 5);

-- Inserir funcionários para o departamento "Direção Marketing" (5 funcionários)

INSERT INTO FUNCIONARIO (nome_funcionario, quantidade_dependentes, salario, cargo, codigo_departamento) VALUES 
('Marcelo Antunes', 1, 8000.00, 'Especialista Data Science', 6),
('Renata Azevedo', 0, 7500.00, 'Gerente', 6),
('Andréia Castro', 2, 7200.00, 'Analista de Comunicação', 6),
('Eduardo Menezes', 1, 7700.00, 'Consultor de Marketing Digital', 6),
('Carolina Martins', 0, 7400.00, 'Analista de Pesquisa de Mercado', 6);

-- Inserir funcionários para o departamento "Recursos Humanos" (6 funcionários)

INSERT INTO FUNCIONARIO (nome_funcionario, quantidade_dependentes, salario, cargo, codigo_departamento) VALUES 
('Clara Souza', 2, 4500.00, 'Analista de RH', 7),
('Rodrigo Lopes', 1, 4700.00, 'Coordenador', 7),
('Monique Andrade', 0, 4600.00, 'Recrutadora', 7),
('Felipe Moreira', 3, 4900.00, 'Gerente', 7),
('Fernanda Ramos', 1, 4400.00, 'Analista de RH', 7),
('André Martins', 0, 4800.00, 'Treinador Corporativo', 7);

/*
  ****  CRIAÇÃO DE USUÁRIOS **** 
Usuário gerente com acesso irrestrito
Usuário funcionario com acesso readonly tabela departamento e a criação de uma view
para a tabela de funcionários, pois o usuário funcionário não deve ter acesso
a dados restritos como salário e nr de dependentes de cada funcionário
*/

CREATE VIEW funcionario_view AS
SELECT codigo_funcionario, nome_funcionario, codigo_departamento
FROM empresadb.funcionario;

CREATE USER 'gerente'@'localhost' IDENTIFIED BY 'utfpr0';
GRANT ALL PRIVILEGES ON empresadb.* TO 'gerente'@'localhost';

CREATE USER 'funcionario'@'localhost' IDENTIFIED BY 'utfpr1';
GRANT SELECT ON empresadb.departamento TO  'funcionario'@'localhost';
GRANT SELECT ON empresadb.funcionario_view TO 'funcionario'@'localhost';
 
/*  **** VIEWS QUESTÃO 4 ****  */

	
-- VIEW ITEM 4a)

CREATE VIEW item_4a AS
WITH total_func_depto AS (
	SELECT departamento.nome_departamento, 
       COUNT(funcionario.codigo_funcionario) AS total_funcionarios
FROM funcionario 
INNER JOIN departamento USING(codigo_departamento)
GROUP BY departamento.codigo_departamento)
SELECT nome_departamento, total_funcionarios
FROM total_func_depto
WHERE total_funcionarios = (SELECT MAX(total_funcionarios) FROM total_func_depto);


-- VIEW ITEM 4b) 

CREATE VIEW item_4b AS
WITH func_sem_dependentes AS (
    SELECT  
        departamento.nome_departamento,
        COUNT(funcionario.codigo_funcionario) AS total_funcionarios,
        COUNT(CASE WHEN funcionario.quantidade_dependentes = 0 THEN 1 END) AS total_func_sem_dependentes
    FROM funcionario
    INNER JOIN departamento ON funcionario.codigo_departamento = departamento.codigo_departamento
    GROUP BY departamento.codigo_departamento
)
SELECT nome_departamento, total_funcionarios, total_func_sem_dependentes
FROM func_sem_dependentes
WHERE total_func_sem_dependentes = (
    SELECT MIN(total_func_sem_dependentes) 
    FROM func_sem_dependentes
);

-- VIEW ITEM 4c) 

CREATE VIEW view_item_4c AS
SELECT departamento.nome_departamento,funcionario.nome_funcionario
FROM funcionario
INNER JOIN departamento ON funcionario.codigo_departamento = departamento.codigo_departamento
WHERE LOWER(departamento.nome_departamento) LIKE 'dir%';

-- VIEW ITEM 4d) 

CREATE VIEW view_item_4d AS
SELECT departamento.nome_departamento,funcionario.nome_funcionario
FROM funcionario
INNER JOIN departamento ON funcionario.codigo_departamento = departamento.codigo_departamento
WHERE funcionario.salario = ( SELECT MAX(funcionario.salario) FROM funcionario);

-- VIEW ITEM 4e) 

CREATE VIEW view_item_4e AS
SELECT departamento.nome_departamento,funcionario.nome_funcionario
FROM funcionario
INNER JOIN departamento ON funcionario.codigo_departamento = departamento.codigo_departamento
WHERE LOWER(funcionario.cargo) = "gerente";
