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
import vista.MenuOrdenar;
import vista.OCartaNivel8;

public class OControladorCartaNivel8 implements MouseListener {

    private OCartaNivel8 objOCartaNivel8;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;

    private String silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7, silaba8, silaba9, silaba10, silaba11,
            silaba12, silaba13;
    private String silabaSeleccionada;
    private JLabel labelOrigenSeleccionada;

    private boolean grupo1Completado = false;
    private boolean grupo2Completado = false;
    private boolean grupo3Completado = false;
    private boolean grupo4Completado = false;

    public OControladorCartaNivel8(OCartaNivel8 objOCartaNivel8) {
        this.objOCartaNivel8 = objOCartaNivel8;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();
        this.objOCartaNivel8.jButton1.addMouseListener(this);
        // MARIPOSA
        this.objOCartaNivel8.jLabel2.addMouseListener(this);
        this.objOCartaNivel8.jLabel3.addMouseListener(this);
        this.objOCartaNivel8.jLabel4.addMouseListener(this);
        this.objOCartaNivel8.jLabel5.addMouseListener(this);
        this.objOCartaNivel8.jLabel6.addMouseListener(this);
        this.objOCartaNivel8.jLabel7.addMouseListener(this);
        this.objOCartaNivel8.jLabel8.addMouseListener(this);
        this.objOCartaNivel8.jLabel9.addMouseListener(this);
        // VOLCAN
        this.objOCartaNivel8.jLabel10.addMouseListener(this);
        this.objOCartaNivel8.jLabel11.addMouseListener(this);
        this.objOCartaNivel8.jLabel12.addMouseListener(this);
        this.objOCartaNivel8.jLabel13.addMouseListener(this);
        // PELOTA
        this.objOCartaNivel8.jLabel14.addMouseListener(this);
        this.objOCartaNivel8.jLabel15.addMouseListener(this);
        this.objOCartaNivel8.jLabel16.addMouseListener(this);
        this.objOCartaNivel8.jLabel17.addMouseListener(this);
        this.objOCartaNivel8.jLabel18.addMouseListener(this);
        this.objOCartaNivel8.jLabel19.addMouseListener(this);
        // PALMERA
        this.objOCartaNivel8.jLabel20.addMouseListener(this);
        this.objOCartaNivel8.jLabel21.addMouseListener(this);
        this.objOCartaNivel8.jLabel22.addMouseListener(this);
        this.objOCartaNivel8.jLabel23.addMouseListener(this);
        this.objOCartaNivel8.jLabel24.addMouseListener(this);
        this.objOCartaNivel8.jLabel25.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == this.objOCartaNivel8.jButton1) {
            MenuOrdenar objMenuOrdenar = new MenuOrdenar();
            objMenuOrdenar.setVisible(true);
            this.objOCartaNivel8.dispose();
        }
        if (source instanceof JLabel) {
            JLabel label = (JLabel) source;

            if (!label.isEnabled()) {
                return;
            }

            // Labels origen
            if (label == objOCartaNivel8.jLabel2 || label == objOCartaNivel8.jLabel3
                    || label == objOCartaNivel8.jLabel4 || label == objOCartaNivel8.jLabel5 || label == objOCartaNivel8.jLabel10
                    || label == objOCartaNivel8.jLabel11 || label == objOCartaNivel8.jLabel14 || label == objOCartaNivel8.jLabel15
                    || label == objOCartaNivel8.jLabel16 || label == objOCartaNivel8.jLabel20
                    || label == objOCartaNivel8.jLabel21 || label == objOCartaNivel8.jLabel22) {
                silabaSeleccionada = label.getText();
                labelOrigenSeleccionada = label;
                System.out.println("Selección: " + silabaSeleccionada);
                objAudio.reproducirAudio(silabaSeleccionada);
            } // Destino MARIPOSA
            else if (!grupo1Completado && (label == objOCartaNivel8.jLabel6 || label == objOCartaNivel8.jLabel7 || label == objOCartaNivel8.jLabel8 || label == objOCartaNivel8.jLabel9)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo MARIPOSA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo1();
                }
            } // Destino VOLCAN
            else if (!grupo2Completado && (label == objOCartaNivel8.jLabel12 || label == objOCartaNivel8.jLabel13)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo VOLCAN destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo2();
                }
            } // Destino PELOTA
            else if (!grupo3Completado && (label == objOCartaNivel8.jLabel17 || label == objOCartaNivel8.jLabel18 || label == objOCartaNivel8.jLabel19)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo PELOTA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo3();
                }
            } // Destino PALMERA
            else if (!grupo4Completado && (label == objOCartaNivel8.jLabel23 || label == objOCartaNivel8.jLabel24 || label == objOCartaNivel8.jLabel25)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    if (labelOrigenSeleccionada != null) {
                        labelOrigenSeleccionada.setEnabled(false);
                    }
                    System.out.println("Grupo PALMERA destino llenado con: " + silabaSeleccionada);
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
            // MARIPOSA
            Palabra palabraActual = lista.get(52);
            String palabraCompleta1 = palabraActual.getPalabra();
            if (palabraCompleta1.length() >= 4) {
                this.silaba1 = palabraCompleta1.substring(0, 2);
                this.silaba2 = palabraCompleta1.substring(2, 4);
                this.silaba3 = palabraCompleta1.substring(4, 6);
                this.silaba4 = palabraCompleta1.substring(6, 8);
                objOCartaNivel8.jLabel2.setText(silaba1);
                objOCartaNivel8.jLabel3.setText(silaba2);
                objOCartaNivel8.jLabel4.setText(silaba3);
                objOCartaNivel8.jLabel5.setText(silaba4);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo MARIPOSA.");
            }

            // VOLCAN
            Palabra palabraActual2 = lista.get(53);
            String palabraCompleta2 = palabraActual2.getPalabra();
            if (palabraCompleta2.length() >= 4) {
                this.silaba5 = palabraCompleta2.substring(0, 3);
                this.silaba6 = palabraCompleta2.substring(3, 6);
                objOCartaNivel8.jLabel10.setText(silaba5);
                objOCartaNivel8.jLabel11.setText(silaba6);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo VOLCAN.");
            }

            // PELOTA
            Palabra palabraActual3 = lista.get(54);
            String palabraCompleta3 = palabraActual3.getPalabra();
            if (palabraCompleta3.length() >= 4) {
                this.silaba7 = palabraCompleta3.substring(0, 2);
                this.silaba8 = palabraCompleta3.substring(2, 4);
                this.silaba9 = palabraCompleta3.substring(4, 6);
                objOCartaNivel8.jLabel14.setText(silaba7);
                objOCartaNivel8.jLabel15.setText(silaba8);
                objOCartaNivel8.jLabel16.setText(silaba9);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo PELOTA.");
            }

            // PALMERA
            Palabra palabraActual4 = lista.get(55);
            String palabraCompleta4 = palabraActual4.getPalabra();
            if (palabraCompleta4.length() >= 6) {
                this.silaba10 = palabraCompleta4.substring(0, 3);
                this.silaba11 = palabraCompleta4.substring(3, 5);
                this.silaba12 = palabraCompleta4.substring(5, 7);
                objOCartaNivel8.jLabel20.setText(silaba10);
                objOCartaNivel8.jLabel21.setText(silaba11);
                objOCartaNivel8.jLabel22.setText(silaba12);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo PALMERA.");
            }
        }
    }

    private void verificarGrupo1() {
        String parte1 = objOCartaNivel8.jLabel6.getText();
        String parte2 = objOCartaNivel8.jLabel7.getText();
        String parte3 = objOCartaNivel8.jLabel8.getText();
        String parte4 = objOCartaNivel8.jLabel9.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty() && !parte4.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3 + parte4;
            String palabraCorrecta = silaba1 + silaba2 + silaba3 + silaba4;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("mariposa");
                deshabilitarGrupo1();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo2() {
        String parte1 = objOCartaNivel8.jLabel12.getText();
        String parte2 = objOCartaNivel8.jLabel13.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba5 + silaba6;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("volcan");
                deshabilitarGrupo2();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo3() {
        String parte1 = objOCartaNivel8.jLabel17.getText();
        String parte2 = objOCartaNivel8.jLabel18.getText();
        String parte3 = objOCartaNivel8.jLabel19.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba7 + silaba8 + silaba9;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("pelota");
                deshabilitarGrupo3();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo4() {
        String parte1 = objOCartaNivel8.jLabel23.getText();
        String parte2 = objOCartaNivel8.jLabel24.getText();
        String parte3 = objOCartaNivel8.jLabel25.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba10 + silaba11 + silaba12;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("palmera");
                deshabilitarGrupo4();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarSiYaCompletasteTodo() {
        if (grupo1Completado && grupo2Completado && grupo3Completado && grupo4Completado) {
            actPuntaje();
        }
    }

    private void actPuntaje() {
        int idUsuario = modelo.Login.getIdUsuarioActivo();
        OperacionesBDCuenta operacionesCuenta = new OperacionesBDCuenta();
        operacionesCuenta.actualizarPuntajeYPalabras(idUsuario, 40, 4);

        JOptionPane.showMessageDialog(
                null,
                "¡Felicidades! Completaste las 4 palabras 🎉\nGanaste 40 puntos 🏆",
                "Nivel Completado",
                JOptionPane.INFORMATION_MESSAGE
        );

        vista.MenuOrdenar objMenuOrdenar = new vista.MenuOrdenar();
        objMenuOrdenar.setVisible(true);
        objOCartaNivel8.dispose();
    }

    private void deshabilitarGrupo1() {
        grupo1Completado = true;
        objOCartaNivel8.jLabel6.setEnabled(false);
        objOCartaNivel8.jLabel7.setEnabled(false);
        objOCartaNivel8.jLabel8.setEnabled(false);
        objOCartaNivel8.jLabel9.setEnabled(false);
    }

    private void deshabilitarGrupo2() {
        grupo2Completado = true;
        objOCartaNivel8.jLabel12.setEnabled(false);
        objOCartaNivel8.jLabel13.setEnabled(false);
    }

    private void deshabilitarGrupo3() {
        grupo3Completado = true;
        objOCartaNivel8.jLabel17.setEnabled(false);
        objOCartaNivel8.jLabel18.setEnabled(false);
        objOCartaNivel8.jLabel19.setEnabled(false);
    }

    private void deshabilitarGrupo4() {
        grupo4Completado = true;
        objOCartaNivel8.jLabel23.setEnabled(false);
        objOCartaNivel8.jLabel24.setEnabled(false);
        objOCartaNivel8.jLabel25.setEnabled(false);
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
