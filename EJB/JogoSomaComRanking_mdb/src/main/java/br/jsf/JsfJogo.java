/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.jsf;

import br.data.model.Jogador;
import br.data.model.Resposta;
import br.ejb.EjbJogoLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Bean JSF responsável por controlar a lógica de interação entre a interface
 * web e a lógica de negócio encapsulada em um EJB local, referente a um jogo
 * de soma aritmética simples.
 *
 * Este bean é anotado com @Named para disponibilização em páginas JSF
 * e com @SessionScoped para manter o estado da sessão do jogador.
 *
 * Funcionalidades principais:
 * - Inicializa um novo jogo com base no nome do jogador informado.
 * - Verifica a resposta enviada pelo jogador por meio do EJB.
 * - Controla o feedback visual apresentado ao usuário, como mensagens de acerto ou erro.
 * - Garante que o jogador não submeta múltiplas respostas para a mesma pergunta.
 * - Monitora mudanças no ranking e envia notificações JMS em caso de novo líder.
 *
 * Utiliza injeção de dependência via @Inject e @Resource
 * para acessar o EJB de negócio e recursos JMS, respeitando o princípio
 * da inversão de dependência (DIP) e promovendo baixo acoplamento entre camadas.
 *
 * Autor: Giovanni L. Rozza
 * Versão: 1.0
 * Desde: 27-05-2025
 */

@Named(value = "jsfJogo")
@SessionScoped
public class JsfJogo implements Serializable {

    @Inject
    private EjbJogoLocal ejbJogo;
    private String mensagemResultado = "";
    private boolean isAcertou = false;

    private String nomeJogador;
    private int somaUsuario;
    // previne jogador ficar "testando" resultados
    private boolean habilitarSoma = false;
    private Jogador ultimoLider = null;

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java/Fila")
    private Queue fila;

    public JsfJogo() {
    }

    public String getMensagemResultado() {
        return mensagemResultado;
    }

    public String getJogadorAtual() {
        return ejbJogo.getJogadorAtual();
    }

    public void setJogadorAtual() {
        if (nomeJogador != null && !nomeJogador.trim().isEmpty()) {
            ejbJogo.setJogadorAtual(nomeJogador);
            somaUsuario = 0;
            mensagemResultado = "";
            habilitarSoma = true;

        }
    }

    public void novoJogo() {
        if (ejbJogo.getJogadorAtual() != null && !ejbJogo.getJogadorAtual().trim().isEmpty()) {
            mensagemResultado = "";
            ejbJogo.gerarNovaResposta();
            somaUsuario = 0;
            habilitarSoma = true;
        } else {
            mensagemResultado = "Por favor, insira um nome primeiro e pressione <OK>!";
        }

    }

    public void verificarResposta() {
        if (!habilitarSoma) {
            return;
        }
        ejbJogo.verificarResposta(somaUsuario);
        Resposta ultimaResposta = ejbJogo.peekRespostaAtual();
        isAcertou = ultimaResposta.isAcertou();
        mensagemResultado = isAcertou ? "OK! Acertou!" : "NOK! Errou!";
        habilitarSoma = false;

    }

    private void verificaMudancaRanking() {
        boolean mudouRanking = false;
        String mensagem = "";

        // Obtem ranking atual ordenado (já vem ordenado do EJB)
        ArrayList<Jogador> rankingAtual = ejbJogo.getRanking();

        // Verifica se existe pelo menos um jogador no ranking
        if (!rankingAtual.isEmpty()) {
            Jogador novoLider = rankingAtual.get(0); // primeiro da lista tem maior pontuação

            if (ultimoLider == null || novoLider.getPontuacao() > ultimoLider.getPontuacao()) {
                mudouRanking = true;
                ultimoLider = novoLider; // atualiza o último líder
                mensagem = "Novo líder do ranking: " + novoLider.getNome() + " com " + novoLider.getPontuacao() + " pontos.";
            }
        }
        if (mudouRanking) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            try ( JMSContext context = connectionFactory.createContext()) {
                context.createProducer().send(fila, mensagem);
            } catch (Exception e) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Falha ao enviar mensagem ao servidor: " + e.getMessage()));
            }
        }
    }

    public ArrayList<Jogador> getRanking() {
        verificaMudancaRanking();
        return ejbJogo.getRanking();
    }

    public Resposta getRespostaAtual() {
        return ejbJogo.getRespostaAtual();
    }

    public boolean isHabilitarSoma() {
        return habilitarSoma;
    }

    public boolean respostaCorreta() {
        return isAcertou;
    }

    public void limparRanking() {
        ejbJogo.limparRanking();
        nomeJogador = "";
        somaUsuario = 0;
        mensagemResultado = "";
        habilitarSoma = false;

    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getSomaUsuario() {
        return somaUsuario;
    }

    public void setSomaUsuario(int somaUsuario) {
        this.somaUsuario = somaUsuario;
    }

}
