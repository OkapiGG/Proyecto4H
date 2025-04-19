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
import vista.OCartaNivel6;

public class OControladorCartaNivel6 implements MouseListener {

    private OCartaNivel6 objOCartaNivel6;
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

    public OControladorCartaNivel6(OCartaNivel6 objOCartaNivel6) {
        this.objOCartaNivel6 = objOCartaNivel6;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();

        // VENTILADOR
        this.objOCartaNivel6.jLabel2.addMouseListener(this);
        this.objOCartaNivel6.jLabel3.addMouseListener(this);
        this.objOCartaNivel6.jLabel4.addMouseListener(this);
        this.objOCartaNivel6.jLabel5.addMouseListener(this);
        this.objOCartaNivel6.jLabel6.addMouseListener(this);
        this.objOCartaNivel6.jLabel7.addMouseListener(this);
        this.objOCartaNivel6.jLabel8.addMouseListener(this);
        this.objOCartaNivel6.jLabel9.addMouseListener(this);
        // LENTES
        this.objOCartaNivel6.jLabel10.addMouseListener(this);
        this.objOCartaNivel6.jLabel11.addMouseListener(this);
        this.objOCartaNivel6.jLabel12.addMouseListener(this);
        this.objOCartaNivel6.jLabel13.addMouseListener(this);
        // GLOBO
        this.objOCartaNivel6.jLabel14.addMouseListener(this);
        this.objOCartaNivel6.jLabel15.addMouseListener(this);
        this.objOCartaNivel6.jLabel16.addMouseListener(this);
        this.objOCartaNivel6.jLabel17.addMouseListener(this);
        // COHETE
        this.objOCartaNivel6.jLabel18.addMouseListener(this);
        this.objOCartaNivel6.jLabel19.addMouseListener(this);
        this.objOCartaNivel6.jLabel20.addMouseListener(this);
        this.objOCartaNivel6.jLabel21.addMouseListener(this);
        this.objOCartaNivel6.jLabel22.addMouseListener(this);
        this.objOCartaNivel6.jLabel23.addMouseListener(this);
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
            if (label == objOCartaNivel6.jLabel2 || label == objOCartaNivel6.jLabel3 ||
                label == objOCartaNivel6.jLabel4 || label == objOCartaNivel6.jLabel5 || label == objOCartaNivel6.jLabel10 ||
                label == objOCartaNivel6.jLabel11 || label == objOCartaNivel6.jLabel14 || label == objOCartaNivel6.jLabel15 ||
                label == objOCartaNivel6.jLabel18 || label == objOCartaNivel6.jLabel19 ||
                label == objOCartaNivel6.jLabel20) {
                silabaSeleccionada = label.getText();
                labelOrigenSeleccionada = label;
                System.out.println("Selección: " + silabaSeleccionada);
                objAudio.reproducirAudio(silabaSeleccionada);
            } 
           

            // Destino VENTILADOR
            else if (!grupo1Completado && (label == objOCartaNivel6.jLabel6 || label == objOCartaNivel6.jLabel7 || label == objOCartaNivel6.jLabel8 || label == objOCartaNivel6.jLabel9)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo VENTILADOR destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo1();
                }
            }
            // Destino LENTES
            else if (!grupo2Completado && (label == objOCartaNivel6.jLabel12 || label == objOCartaNivel6.jLabel13)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo LENTES destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo2();
                }
            }
            // Destino GLOBO
            else if (!grupo3Completado && (label == objOCartaNivel6.jLabel16 || label == objOCartaNivel6.jLabel17)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo GLOBO destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo3();
                }
            }
            // Destino COHETE
            else if (!grupo4Completado && (label == objOCartaNivel6.jLabel21 || label == objOCartaNivel6.jLabel22 || label == objOCartaNivel6.jLabel23)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo COHETE destino llenado con: " + silabaSeleccionada);
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
            // VENTILADOR
            Palabra palabraActual = lista.get(44);
            String palabraCompleta1 = palabraActual.getPalabra();
            if (palabraCompleta1.length() >= 4) { 
                this.silaba1 = palabraCompleta1.substring(0, 3);
                this.silaba2 = palabraCompleta1.substring(3, 5);
                this.silaba3 = palabraCompleta1.substring(5, 7);
                this.silaba4 = palabraCompleta1.substring(7, 10);
                objOCartaNivel6.jLabel2.setText(silaba1);
                objOCartaNivel6.jLabel3.setText(silaba2);
                objOCartaNivel6.jLabel4.setText(silaba3);
                objOCartaNivel6.jLabel5.setText(silaba4);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo VENTILADOR.");
            }
            
            // LENTES
            Palabra palabraActual2 = lista.get(45);
            String palabraCompleta2 = palabraActual2.getPalabra();
            if (palabraCompleta2.length() >= 4) {
                this.silaba5 = palabraCompleta2.substring(0, 3);
                this.silaba6 = palabraCompleta2.substring(3, 6);
                objOCartaNivel6.jLabel10.setText(silaba5);
                objOCartaNivel6.jLabel11.setText(silaba6);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo LENTES.");
            }
            
            // GLOBO
            Palabra palabraActual3 = lista.get(46);
            String palabraCompleta3 = palabraActual3.getPalabra();
            if (palabraCompleta3.length() >= 4) {
                this.silaba7 = palabraCompleta3.substring(0, 3);
                this.silaba8 = palabraCompleta3.substring(3, 5);
                objOCartaNivel6.jLabel14.setText(silaba7);
                objOCartaNivel6.jLabel15.setText(silaba8);
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
                objOCartaNivel6.jLabel18.setText(silaba9);
                objOCartaNivel6.jLabel19.setText(silaba10);
                objOCartaNivel6.jLabel20.setText(silaba11);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo COHETE.");
            }
        }
    }
    
    private void verificarGrupo1() {
        String parte1 = objOCartaNivel6.jLabel6.getText();
        String parte2 = objOCartaNivel6.jLabel7.getText();
        String parte3 = objOCartaNivel6.jLabel8.getText();
        String parte4 = objOCartaNivel6.jLabel9.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty() && !parte4.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3 + parte4;
            String palabraCorrecta = silaba1 + silaba2 + silaba3 + silaba4;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("ventilador");
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo1();
        }
    }
    
    private void verificarGrupo2() {
        String parte1 = objOCartaNivel6.jLabel12.getText();
        String parte2 = objOCartaNivel6.jLabel13.getText();
        
        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba5 + silaba6;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("lentes");
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo2();
        }
    }
    
    private void verificarGrupo3() {
        String parte1 = objOCartaNivel6.jLabel16.getText();
        String parte2 = objOCartaNivel6.jLabel17.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba7 + silaba8;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("globo");
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo3();
        }
    }
    
    private void verificarGrupo4() {
        String parte1 = objOCartaNivel6.jLabel21.getText();
        String parte2 = objOCartaNivel6.jLabel22.getText();
        String parte3 = objOCartaNivel6.jLabel23.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba9 + silaba10 + silaba11;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("cohete");
                JOptionPane.showMessageDialog(null, "¡Correcto! Formaste la palabra: " + palabraCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
            deshabilitarGrupo4();
        }
    }
    
    private void deshabilitarGrupo1() {
        grupo1Completado = true;
        objOCartaNivel6.jLabel6.setEnabled(false);
        objOCartaNivel6.jLabel7.setEnabled(false);
        objOCartaNivel6.jLabel8.setEnabled(false);
        objOCartaNivel6.jLabel9.setEnabled(false);
    }
    
    private void deshabilitarGrupo2() {
        grupo2Completado = true;
        objOCartaNivel6.jLabel12.setEnabled(false);
        objOCartaNivel6.jLabel13.setEnabled(false);
    }
    
    private void deshabilitarGrupo3() {
        grupo3Completado = true;
        objOCartaNivel6.jLabel16.setEnabled(false);
        objOCartaNivel6.jLabel17.setEnabled(false);
    }
    
    private void deshabilitarGrupo4() {
        grupo4Completado = true;
        objOCartaNivel6.jLabel21.setEnabled(false);
        objOCartaNivel6.jLabel22.setEnabled(false);
        objOCartaNivel6.jLabel23.setEnabled(false);
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
