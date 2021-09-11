/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unbosque.reto_5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sergiohh
 */
public class Control implements ActionListener {

    private ServiEstudiante servicio;
    private VistaGUI vista;
    private Estudiante estudiante, estudGuardar;
    private int opcionMenu, opcionMenuCon;
    private String nombres, apellidos, fechaNac, correoInst, correoPerso;
    private long numCelular, numFijo;
    private String programa;
    private List<Estudiante> listaEstud;
    private String correoBuscar;
    boolean proceso;
    
    private String correo = "";
    private String fecha = "";
    private long celular = 0;
    
    int continuar = 0;

    public Control(VistaGUI vista, ServiEstudiante servicio) {
        this.vista = vista;
        this.servicio = servicio;
        // menu 
        this.vista.jMopcion1.addActionListener(this);
        this.vista.jMopcion3.addActionListener(this);
        this.vista.jMopcion4.addActionListener(this);
        this.vista.jMopcion5.addActionListener(this);
        // modificarEstudiante
        this.vista.jMCopcion1.addActionListener(this);
        this.vista.jMCopcion2.addActionListener(this);
        this.vista.jMCopcion3.addActionListener(this);
        this.vista.jMCopcion4.addActionListener(this);
        this.vista.jMCopcion5.addActionListener(this);
        this.vista.jMCopcion6.addActionListener(this);
        
        
        
    }
    public void actionPerformed(ActionEvent e){
        //menu
        if( e.getSource() == this.vista.jMopcion1 ){
            this.ingresarEstudiante();
        }
        if( e.getSource() == this.vista.jMopcion3 ){
            this.modificarEstudiante();
        }
        if( e.getSource() == this.vista.jMopcion4 ){
            this.eliminarEstudiante();
        }
        if( e.getSource() == this.vista.jMopcion5 ){
            this.verDirectorio();
        }
        //consulta
        if( e.getSource() == this.vista.jMCopcion1 ){
            this.buscarPorCorreo();
        }
        if( e.getSource() == this.vista.jMCopcion2 ){
            this.buscarPorApellido();
        }
        if( e.getSource() == this.vista.jMCopcion3 ){
            this.buscarPorPrograma();
        }
        if( e.getSource() == this.vista.jMCopcion4 ){
            this.buscarcantidad();
        }
        if( e.getSource() == this.vista.jMCopcion5 ){
            this.buscarFNacimiento();
        }
        if( e.getSource() == this.vista.jMCopcion6 ){
            this.buscarPorCelular();
        }
       
        
        
    }


