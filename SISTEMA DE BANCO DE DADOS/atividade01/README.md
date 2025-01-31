# 📌 Revisão SQL - Sistemas de Banco de Dados

Este repositório contém a solução para a **Atividade 01 - Revisão SQL** da disciplina **Sistemas de Banco de Dados** na **Pós-Graduação em Tecnologia Java**.

## 📖 Descrição da Atividade

A atividade envolve a execução de consultas SQL sobre um banco de dados MariaDB (MySQL), com foco na manipulação e recuperação de dados de um esquema fornecido. O objetivo é reforçar conceitos fundamentais de **consultas SQL**, como **agregação, filtragem, ordenação e junção de tabelas**.

## 🚀 Tecnologias Utilizadas

- **MariaDB (MySQL)**
- **SQL (Structured Query Language)**

## 🛠 Instruções para Execução

1. **Executar o script inicial**
   - O script disponível no Moodle deve ser rodado no **MariaDB** para criar e popular as tabelas do banco de dados.
   
2. **Implementar as consultas SQL**
   - Criar as consultas necessárias para responder às questões propostas.
   - Cada consulta deve estar identificada com um comentário correspondente.

3. **Salvar as consultas em um arquivo TXT**
   - O arquivo deve seguir o formato:  
     ```
     <NOMEDOALUNO>_Atividade01.txt
     ```
   - Deve conter todas as consultas implementadas.

## 📌 Questões e Desafios

### ✅ **Questão 01 - Cantor com menos gravações**
Obter o nome do cantor com o **menor número de gravações**. Caso haja mais de um cantor com o valor mínimo, todos devem ser retornados.

**Desafio:**  
- Utilizar funções de agregação para contar as gravações.
- Garantir que todos os cantores com o mínimo de gravações sejam exibidos.

---

### ✅ **Questão 02 - Cantor com mais gravadoras diferentes**
Obter o nome do cantor que gravou com o **maior número de gravadoras diferentes**.

**Desafio:**  
- Contabilizar **gravadoras únicas** associadas a cada cantor.
- Lidar com a possibilidade de múltiplos cantores com o mesmo valor máximo.

---

### ✅ **Questão 03 - Cantor com maior média de duração**
Obter o nome do cantor que possui a **maior média de duração de gravações**.

**Desafio:**  
- Trabalhar com funções de agregação (`AVG()`) para calcular a duração média.
- Lidar com valores nulos ou casos sem gravações.

---

### ✅ **Questão 04 - Cantores que nunca gravaram com a Sony**
Selecionar os cantores que **nunca gravaram** com a gravadora **Sony**.

**Desafio:**  
- Utilizar subconsultas ou `LEFT JOIN` com `NOT EXISTS` para excluir cantores que tenham gravações na Sony.

---

### ✅ **Questão 05 - Gravações do ano de 2004**
Selecionar:
- Nome das músicas
- Nome dos cantores
- Datas de gravação  
Apenas para gravações feitas no ano de **2004**.

**Desafio:**  
- Filtrar os registros corretamente usando `YEAR(data_gravacao) = 2004`.

---

### ✅ **Questão 06 - Última gravação dos cantores**
Selecionar o nome de cada cantor e a data da **última gravação**, em ordem decrescente de data. Se o cantor não tiver gravações, a data deve aparecer em branco.

**Desafio:**  
- Utilizar `MAX(data_gravacao)` para encontrar a última gravação.
- Exibir **nulo** (`NULL`) para cantores sem gravações.

---

### ✅ **Questão 07 - Telefones por Pessoa**
Dadas as tabelas **pessoa** e **fone**, retornar os números de **telefone residencial, comercial e celular** de cada pessoa em uma única linha.

**Desafio:**  
- Fazer a junção correta das tabelas.
- Exibir os telefones separados em colunas distintas.

---

## ⚠️ Regras Importantes

- **É proibido usar `LIMIT`** para ajustar os resultados.
- As consultas devem ser **executáveis no MariaDB** sem alterações.
- O código SQL deve estar devidamente comentado, identificando cada questão.
