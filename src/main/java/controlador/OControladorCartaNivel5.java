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
import vista.OCartaNivel5;

public class OControladorCartaNivel5 implements MouseListener {

    private OCartaNivel5 objOCartaNivel5;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private static final Color VERDE = new Color(34, 139, 34);

    private String silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7, silaba8, silaba9, silaba10, silaba11;
    private String silabaSeleccionada;
    private JLabel labelOrigenSeleccionada;

    private boolean grupo1Completado = false;
    private boolean grupo2Completado = false;
    private boolean grupo3Completado = false;
    private boolean grupo4Completado = false;
    private boolean terminoConExito = false;

    public OControladorCartaNivel5(OCartaNivel5 objOCartaNivel5) {
        this.objOCartaNivel5 = objOCartaNivel5;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();
        this.objOCartaNivel5.jButton1.addMouseListener(this);

        // NUBE
        this.objOCartaNivel5.jLabel2.addMouseListener(this);
        this.objOCartaNivel5.jLabel3.addMouseListener(this);
        this.objOCartaNivel5.jLabel4.addMouseListener(this);
        this.objOCartaNivel5.jLabel5.addMouseListener(this);
        // CUCHARA
        this.objOCartaNivel5.jLabel6.addMouseListener(this);
        this.objOCartaNivel5.jLabel7.addMouseListener(this);
        this.objOCartaNivel5.jLabel8.addMouseListener(this);
        this.objOCartaNivel5.jLabel9.addMouseListener(this);
        this.objOCartaNivel5.jLabel10.addMouseListener(this);
        this.objOCartaNivel5.jLabel11.addMouseListener(this);
        // MOCHILA
        this.objOCartaNivel5.jLabel12.addMouseListener(this);
        this.objOCartaNivel5.jLabel13.addMouseListener(this);
        this.objOCartaNivel5.jLabel14.addMouseListener(this);
        this.objOCartaNivel5.jLabel15.addMouseListener(this);
        this.objOCartaNivel5.jLabel16.addMouseListener(this);
        this.objOCartaNivel5.jLabel17.addMouseListener(this);
        // GALLLINA
        this.objOCartaNivel5.jLabel18.addMouseListener(this);
        this.objOCartaNivel5.jLabel19.addMouseListener(this);
        this.objOCartaNivel5.jLabel20.addMouseListener(this);
        this.objOCartaNivel5.jLabel21.addMouseListener(this);
        this.objOCartaNivel5.jLabel22.addMouseListener(this);
        this.objOCartaNivel5.jLabel23.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == this.objOCartaNivel5.jButton1) {
            MenuOrdenar objMenuOrdenar = new MenuOrdenar();
            objMenuOrdenar.setVisible(true);
            this.objOCartaNivel5.dispose();
        }
        if (source instanceof JLabel) {
            JLabel label = (JLabel) source;

            if (!label.isEnabled()) {
                return;
            }

            // Labels origen
            if (label == objOCartaNivel5.jLabel2 || label == objOCartaNivel5.jLabel3
                    || label == objOCartaNivel5.jLabel6 || label == objOCartaNivel5.jLabel7 || label == objOCartaNivel5.jLabel8
                    || label == objOCartaNivel5.jLabel12 || label == objOCartaNivel5.jLabel13 || label == objOCartaNivel5.jLabel14
                    || label == objOCartaNivel5.jLabel18 || label == objOCartaNivel5.jLabel19
                    || label == objOCartaNivel5.jLabel20) {
                silabaSeleccionada = label.getText();
                labelOrigenSeleccionada = label;
                System.out.println("Selección: " + silabaSeleccionada);
                objAudio.reproducirAudio(silabaSeleccionada);
            } // Destino NUBE
            else if (!grupo1Completado && (label == objOCartaNivel5.jLabel4 || label == objOCartaNivel5.jLabel5)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);

                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel5.jLabel4) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba1) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba2) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo NUBE destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo1();
                }
            } // Destino CUCHARA
            else if (!grupo2Completado && (label == objOCartaNivel5.jLabel9 || label == objOCartaNivel5.jLabel10 || label == objOCartaNivel5.jLabel11)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel5.jLabel9) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba3) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel5.jLabel10) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba4) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba11) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo CUCHARA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo2();
                }
            } // Destino MOCHILA
            else if (!grupo3Completado && (label == objOCartaNivel5.jLabel15 || label == objOCartaNivel5.jLabel16 || label == objOCartaNivel5.jLabel17)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel5.jLabel15) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba5) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel5.jLabel16) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba6) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba7) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo MOCHILA destino llenado con: " + silabaSeleccionada);
                    silabaSeleccionada = null;
                    labelOrigenSeleccionada = null;
                    verificarGrupo3();
                }
            } // Destino GALLINA
            else if (!grupo4Completado && (label == objOCartaNivel5.jLabel21 || label == objOCartaNivel5.jLabel22 || label == objOCartaNivel5.jLabel23)) {
                if (silabaSeleccionada != null && label.getText().isEmpty()) {
                    label.setText(silabaSeleccionada);
                    labelOrigenSeleccionada.setEnabled(false);
                    if (label == objOCartaNivel5.jLabel21) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba8) ? VERDE : Color.RED);
                    } else if (label == objOCartaNivel5.jLabel22) {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba9) ? VERDE : Color.RED);
                    } else {
                        label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba10) ? VERDE : Color.RED);
                    }
                    System.out.println("Grupo GALLINA destino llenado con: " + silabaSeleccionada);
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
            // NUBE
            Palabra palabraActual = lista.get(40);
            String palabraCompleta1 = palabraActual.getPalabra();
            if (palabraCompleta1.length() >= 4) {
                this.silaba1 = palabraCompleta1.substring(0, 2);
                this.silaba2 = palabraCompleta1.substring(2, 4);
                objOCartaNivel5.jLabel2.setText(silaba1);
                objOCartaNivel5.jLabel3.setText(silaba2);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo NUBE.");
            }

            // CUCHARA
            Palabra palabraActual2 = lista.get(41);
            String palabraCompleta2 = palabraActual2.getPalabra();
            if (palabraCompleta2.length() >= 4) {
                this.silaba3 = palabraCompleta2.substring(0, 2);
                this.silaba4 = palabraCompleta2.substring(2, 5);
                this.silaba11 = palabraCompleta2.substring(5, 7);
                objOCartaNivel5.jLabel6.setText(silaba3);
                objOCartaNivel5.jLabel7.setText(silaba4);
                objOCartaNivel5.jLabel8.setText(silaba11);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo CUCHARA.");
            }

            // MOCHILA
            Palabra palabraActual3 = lista.get(42);
            String palabraCompleta3 = palabraActual3.getPalabra();
            if (palabraCompleta3.length() >= 6) {
                this.silaba5 = palabraCompleta3.substring(0, 2);
                this.silaba6 = palabraCompleta3.substring(2, 5);
                this.silaba7 = palabraCompleta3.substring(5, 7);
                objOCartaNivel5.jLabel12.setText(silaba5);
                objOCartaNivel5.jLabel13.setText(silaba6);
                objOCartaNivel5.jLabel14.setText(silaba7);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo MOCHILA.");
            }

            // GALLINA
            Palabra palabraActual4 = lista.get(43);
            String palabraCompleta4 = palabraActual4.getPalabra();
            if (palabraCompleta4.length() >= 6) {
                this.silaba8 = palabraCompleta4.substring(0, 2);
                this.silaba9 = palabraCompleta4.substring(2, 5);
                this.silaba10 = palabraCompleta4.substring(5, 7);
                objOCartaNivel5.jLabel18.setText(silaba8);
                objOCartaNivel5.jLabel19.setText(silaba9);
                objOCartaNivel5.jLabel20.setText(silaba10);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una palabra válida para el Grupo GALLINA.");
            }
        }
    }

    private void verificarGrupo1() {
        String parte1 = objOCartaNivel5.jLabel4.getText();
        String parte2 = objOCartaNivel5.jLabel5.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty()) {
            String palabraFormada = parte1 + parte2;
            String palabraCorrecta = silaba1 + silaba2;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("nube");
                deshabilitarGrupo1();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo2() {
        String parte1 = objOCartaNivel5.jLabel9.getText();
        String parte2 = objOCartaNivel5.jLabel10.getText();
        String parte3 = objOCartaNivel5.jLabel11.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba3 + silaba4 + silaba11;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("cuchara");
                deshabilitarGrupo2();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo3() {
        String parte1 = objOCartaNivel5.jLabel15.getText();
        String parte2 = objOCartaNivel5.jLabel16.getText();
        String parte3 = objOCartaNivel5.jLabel17.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba5 + silaba6 + silaba7;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("mochila");
                deshabilitarGrupo3();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto. Formaste: " + palabraFormada);
            }
        }
    }

    private void verificarGrupo4() {
        String parte1 = objOCartaNivel5.jLabel21.getText();
        String parte2 = objOCartaNivel5.jLabel22.getText();
        String parte3 = objOCartaNivel5.jLabel23.getText();

        if (!parte1.isEmpty() && !parte2.isEmpty() && !parte3.isEmpty()) {
            String palabraFormada = parte1 + parte2 + parte3;
            String palabraCorrecta = silaba8 + silaba9 + silaba10;
            if (palabraFormada.equalsIgnoreCase(palabraCorrecta)) {
                objAudio.reproducirAudio("gallina");
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
        objOCartaNivel5.dispose();
    }

    private void deshabilitarGrupo1() {
        grupo1Completado = true;
        objOCartaNivel5.jLabel4.setEnabled(false);
        objOCartaNivel5.jLabel5.setEnabled(false);
    }

    private void deshabilitarGrupo2() {
        grupo2Completado = true;
        objOCartaNivel5.jLabel9.setEnabled(false);
        objOCartaNivel5.jLabel10.setEnabled(false);
        objOCartaNivel5.jLabel11.setEnabled(false);
    }

    private void deshabilitarGrupo3() {
        grupo3Completado = true;
        objOCartaNivel5.jLabel15.setEnabled(false);
        objOCartaNivel5.jLabel16.setEnabled(false);
        objOCartaNivel5.jLabel17.setEnabled(false);
    }

    private void deshabilitarGrupo4() {
        grupo4Completado = true;
        objOCartaNivel5.jLabel21.setEnabled(false);
        objOCartaNivel5.jLabel22.setEnabled(false);
        objOCartaNivel5.jLabel23.setEnabled(false);
    }

    private void evaluarFinDeJuego() {
        if (terminoConExito) {
            return;
        }

        boolean todasLlenas
                = !objOCartaNivel5.jLabel4.getText().isEmpty()
                && !objOCartaNivel5.jLabel5.getText().isEmpty()
                && !objOCartaNivel5.jLabel9.getText().isEmpty()
                && !objOCartaNivel5.jLabel10.getText().isEmpty()
                && !objOCartaNivel5.jLabel11.getText().isEmpty()
                && !objOCartaNivel5.jLabel15.getText().isEmpty()
                && !objOCartaNivel5.jLabel16.getText().isEmpty()
                && !objOCartaNivel5.jLabel17.getText().isEmpty()
                && !objOCartaNivel5.jLabel21.getText().isEmpty()
                && !objOCartaNivel5.jLabel22.getText().isEmpty()
                && !objOCartaNivel5.jLabel23.getText().isEmpty();

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
                objOCartaNivel5.dispose();
            }
        }
    }

    private void reiniciarJuego() {
        // limpiar destinos
        JLabel[] destinos = {
            objOCartaNivel5.jLabel4, objOCartaNivel5.jLabel5,
            objOCartaNivel5.jLabel9, objOCartaNivel5.jLabel10, objOCartaNivel5.jLabel11,
            objOCartaNivel5.jLabel15, objOCartaNivel5.jLabel16, objOCartaNivel5.jLabel17,
            objOCartaNivel5.jLabel21, objOCartaNivel5.jLabel22, objOCartaNivel5.jLabel23
        };
        for (JLabel lbl : destinos) {
            lbl.setText("");
            lbl.setEnabled(true);
            lbl.setForeground(Color.BLACK);
        }

        // habilitar orígenes
        JLabel[] origenes = {
            objOCartaNivel5.jLabel2, objOCartaNivel5.jLabel3,
            objOCartaNivel5.jLabel6, objOCartaNivel5.jLabel7, objOCartaNivel5.jLabel8,
            objOCartaNivel5.jLabel12, objOCartaNivel5.jLabel13, objOCartaNivel5.jLabel14,
            objOCartaNivel5.jLabel18, objOCartaNivel5.jLabel19, objOCartaNivel5.jLabel20
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