    /**
     * si encuetra la base de datos muestra en pantalla el menu y en base a la
     * opcion que el usuario digita realiza el proceso correspondiente:( 1.
     * Ingresar estudiante 2. consultas 3. Modificar estudiante 4. Eliminar
     * Estudiante 5. Ver directorio de estudiantes 6. Salir)
     *
     */
    public void Inicio() {

        if (!this.servicio.pruebaConecion()) {
            this.mostrarMensaje(
                    " error: no se ha establecido conexion con la base de datos ");
        } else {
      
            vista.setVisible(true);
            
           
        }

    }

   
    //--------------------------------------metodos menu bar------------------
    //-------------------------------menu---------------------------------
    public void ingresarEstudiante(){
        estudGuardar = new Estudiante();
                        this.mostrarMensaje("Ingresar estudiante");
                        nombres = this.pedirString("Ingresar nombres:");
                        apellidos = this.pedirString("Ingresar apellidos: ");
                        fechaNac = this.pedirString("Ingresar fecha de nacimiento (YYYY-MM-DD):");
                        correoInst = this.pedirString("Ingresar correo institucional:");
                        correoPerso = this.pedirString("Ingresar correo personal:");
                        numCelular = this.pedirLong(
                                    "Ingresar número de celular:");
                        numFijo = this.pedirLong(
                                    "Ingresar número fijo:");
                    
                        programa = this.pedirString("Ingresar programa:");

                        estudGuardar.setNombres(nombres);
                        estudGuardar.setApellidos(apellidos);
                        estudGuardar.setFechaNacim(fechaNac);
                        estudGuardar.setCorreoInstitu(correoInst);
                        estudGuardar.setCorreoPersonal(correoPerso);
                        estudGuardar.setNumCelular(numCelular);
                        estudGuardar.setNumFijo(numFijo);
                        estudGuardar.setPrograma(programa);

                        servicio.guardarEstudiante(estudGuardar);
    
    }
    public void modificarEstudiante(){
        correoBuscar = this.pedirString(
                                "Ingresar correo institucional:");
                        correoPerso = this.pedirString("Ingresar correo personal:");
                        do {
                            numCelular = this.pedirLong(
                                    "Ingresar número de celular:");
                            if (numCelular != -1) {
                                break;
                            }
                        } while (true);
                        do {
                            numFijo = this.pedirLong(
                                    "Ingresar número fijo:");
                            if (numFijo != -1) {
                                break;
                            }
                        } while (true);
                        programa = this.pedirString("Ingresar programa:");

                        proceso = servicio.actualizarEstud(correoBuscar, correoPerso,
                                numCelular, numFijo, programa);
                        if (proceso) {
                            this.mostrarMensaje("Se modificó el estudiante");
                        } else {
                            this.mostrarMensaje(
                                    "El estudiante no se encuentra registrado en "
                                    + "el instituto");
                        }

    
    }
    public void eliminarEstudiante(){
        
                        String correoElim;
                        correoElim = this.pedirString(
                                "Ingresar correo institucional:");
                        proceso = servicio.eliminarEstud(correoElim);
                        if (proceso) {
                            this.mostrarMensaje("Se eliminó el estudiante");
                        } else {
                            this.mostrarMensaje(
                                    "El estudiante no se encuentra registrado en "
                                    + "el instituto");
                        }
    
    }
    public void verDirectorio(){
        listaEstud = new ArrayList<>();
                        listaEstud = this.servicio.leerTodos();

                        if (!listaEstud.isEmpty()) {

                            imprimirEstudiantes(listaEstud);
                        } else {
                            this.mostrarMensaje("lista vacia ");

                        }
        
    }
    //--------------------------- consultas --------------------------------
    
    public void buscarPorCorreo(){
                correo = this.pedirString(" Ingrese correo institucional:");
                estudiante = this.servicio.buscarCorreo(correo);
                if (estudiante == null) {
                    this.mostrarMensaje("no se encontro al estudiante");
                } else {
                    imprimirEstud(estudiante);
                }
    }
    public void buscarPorApellido(){
         listaEstud = new ArrayList();
                apellidos = this.pedirString(" ingrese apellidos completos: ");
                listaEstud = this.servicio.buscarApellido(apellidos);
                if (listaEstud == null) {
                    this.mostrarMensaje("no se ha podido encontrar el estudionte ");
                } else {
                    if (listaEstud.isEmpty()) {
                        this.mostrarMensaje(" no se encontraron estudiantes "
                                + "con los apellidos " + apellidos);
                    } else {

                        imprimirEstudiantes(listaEstud);
                    }
                }
    }
    public void buscarPorPrograma(){
        List<String> listaNombres = new ArrayList();
                programa = this.pedirString(" ingrese programa ");
                listaNombres = this.servicio.buscarPrograma(programa);

                if (listaNombres == null) {
                    this.mostrarMensaje(" no se ha podido conectar con la base de datos ");
                } else {
                    if (listaNombres.isEmpty()) {
                        this.mostrarMensaje(" no se han encontrado estudiantes en el programa " 
                                + programa);
                    }

                    for (int i = 0; i < listaNombres.size(); i += 2) {
                        this.mostrarMensaje("Nombre : " + listaNombres.get(i + 1) 
                                + " " + listaNombres.get(i));

                    }
                }
    }
    public void buscarcantidad(){
         int cantidad = -1;
                programa = this.pedirString(" ingrese programa: ");
                cantidad = this.servicio.cantEstudiantes(programa);
                if (cantidad == -1) {
                    this.mostrarMensaje("no se encontro estudiantes en el programa" 
                            + programa);
                } else {
                    this.mostrarMensaje(" cantidad de estudiantes en el"
                            + " programa de " + programa + " es de "
                            + String.valueOf(cantidad));
                }
    }
    public void buscarFNacimiento(){
        listaEstud = new ArrayList();
                fecha = this.pedirString(" ingresar la fecha: ");
                listaEstud = this.servicio.buscarFecha(fecha);
                if (listaEstud == null) {

                    this.mostrarMensaje("no se pudo acceder a la base de datos");
                } else {
                    if (listaEstud.isEmpty()) {
                        this.mostrarMensaje(" no se encontraron estudiantees con fecha " 
                                + fecha);
                    } else {
                        imprimirEstudiantes(listaEstud);
                    }
                }
    }
    public void buscarPorCelular(){
     List<String> lEncontrados = null;
                celular = this.pedirLong("ingrese el numero de celular ");
                lEncontrados = this.servicio.buscarCelular(celular);
                if (lEncontrados == null) {
                    this.mostrarMensaje(" no fue posible acceder a la base de datos ");
                } else {
                    if (lEncontrados.isEmpty()) {
                        this.mostrarMensaje("no se encontraron estudiantes con el numero "
                                + celular);
                    } else {
                        for (int i = 0; i < lEncontrados.size(); i += 2) {
                            this.mostrarMensaje("nombre: " + lEncontrados.get(i) 
                                    + " programa: " + lEncontrados.get(i + 1));
                        }
                    }   
    }
    }
    
