#### Projeto JPA Consultas - Spring Boot, JPA e Docker

##### Este repositório contém um projeto desenvolvido em Spring Boot que utiliza Spring Data JPA para consultas em um banco de dados MariaDB, rodando como um container Docker. O projeto explora diferentes tipos de consultas utilizando JPQL, NamedQuery e Native Query, seguindo boas práticas de desenvolvimento.

##### Tecnologias Utilizadas

- ##### _Java 17 (ou versão compatível)_
- ##### _Spring Boot_
- ##### _Spring Data JPA_
- ##### _MariaDB (via Docker)_
- ##### _Maven_
- ##### _Lombok (para reduzir boilerplate)_
- ##### _Hibernate (implementação JPA)_

##### Funcionalidades Implementadas

##### O projeto implementa consultas JPA para um cenário fictício de Funcionários e Departamentos, incluindo:

- ##### Listagem de funcionários por nome e quantidade de dependentes.
- ##### Busca de funcionários por departamento usando JPQL via _@Query_.
- ##### Consulta do primeiro departamento cadastrado.
- ##### Identificação do funcionário com o maior salário.
- ##### Listagem dos três funcionários com os maiores salários.
- ##### Consulta de funcionários sem dependentes, ordenados alfabeticamente.
- ##### Consultas usando JPQL e Native Query para buscar funcionários com salários acima de um valor determinado.
- ##### Uso de _@NamedQuery_ e _@NamedNativeQuery_ para consultas reutilizáveis.

##### Aprendizados e Desafios

- ##### Implementação programática da configuração do Spring Boot.
- ##### Uso de queries JPQL e Native Query para otimizar a recuperação de dados.
- ##### Utilização de Docker para facilitar a configuração do banco de dados.
