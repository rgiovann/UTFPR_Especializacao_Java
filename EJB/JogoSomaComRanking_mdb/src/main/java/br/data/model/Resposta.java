/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data.model;
/**
 * Representa uma tentativa de resposta em um jogo de soma.
 * 
 * Esta classe armazena os dois números que devem ser somados,
 * o valor informado pelo usuário como resposta, e um indicador
 * booleano que informa se a resposta foi correta ou não.
 * 
 *
 * @author Giovanni L. Rozza
 * @version 1.0
 * @since 2025-05-08
 */
import lombok.Data;

@Data
public class Resposta {
    private int numero1;
    private int numero2;
    private int somaUsuario;
    private boolean acertou;

    public Resposta() {
    }

    public Resposta(int numero1, int numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.somaUsuario = 0;
        this.acertou = false;
    }
}
