# Projeto EJB - CalcInterface & CalcWeb

Este projeto foi desenvolvido como parte da disciplina CETEJ6 - Enterprise Java Beans (EJB) da UTFPR, com o objetivo de criar uma aplicação web que realiza operações matemáticas básicas utilizando EJBs Stateless e a tecnologia JSF (JavaServer Faces).

## Escopo do Projeto

O sistema consiste em duas aplicações Java integradas:

### CalcInterface (Aplicação Java)
- Projeto que define a interface remota da calculadora.
- Contém a interface `CalcInterface` anotada com `@Remote`.
- Declara os seguintes métodos:
  - `double somar(double a, double b)`
  - `double subtrair(double a, double b)`
  - `double multiplicar(double a, double b)`
  - `double dividir(double a, double b)`

### CalcWeb (Aplicação Web)
- Projeto principal que consome a interface remota `CalcInterface`.
- Componentes:
  - EJB Stateless que implementa os métodos da interface.
  - ManagedBean JSF (`@Named`) responsável por receber os valores digitados e exibir os resultados.
  - Página XHTML para entrada de dados e exibição dos resultados.

## Funcionalidades
- Entrada de dois números (A e B) via interface web.
- Cálculo e exibição dos resultados de:
  - Soma
  - Subtração
  - Multiplicação
  - Divisão
- Tratamento de exceções, como tentativa de divisão por zero.

## Tecnologias Utilizadas
- Java EE (Jakarta EE)
- EJB Stateless
- JSF (JavaServer Faces)
- Apache NetBeans 21
- Payara Server 6.2025.3
- Java 11

## Ambiente de Desenvolvimento

A aplicação foi desenvolvida em um ambiente virtualizado utilizando Docker com Kasm Workspaces, o qual já incluía:
- Apache NetBeans com suporte completo a Java EE
- Payara Server configurado
- JSF pré-instalado

Esse ambiente facilitou o setup rápido e padronizado para desenvolvimento e testes.

## Observações
- A aplicação foi desenvolvida seguindo rigorosamente as premissas do enunciado:
  - Estrutura de projetos com nomes `CalcInterface` e `CalcWeb`.
  - Uso da API `jakarta.*` conforme exigido.
  - Compatibilidade com o ambiente NetBeans + Payara.
  - Código testado e funcional, com deploy bem-sucedido no servidor de aplicação.
