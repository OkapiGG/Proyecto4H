/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Login;
import vista.VistaInicioSesion;
import vista.VistaPatronInicioSesion;

/**
 *
 * @author alancervantes
 */
public class ControladorVistaInicioSesion implements ActionListener {

    private VistaInicioSesion objVistaInicioSesion;
    private String perfilSeleccionado;

    public ControladorVistaInicioSesion(VistaInicioSesion objVistaInicioSesion) {
        this.objVistaInicioSesion = objVistaInicioSesion;
        this.objVistaInicioSesion.jButton1.addActionListener(this);
        this.objVistaInicioSesion.jButton2.addActionListener(this);
        this.objVistaInicioSesion.jButton3.addActionListener(this);
        this.objVistaInicioSesion.jButton4.addActionListener(this);
        this.objVistaInicioSesion.jButton5.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objVistaInicioSesion.jButton1) {
            perfilSeleccionado = "barco";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: barco ✅");
        }
        if (e.getSource() == this.objVistaInicioSesion.jButton2) {
            perfilSeleccionado = "sol";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: sol ✅");
        }
        if (e.getSource() == this.objVistaInicioSesion.jButton3) {
            perfilSeleccionado = "mariposa";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: mariposa ✅");
        }
        if (e.getSource() == this.objVistaInicioSesion.jButton4) {
            perfilSeleccionado = "arbol";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: arbol ✅");
        }
        if (e.getSource() == this.objVistaInicioSesion.jButton5) {
            if (perfilSeleccionado != null) {
                Login nuevoUsuario = new Login();
                nuevoUsuario.setPerfil(perfilSeleccionado);
                VistaPatronInicioSesion objVistaPatron = new VistaPatronInicioSesion(perfilSeleccionado);
                objVistaPatron.setVisible(true);
                objVistaInicioSesion.dispose();
                System.out.println(perfilSeleccionado);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona primero un perfil antes de Confirmar.");
            }
        }
    }
}
