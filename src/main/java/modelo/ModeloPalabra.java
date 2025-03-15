/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Alan
 */


public class ModeloPalabra {

    private Connection conexion;

    public ModeloPalabra() {
        try {
            this.conexion = ConexionBD.getInstancia().getConexion(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Palabra> obtenerPalabras() {
        List<Palabra> lista = new ArrayList<>();
        try {
            CallableStatement cs = conexion.prepareCall("{? = call obtener_palabras()}");
            cs.registerOutParameter(1, Types.OTHER);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Palabra objPalabra = new Palabra();
                objPalabra.setId(rs.getInt("idpalabra"));
                objPalabra.setPalabra(rs.getString("palabra"));
                objPalabra.setImagen(rs.getString("imagen"));
                lista.add(objPalabra);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

