/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.jsf;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;
import bri.CalcInterface;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author default
 */
@Named(value = "jsfCalculadora")
@RequestScoped
public class JsfCalculadora {

    @EJB
    private CalcInterface ejbCalculadora;

    /**
     * Creates a new instance of JsfCalculadora
     */
    public JsfCalculadora() {
    }

    public void calcularTudo() {
        resultadoSoma = ejbCalculadora.somar(valora, valorb);
        resultadoSubtracao = ejbCalculadora.subtrair(valora, valorb);
        resultadoMultiplicacao = ejbCalculadora.multiplicar(valora, valorb);
        try {
            resultadoDivisao = ejbCalculadora.dividir(valora, valorb);
        } catch (EJBException e) {
            // Verificar se a causa é uma IllegalArgumentException (divisão por zero)
            if (e.getCause() instanceof IllegalArgumentException) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Denominador b de a/b não pode ser zero.", null));
            } else {
                // Caso seja outra exceção, exibir uma mensagem genérica
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar a divisão: " + e.getMessage(), null));
            }
            resultadoDivisao = 0.0; // Resetar o valor para evitar exibição de resultado inválido
        }
    }

    @Setter
    @Getter
    private double valora;
    @Setter
    @Getter
    private double valorb;
    @Getter
    private double resultadoSoma;
    @Getter
    private double resultadoSubtracao;
    @Getter
    private double resultadoDivisao;
    @Getter
    private double resultadoMultiplicacao;
}
