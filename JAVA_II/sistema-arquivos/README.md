# Sistema de Arquivos Virtual em Java

## Descrição Técnica

### Objetivo

Desenvolver uma aplicação de console para manipulação de um sistema de
arquivos virtual utilizando recursos nativos de I/O do Java. **_Este projeto é uma atividade  da disciplina Java II  da UTFPR (Especialização). _**O objetivo é fixar o aprendizado da API de IO do Java através da construção de um sistema de arquivos virtual em linha de comando.

### Funcionalidades do Sistema
#### Comandos Implementados

- **SHOW**:
  - Exibe conteúdo de arquivos de texto (extensão .txt)
  - Restrição: Não funciona para diretórios
- **LIST**:
  - Lista conteúdo do diretório atual
- **BACK**:
  - Retorna ao diretório pai
  - Restrição: Não permite navegação acima do diretório raiz
- **OPEN**:
  - Acessa um diretório específico
- **DETAIL**:
  - Recupera metadados de arquivos e diretórios
  - Utiliza `BasicFileAttributeView` do pacote NIO2

### Requisitos Técnicos

- Utilização exclusiva de bibliotecas padrão do JDK
- Tratamento robusto de erros
- Implementação seguindo boas práticas da linguagem Java

### Tecnologias

- Linguagem: Java
- APIs:
  - java.nio.file
  - java.io
- Paradigma: Orientação a Objetos

### Desafios Técnicos

- Manipulação de sistemas de arquivos
- Tratamento de exceções de I/O
- Implementação de navegação entre diretórios
- Recuperação de metadados de arquivos
