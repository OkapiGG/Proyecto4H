/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alan
 */
public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private final String URL = "jdbc:postgresql://localhost:5432/proyecto4h";
    private final String USUARIO = "adminapp";
    private final String CONTRASENA = "admin";
//    private final String CONTRASENA = "ema24";

    private ConexionBD() throws SQLException {
        try {//el try para manejar errores que puedan surgui en la conexion
            Class.forName("org.postgresql.Driver");
            this.conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Error al cargar el driver de PostgreSQL", ex);
        }
    }

    //Get para obtener la conexion en otras clases
    public Connection getConexion() {
        return conexion;
    }

    //Singleton
    public static ConexionBD getInstancia() throws SQLException {
        if (instancia == null || instancia.getConexion().isClosed()) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

}
