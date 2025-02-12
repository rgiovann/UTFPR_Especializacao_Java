# Projeto JPA Consultas - Spring Boot, JPA e Docker

Este repositório contém um projeto desenvolvido em Spring Boot que utiliza Spring Data JPA para consultas em um banco de dados MariaDB, rodando como um container Docker. O projeto explora diferentes tipos de consultas utilizando JPQL, NamedQuery e Native Query, seguindo boas práticas de desenvolvimento.

## Tecnologias Utilizadas

1. **Java 17 (ou versão compatível)**

2. **Spring Boot**

3. **Spring Data JPA**

4. **MariaDB (via Docker)**

3. **Maven**

4. **Lombok (para reduzir boilerplate)**
   
3. **Maven**

4. **Hibernate (implementação JPA)**
       
## Funcionalidades Implementadas

- Listagem de funcionários por nome e quantidade de dependentes.
- Busca de funcionários por departamento usando JPQL via _@Query_
- Consulta do primeiro departamento cadastrado.
- Identificação do funcionário com o maior salário.
- Listagem dos três funcionários com os maiores salários.
- Consulta de funcionários sem dependentes, ordenados alfabeticamente.
- Consultas usando JPQL e Native Query para buscar funcionários com salários acima de um valor determinado.
- Uso de _@NamedQuery_ e _@NamedNativeQuery_ para consultas reutilizáveis.
  
## Aprendizados e Desafios

- Implementação programática da configuração do Spring Boot.
- Uso de queries JPQL e Native Query para otimizar a recuperação de dados.
- Utilização de Docker para facilitar a configuração do banco de dados.
