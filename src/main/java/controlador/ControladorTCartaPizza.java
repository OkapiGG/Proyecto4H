/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaPizza;

/**
 *
 * @author alancervantes
 */
public class ControladorTCartaPizza implements MouseListener {

    private TCartaPizza objTCartaPizza;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaPizza(TCartaPizza objTCartaPizza) {
        this.objTCartaPizza = objTCartaPizza;
        this.objTCartaPizza.jButton1.addMouseListener(this);
        this.objTCartaPizza.jButton2.addMouseListener(this);
        this.objTCartaPizza.jButton3.addMouseListener(this);
        this.objTCartaPizza.jButton4.addMouseListener(this);
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == this.objTCartaPizza.jButton1) {
            this.silabaSeleccionada = objTCartaPizza.jButton1.getText();
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaPizza.jButton2) {
            this.silabaSeleccionada = objTCartaPizza.jButton2.getText();
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaPizza.jButton3) {
            this.silabaSeleccionada = objTCartaPizza.jButton3.getText();
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaPizza.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaPizza.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(13);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);

                objTCartaPizza.jButton1.setText(silabaCorrecta);
                objTCartaPizza.jButton2.setText("HU");
                objTCartaPizza.jButton3.setText("KI");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    public void verificarPalabra(String silabaSeleccionada) {
        System.out.println(silabaSeleccionada);

        if (silabaSeleccionada.equals(silabaCorrecta)) {
            JOptionPane.showMessageDialog(null, "¡Correcto!");
        } else {
            JOptionPane.showMessageDialog(null, "Inténtalo de nuevo.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
