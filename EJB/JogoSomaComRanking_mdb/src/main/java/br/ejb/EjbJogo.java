/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package br.ejb;

import br.data.model.Jogador;
import br.data.model.Resposta;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * EJB com estado que gerencia um jogo simples de soma para um jogador por vez.
 * 
 * A classe {@code EjbJogo} representa um bean com estado ({@code @Stateful}), ou seja, 
 * mantém os dados da sessão de um jogador individual, como seu nome, a resposta atual do jogo 
 * e o ranking acumulado durante sua sessão.
 *
 *  Funcionalidades principais: 
 *  
 *   Gerar duas somas aleatórias para o jogador resolver. 
 *   Verificar se a resposta do jogador está correta. 
 *   Gerenciar o ranking de jogadores e suas pontuações. 
 *   Resetar ou limpar os dados da sessão quando necessário. 
 *  
 * Autor: Giovanni L. Rozza
 * Versão: 1.0
 * Desde: 27-05-2025
 */
 
@Stateful
public class EjbJogo implements EjbJogoLocal {

    private ArrayList<Jogador> ranking;
    private Resposta respostaAtual;
    private Random random;
    private String jogadorAtual;

    public EjbJogo() {
        ranking = new ArrayList<>();
        random = new Random();
        gerarNovaResposta();
    }

    @Override
    public void setJogadorAtual(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return;  
        }
        this.jogadorAtual = nome.trim();

        gerarNovaResposta();
    }

    @Override
    public String getJogadorAtual() {
        return jogadorAtual;
    }

    @Override
    public void verificarResposta(int somaUsuario) {
        if (jogadorAtual == null || jogadorAtual.trim().isEmpty()) {
            return;
        }
        respostaAtual.setSomaUsuario(somaUsuario);
        boolean acertou = (respostaAtual.getNumero1() + respostaAtual.getNumero2()) == somaUsuario;
        respostaAtual.setAcertou(acertou);
        if (acertou) {
             
            Jogador jogadorExistente = null;
            for (Jogador jogador : ranking) {
                if (jogador.getNome().equals(jogadorAtual)) {
                    jogadorExistente = jogador;
                    break;
                }
            }
            if (jogadorExistente != null) {
                 
                jogadorExistente.setPontuacao(jogadorExistente.getPontuacao() + 1);
            } else {
                 
                ranking.add(new Jogador(jogadorAtual));
                ranking.get(ranking.size() - 1).setPontuacao(1);
            }
        }

    }
    
    @Override
    public void gerarNovaResposta() {
        int numero1 = random.nextInt(9) + 1; // 1 a 9
        int numero2 = random.nextInt(9) + 1; // 1 a 9
        respostaAtual = new Resposta(numero1, numero2);
    }

    @Override
    public ArrayList<Jogador> getRanking() {
        ArrayList<Jogador> rankingOrdenado = new ArrayList<>(ranking);
        Collections.sort(rankingOrdenado, Comparator.comparingInt(Jogador::getPontuacao).reversed());
        return rankingOrdenado;
    }

    @Override
    public Resposta getRespostaAtual() {
 
        return respostaAtual;
    }

    @Override
    public void limparRanking() {
        ranking.clear();
        jogadorAtual = null;
        gerarNovaResposta();
    }

    @Override
    public Resposta peekRespostaAtual() {
        return respostaAtual; 
    }

}
