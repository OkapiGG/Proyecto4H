/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import modelo.ConexionBD;

public class OperacionesBDCuenta {

    private Connection conexion;

    public OperacionesBDCuenta() {
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerDatosCuenta(int idUsuario) {
        try {
            CallableStatement cs = conexion.prepareCall("{ call obtener_datos_usuario(?) }");
            cs.setInt(1, idUsuario);
            ResultSet rs = cs.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Error al obtener datos de la cuenta: " + e.getMessage());
            return null;
        }
    }

    public void actualizarPuntajeYPalabras(int idUsuario, int nuevosPuntos, int nuevasPalabras) {
        try {
            CallableStatement cs = conexion.prepareCall("{ call actualizar_puntaje_y_palabras(?, ?, ?) }");
            cs.setInt(1, idUsuario);
            cs.setInt(2, nuevosPuntos);
            cs.setInt(3, nuevasPalabras);
            cs.execute();
        } catch (Exception e) {
            System.out.println("Error al actualizar puntaje y palabras: " + e.getMessage());
        }
    }

}
