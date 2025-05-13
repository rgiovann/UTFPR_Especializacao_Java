/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author default
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java/Fila"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
}
)
public class EjbConsumidor implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        System.out.print("Mensagem recebida: ");
        try {
            TextMessage tm = (TextMessage) msg;
            System.out.println(tm.getText());

            // Simula tarefa custosa
            Thread.sleep(5000);
            System.out.println("Processamento finalizado da mensagem: " + tm.getText());

        } catch (JMSException | InterruptedException e) {
            System.out.println("ERRO");
            System.out.println(e.getMessage());
        }
    }

}