    //--------------------------metodos generales ----------------------------

    /**
     * {@code imprimirEstud} muestra por medio de la vista los atributos del
     * objeto Estudiante que recibe
     *
     * @param estudMostrar
     */
    public void imprimirEstud(Estudiante estudMostrar) {
        if (estudMostrar != null) {
//            this.mostrarMensaje("Nombres: " + estudMostrar.getNombres()
//                    + "\nApellidos: " + estudMostrar.getApellidos()
//                    + "\nFecha nacimiento: " + estudMostrar.getFechaNacim()
//                    + "\nCorreo institucional: " + estudMostrar.getCorreoInstitu()
//                    + "\nCorreo personal: " + estudMostrar.getCorreoPersonal()
//                    + "\nNúmero de teléfono celular: "
//                    + String.valueOf(estudMostrar.getNumCelular())
//                    + "\nNúmero de teléfono fijo: "
//                    + String.valueOf(estudMostrar.getNumFijo())
//                    + "\nPrograma académico: " + estudMostrar.getPrograma());
            
            this.vista.jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {estudMostrar.getNombres(), 
                 estudMostrar.getApellidos(), 
                 estudMostrar.getFechaNacim(), 
                 estudMostrar.getCorreoInstitu(), 
                 estudMostrar.getCorreoPersonal(), 
                 estudMostrar.getNumCelular(), 
                 estudMostrar.getNumFijo(), 
                 estudMostrar.getPrograma()},
                
            },
            new String [] {
                "Nombres", "Apellidos", "F nacimiento", "Correo I", "Correo P", "celular", "fijo", "Programa"
            }
        ));

        }
        
    }
    public void imprimirEstudiantes(List<Estudiante> listaEstudiantes){
        int resultados = 0;
        resultados = listaEstudiantes.size();
        
        
        String [][] matriz = new String[resultados][8];
        
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            estudiante = new Estudiante();
            estudiante = listaEstudiantes.get(i);
            
            matriz[i][0] = estudiante.getNombres();
            matriz[i][1] = estudiante.getApellidos();
            matriz[i][2] = estudiante.getFechaNacim();
            matriz[i][3] = estudiante.getCorreoInstitu();
            matriz[i][4] = estudiante.getCorreoPersonal();
            matriz[i][5] = Long.toString( estudiante.getNumCelular() );
            matriz[i][6] = Long.toString( estudiante.getNumFijo());
            matriz[i][7] = estudiante.getPrograma();   
        }
        
        this.vista.jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Nombres", "Apellidos", "F nacimiento", "Correo I", "Correo P", "celular", "fijo", "Programa"
            }
        ));
   
        
        
    }
    private void mostrarMensaje(String mensaje){
    
        JOptionPane.showMessageDialog(null, mensaje);
    }
    private int pedirInt(String mesage){
        int respuesta;
        try {
            respuesta = Integer.valueOf( JOptionPane.showInputDialog(mesage));
        } catch (Exception e) {
            respuesta = -1;
        }
    return respuesta;
    }
    private String pedirString(String mensaje){
        String respuesta;
        respuesta = JOptionPane.showInputDialog(mensaje);
        return respuesta;
        
    }
    private long pedirLong(String mensaje){
        long respuesta;
        try {
            respuesta = Long.valueOf(JOptionPane.showInputDialog(mensaje));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error valor invalido");
            respuesta = -1;
        }
        
    return respuesta;
    }

  

}
