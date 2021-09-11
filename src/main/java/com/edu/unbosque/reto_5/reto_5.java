/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unbosque.reto_5;

/**
 *
 * @author sergiohh
 */
public class reto_5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        VistaGUI vis = new VistaGUI();
        ServiEstudiante servi = new ServiEstudiante();
        
        Control control = new Control( vis , servi);
        control.Inicio();
    }
    
}
