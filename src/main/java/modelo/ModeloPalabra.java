/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alan
 */
public class ModeloPalabra {

    private Connection conexion;
    private ModeloGuardaPalabras objmodeloGuardaPalabras;

    public ModeloPalabra() {
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objmodeloGuardaPalabras = new ModeloGuardaPalabras(this);
    }

    public List<Palabra> cargarPalabrasComoObjetos() {
        List<Palabra> listaPalabras = new ArrayList<>();
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM obtener_palabras()"); //Se llama a la funcion
            while (rs.next()) {
                String palabraTexto = rs.getString(1);  // La función retorna solo el texto
                Palabra p = new Palabra();
                p.setPalabra(palabraTexto);
                listaPalabras.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPalabras;
    }

}
