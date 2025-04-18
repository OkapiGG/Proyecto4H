package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Login;
import vista.MenuInicio;
import vista.VistaRegistro;
import vista.VistaPatron;
import vista.VistaPatronInicioSesion;

public class ControladorPatronInicioSesion implements ActionListener {

    private OperacionesBDLogin objOperacionesBDLogin;
    private VistaPatronInicioSesion objVistaPatronInicioSesion;
    private String perfilSeleccionado;
    private String patronSeleccionado = "";

    public ControladorPatronInicioSesion(VistaPatronInicioSesion objVistaPatronInicioSesion, String perfilSeleccionado) {
        this.objVistaPatronInicioSesion = objVistaPatronInicioSesion;
        this.objOperacionesBDLogin = new OperacionesBDLogin();
        this.perfilSeleccionado = perfilSeleccionado;
        this.objVistaPatronInicioSesion.jButton1.addActionListener(this); // gato
        this.objVistaPatronInicioSesion.jButton2.addActionListener(this); // carro
        this.objVistaPatronInicioSesion.jButton3.addActionListener(this); // estrella
        this.objVistaPatronInicioSesion.jButton4.addActionListener(this); // buho
        this.objVistaPatronInicioSesion.jButton5.addActionListener(this); // balon
        this.objVistaPatronInicioSesion.jButton6.addActionListener(this); // arcoiris
        this.objVistaPatronInicioSesion.jButton7.addActionListener(this); // confirmar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objVistaPatronInicioSesion.jButton1) {
            patronSeleccionado += "gato,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatronInicioSesion.jButton2) {
            patronSeleccionado += "carro,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatronInicioSesion.jButton3) {
            patronSeleccionado += "estrella,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatronInicioSesion.jButton4) {
            patronSeleccionado += "buho,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatronInicioSesion.jButton5) {
            patronSeleccionado += "balon,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatronInicioSesion.jButton6) {
            patronSeleccionado += "arcoiris,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatronInicioSesion.jButton7) {
            if (!patronSeleccionado.isEmpty()) {
                if (patronSeleccionado.endsWith(",")) {
                    patronSeleccionado = patronSeleccionado.substring(0, patronSeleccionado.length() - 1);
                }

                String[] elementos = patronSeleccionado.split(",");
                if (elementos.length >= 2) {
                    Login objLogin = new Login();
                    objLogin.setPerfil(perfilSeleccionado);
                    objLogin.setPatron(patronSeleccionado);

                    objOperacionesBDLogin.setObjLogin(objLogin);
                    boolean loginExitoso = objOperacionesBDLogin.read();

                    if (loginExitoso) {
                        JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso!");
                            MenuInicio menuInicio = new MenuInicio();
                            menuInicio.setVisible(true);
                            objVistaPatronInicioSesion.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Perfil o patrón incorrecto. Intenta de nuevo.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar mínimo 2 imágenes para tu patrón.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar mínimo 2 imágenes para tu patrón.");
            }
        }

    }

}
