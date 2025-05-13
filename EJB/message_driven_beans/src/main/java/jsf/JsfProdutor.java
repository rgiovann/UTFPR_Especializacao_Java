/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package jsf;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Iterator;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author default
 */
@Named(value = "jsfProdutor")
@RequestScoped
public class JsfProdutor {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java/Fila")
    private Queue fila;

    /**
     * Creates a new instance of JsfProdutor
     */
    public JsfProdutor() {
    }

    @Getter
    @Setter
    private String mensagem;

    @Getter
    @Setter
    private String mensagemFeedback; // Nova propriedade para feedback

    public void send() {
        FacesContext facesContext = FacesContext.getCurrentInstance(); // Get FacesContext statically
        if (mensagem == null || mensagem.trim().isEmpty()) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Mensagem não pode ser vazia"));
            return;
        }
        try ( JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(fila, mensagem);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Mensagem enviada com sucesso!"));
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Falha ao enviar mensagem: " + e.getMessage()));
        }
    }

    public void processarDireto() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (mensagem == null || mensagem.trim().isEmpty()) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Mensagem não pode ser vazia"));
            return;
        }

        try {
            // Simula processamento direto e bloqueante
            System.out.println("Início do processamento direto...");
            Thread.sleep(5000);
            System.out.println("Processamento finalizado da mensagem: " + mensagem);

            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Processamento direto finalizado!"));

        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Falha no processamento direto: " + e.getMessage()));
        }
    }

    public void limpar() {
        this.mensagem = "";

        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Remove todas as mensagens manualmente
        Iterator<FacesMessage> it = facesContext.getMessages();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }

        // Também limpa possíveis mensagens em flash scope
        facesContext.getExternalContext().getFlash().clear();
    }

}
