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

public class ModeloPalabra {

    private Connection conexion;

    public ModeloPalabra(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Palabra> obtenerPalabras() {
        List<Palabra> lista = new ArrayList<>();
        try {
            
            CallableStatement cs = conexion.prepareCall("{? = call obtener_palabras()}");
            cs.registerOutParameter(1, Types.OTHER);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                Palabra p = new Palabra();
                p.setId(rs.getInt("idpalabra"));
                p.setPalabra(rs.getString("palabra"));
                p.setImagen(rs.getString("imagen"));   
                lista.add(p);
            }

            rs.close();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
