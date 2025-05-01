/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bri;

import javax.ejb.Remote;

/**
 *
 * @author default
 */

@Remote
public interface CalcInterface {
    public double somar(double a, double b);
    public double subtrair(double a, double b);
    public double multiplicar(double a, double b);
    public double dividir(double a, double b);
    
}
