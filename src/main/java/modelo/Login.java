/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author Alan
 */
public class Login {

    private String perfil;
    private String patron;
    private static int idUsuarioActivo;

    public Login(String username, String contrasena) {
        this.perfil = username;
        this.patron = contrasena;
    }

    public Login() {

    }

    // Getters y setters
    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public static int getIdUsuarioActivo() {
        return idUsuarioActivo;
    }

    public static void setIdUsuarioActivo(int id) {
        idUsuarioActivo = id;
    }

}
