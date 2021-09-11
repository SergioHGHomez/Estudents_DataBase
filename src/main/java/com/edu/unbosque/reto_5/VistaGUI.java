/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unbosque.reto_5;

import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author sergiohh
 */
public class VistaGUI extends JFrame {
        JScrollPane jScrollPane1; 
        JTable jTable1 ;
        JMenuBar jMenuBar1;
        JMenu jMMenu;
        JMenuItem jMopcion1 ;
        JMenuItem jMopcion2 ;
        JMenuItem jMopcion3 ;
        JMenuItem jMopcion4 ;
        JMenuItem jMopcion5 ;
        JMenu jMConsultas ;
        JMenuItem jMCopcion1 ;
        JMenuItem jMCopcion2 ;
        JMenuItem jMCopcion3 ;
        JMenuItem jMCopcion4 ;
        JMenuItem jMCopcion5 ;
        JMenuItem jMCopcion6 ;
    
        public VistaGUI(){
            iniciar();
        }
    private void iniciar(){
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMMenu = new javax.swing.JMenu();
        jMopcion1 = new javax.swing.JMenuItem();
        jMopcion3 = new javax.swing.JMenuItem();
        jMopcion4 = new javax.swing.JMenuItem();
        jMopcion5 = new javax.swing.JMenuItem();
        jMConsultas = new javax.swing.JMenu();
        jMCopcion1 = new javax.swing.JMenuItem();
        jMCopcion2 = new javax.swing.JMenuItem();
        jMCopcion3 = new javax.swing.JMenuItem();
        jMCopcion4 = new javax.swing.JMenuItem();
        jMCopcion5 = new javax.swing.JMenuItem();
        jMCopcion6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
               
                
            },
            new String [] {
                "Nombres", "Apellidos", "F nacimiento", "Correo I", "Correo P", "celular", "fijo", "Programa"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jMMenu.setText("Menu");

        jMopcion1.setText("Ingresar estudiante");
        
        jMMenu.add(jMopcion1);

        jMopcion3.setText("Modificar estudiante ");
        jMMenu.add(jMopcion3);

        jMopcion4.setText("Eliminar Estudiante ");
        jMMenu.add(jMopcion4);

        jMopcion5.setText("Ver directorio ");
        jMMenu.add(jMopcion5);

        jMenuBar1.add(jMMenu);

        jMConsultas.setText("Consultas");

        jMCopcion1.setText("por correo");
        jMConsultas.add(jMCopcion1);

        jMCopcion2.setText("por apellidos ");
        jMConsultas.add(jMCopcion2);

        jMCopcion3.setText("por programa");
        jMConsultas.add(jMCopcion3);

        jMCopcion4.setText("cantidad por programa");
        jMConsultas.add(jMCopcion4);

        jMCopcion5.setText("por fecha de nacimiento");
        jMConsultas.add(jMCopcion5);

        jMCopcion6.setText("por celular");
        jMConsultas.add(jMCopcion6);

        jMenuBar1.add(jMConsultas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );

        pack();
        
    }
}
