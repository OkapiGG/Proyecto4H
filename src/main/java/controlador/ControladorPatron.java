package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Login;
import vista.MenuInicio;
import vista.VistaRegistro;
import vista.VistaPatron;

public class ControladorPatron implements ActionListener {

    private OperacionesBDLogin objOperacionesBDLogin;
    private VistaPatron objVistaPatron;
    private String perfilSeleccionado; 
    private String patronSeleccionado = "";

    public ControladorPatron(VistaPatron objVistaPatron, String perfilSeleccionado) {
        this.objVistaPatron = objVistaPatron;
        this.objOperacionesBDLogin = new OperacionesBDLogin();
        this.perfilSeleccionado = perfilSeleccionado;
        this.objVistaPatron.jButton1.addActionListener(this); // gato
        this.objVistaPatron.jButton2.addActionListener(this); // carro
        this.objVistaPatron.jButton3.addActionListener(this); // estrella
        this.objVistaPatron.jButton4.addActionListener(this); // buho
        this.objVistaPatron.jButton5.addActionListener(this); // balon
        this.objVistaPatron.jButton6.addActionListener(this); // arcoiris
        this.objVistaPatron.jButton7.addActionListener(this); // confirmar
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objVistaPatron.jButton1) {
            patronSeleccionado += "gato,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatron.jButton2) {
            patronSeleccionado += "carro,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatron.jButton3) {
            patronSeleccionado += "estrella,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatron.jButton4) {
            patronSeleccionado += "buho,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatron.jButton5) {
            patronSeleccionado += "balon,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatron.jButton6) {
            patronSeleccionado += "arcoiris,";
            System.out.println("Patrón actual: " + patronSeleccionado);
        }
        if (e.getSource() == objVistaPatron.jButton7) {
            if (!patronSeleccionado.isEmpty()) {
                if (patronSeleccionado.endsWith(",")) {
                    patronSeleccionado = patronSeleccionado.substring(0, patronSeleccionado.length() - 1);
                }

                
                String[] elementos = patronSeleccionado.split(",");
                if (elementos.length >= 2) {
                    Login nuevoUsuario = new Login();
                    nuevoUsuario.setPerfil(perfilSeleccionado);
                    nuevoUsuario.setPatron(patronSeleccionado);

                    objOperacionesBDLogin.setObjLogin(nuevoUsuario);
                    objOperacionesBDLogin.create();

                    JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
                    
                    MenuInicio menuInicio = new MenuInicio();
                    menuInicio.setVisible(true);
                    this.objVistaPatron.dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar mínimo 2 imágenes para tu patrón.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar mínimo 2 imágenes para tu patrón.");
            }
        }
    } 
    
}
