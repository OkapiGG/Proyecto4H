/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author alancervantes
 */
public class ModeloUsuario {

    private Connection conexion;

    public ModeloUsuario() {
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public void actualizarPuntajeYContador(int idusuario, int puntosIncremento, int contadorIncremento) {
        String sql = "SELECT actualizarpuntaje(?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idusuario);
            ps.setInt(2, puntosIncremento);
            ps.setInt(3, contadorIncremento);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
