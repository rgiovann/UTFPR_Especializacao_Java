set foreign_key_checks = 0;

DELETE FROM departamento;
DELETE FROM funcionario;

set foreign_key_checks = 1;

ALTER TABLE departamento auto_increment = 1;
ALTER TABLE funcionario auto_increment = 1;


INSERT INTO departamento (nome_departamento) VALUES ('Atendimento ao Cliente');
INSERT INTO departamento (nome_departamento) VALUES ('Infraestrutura de Rede');
INSERT INTO departamento (nome_departamento) VALUES ('Desenvolvimento de Software');
INSERT INTO departamento (nome_departamento) VALUES ('Financeiro');
INSERT INTO departamento (nome_departamento) VALUES ('Direção Geral');
INSERT INTO departamento (nome_departamento) VALUES ('Direção Marketing');
INSERT INTO departamento (nome_departamento) VALUES ('Recursos Humanos');

INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Ana Silva', 2, 3500.00, 'Atendente', 1);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Carlos Oliveira', 1, 4000.00, 'Atendente', 1);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Mariana Santos', 0, 3700.00, 'Supervisor', 1);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Fernando Souza', 3, 4200.00, 'Gerente', 1);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Beatriz Lima', 1, 3600.00, 'Analista', 1);

INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('João Pedro', 2, 5000.00, 'Engenheiro de Redes', 2);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Ricardo Martins', 0, 4500.00, 'Técnico de Redes', 2);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Laura Mendes', 1, 5200.00, 'Gerente', 2);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Gustavo Farias', 2, 4800.00, 'Analista de Redes', 2);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Paula Rocha', 0, 4300.00, 'Técnico de Suporte', 2);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Sérgio Almeida', 1, 4600.00, 'Analista de Redes', 2);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Camila Pereira', 2, 4700.00, 'Técnico de Suporte', 2);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Gusta Nunes da Silva', 0, 4400.00, 'Coordenador de Rede', 2);

INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Lucas Silva', 1, 6000.00, 'Desenvolvedor Backend', 3);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Juliana Costa', 0, 5800.00, 'Desenvolvedora Frontend', 3);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Pedro Henrique', 2, 6100.00, 'Arquiteto de Software', 3);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Vanessa Oliveira', 1, 5700.00, 'Desenvolvedora Mobile', 3);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Tiago Fernandes', 3, 5900.00, 'Analista de Qualidade', 3);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Sales de Mattos Souza', 0, 6200.00, 'Coordenador de Projetos', 3);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Rafael Santos', 1, 6300.00, 'Tech Lead', 3);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Jose de Souza Santos', 1, 8300.00, 'Gerente', 3);

INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Fábio Alves', 0, 5200.00, 'Contador', 4);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Rafaela Moura de Souza', 1, 5400.00, 'Analista Financeiro', 4);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Tatiane Freitas Salles', 2, 5000.00, 'Gerente', 4);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Hugo Batista', 0, 5300.00, 'Auxiliar Financeiro', 4);

INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Luís Almeida', 1, 10000.00, 'Gerente', 5);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Patrícia Ferreira', 0, 8500.00, 'Assistente Executivo', 5);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Bruno Cavalcanti', 2, 9500.00, 'PMO', 5);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Silvia Moreira', 3, 9000.00, 'Consultora Estratégica', 5);

INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Marcelo Salles Antunes', 1, 8000.00, 'Especialista Data Science', 6);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Renata Azevedo', 0, 7500.00, 'Gerente', 6);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Andréia Castro', 2, 7200.00, 'Analista de Comunicação', 6);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Silvio Menezes', 1, 7700.00, 'Consultor de Marketing Digital', 6);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Carolina Martins', 0, 7400.00, 'Analista de Pesquisa de Mercado', 6);

INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Clara Souza', 2, 4500.00, 'Analista de RH', 7);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Rodrigo Lopes', 1, 4700.00, 'Coordenador', 7);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Monique Andrade', 0, 4600.00, 'Recrutadora', 7);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Fernando Moreira', 3, 4900.00, 'Gerente', 7);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('Fernanda Ramos', 1, 4400.00, 'Analista de RH', 7);
INSERT INTO funcionario (nome_funcionario, qtd_dependentes, salario, cargo, cod_departamento) VALUES ('André Sales Martins', 0, 4800.00, 'Treinador Corporativo', 7);
