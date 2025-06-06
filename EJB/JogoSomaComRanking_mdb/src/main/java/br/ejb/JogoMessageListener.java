package br.ejb;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Classe Message-Driven Bean (MDB) responsável por processar mensagens
 * JMS recebidas de uma fila configurada no servidor de aplicação.
 *
 * Esta classe escuta mensagens da fila chamada "java/Fila",
 * do tipo javax.jms.Queue, utilizando a interface MessageListener.
 * Quando uma nova mensagem do tipo TextMessage é recebida,
 * o conteúdo textual é impresso no console.
 *
 * Essa classe é útil para propósitos de logging, auditoria
 * ou comunicação assíncrona entre componentes distribuídos da aplicação.
 *
 * Importante: o processamento de mensagens ocorre fora do
 * contexto da requisição HTTP, portanto o uso de escopos como @SessionScoped
 * ou dependências de estado do usuário não se aplicam aqui.
 *
 * Autor: Giovanni L. Rozza
 * Versão: 1.0
 * Desde: 27-05-2025
 */

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java/Fila"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
}, messageListenerInterface = MessageListener.class)
public class JogoMessageListener implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        try {
            TextMessage tm = (TextMessage) msg;
            System.out.println("Mensagem recebida: " + tm.getText());
        } catch (JMSException e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}

