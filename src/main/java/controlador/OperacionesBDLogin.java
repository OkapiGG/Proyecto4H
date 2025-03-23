/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.CRUD;
import modelo.Login;
import java.util.ArrayList;
import modelo.ConexionBD;

public class OperacionesBDLogin extends CRUD {

    public Login objLogin;
    private Connection conexion;

    public OperacionesBDLogin() {
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void create() {
        try {
            System.out.println("Insercion a la BD a traves de la funcion");
            CallableStatement cs = conexion.prepareCall("{ call insertar_usuario(?, ?) }");
            cs.setString(1, objLogin.getUsername());
            cs.setString(2, objLogin.getContrasena());
            cs.execute();
        } catch (Exception e) {
            System.out.println("Error al insertar en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public ArrayList read() {
        return null;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Login getObjLogin() {
        return objLogin;
    }

    public void setObjLogin(Login objLogin) {
        this.objLogin = objLogin;
    }

}
