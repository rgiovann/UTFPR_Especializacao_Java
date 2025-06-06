# Atividade – Comunicação Cliente-Servidor com Java Sockets

## 📚 Disciplina

**Java Redes** – Especialização em Java – UTFPR

## 👤 Autor

**Giovanni L. Rozza**

---

## 📝 Descrição da Atividade

Esta atividade propõe o desenvolvimento de uma aplicação baseada em arquitetura **cliente-servidor** utilizando **Java e Sockets TCP/IP**, com o objetivo de exercitar conceitos essenciais de programação em rede. O cliente possui uma interface gráfica feita com **Swing**, permitindo ao usuário preencher os dados de uma pessoa (nome e idade), que são enviados ao servidor como objetos serializados.

No servidor, cada conexão é tratada de forma **concorrente** em uma nova **thread**, permitindo o atendimento simultâneo de múltiplos clientes. O servidor processa o objeto recebido, imprime os dados no console e envia uma resposta textual ao cliente.

---

## 🎯 Aspectos do Java Exercitados

* Comunicação em rede com **Sockets TCP/IP**
* **Serialização de objetos** com `Serializable`
* **Programação concorrente** com `Thread` para múltiplas conexões
* **Interface gráfica** com Java **Swing** e `GridBagLayout`
* **Validação de entrada de dados** com `JFormattedTextField` e `NumberFormatter`
* Tratamento de exceções e uso de `try-with-resources`
* Organização do código com **separação de responsabilidades** (modelo, cliente, servidor)

---

## 🧩 Componentes do Projeto

### 1. `Pessoa.java`

Classe serializável que representa uma pessoa com nome e idade.

### 2. `Servidor.java`

Servidor TCP que:

* Escuta a porta `50000`
* Recebe objetos `Pessoa`
* Imprime os dados no console
* Envia mensagem de confirmação ao cliente
* Usa threads para tratar cada cliente individualmente

### 3. `FormCliente.java`

Aplicação cliente com interface gráfica:

* Permite entrada de nome e idade
* Valida os campos antes de enviar
* Envia o objeto `Pessoa` via socket
* Exibe a resposta do servidor em uma área de texto
* Ajusta automaticamente seu tamanho com base na resolução do usuário

---

## 🔧 Tecnologias Utilizadas

* Java SE 8+
* Java Swing (GUI)
* Sockets TCP (`java.net`)
* Threads
* Serialização (`Serializable`)
* `try-with-resources`, `Logger`

---

## ▶️ Como Executar

### Servidor

1. Compile e execute `Servidor.java`.

```bash
javac local/redes/Servidor.java
java local.redes.Servidor
```

### Cliente

1. Compile e execute `FormCliente.java`.

```bash
javac local/redes/FormCliente.java
java local.redes.FormCliente
```

⚠️ **O servidor deve estar em execução antes de iniciar o cliente.**

---

## ✅ Validações Implementadas

* Campo **nome** não pode estar vazio
* Campo **idade** deve conter apenas números inteiros ≥ 0
* Mensagens de erro amigáveis via `JOptionPane`

---

Se desejar, posso gerar uma versão `.md` formatada para uso em GitHub ou `.docx` para entrega. Deseja isso?
