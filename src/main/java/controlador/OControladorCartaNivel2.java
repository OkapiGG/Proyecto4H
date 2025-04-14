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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;
import modelo.Palabra;
import vista.OCartaNivel2;

public class OControladorCartaNivel2 implements MouseListener {

    private OCartaNivel2 objOCartaNivel2;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;

    private String silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7, silaba8, silaba9, silaba10;
    private String silabaSeleccionada;
    private JLabel labelOrigenSeleccionada;

    private boolean grupo1Completado = false;
    private boolean grupo2Completado = false;
    private boolean grupo3Completado = false;
    private boolean grupo4Completado = false;

    public OControladorCartaNivel2(OCartaNivel2 objOCartaNivel2) {
        this.objOCartaNivel2 = objOCartaNivel2;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();

        // LLAVE
        this.objOCartaNivel2.jLabel2.addMouseListener(this);
        this.objOCartaNivel2.jLabel3.addMouseListener(this);
        this.objOCartaNivel2.jLabel4.addMouseListener(this);
        this.objOCartaNivel2.jLabel5.addMouseListener(this);
        // PERA
        this.objOCartaNivel2.jLabel6.addMouseListener(this);
        this.objOCartaNivel2.jLabel7.addMouseListener(this);
        this.objOCartaNivel2.jLabel8.addMouseListener(this);
        this.objOCartaNivel2.jLabel9.addMouseListener(this);
        // ARDILLA
        this.objOCartaNivel2.jLabel10.addMouseListener(this);
        this.objOCartaNivel2.jLabel11.addMouseListener(this);
        this.objOCartaNivel2.jLabel12.addMouseListener(this);
        this.objOCartaNivel2.jLabel13.addMouseListener(this);
        this.objOCartaNivel2.jLabel14.addMouseListener(this);
        this.objOCartaNivel2.jLabel15.addMouseListener(this);
        // CAMISA
        this.objOCartaNivel2.jLabel16.addMouseListener(this);
        this.objOCartaNivel2.jLabel17.addMouseListener(this);
        this.objOCartaNivel2.jLabel18.addMouseListener(this);
        this.objOCartaNivel2.jLabel19.addMouseListener(this);
        this.objOCartaNivel2.jLabel20.addMouseListener(this);
        this.objOCartaNivel2.jLabel21.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source instanceof JLabel) {
            JLabel label = (JLabel) source;

            if (!label.isEnabled()) {
                return;
            }

            // Labels origen
            if (label == objOCartaNivel2.jLabel2 || label == objOCartaNivel2.jLabel3 ||
                label == objOCartaNivel2.jLabel6 || label == objOCartaNivel2.jLabel7 ||
                label == objOCartaNivel2.jLabel10 || label == objOCartaNivel2.jLabel11 || 
                label == objOCartaNivel2.jLabel12 || label == objOCartaNivel2.jLabel16 ||
                label == objOCartaNivel2.jLabel17 || label == objOCartaNivel2.jLabel18) {
                silabaSeleccionada = label.getText();
                labelOrigenSeleccionada = label;
                System.out.println("Selección: " + silabaSeleccionada);
            } 
           

            // Destino LLAVE
            else if (!grupo1Completado && (label == objOCartaNivel2.jLabel4 || label == objOCartaNivel2.jLabel5)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo LLAVE destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo1();
                }
            }
            // Destino PERA
            else if (!grupo2Completado && (label == objOCartaNivel2.jLabel8 || label == objOCartaNivel2.jLabel9)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo PERA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo2();
                }
            }
            // Destino ARDILLA
            else if (!grupo3Completado && (label == objOCartaNivel2.jLabel13 || label == objOCartaNivel2.jLabel14 || label == objOCartaNivel2.jLabel15)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo ARDILLA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo3();
                }
            }
            // Destino CAMISA
            else if (!grupo4Completado && (label == objOCartaNivel2.jLabel19 || label == objOCartaNivel2.jLabel20 || label == objOCartaNivel2.jLabel21)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo CAMISA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo4();
                }
            }
        }
    }

    private void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            // LLAVE
            Palabra palabraActual = lista.get(28);
            String palabraCompleta1 = palabraActual.getPalabra();
            if (palabraCompleta1.length() >= 4) { 
                this.silaba1 = palabraCompleta1.substring(0, 3);
                this.silaba2 = palabraCompleta1.substring(3, 5);
                objOCartaNivel2.jLabel2.setText(silaba1);
                objOCartaNivel2.jLabel3.setText(silaba2);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo LLAVE.");
            }
            
            // PERA
            Palabra palabraActual2 = lista.get(29);
            String palabraCompleta2 = palabraActual2.getPalabra();
            if (palabraCompleta2.length() >= 4) {
                this.silaba3 = palabraCompleta2.substring(0, 2);
                this.silaba4 = palabraCompleta2.substring(2, 4);
                objOCartaNivel2.jLabel6.setText(silaba3);
                objOCartaNivel2.jLabel7.setText(silaba4);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo PERA.");
            }
            
            // ARDILLA
            Palabra palabraActual3 = lista.get(30);
            String palabraCompleta3 = palabraActual3.getPalabra();
            if (palabraCompleta3.length() >= 6) {
                this.silaba5 = palabraCompleta3.substring(0, 2);
                this.silaba6 = palabraCompleta3.substring(2, 4);
                this.silaba7 = palabraCompleta3.substring(4, 7);
                objOCartaNivel2.jLabel10.setText(silaba5);
                objOCartaNivel2.jLabel11.setText(silaba6);
                objOCartaNivel2.jLabel12.setText(silaba7);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo ARDILLA.");
            }
            
            // CAMISA
            Palabra palabraActual4 = lista.get(31);
            String palabraCompleta4 = palabraActual4.getPalabra();
            if (palabraCompleta4.length() >= 6) {
                this.silaba8 = palabraCompleta4.substring(0, 2);
                this.silaba9 = palabraCompleta4.substring(2, 4);
                this.silaba10 = palabraCompleta4.substring(4, 6);
                objOCartaNivel2.jLabel16.setText(silaba8);
                objOCartaNivel2.jLabel17.setText(silaba9);
                objOCartaNivel2.jLabel18.setText(silaba10);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo CAMISA.");
            }
        }
    }
    
    private void verificarGrupo1() {
        String parte1 = objOCartaNivel2.jLabel4.getText();
        String parte2 = objOCartaNivel2.jLabel5.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba1 + silaba2;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo1();
        }
    }
    
    private void verificarGrupo2() {
        String parte1 = objOCartaNivel2.jLabel8.getText();
        String parte2 = objOCartaNivel2.jLabel9.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba3 + silaba4;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo2();
        }
    }
    
    private void verificarGrupo3() {
        String parte1 = objOCartaNivel2.jLabel13.getText();
        String parte2 = objOCartaNivel2.jLabel14.getText();
        String parte3 = objOCartaNivel2.jLabel15.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba5 + silaba6 + silaba7;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo3();
        }
    }
    
    private void verificarGrupo4() {
        String parte1 = objOCartaNivel2.jLabel19.getText();
        String parte2 = objOCartaNivel2.jLabel20.getText();
        String parte3 = objOCartaNivel2.jLabel21.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba8 + silaba9 + silaba10;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo4();
        }
    }
    
    private void deshabilitarGrupo1() {
        grupo1Completado = true;
        objOCartaNivel2.jLabel4.setEnabled(false);
        objOCartaNivel2.jLabel5.setEnabled(false);
    }
    
    private void deshabilitarGrupo2() {
        grupo2Completado = true;
        objOCartaNivel2.jLabel8.setEnabled(false);
        objOCartaNivel2.jLabel9.setEnabled(false);
    }
    
    private void deshabilitarGrupo3() {
        grupo3Completado = true;
        objOCartaNivel2.jLabel13.setEnabled(false);
        objOCartaNivel2.jLabel14.setEnabled(false);
        objOCartaNivel2.jLabel15.setEnabled(false);
    }
    
    private void deshabilitarGrupo4() {
        grupo4Completado = true;
        objOCartaNivel2.jLabel19.setEnabled(false);
        objOCartaNivel2.jLabel20.setEnabled(false);
        objOCartaNivel2.jLabel21.setEnabled(false);
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
