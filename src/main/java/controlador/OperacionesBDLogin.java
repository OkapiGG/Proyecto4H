/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
            System.out.println("Registro completo de Usuario");
            CallableStatement cs = conexion.prepareCall("{ call registrar_usuario(?, ?) }");
            cs.setString(1, objLogin.getPerfil());
            cs.setString(2, objLogin.getPatron());
            cs.execute();
            
            read();

        } catch (Exception e) {
            System.out.println("Error al registrar usuario y puntaje: " + e.getMessage());
        }
    }

    @Override
    public boolean read() {
        boolean encontrado = false;
        try {
            CallableStatement cs = conexion.prepareCall("{ call buscar_usuario(?, ?) }");
            cs.setString(1, objLogin.getPerfil());
            cs.setString(2, objLogin.getPatron());
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                encontrado = true;
                int idUsuario = rs.getInt("idusuario");
                Login.setIdUsuarioActivo(idUsuario);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return encontrado;
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
