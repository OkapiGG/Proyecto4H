/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
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
import vista.OCartaNivel9;

public class OControladorCartaNivel9 implements MouseListener {

    private OCartaNivel9 objOCartaNivel9;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private static final Color VERDE = new Color(34, 139, 34);

    private String silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7, silaba8, silaba9, silaba10, silaba11, silaba12, silaba13;
    private String silabaSeleccionada;
    private JLabel labelOrigenSeleccionada;

    private boolean grupo1Completado = false;
    private boolean grupo2Completado = false;
    private boolean grupo3Completado = false;
    private boolean grupo4Completado = false;
    private boolean terminoConExito = false;

    public OControladorCartaNivel9(OCartaNivel9 objOCartaNivel9) {
        this.objOCartaNivel9 = objOCartaNivel9;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();
        this.objOCartaNivel9.jButton1.addMouseListener(this);
        // ELEFANTE
        this.objOCartaNivel9.jLabel2.addMouseListener(this);
        this.objOCartaNivel9.jLabel3.addMouseListener(this);
        this.objOCartaNivel9.jLabel4.addMouseListener(this);
        this.objOCartaNivel9.jLabel5.addMouseListener(this);
        this.objOCartaNivel9.jLabel6.addMouseListener(this);
        this.objOCartaNivel9.jLabel7.addMouseListener(this);
        this.objOCartaNivel9.jLabel8.addMouseListener(this);
        this.objOCartaNivel9.jLabel9.addMouseListener(this);
        // ELOTE
        this.objOCartaNivel9.jLabel10.addMouseListener(this);
        this.objOCartaNivel9.jLabel11.addMouseListener(this);
        this.objOCartaNivel9.jLabel12.addMouseListener(this);
        this.objOCartaNivel9.jLabel13.addMouseListener(this);
        this.objOCartaNivel9.jLabel14.addMouseListener(this);
        this.objOCartaNivel9.jLabel15.addMouseListener(this);
        // ESCOBA
        this.objOCartaNivel9.jLabel16.addMouseListener(this);
        this.objOCartaNivel9.jLabel17.addMouseListener(this);
        this.objOCartaNivel9.jLabel18.addMouseListener(this);
        this.objOCartaNivel9.jLabel19.addMouseListener(this);
        this.objOCartaNivel9.jLabel20.addMouseListener(this);
        this.objOCartaNivel9.jLabel21.addMouseListener(this);
        // VENTANA
        this.objOCartaNivel9.jLabel22.addMouseListener(this);
        this.objOCartaNivel9.jLabel23.addMouseListener(this);
        this.objOCartaNivel9.jLabel24.addMouseListener(this);
        this.objOCartaNivel9.jLabel25.addMouseListener(this);
        this.objOCartaNivel9.jLabel26.addMouseListener(this);
        this.objOCartaNivel9.jLabel27.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == this.objOCartaNivel9.jButton1) {
            MenuOrdenar objMenuOrdenar = new MenuOrdenar();
            objMenuOrdenar.setVisible(true);
            this.objOCartaNivel9.dispose();
        }
        if (source instanceof JLabel) {
            JLabel label = (JLabel) source;

            if (!label.isEnabled()) {
                return;
            }

            // Labels origen
            if (label == objOCartaNivel9.jLabel2 || label == objOCartaNivel9.jLabel3
                    || label == objOCartaNivel9.jLabel4 || label == objOCartaNivel9.jLabel5 || label == objOCartaNivel9.jLabel10
                    || label == objOCartaNivel9.jLabel11 || label == objOCartaNivel9.jLabel12 || label == objOCartaNivel9.jLabel16
                    || label == objOCartaNivel9.jLabel17 || label == objOCartaNivel9.jLabel18
                    || label == objOCartaNivel9.jLabel22 || label == objOCartaNivel9.jLabel23 || label == objOCartaNivel9.jLabel24) {
                silabaSeleccionada = label.getText();
                labelOrigenSeleccionada = label;
                System.out.println("Selección: " + silabaSeleccionada);
                objAudio.reproducirAudio(silabaSeleccionada);
            } // Destino ELEFANTE
            else if (!grupo1Completado && (label == objOCartaNivel9.jLabel6 || label == objOCartaNivel9.jLabel7 || label == objOCartaNivel9.jLabel8 || label == objOCartaNivel9.jLabel9)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel9.jLabel6) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba1) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel9.jLabel7) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba2) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel9.jLabel8) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba3) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba4) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo ELEFANTE destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo1();
                }
            } // Destino ELOTE
            else if (!grupo2Completado && (label == objOCartaNivel9.jLabel13 || label == objOCartaNivel9.jLabel14 || label == objOCartaNivel9.jLabel15)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel9.jLabel13) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba5) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel9.jLabel14) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba6) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba7) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo ELOTE destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo2();
                }
            } // Destino ESCOBA
            else if (!grupo3Completado && (label == objOCartaNivel9.jLabel19 || label == objOCartaNivel9.jLabel20 || label == objOCartaNivel9.jLabel21)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel9.jLabel19) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba8) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel9.jLabel20) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba9) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba10) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo ESCOBA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo3();
                }
            } // Destino VENTANA
            else if (!grupo4Completado && (label == objOCartaNivel9.jLabel25 || label == objOCartaNivel9.jLabel26 || label == objOCartaNivel9.jLabel27)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel9.jLabel25) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba11) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel9.jLabel26) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba12) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba13) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo VENTANA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo4();
                }
            }
        }
        evaluarFinDeJuego();
    }

    private void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            // ELEFANTE
            Palabra palabraActual = lista.get(56);
            String palabraCompleta1 = palabraActual.getPalabra();
            if (palabraCompleta1.length() >= 4) {
                this.silaba1 = palabraCompleta1.substring(0, 1);
                this.silaba2 = palabraCompleta1.substring(1, 3);
                this.silaba3 = palabraCompleta1.substring(3, 6);
                this.silaba4 = palabraCompleta1.substring(6, 8);
                objOCartaNivel9.jLabel2.setText(silaba1);
                objOCartaNivel9.jLabel3.setText(silaba2);
                objOCartaNivel9.jLabel4.setText(silaba3);
                objOCartaNivel9.jLabel5.setText(silaba4);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo ELEFANTE.");
            }

            // ELOTE
            Palabra palabraActual2 = lista.get(57);
            String palabraCompleta2 = palabraActual2.getPalabra();
            if (palabraCompleta2.length() >= 4) {
                this.silaba5 = palabraCompleta2.substring(0, 1);
                this.silaba6 = palabraCompleta2.substring(1, 3);
                this.silaba7 = palabraCompleta2.substring(3, 5);
                objOCartaNivel9.jLabel10.setText(silaba5);
                objOCartaNivel9.jLabel11.setText(silaba6);
                objOCartaNivel9.jLabel12.setText(silaba7);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo ELOTE.");
            }

            // ESCOBA
            Palabra palabraActual3 = lista.get(58);
            String palabraCompleta3 = palabraActual3.getPalabra();
            if (palabraCompleta3.length() >= 4) {
                this.silaba8 = palabraCompleta3.substring(0, 2);
                this.silaba9 = palabraCompleta3.substring(2, 4);
                this.silaba10 = palabraCompleta3.substring(4, 6);
                objOCartaNivel9.jLabel16.setText(silaba8);
                objOCartaNivel9.jLabel17.setText(silaba9);
                objOCartaNivel9.jLabel18.setText(silaba10);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo ESCOBA.");
            }

            // VENTANA
            Palabra palabraActual4 = lista.get(59);
            String palabraCompleta4 = palabraActual4.getPalabra();
            if (palabraCompleta4.length() >= 6) {
                this.silaba11 = palabraCompleta4.substring(0, 3);
                this.silaba12 = palabraCompleta4.substring(3, 5);
                this.silaba13 = palabraCompleta4.substring(5, 7);
                objOCartaNivel9.jLabel22.setText(silaba11);
                objOCartaNivel9.jLabel23.setText(silaba12);
                objOCartaNivel9.jLabel24.setText(silaba13);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo VENTANA.");
            }
        }
    }

    private void verificarGrupo1() {
        String parte1 = objOCartaNivel9.jLabel6.getText();
        String parte2 = objOCartaNivel9.jLabel7.getText();
        String parte3 = objOCartaNivel9.jLabel8.getText();
        String parte4 = objOCartaNivel9.jLabel9.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty() && !parte4.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3 + parte4;
            String palabraCorrecta = silaba1 + silaba2 + silaba3 + silaba4;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("elefante");
                deshabilitarGrupo1();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo2() {
        String parte1 = objOCartaNivel9.jLabel13.getText();
        String parte2 = objOCartaNivel9.jLabel14.getText();
        String parte3 = objOCartaNivel9.jLabel15.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba5 + silaba6 + silaba7;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("elote");
                deshabilitarGrupo2();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo3() {
        String parte1 = objOCartaNivel9.jLabel19.getText();
        String parte2 = objOCartaNivel9.jLabel20.getText();
        String parte3 = objOCartaNivel9.jLabel21.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba8 + silaba9 + silaba10;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("escoba");
                deshabilitarGrupo3();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo4() {
        String parte1 = objOCartaNivel9.jLabel25.getText();
        String parte2 = objOCartaNivel9.jLabel26.getText();
        String parte3 = objOCartaNivel9.jLabel27.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba11 + silaba12 + silaba13;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("ventana");
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
        terminoConExito = true;
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
        objOCartaNivel9.dispose();
    }

    private void deshabilitarGrupo1() {
        grupo1Completado = true;
        objOCartaNivel9.jLabel6.setEnabled(false);
        objOCartaNivel9.jLabel7.setEnabled(false);
        objOCartaNivel9.jLabel8.setEnabled(false);
        objOCartaNivel9.jLabel9.setEnabled(false);
    }

    private void deshabilitarGrupo2() {
        grupo2Completado = true;
        objOCartaNivel9.jLabel13.setEnabled(false);
        objOCartaNivel9.jLabel14.setEnabled(false);
        objOCartaNivel9.jLabel15.setEnabled(false);
    }

    private void deshabilitarGrupo3() {
        grupo3Completado = true;
        objOCartaNivel9.jLabel19.setEnabled(false);
        objOCartaNivel9.jLabel20.setEnabled(false);
        objOCartaNivel9.jLabel21.setEnabled(false);
    }

    private void deshabilitarGrupo4() {
        grupo4Completado = true;
        objOCartaNivel9.jLabel25.setEnabled(false);
        objOCartaNivel9.jLabel26.setEnabled(false);
        objOCartaNivel9.jLabel27.setEnabled(false);
    }

    private void evaluarFinDeJuego() {
        if (terminoConExito) {
            return;
        }

        boolean todasLlenas
                = !objOCartaNivel9.jLabel6.getText().isEmpty()
                && !objOCartaNivel9.jLabel7.getText().isEmpty()
                && !objOCartaNivel9.jLabel8.getText().isEmpty()
                && !objOCartaNivel9.jLabel9.getText().isEmpty()
                && !objOCartaNivel9.jLabel13.getText().isEmpty()
                && !objOCartaNivel9.jLabel14.getText().isEmpty()
                && !objOCartaNivel9.jLabel15.getText().isEmpty()
                && !objOCartaNivel9.jLabel19.getText().isEmpty()
                && !objOCartaNivel9.jLabel20.getText().isEmpty()
                && !objOCartaNivel9.jLabel21.getText().isEmpty()
                && !objOCartaNivel9.jLabel25.getText().isEmpty()
                && !objOCartaNivel9.jLabel26.getText().isEmpty()
                && !objOCartaNivel9.jLabel27.getText().isEmpty();

        if (todasLlenas) {
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "¡Juego terminado!\n¿Deseas reiniciar o volver al menú?",
                    "Fin de juego",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Reiniciar", "Salir al menú"},
                    "Reiniciar"
            );
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego();
            } else {
                new vista.MenuOrdenar().setVisible(true);
                objOCartaNivel9.dispose();
            }
        }
    }

    private void reiniciarJuego() {
        // limpiar destinos
        JLabel[] destinos = {
            objOCartaNivel9.jLabel6, objOCartaNivel9.jLabel7, objOCartaNivel9.jLabel8, objOCartaNivel9.jLabel9,
            objOCartaNivel9.jLabel13, objOCartaNivel9.jLabel14, objOCartaNivel9.jLabel15,
            objOCartaNivel9.jLabel19, objOCartaNivel9.jLabel20, objOCartaNivel9.jLabel21,
            objOCartaNivel9.jLabel25, objOCartaNivel9.jLabel26, objOCartaNivel9.jLabel27
        };
        for (JLabel lbl : destinos) {
            lbl.setText("");
            lbl.setEnabled(true);
            lbl.setForeground(Color.BLACK);
        }

        // habilitar orígenes
        JLabel[] origenes = {
            objOCartaNivel9.jLabel2, objOCartaNivel9.jLabel3, objOCartaNivel9.jLabel4, objOCartaNivel9.jLabel5,
            objOCartaNivel9.jLabel10, objOCartaNivel9.jLabel11, objOCartaNivel9.jLabel12,
            objOCartaNivel9.jLabel16, objOCartaNivel9.jLabel17, objOCartaNivel9.jLabel18,
            objOCartaNivel9.jLabel22, objOCartaNivel9.jLabel23, objOCartaNivel9.jLabel24
        };
        for (JLabel lbl : origenes) {
            lbl.setEnabled(true);
        }

        grupo1Completado = grupo2Completado = grupo3Completado = grupo4Completado = false;
        terminoConExito = false;
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
