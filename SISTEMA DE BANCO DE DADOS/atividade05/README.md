# Atividade JPA - Manipular Dados e Transações

Este repositório contém a implementação da atividade prática de **JPA - Manipular Dados e Transações**, realizada na disciplina **Sistemas de Banco de Dados** da **Especialização em Linguagem Java da UTFPR**.

## Descrição da Atividade

A atividade consiste em aprimorar um projeto Java utilizando **Spring Boot** e **JPA (Java Persistence API)** para manipular dados e transações no banco de dados. As seguintes tarefas foram implementadas:

1. **Chamada de Stored Procedure**: Implementação de um método que chama uma stored procedure no banco de dados, a qual aumenta o salário de todos os funcionários em X%.
2. **Consulta com Parâmetros Nomeados**: Implementação de um método que lista todos os funcionários de um determinado departamento que não possuem dependentes.
3. **Atualização de Dados**: Implementação de uma operação `update` que transfere todos os funcionários de um departamento para outro, utilizando a anotação `@Modifying`.
4. **Exclusão de Registros**: Implementação de uma operação `delete` para remover todos os funcionários de um determinado departamento, utilizando `@Modifying`.
5. **Transação com `@Transactional`**: Implementação de um método na camada de serviço que salva um novo departamento, associa um funcionário a ele e persiste ambos na mesma transação.

## Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Banco de dados relacional (ex.: MySQL, PostgreSQL ou outro de escolha livre)

## Estrutura do Projeto

```
projeto-jpa
├── src/main/java/com/exemplo
│   ├── repository/      # Interfaces de persistência (Spring Data JPA)
│   ├── service/         # Camada de serviço contendo a lógica de negócio
│   ├── entity/          # Classes de entidade mapeadas com JPA
│   ├── config/          # Configuração do banco de dados e do Spring Boot
│   ├── main/            # Classe principal do Spring Boot
├── src/main/resources/
│   ├── application.properties  # Configuração do banco de dados
├── pom.xml             # Dependências do Maven
└── README.md           # Documentação do projeto
```

Este projeto faz parte da **Especialização em Linguagem Java** da **UTFPR** e tem como objetivo fortalecer o conhecimento em persistência de dados utilizando **Spring Boot e JPA**.

**Nota**: saida em console do projeto e stored procedure em src/main/resources
