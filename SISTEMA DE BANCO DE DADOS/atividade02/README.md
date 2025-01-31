# 📌 Revisão SQL - Sistemas de Banco de Dados

Este repositório contém a solução para a **Atividade 02 - Revisão SQL** da disciplina **Sistemas de Banco de Dados** na **Pós-Graduação em Tecnologia Java**. 

## Descrição da Atividade

Esta atividade foi realizada na disciplina **Sistemas de Banco de Dados** da pós-graduação em **Tecnologia Java** na **UTFPR** e teve como objetivo consolidar conhecimentos sobre modelagem e manipulação de bancos de dados relacionais utilizando SQL.

A atividade propôs um cenário onde uma empresa possui departamentos e funcionários, e cada funcionário está vinculado a um único departamento. A partir desse contexto, foram realizadas diversas tarefas, incluindo modelagem de dados, criação do banco de dados, inserção de registros, construção de consultas avançadas por meio de **views** e configuração de usuários com permissões distintas.

## Estrutura da Atividade

A atividade foi dividida nas seguintes etapas:

### 1️⃣ Elaboração do Diagrama Entidade-Relacionamento (DER)

**O que foi pedido:**

* Criar um **DER** que represente a relação entre os departamentos e os funcionários da empresa.

**Desafios enfrentados:**

* Definir corretamente os atributos e chaves primárias de cada entidade.
* Estabelecer o relacionamento entre as tabelas, garantindo a integridade referencial.
* Escolher uma ferramenta adequada para criar e exportar o diagrama.

### 2️⃣ Criação do Banco de Dados no SGBD

**O que foi pedido:**

* Criar o banco de dados e as tabelas necessárias para armazenar as informações modeladas no DER.

**Desafios enfrentados:**

* Estruturar as tabelas corretamente, garantindo a integridade dos dados.
* Configurar **chaves primárias** e **chaves estrangeiras** para refletir os relacionamentos do DER.
* Garantir a compatibilidade com o **MariaDB/MySQL**, respeitando sua sintaxe e limitações.

### 3️⃣ População do Banco de Dados

**O que foi pedido:**

* Inserir dados fictícios nas tabelas **Departamento** e **Funcionário** para viabilizar a execução das consultas.

**Desafios enfrentados:**

* Criar um conjunto de dados coerente e suficiente para validar todas as queries exigidas.
* Garantir que as inserções respeitassem as **restrições de integridade** impostas pelo esquema do banco.

### 4️⃣ Criação de **Views** para Consultas Específicas

Foram criadas **visões (views)** para facilitar a consulta de informações importantes no banco de dados. As queries desenvolvidas incluíram:

#### a) Departamento com maior número de funcionários

**Desafio:** Usar agregação para contar os funcionários por departamento e identificar o que possui a maior quantidade.

#### b) Departamento com menor número de funcionários sem dependentes

**Desafio:** Filtrar funcionários sem dependentes e encontrar o departamento com a menor quantidade.

#### c) Lista de funcionários de departamentos cujo nome começa com "DIR"

**Desafio:** Usar **filtros avançados** para selecionar apenas os departamentos que atendem ao critério, garantindo que a consulta retorne os dados corretos.

#### d) Funcionário com maior salário e seu respectivo departamento

**Desafio:** Determinar o funcionário com o maior salário sem impactar a performance da consulta, especialmente em bancos com grandes volumes de dados.

#### e) Lista de gerentes e seus respectivos departamentos

**Desafio:** Criar uma consulta eficiente para localizar funcionários com o cargo de **"Gerente"**, garantindo a correta associação com seus departamentos.

### 5️⃣ Criação de Usuários com Permissões Diferenciadas

**O que foi pedido:**

* Criar dois usuários no banco de dados com permissões distintas:

  * **Usuário "funcionario"**: acesso **limitado** a determinadas operações.
  * **Usuário "gerente"**: acesso **completo** ao banco de dados.

**Desafios enfrentados:**

* Definir corretamente os privilégios para restringir o que cada usuário pode ou não pode fazer.
* Configurar as permissões corretamente no **MariaDB/MySQL** para evitar problemas de acesso indevido.

## Arquivos Entregues

Os seguintes arquivos foram gerados e entregues conforme exigido pela atividade:

* **DER** exportado em formato de imagem (**PNG, JPG ou BMP**).
* **Script SQL** contendo a criação do banco, tabelas, inserção de dados, views e usuários.
* **Relatório dos dados** exportado em **CSV/XLSX**, contendo apenas as tabelas **Funcionário** e **Departamento**, sem os dados das views.

## Conclusão

A atividade proporcionou uma revisão abrangente de conceitos fundamentais de bancos de dados, desde a modelagem até a execução de consultas avançadas e a configuração de permissões. Os desafios enfrentados envolveram a correta implementação de constraints, a otimização de queries e a aplicação de boas práticas para garantir a integridade e segurança dos dados.

