package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Login;
import vista.VistaRegistro;
import vista.VistaPatron;

public class ControladorRegistro implements ActionListener {

    private VistaRegistro objVistaInicioSesion;
    private String perfilSeleccionado; 

    public ControladorRegistro(VistaRegistro objVistaInicioSesion) {
        this.objVistaInicioSesion = objVistaInicioSesion;

        this.objVistaInicioSesion.jButton1.addActionListener(this); // barco
        this.objVistaInicioSesion.jButton2.addActionListener(this); // sol
        this.objVistaInicioSesion.jButton3.addActionListener(this); // mariposa
        this.objVistaInicioSesion.jButton4.addActionListener(this); // árbol
        this.objVistaInicioSesion.jButton5.addActionListener(this); // botón para confirmar registro
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objVistaInicioSesion.jButton1) {
            perfilSeleccionado = "barco";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: barco");
        }
        if (e.getSource() == objVistaInicioSesion.jButton2) {
            perfilSeleccionado = "sol";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: sol");
        }
        if (e.getSource() == objVistaInicioSesion.jButton3) {
            perfilSeleccionado = "mariposa";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: mariposa");
        }
        if (e.getSource() == objVistaInicioSesion.jButton4) {
            perfilSeleccionado = "arbol";
            JOptionPane.showMessageDialog(null, "Perfil seleccionado: arbol");
        }
        if (e.getSource() == objVistaInicioSesion.jButton5) {
            if (perfilSeleccionado != null) {
                Login nuevoUsuario = new Login();
                nuevoUsuario.setPerfil(perfilSeleccionado);

                VistaPatron objVistaPatron = new VistaPatron(perfilSeleccionado);
                objVistaPatron.setVisible(true);
                objVistaInicioSesion.dispose();
              
                System.out.println(perfilSeleccionado);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona primero un perfil antes de registrar.");
            }
        }
    }
}
