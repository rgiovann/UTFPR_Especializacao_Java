/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data.model;
/**
 * Representa um jogador no jogo de soma, armazenando o nome e a pontuação acumulada.
 * 
 * @author Giovanni L. Rozza
 * @version 1.0
 * @since 2025-05-08
 */
import lombok.Data;

@Data
public class Jogador {
    private String nome;
    private int pontuacao;

    public Jogador() {
    }

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }
}
