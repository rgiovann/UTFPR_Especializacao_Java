# 🧮 Jogo Soma com Ranking

Projeto desenvolvido como atividade prática para a disciplina de **Java EJB** da **UTFPR**, com o objetivo de aplicar os conceitos de desenvolvimento corporativo com **Jakarta EE**, incluindo componentes EJB e mensageria assíncrona com JMS.

## 📌 Descrição

Este projeto implementa um **jogo simples de soma**, onde os jogadores devem somar corretamente dois números inteiros aleatórios. A aplicação registra as tentativas e contabiliza a pontuação dos jogadores, exibindo um ranking ao final.

O diferencial do projeto é o uso de **mensageria assíncrona com JMS** e **Message-Driven Bean (MDB)** para **detecção automática de mudança de líder no ranking**, garantindo que o sistema possa reagir de forma desacoplada e escalável a eventos relevantes do jogo.

## 🎯 Objetivos da Atividade

* Aplicar EJBs de sessão e de mensageria (Message-Driven Beans).
* Modelar entidades de domínio utilizando Lombok.
* Processar eventos de jogo de forma reativa usando **filas JMS**.
* Exibir informações sobre o **líder atual**, sempre que houver mudança.
* Desenvolver uma aplicação web distribuída com componentes Jakarta EE.

## 🛠️ Tecnologias Utilizadas

* **Java 8**
* **Jakarta EE 8 (incluindo EJB, JMS)**
* **JMS (Java Message Service)**
* **Lombok**
* **Maven**
* **Payara Server**
* **WAR packaging**

## 🔄 Detecção de Novo Líder com MDB

O projeto implementa um **Message-Driven Bean (MDB)** que consome mensagens de uma **fila JMS** configurada no servidor. Toda vez que um jogador realiza uma tentativa e sua pontuação é atualizada, a aplicação verifica se houve **mudança no líder do jogo**. Se sim, uma nova **mensagem é enviada para a fila**, e o MDB a consome.

A função principal do MDB é **informar automaticamente o novo líder** da partida. Isso permite que a aplicação tenha uma arquitetura orientada a eventos, podendo futuramente se expandir para notificações em tempo real, integração com dashboards, ou logging assíncrono de eventos de destaque.

### ✔️ Lógica do MDB

* Escuta uma fila JMS (ex: `queue/liderJogo`).
* Sempre que há mudança no jogador com maior pontuação, uma mensagem é enviada à fila.
* O MDB consome a mensagem e **informa o novo líder**.
* Isso garante que o sistema saiba **em tempo real quem está liderando a partida**, sem bloqueio ou acoplamento entre as camadas do jogo.

## 📁 Estrutura do Projeto

* `br.data.model.Jogador`: armazena o nome e a pontuação atual do jogador.
* `br.data.model.Resposta`: representa uma tentativa de soma e seu resultado.
* `br.data.mdb.LiderMessageBean`: o MDB que processa mensagens com dados do novo líder.
* `rest/...`: camada REST que expõe endpoints para interação com o jogo.
* `pom.xml`: configuração Maven com todas as dependências e plugins necessários.

## 🚀 Execução

1. Configure um servidor de aplicação com suporte a Jakarta EE e JMS (GlassFish, Payara).
2. Registre a **fila JMS** usada no MDB (por exemplo, `queue/liderJogo`).
3. Compile e empacote o projeto:

   ```bash
   mvn clean package
   ```
4. Faça o deploy do `.war` gerado no servidor.
5. Acesse a aplicação via navegador e jogue normalmente.
6. Quando um novo líder for identificado, o MDB processará a mensagem e informará quem está liderando.

## 📊 Exemplo de Funcionamento

1. Jogador A acerta 3 somas e lidera.
2. Jogador B joga e ultrapassa a pontuação de A.
3. A aplicação envia uma mensagem JMS com os dados do novo líder.
4. O **Message-Driven Bean** consome a mensagem e **exibe/loga/informa o novo líder** do jogo.

