/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unbosque.reto_5;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergiohh
 */
public class ServiEstudiante {

    private String URL;
    private Estudiante estudianteAux;//objeto temporal temporarl
    private List<Estudiante> listaEstud = new ArrayList<>(); // en esta lista 
    // se guardan temporalmente los datos que se encuentran en la base de datos

    //costructores
    /**
     * este constructor establece la ruta en la que se encuentra la base de
     * datos
     */
    public ServiEstudiante() {
        this.URL = "jdbc:sqlite:bd_estudiantes.db";
    }

    /**
     * este constructor genera permite el cambio del nombre o ruta de la base de
     * dates sqlite
     *
     * @param nombreArchivo
     */
    public ServiEstudiante(String URL) {
        this.URL = "jdbc:sqlite:" + URL;
    }

    //---------------------- metodos menu principal ----------------------------
    /**
     * este metodo verifica que se pueda conectar con la base de datos
     *
     * @return {@code true } si logra la conexion
     */
    public boolean pruebaConecion() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rst = null;
        String name = "";
        try {
            conn = DriverManager.getConnection(URL);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ServiEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * este metodo se encarga de realizar la conexion para que los demas metodos
     * puedan utilizar la base de datos
     *
     * @return {@code Connection}
     */
    private Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);

        } catch (Exception e) {
            System.out.println("no se pudo conectar con la base de datos"
                    + e.getMessage());
        }
        return conn;
    }

    /**
     * lee todos los registros de la base de datos y retorna una lista de
     * objetos de clase Estudiante
     *
     * @return {@code List<Estudiante>}
     */
    public List<Estudiante> leerTodos() {
        String query = "SELECT * FROM estudiantes";

        List<Estudiante> listaTemporal = new ArrayList<>();

        try ( Connection connec = this.conectar();  Statement stmt = connec.createStatement();) {
            ResultSet result = stmt.executeQuery(query);
            if (result == null) {
                System.out.println("es null");

            }

            while (result.next()) {
                estudianteAux = new Estudiante();
                estudianteAux.setNombres(result.getString("nombres"));
                estudianteAux.setApellidos(result.getString("apellidos"));
                estudianteAux.setFechaNacim(result.getString("fechaNacim"));
                estudianteAux.setCorreoInstitu(result.getString("correoInstitu"));
                estudianteAux.setCorreoPersonal(result.getString("correoPersonal"));
                estudianteAux.setNumCelular(result.getLong("numCelular"));
                estudianteAux.setNumFijo(result.getLong("numFijo"));
                estudianteAux.setPrograma(result.getString("programa"));

                listaTemporal.add(estudianteAux);

            }
            connec.close();

        } catch (Exception e) {
            System.out.println("can't read " + e.getMessage());
            e.getStackTrace();
        }

        return listaTemporal;

    }

    /**
     * recibe y guarda un objeto Estudiante en la base de datos
     *
     * @param estudGuard
     * @return true si logra guardar el objeto Estudiante
     */
    public boolean guardarEstudiante(Estudiante estudGuard) {
        boolean guardado = false;
        String query = "INSERT INTO Estudiantes (nombres, apellidos, fechaNacim,"
                + " correoInstitu, correoPersonal, numCelular, numFijo, programa)"
                + "				VALUES(?,?,?,?,?,?,?,?)";

        try (
                 Connection connect = conectar();  PreparedStatement pstmt
                = connect.prepareStatement(query);) {

            pstmt.setString(1, estudGuard.getNombres());
            pstmt.setString(2, estudGuard.getApellidos());
            pstmt.setString(3, estudGuard.getFechaNacim());
            pstmt.setString(4, estudGuard.getCorreoInstitu());
            pstmt.setString(5, estudGuard.getCorreoPersonal());
            pstmt.setLong(6, estudGuard.getNumCelular());
            pstmt.setLong(7, estudGuard.getNumFijo());
            pstmt.setString(8, estudGuard.getPrograma());

            pstmt.executeUpdate();
            guardado = true;
            connect.close();

        } catch (Exception e) {
            System.out.println("no se logro guardar en la base de datos "
                    + e.getMessage());
        }
        return guardado;

    }

    /**
     * recibe el correo institucional y elimina al Estudiante de la base de
     * datos
     *
     * @param correoInstitu
     * @return true si logro eliminar al estudiante
     */
    public boolean eliminarEstud(String correoInstitu) {
        boolean eliminado = false;
        String query = "DELETE FROM Estudiantes WHERE correoInstitu = ?";

        try (
                 Connection connect = conectar();
                PreparedStatement pstmt = connect.prepareStatement(query);) {
            pstmt.setString(1, correoInstitu);
            pstmt.executeUpdate();
            eliminado = true;
            connect.close();

        } catch (Exception e) {
            System.out.println("no se ha podido guardar " + e.getMessage());
        }
        return eliminado;

    }

    /**
     * recibe el correo institucional para realizar la busqueda, y los
     * parametros correo personal, numero de celular , numero fijo , nombre del
     * programa, para actulizar al estudiante en la base de datos
     *
     * @param correoInstitu
     * @param correoP
     * @param numCelular
     * @param numFijo
     * @param nomPrograma
     * @return true si logro actualizar al objeto Estudiante
     */
    public boolean actualizarEstud(String correoInstitu, String correoP,
            long numCelular, long numFijo, String nomPrograma) {
        boolean actualizado = false;
        String query = "UPDATE Estudiantes "
                + "SET correoPersonal = ?,\n"
                + "numCelular = ? , "
                + "numFijo = ? ,"
                + "programa = ?\n"
                + "WHERE correoInstitu = ?";
        try (
                 Connection connect = conectar();  PreparedStatement pstmt = connect.prepareStatement(query);) {
            pstmt.setString(1, correoP);
            pstmt.setLong(2, numCelular);
            pstmt.setLong(3, numFijo);
            pstmt.setString(4, nomPrograma);
            pstmt.setString(5, correoInstitu);
            pstmt.executeUpdate();
            actualizado = true;
            connect.close();

        } catch (Exception e) {
            System.out.println("no fue posible actualizar" + e.getMessage());
        }

        return actualizado;
    }

    //---------------------- metodos menu consultas ---------------------------
    /**
     * busca por correo intitucional en la base de datos y devuelve todos los
     * datos del estudiante
     *
     * @param correoBuscar
     * @return {@code Estudiante} si lo encuentra caso contrario retorna
     * {@code null}
     */
    public Estudiante buscarCorreo(String correoBuscar) {
        //nombres, apellidos, fechaNacim, correoInstitu, correoPersonal, numCelular, numFijo, programa
        String query = "SELECT * FROM Estudiantes WHERE correoInstitu = '" + correoBuscar + "'";

        estudianteAux = new Estudiante();
        ;
        try (
                 Connection connect = conectar();  Statement pstmt = connect.createStatement();) {
            ResultSet result = pstmt.executeQuery(query);
            estudianteAux.setNombres(result.getString("nombres"));
            estudianteAux.setApellidos(result.getString("apellidos"));
            estudianteAux.setFechaNacim(result.getString("fechaNacim"));
            estudianteAux.setCorreoInstitu(result.getString("correoInstitu"));
            estudianteAux.setCorreoPersonal(result.getString("correoPersonal"));
            estudianteAux.setNumCelular(result.getLong("numCelular"));
            estudianteAux.setNumFijo(result.getLong("numFijo"));
            estudianteAux.setPrograma(result.getString("programa"));

            connect.close();
        } catch (Exception e) {
            System.out.println("no se ha podido buscar " + e.getMessage());
            estudianteAux = null;
        }

        return estudianteAux;
    }

    /**
     * busca en en la base de datos por apellido y regresa todos los resultados
     * que encuentra
     *
     * @param apellido
     * @return {@code List<Estudiante>}
     */

    public List<Estudiante> buscarApellido(String apellido) {

        listaEstud = new ArrayList();

        String query = "SELECT * FROM Estudiantes WHERE apellidos = '" + apellido + "'";

        try (
                 Connection connect = conectar();  Statement stmt = connect.createStatement();) {
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                estudianteAux = new Estudiante();
                estudianteAux.setNombres(result.getString("nombres"));
                estudianteAux.setApellidos(result.getString("apellidos"));
                estudianteAux.setFechaNacim(result.getString("fechaNacim"));
                estudianteAux.setCorreoInstitu(result.getString("correoInstitu"));
                estudianteAux.setCorreoPersonal(result.getString("correoPersonal"));
                estudianteAux.setNumCelular(result.getLong("numCelular"));
                estudianteAux.setNumFijo(result.getLong("numFijo"));
                estudianteAux.setPrograma(result.getString("programa"));

                listaEstud.add(estudianteAux);
            }

        } catch (Exception e) {
            System.out.println("no se pudo encontrar a los estudiantes " + e.getMessage());
            listaEstud = null;
        }

        return listaEstud;
    }

    /**
     * busca en la base de datos por programa y regresa una lista con nombres y
     * apellidos que halla encontrado
     *
     * @param programa
     * @return {@code List<String>}
     */

    public List<String> buscarPrograma(String programa) {
        List<String> listaNombres = new ArrayList();
        String query = "SELECT nombres, apellidos from Estudiantes "
                + "WHERE programa = '" + programa + "'";

        try ( Connection connect = conectar();  Statement stmt = connect.createStatement();) {
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                listaNombres.add(result.getString("nombres"));
                listaNombres.add(result.getString("apellidos"));
            }

            if (connect != null) {

                try {
                    connect.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            System.out.println("no se ha podido buscar " + e.getMessage());
            listaNombres = null;
        }
        return listaNombres;
    }

    /**
     * busca en la base de datos por programa y regresa la cantidad de
     * estudiantes que se encuentran en es programa
     *
     * @param programa
     * @return {@code int} con el numero de estudiantes
     */
    public int cantEstudiantes(String programa) {
        int cantidad = -1;
        String query = "select count(idEstudiante) as 'cantidad' from Estudiantes"
                + " WHERE programa = '" + programa + "'";

        try ( Connection connect = conectar();  Statement stmt = connect.createStatement();) {
            ResultSet result = stmt.executeQuery(query);
            cantidad = result.getInt("cantidad");

        } catch (Exception e) {
            System.out.println("ocurrio un error con la base de datos " + e.getMessage());

        }
        return cantidad;
    }

    /**
     * busca en la base de datos y retorna los estudiantes que tengan registrada
     * esa fecha de nacimiento
     *
     * @param fecha
     * @return {@code List<Estudiante>}
     */
    public List<Estudiante> buscarFecha(String fecha) {
        listaEstud = new ArrayList();
        String query = "SELECT * FROM Estudiantes WHERE fechaNacim = '" + fecha + "'";

        try ( Connection connect = conectar();  Statement stmt = connect.createStatement();) {
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                estudianteAux = new Estudiante();
                estudianteAux.setNombres(result.getString("nombres"));
                estudianteAux.setApellidos(result.getString("apellidos"));
                estudianteAux.setFechaNacim(result.getString("fechaNacim"));
                estudianteAux.setCorreoInstitu(result.getString("correoInstitu"));
                estudianteAux.setCorreoPersonal(result.getString("correoPersonal"));
                estudianteAux.setNumCelular(result.getLong("numCelular"));
                estudianteAux.setNumFijo(result.getLong("numFijo"));
                estudianteAux.setPrograma(result.getString("programa"));

                listaEstud.add(estudianteAux);

            }
        } catch (Exception e) {
            System.out.println("error al buscar por fechas " + e.getMessage());
            listaEstud = null;
        }
        return listaEstud;
    }

    /**
     * busca por numero de celular en la base de datos y retorna nombre y
     * programa de los estudiantes que tengan ese numero
     *
     * @param celular
     * @return {@code List<String>} con nombres y programas
     */
    public List<String> buscarCelular(long celular) {
        List<String> listaEncontrados = new ArrayList();
        String query = "SELECT nombres, programa from Estudiantes WHERE numCelular = " + celular;

        try ( Connection connect = conectar();  Statement stmt = connect.createStatement();) {
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                listaEncontrados.add(result.getString("nombres"));
                listaEncontrados.add(result.getString("programa"));
            }

            if (connect != null) {

                try {
                    connect.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            System.out.println("no se ha podido buscar " + e.getMessage());
            listaEncontrados = null;
        }
        return listaEncontrados;
    }

    // getters y setters
    //------------------------------------------------------------------------
    /**
     * @return un {@code ArrayList} con los objetos Estudiantes que esten
     * guardados
     */
    public List<Estudiante> getListaEstud() {
        return listaEstud;
    }

    /**
     * asigna listaEstud a this.listaEstud
     *
     * @param listaEstud
     */
    public void setListaEstud(ArrayList<Estudiante> listaEstud) {
        this.listaEstud = listaEstud;
    }

}
