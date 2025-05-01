/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author default
 */
@Stateless
public class EjbCalculadora implements bri.CalcInterface {

    @Override
    public double somar(double a, double b) {
        return a + b;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public double subtrair(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplicar(double a, double b) {
        return a * b;
    }

    @Override
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("O denominador não pode ser zero.");
        }
        return a / b;
    }
}
