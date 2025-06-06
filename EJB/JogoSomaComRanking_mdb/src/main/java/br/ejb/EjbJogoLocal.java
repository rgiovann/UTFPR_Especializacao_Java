/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ejb;

import br.data.model.Jogador;
import br.data.model.Resposta;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 * Interface local do EJB que define as operações disponíveis para o jogo de soma simples.
 *
 * Esta interface é anotada com @Local, o que significa que o bean associado será
 * acessível apenas dentro do mesmo módulo da aplicação (por exemplo, mesma aplicação web ou EAR).
 *
 * Responsabilidades da interface:
 * - Controlar o jogador atual da sessão.
 * - Gerar novos desafios de soma.
 * - Verificar a resposta do usuário e atualizar o ranking.
 * - Fornecer acesso ao ranking ordenado dos jogadores.
 * - Permitir a leitura da resposta atual e sua reinicialização.
 *
 * É implementada pela classe EjbJogo, um bean com estado (@Stateful),
 * onde cada instância mantém o estado individual da sessão de um jogador.
 *
 * Autor: Giovanni L. Rozza
 * Versão: 1.0
 * Desde: 27-05-2025
 */
@Local
public interface EjbJogoLocal {
    void setJogadorAtual(String nome);
    String getJogadorAtual();
    void verificarResposta(int somaUsuario);
    void gerarNovaResposta();
    ArrayList<Jogador> getRanking();
    Resposta getRespostaAtual();
    void limparRanking();
    public Resposta peekRespostaAtual();
}
