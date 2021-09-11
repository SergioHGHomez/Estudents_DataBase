/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unbosque.reto_5;

import java.io.Serializable;

/**
 *
 * @author sergiohh
 */
public class Estudiante implements Serializable {

    private String nombres;
    private String apellidos;
    private String fechaNacim;
    private String correoInstitu;
    private String correoPersonal;
    private long numCelular;
    private long numFijo;
    private String programa;

    @Override
    public String toString() {
        return "\nNombres :" + nombres
                + "Apellidos=" + apellidos
                + "Fecha Nacimiento=" + fechaNacim
                + "Correo institucional=" + correoInstitu
                + "Correo personal=" + correoPersonal
                + "Numero celular=" + numCelular
                + "Numero fijo=" + numFijo
                + "Programa=" + programa;
    }

    /**
     * @param este constructor genera un objeto Estudiante en blanco
     */
    public Estudiante() {

        nombres = "";
        apellidos = "";
        fechaNacim = "";
        correoInstitu = "";
        correoPersonal = "";
        numCelular = 0;
        numFijo = 0;
        programa = "";
    }

    /**
     * este constructor genera un objeto con los parametros que se le envian al
     * invocarl
     *
     * @param nombres
     * @param apellidos
     * @param fechaNacim
     * @param correoIntitu
     * @param correoPersonal
     * @param numCelular
     * @param numFijo
     * @param programa
     */
    public Estudiante(
            String nombres, String apellidos, String fechaNacim,
            String correoIntitu, String correoPersonal, long numCelular,
            long numFijo, String programa) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacim = fechaNacim;
        this.correoInstitu = correoIntitu;
        this.correoPersonal = correoPersonal;
        this.numCelular = numCelular;
        this.numFijo = numFijo;

    }

    /**
     *
     * @return nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * asigna nombres a this.nombres de la clase Estudiantes
     *
     * @param nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     *
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * asigna apellidos a this.apellidos de la clase Estudiantes
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *
     * @return fechaNacim
     */
    public String getFechaNacim() {
        return fechaNacim;
    }

    /**
     * asigna fechaNacim a this.fechaNacim de la clase Estudiantes
     *
     * @param fechaNacim
     */
    public void setFechaNacim(String fechaNacim) {
        this.fechaNacim = fechaNacim;
    }

    /**
     *
     * @return CorreoInstitu
     */
    public String getCorreoInstitu() {
        return correoInstitu;
    }

    /**
     * asigna correoInstitu a this.setCorreoInstitu de la clase Estudiantes
     *
     * @param correoInstitu
     */
    public void setCorreoInstitu(String correoInstitu) {
        this.correoInstitu = correoInstitu;
    }

    /**
     *
     * @return correoPersonal
     */
    public String getCorreoPersonal() {
        return correoPersonal;
    }

    /**
     * asigna correoPersonal a this.correoPersonal de la clase Estudiantes
     *
     * @param correoPersonal
     */
    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    /**
     *
     * @return numCelular
     */
    public long getNumCelular() {
        return numCelular;
    }

    /**
     * asigna numCelular a this.numCelular de la clase Estudiantes
     *
     * @param numCelular
     */
    public void setNumCelular(long numCelular) {
        this.numCelular = numCelular;
    }

    /**
     *
     * @return numFijo
     */
    public long getNumFijo() {
        return numFijo;
    }

    /**
     * asigna numFijo a this.numFijo de la clase Estudiantes
     *
     * @param numFijo
     */
    public void setNumFijo(long numFijo) {
        this.numFijo = numFijo;
    }

    /**
     *
     * @return getPrograma
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * asigna programa a this.programa de la clase Estudiantes
     *
     * @param programa
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }
}
