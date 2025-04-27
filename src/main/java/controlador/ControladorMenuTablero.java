/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.ConexionBD;
import modelo.ProgresoJugador;
import modelo.Login;
import vista.*;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;

public class ControladorMenuTablero implements ActionListener {

    private MenuTablero objMenuTablero;
    private ProgresoJugador progresoJugador;
    private Map<String, JButton> mapaBotones;

    public ControladorMenuTablero(MenuTablero objMenuTablero) {
        this.objMenuTablero = objMenuTablero;
        this.progresoJugador = new ProgresoJugador();

        registrarListeners();
        inicializarMapaBotones();
        cargarProgresoJugador();
        actualizarEstadoBotones();
    }

    private void registrarListeners() {
        objMenuTablero.jButton1.addActionListener(this);
        objMenuTablero.jButton2.addActionListener(this);
        objMenuTablero.jButton3.addActionListener(this);
        objMenuTablero.jButton4.addActionListener(this);
        objMenuTablero.jButton5.addActionListener(this);
        objMenuTablero.jButton6.addActionListener(this);
        objMenuTablero.jButton7.addActionListener(this);
        objMenuTablero.jButton8.addActionListener(this);
        objMenuTablero.jButton9.addActionListener(this);
        objMenuTablero.jButton10.addActionListener(this);
        objMenuTablero.jButton11.addActionListener(this);
        objMenuTablero.jButton12.addActionListener(this);
        objMenuTablero.jButton13.addActionListener(this);
    }

    private void inicializarMapaBotones() {
        mapaBotones = new HashMap<>();
        mapaBotones.put("pizza", objMenuTablero.jButton2);
        mapaBotones.put("perro", objMenuTablero.jButton3);
        mapaBotones.put("silla", objMenuTablero.jButton4);
        mapaBotones.put("hamburguesa", objMenuTablero.jButton5);
        mapaBotones.put("manzana", objMenuTablero.jButton6);
        mapaBotones.put("mesa", objMenuTablero.jButton7);
        mapaBotones.put("billete", objMenuTablero.jButton8);
        mapaBotones.put("foco", objMenuTablero.jButton9);
        mapaBotones.put("conejo", objMenuTablero.jButton10);
        mapaBotones.put("zapato", objMenuTablero.jButton11);
        mapaBotones.put("leon", objMenuTablero.jButton12);
        mapaBotones.put("cama", objMenuTablero.jButton13);
    }

    private void cargarProgresoJugador() {
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT obtenernivelactual(?)"
            );
            ps.setInt(1, Login.getIdUsuarioActivo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                progresoJugador.setNivelActual(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarEstadoBotones() {
        String[] niveles = {
            "pizza", "perro", "silla", "hamburguesa", "manzana",
            "mesa", "billete", "foco", "conejo", "zapato",
            "leon", "cama"
        };

        objMenuTablero.jButton1.setEnabled(true);

        boolean activar = true;

        for (String nivel : niveles) {
            JButton boton = mapaBotones.get(nivel);

            if (activar) {
                boton.setEnabled(true);
                boton.setContentAreaFilled(false);
                boton.setForeground(Color.BLACK);    
            } else {
                boton.setEnabled(false);
                boton.setContentAreaFilled(true);    
                boton.setBackground(new Color(192, 192, 192));
                boton.setForeground(Color.DARK_GRAY); 
            }

            if (nivel.equals(progresoJugador.getNivelActual())) {
                activar = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objMenuTablero.jButton1) {
            MenuInicio objMenuInicio = new MenuInicio();
            objMenuInicio.setVisible(true);
            objMenuTablero.dispose();
        }
        if (e.getSource() == objMenuTablero.jButton2) {
            abrirNivel(new TCartaPizza());
        }
        if (e.getSource() == objMenuTablero.jButton3) {
            abrirNivel(new TCartaPerro());
        }
        if (e.getSource() == objMenuTablero.jButton4) {
            abrirNivel(new TCartaSilla());
        }
        if (e.getSource() == objMenuTablero.jButton5) {
            abrirNivel(new TCartaHamburguesa());
        }
        if (e.getSource() == objMenuTablero.jButton6) {
            abrirNivel(new TCartaManzana());
        }
        if (e.getSource() == objMenuTablero.jButton7) {
            abrirNivel(new TCartaMesa());
        }
        if (e.getSource() == objMenuTablero.jButton8) {
            abrirNivel(new TCartaBillete());
        }
        if (e.getSource() == objMenuTablero.jButton9) {
            abrirNivel(new TCartaFoco());
        }
        if (e.getSource() == objMenuTablero.jButton10) {
            abrirNivel(new TCartaConejo());
        }
        if (e.getSource() == objMenuTablero.jButton11) {
            abrirNivel(new TCartaZapato());
        }
        if (e.getSource() == objMenuTablero.jButton12) {
            abrirNivel(new TCartaLeon());
        }
        if (e.getSource() == objMenuTablero.jButton13) {
            abrirNivel(new TCartaCama());
        }
    }

    private void abrirNivel(javax.swing.JFrame carta) {
        carta.setVisible(true);
        objMenuTablero.dispose();
    }
}
