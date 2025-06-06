
# JMS com Message-Driven Bean (MDB) no Jakarta EE 8

Este projeto demonstra, de forma prática e didática, como implementar um **Message-Driven Bean** para consumir mensagens de uma fila JMS (Java Message Service) de forma **assíncrona**. Ele também compara o modelo de chamada **síncrona** direta via JSF.

## 🧪 Tecnologias Utilizadas

- **Jakarta EE 8**
- **Payara Server**
- **JavaServer Faces (JSF)**
- **JMS (Java Message Service)**
- **Apache NetBeans IDE**

---

## 📌 Objetivo

O objetivo principal deste projeto é demonstrar os dois modelos de processamento de mensagens:

- ✅ **Assíncrono**: utilizando um `Message-Driven Bean` (MDB) para consumir e processar mensagens enviadas a uma fila JMS.
- ⛔ **Síncrono**: processamento direto do conteúdo no `Managed Bean`, bloqueando a resposta até a conclusão.

---

## 🔄 Diferença entre Processamento Síncrono e Assíncrono

### 🔁 Assíncrono (via JMS + MDB)

- ✅ O usuário envia a mensagem.
- ✅ O envio é **rápido**, não depende do processamento.
- ✅ O MDB (`EjbConsumidor`) consome a mensagem da fila e processa em **segundo plano**.
- ✅ Ideal para tarefas custosas ou que demoram.

### ⌛ Síncrono (processamento direto via método JSF)

- ⛔ O usuário envia e **espera** o processamento ser concluído.
- ⛔ A interface só é liberada após o término da operação.
- ⛔ Pode travar a aplicação em operações demoradas.

---

## 🧩 Estrutura do Projeto

```
src/
├── br.ejb.EjbConsumidor        # Message-Driven Bean - consumidor JMS
├── br.jsf.JsfProdutor          # Managed Bean JSF - envia mensagens
└── resources/index.xhtml       # Interface JSF com botões de envio
```

## 🔧 Como Funciona

### ✅ Envio Assíncrono
1. Usuário digita a mensagem na interface.
2. Ao clicar em **"Enviar Assíncrono"**, o `JsfProdutor` envia a mensagem para a fila JMS.
3. O `EjbConsumidor` é automaticamente ativado e processa a mensagem em background (com delay de 5 segundos simulado com `Thread.sleep(5000)`).

### ⛔ Processamento Síncrono
1. Ao clicar em **"Processar Direto (Síncrono)"**, o `JsfProdutor` processa diretamente a mensagem com `Thread.sleep(5000)`.
2. A interface fica bloqueada até a conclusão.

---

## 🔨 Como Executar

1. Importe o projeto no **Apache NetBeans**.
2. Certifique-se de que o **Payara Server** está configurado e rodando.
3. Crie a fila JMS no Payara com o nome **`java/Fila`**:
   - Vá em **Resources > JMS Resources > Destination Resources**.
   - Crie uma fila com:
     - JNDI Name: `java/Fila`
     - Resource Type: `javax.jms.Queue`
4. Faça o deploy do projeto.
5. Acesse `http://localhost:8080/<nome-do-projeto>/faces/index.xhtml`.

---

## 🧠 Conceitos Reforçados

- **Message-Driven Bean (MDB)**: EJB que consome mensagens de uma fila ou tópico de forma assíncrona.
- **JMSContext**: API simplificada para produção de mensagens JMS no Jakarta EE.
- **JSF Managed Beans**: Integração de backend e frontend em aplicações web EE.
- **Simulação de carga**: Uso de `Thread.sleep()` para simular tarefas demoradas.

---

## 📚 Referências

- [Jakarta EE 8 Documentation](https://jakarta.ee/specifications/platform/8/)
- [JMS 2.0 Specification](https://jakarta.ee/specifications/messaging/2.0/)
- [Payara Server Documentation](https://docs.payara.fish/)
- [JSF (Jakarta Faces)](https://projects.eclipse.org/projects/ee4j.faces)

---

## 🧑‍🏫 Observação Didática

Este projeto é ideal para alunos e desenvolvedores iniciantes que desejam compreender, na prática, o impacto entre chamadas síncronas e assíncronas em aplicações corporativas, usando os recursos da especificação Jakarta EE.

---

## ✉️ Contato

Este projeto foi desenvolvido com fins educacionais. Em caso de dúvidas ou sugestões, sinta-se à vontade para contribuir ou entrar em contato.

