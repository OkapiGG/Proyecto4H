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
import vista.OCartaNivel7;

public class OControladorCartaNivel7 implements MouseListener {

    private OCartaNivel7 objOCartaNivel7;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;

    private String silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7, silaba8, silaba9, silaba10, silaba11;
    private String silabaSeleccionada;
    private JLabel labelOrigenSeleccionada;

    private boolean grupo1Completado = false;
    private boolean grupo2Completado = false;
    private boolean grupo3Completado = false;
    private boolean grupo4Completado = false;

    public OControladorCartaNivel7(OCartaNivel7 objOCartaNivel7) {
        this.objOCartaNivel7 = objOCartaNivel7;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();

        // COCODRILLO
        this.objOCartaNivel7.jLabel2.addMouseListener(this);
        this.objOCartaNivel7.jLabel3.addMouseListener(this);
        this.objOCartaNivel7.jLabel4.addMouseListener(this);
        this.objOCartaNivel7.jLabel5.addMouseListener(this);
        this.objOCartaNivel7.jLabel6.addMouseListener(this);
        this.objOCartaNivel7.jLabel7.addMouseListener(this);
        this.objOCartaNivel7.jLabel8.addMouseListener(this);
        this.objOCartaNivel7.jLabel9.addMouseListener(this);
        // CORONA
        this.objOCartaNivel7.jLabel10.addMouseListener(this);
        this.objOCartaNivel7.jLabel11.addMouseListener(this);
        this.objOCartaNivel7.jLabel12.addMouseListener(this);
        this.objOCartaNivel7.jLabel13.addMouseListener(this);
        this.objOCartaNivel7.jLabel14.addMouseListener(this);
        this.objOCartaNivel7.jLabel15.addMouseListener(this);
        // OVEJA
        this.objOCartaNivel7.jLabel16.addMouseListener(this);
        this.objOCartaNivel7.jLabel17.addMouseListener(this);
        this.objOCartaNivel7.jLabel18.addMouseListener(this);
        this.objOCartaNivel7.jLabel19.addMouseListener(this);
        this.objOCartaNivel7.jLabel20.addMouseListener(this);
        this.objOCartaNivel7.jLabel21.addMouseListener(this);
        // TORTUGA
        this.objOCartaNivel7.jLabel22.addMouseListener(this);
        this.objOCartaNivel7.jLabel23.addMouseListener(this);
        this.objOCartaNivel7.jLabel24.addMouseListener(this);
        this.objOCartaNivel7.jLabel25.addMouseListener(this);
        this.objOCartaNivel7.jLabel26.addMouseListener(this);
        this.objOCartaNivel7.jLabel27.addMouseListener(this);
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
            if (label == objOCartaNivel7.jLabel2 || label == objOCartaNivel7.jLabel3 ||
                label == objOCartaNivel7.jLabel4 || label == objOCartaNivel7.jLabel5 || label == objOCartaNivel7.jLabel10 ||
                label == objOCartaNivel7.jLabel11 || label == objOCartaNivel7.jLabel12 || label == objOCartaNivel7.jLabel16 ||
                label == objOCartaNivel7.jLabel17 || label == objOCartaNivel7.jLabel18 ||
                label == objOCartaNivel7.jLabel22 || label == objOCartaNivel7.jLabel23 || label == objOCartaNivel7.jLabel24) {
                silabaSeleccionada = label.getText();
                labelOrigenSeleccionada = label;
                System.out.println("Selección: " + silabaSeleccionada);
            } 
           

            // Destino COCODRILO
            else if (!grupo1Completado && (label == objOCartaNivel7.jLabel6 || label == objOCartaNivel7.jLabel7 || label == objOCartaNivel7.jLabel8 || label == objOCartaNivel7.jLabel9)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo COCODRILO destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo1();
                }
            }
            // Destino CORONA
            else if (!grupo2Completado && (label == objOCartaNivel7.jLabel13 || label == objOCartaNivel7.jLabel14 || label == objOCartaNivel7.jLabel15)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo CORONA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo2();
                }
            }
            // Destino OVEJA
            else if (!grupo3Completado && (label == objOCartaNivel7.jLabel19 || label == objOCartaNivel7.jLabel20 || label == objOCartaNivel7.jLabel21)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo OVEJA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo3();
                }
            }
            // Destino TORTUGA
            else if (!grupo4Completado && (label == objOCartaNivel7.jLabel25 || label == objOCartaNivel7.jLabel26 || label == objOCartaNivel7.jLabel27)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo TORTUGA destino llenado con: " + silabaSeleccionada);
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
            // COCODRILO
            Palabra palabraActual = lista.get(48);
            String palabraCompleta1 = palabraActual.getPalabra();
            if (palabraCompleta1.length() >= 4) { 
                this.silaba1 = palabraCompleta1.substring(0, 2);
                this.silaba2 = palabraCompleta1.substring(2, 4);
                this.silaba3 = palabraCompleta1.substring(4, 7);
                this.silaba4 = palabraCompleta1.substring(7, 9);
                objOCartaNivel7.jLabel2.setText(silaba1);
                objOCartaNivel7.jLabel3.setText(silaba2);
                objOCartaNivel7.jLabel4.setText(silaba3);
                objOCartaNivel7.jLabel5.setText(silaba4);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo COCODRILO.");
            }
            /////////////////////FALTA ARREGLAR LO DE ABAJO
            
            // CORONA
            Palabra palabraActual2 = lista.get(45);
            String palabraCompleta2 = palabraActual2.getPalabra();
            if (palabraCompleta2.length() >= 4) {
                this.silaba5 = palabraCompleta2.substring(0, 3);
                this.silaba6 = palabraCompleta2.substring(3, 6);
                objOCartaNivel7.jLabel10.setText(silaba5);
                objOCartaNivel7.jLabel11.setText(silaba6);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo LENTES.");
            }
            
            // GLOBO
            Palabra palabraActual3 = lista.get(46);
            String palabraCompleta3 = palabraActual3.getPalabra();
            if (palabraCompleta3.length() >= 4) {
                this.silaba7 = palabraCompleta3.substring(0, 3);
                this.silaba8 = palabraCompleta3.substring(3, 5);
                objOCartaNivel7.jLabel14.setText(silaba7);
                objOCartaNivel7.jLabel15.setText(silaba8);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo GLOBO.");
            }
            
            // COHETE
            Palabra palabraActual4 = lista.get(47);
            String palabraCompleta4 = palabraActual4.getPalabra();
            if (palabraCompleta4.length() >= 6) {
                this.silaba9 = palabraCompleta4.substring(0, 2);
                this.silaba10 = palabraCompleta4.substring(2, 4);
                this.silaba11 = palabraCompleta4.substring(4, 6);
                objOCartaNivel7.jLabel18.setText(silaba9);
                objOCartaNivel7.jLabel19.setText(silaba10);
                objOCartaNivel7.jLabel20.setText(silaba11);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo COHETE.");
            }
        }
    }
    
    private void verificarGrupo1() {
        String parte1 = objOCartaNivel7.jLabel6.getText();
        String parte2 = objOCartaNivel7.jLabel7.getText();
        String parte3 = objOCartaNivel7.jLabel8.getText();
        String parte4 = objOCartaNivel7.jLabel9.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty() && !parte4.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3 + parte4;
            String palabraCorrecta = silaba1 + silaba2 + silaba3 + silaba4;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo1();
        }
    }
    
    private void verificarGrupo2() {
        String parte1 = objOCartaNivel7.jLabel12.getText();
        String parte2 = objOCartaNivel7.jLabel13.getText();
        
        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba5 + silaba6;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo2();
        }
    }
    
    private void verificarGrupo3() {
        String parte1 = objOCartaNivel7.jLabel16.getText();
        String parte2 = objOCartaNivel7.jLabel17.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba7 + silaba8;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo3();
        }
    }
    
    private void verificarGrupo4() {
        String parte1 = objOCartaNivel7.jLabel21.getText();
        String parte2 = objOCartaNivel7.jLabel22.getText();
        String parte3 = objOCartaNivel7.jLabel23.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba9 + silaba10 + silaba11;
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
        objOCartaNivel7.jLabel6.setEnabled(false);
        objOCartaNivel7.jLabel7.setEnabled(false);
        objOCartaNivel7.jLabel8.setEnabled(false);
        objOCartaNivel7.jLabel9.setEnabled(false);
    }
    
    private void deshabilitarGrupo2() {
        grupo2Completado = true;
        objOCartaNivel7.jLabel12.setEnabled(false);
        objOCartaNivel7.jLabel13.setEnabled(false);
    }
    
    private void deshabilitarGrupo3() {
        grupo3Completado = true;
        objOCartaNivel7.jLabel16.setEnabled(false);
        objOCartaNivel7.jLabel17.setEnabled(false);
    }
    
    private void deshabilitarGrupo4() {
        grupo4Completado = true;
        objOCartaNivel7.jLabel21.setEnabled(false);
        objOCartaNivel7.jLabel22.setEnabled(false);
        objOCartaNivel7.jLabel23.setEnabled(false);
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
