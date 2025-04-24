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
import vista.OCartaNivel2;

public class OControladorCartaNivel2 implements MouseListener {

    private OCartaNivel2 objOCartaNivel2;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private static final Color VERDE = new Color(34, 139, 34);

    private String silaba1, silaba2, silaba3, silaba4, silaba5, silaba6, silaba7, silaba8, silaba9, silaba10;
    private String silabaSeleccionada;
    private JLabel labelOrigenSeleccionada;

    private boolean grupo1Completado = false;
    private boolean grupo2Completado = false;
    private boolean grupo3Completado = false;
    private boolean grupo4Completado = false;
    private boolean terminoConExito = false;

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

        objOCartaNivel2.jButton1.addMouseListener(this);
        // Orígenes
        objOCartaNivel2.jLabel2.addMouseListener(this);
        objOCartaNivel2.jLabel3.addMouseListener(this);
        objOCartaNivel2.jLabel6.addMouseListener(this);
        objOCartaNivel2.jLabel7.addMouseListener(this);
        objOCartaNivel2.jLabel10.addMouseListener(this);
        objOCartaNivel2.jLabel11.addMouseListener(this);
        objOCartaNivel2.jLabel12.addMouseListener(this);
        objOCartaNivel2.jLabel16.addMouseListener(this);
        objOCartaNivel2.jLabel17.addMouseListener(this);
        objOCartaNivel2.jLabel18.addMouseListener(this);
        // Destinos
        objOCartaNivel2.jLabel4.addMouseListener(this);
        objOCartaNivel2.jLabel5.addMouseListener(this);
        objOCartaNivel2.jLabel8.addMouseListener(this);
        objOCartaNivel2.jLabel9.addMouseListener(this);
        objOCartaNivel2.jLabel13.addMouseListener(this);
        objOCartaNivel2.jLabel14.addMouseListener(this);
        objOCartaNivel2.jLabel15.addMouseListener(this);
        objOCartaNivel2.jLabel19.addMouseListener(this);
        objOCartaNivel2.jLabel20.addMouseListener(this);
        objOCartaNivel2.jLabel21.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == objOCartaNivel2.jButton1) {
            new MenuOrdenar().setVisible(true);
            objOCartaNivel2.dispose();
            return;
        }
        if (!(source instanceof JLabel)) return;
        JLabel label = (JLabel) source;
        if (!label.isEnabled()) return;

        // Orígenes
        if (label == objOCartaNivel2.jLabel2 || label == objOCartaNivel2.jLabel3
         || label == objOCartaNivel2.jLabel6 || label == objOCartaNivel2.jLabel7
         || label == objOCartaNivel2.jLabel10 || label == objOCartaNivel2.jLabel11 || label == objOCartaNivel2.jLabel12
         || label == objOCartaNivel2.jLabel16 || label == objOCartaNivel2.jLabel17 || label == objOCartaNivel2.jLabel18) {
            silabaSeleccionada = label.getText();
            labelOrigenSeleccionada = label;
            objAudio.reproducirAudio(silabaSeleccionada.toLowerCase());
            return;
        }

        // Destino LLAVE
        if (!grupo1Completado && (label == objOCartaNivel2.jLabel4 || label == objOCartaNivel2.jLabel5)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                // color de verificación inmediata
                if (label == objOCartaNivel2.jLabel4) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba1) ? VERDE : Color.RED);
                } else {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba2) ? VERDE : Color.RED);
                }
                silabaSeleccionada = null;
                labelOrigenSeleccionada = null;
                verificarGrupo1();
            }
        }
        // Destino PERA
        else if (!grupo2Completado && (label == objOCartaNivel2.jLabel8 || label == objOCartaNivel2.jLabel9)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                if (label == objOCartaNivel2.jLabel8) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba3) ? VERDE : Color.RED);
                } else {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba4) ? VERDE : Color.RED);
                }
                silabaSeleccionada = null;
                labelOrigenSeleccionada = null;
                verificarGrupo2();
            }
        }
        // Destino ARDILLA
        else if (!grupo3Completado && (label == objOCartaNivel2.jLabel13 || label == objOCartaNivel2.jLabel14 || label == objOCartaNivel2.jLabel15)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                if (label == objOCartaNivel2.jLabel13) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba5) ? VERDE : Color.RED);
                } else if (label == objOCartaNivel2.jLabel14) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba6) ? VERDE : Color.RED);
                } else {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba7) ? VERDE : Color.RED);
                }
                silabaSeleccionada = null;
                labelOrigenSeleccionada = null;
                verificarGrupo3();
            }
        }
        // Destino CAMISA
        else if (!grupo4Completado && (label == objOCartaNivel2.jLabel19 || label == objOCartaNivel2.jLabel20 || label == objOCartaNivel2.jLabel21)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                if (label == objOCartaNivel2.jLabel19) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba8) ? VERDE : Color.RED);
                } else if (label == objOCartaNivel2.jLabel20) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba9) ? VERDE : Color.RED);
                } else {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba10) ? VERDE : Color.RED);
                }
                silabaSeleccionada = null;
                labelOrigenSeleccionada = null;
                verificarGrupo4();
            }
        }

        evaluarFinDeJuego();
    }

    private void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            // LLAVE (3+2)
            Palabra p1 = lista.get(28);
            String pc1 = p1.getPalabra();
            if (pc1.length() >= 5) {
                silaba1 = pc1.substring(0, 3);
                silaba2 = pc1.substring(3, 5);
                objOCartaNivel2.jLabel2.setText(silaba1);
                objOCartaNivel2.jLabel3.setText(silaba2);
            } else JOptionPane.showMessageDialog(null, "No válida LLAVE");
            // PERA (2+2)
            Palabra p2 = lista.get(29);
            String pc2 = p2.getPalabra();
            if (pc2.length() >= 4) {
                silaba3 = pc2.substring(0, 2);
                silaba4 = pc2.substring(2, 4);
                objOCartaNivel2.jLabel6.setText(silaba3);
                objOCartaNivel2.jLabel7.setText(silaba4);
            } else JOptionPane.showMessageDialog(null, "No válida PERA");
            // ARDILLA (2+2+3)
            Palabra p3 = lista.get(30);
            String pc3 = p3.getPalabra();
            if (pc3.length() >= 7) {
                silaba5 = pc3.substring(0, 2);
                silaba6 = pc3.substring(2, 4);
                silaba7 = pc3.substring(4, 7);
                objOCartaNivel2.jLabel10.setText(silaba5);
                objOCartaNivel2.jLabel11.setText(silaba6);
                objOCartaNivel2.jLabel12.setText(silaba7);
            } else JOptionPane.showMessageDialog(null, "No válida ARDILLA");
            // CAMISA (2+2+2)
            Palabra p4 = lista.get(31);
            String pc4 = p4.getPalabra();
            if (pc4.length() >= 6) {
                silaba8 = pc4.substring(0, 2);
                silaba9 = pc4.substring(2, 4);
                silaba10 = pc4.substring(4, 6);
                objOCartaNivel2.jLabel16.setText(silaba8);
                objOCartaNivel2.jLabel17.setText(silaba9);
                objOCartaNivel2.jLabel18.setText(silaba10);
            } else JOptionPane.showMessageDialog(null, "No válida CAMISA");
        }
    }

    private void verificarGrupo1() {
        String p1 = objOCartaNivel2.jLabel4.getText();
        String p2 = objOCartaNivel2.jLabel5.getText();
        if (!p1.isEmpty() && !p2.isEmpty()) {
            String formado = p1 + p2;
            if (formado.equalsIgnoreCase(silaba1 + silaba2)) {
                objAudio.reproducirAudio("llave");
                deshabilitarGrupo1();
                verificarSiYaCompletasteTodo();
            } else JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
        }
    }

    private void verificarGrupo2() {
        String p1 = objOCartaNivel2.jLabel8.getText();
        String p2 = objOCartaNivel2.jLabel9.getText();
        if (!p1.isEmpty() && !p2.isEmpty()) {
            String formado = p1 + p2;
            if (formado.equalsIgnoreCase(silaba3 + silaba4)) {
                objAudio.reproducirAudio("pera");
                deshabilitarGrupo2();
                verificarSiYaCompletasteTodo();
            } else JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
        }
    }

    private void verificarGrupo3() {
        String p1 = objOCartaNivel2.jLabel13.getText();
        String p2 = objOCartaNivel2.jLabel14.getText();
        String p3 = objOCartaNivel2.jLabel15.getText();
        if (!p1.isEmpty() && !p2.isEmpty() && !p3.isEmpty()) {
            String formado = p1 + p2 + p3;
            if (formado.equalsIgnoreCase(silaba5 + silaba6 + silaba7)) {
                objAudio.reproducirAudio("ardilla");
                deshabilitarGrupo3();
                verificarSiYaCompletasteTodo();
            } else JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
        }
    }

    private void verificarGrupo4() {
        String p1 = objOCartaNivel2.jLabel19.getText();
        String p2 = objOCartaNivel2.jLabel20.getText();
        String p3 = objOCartaNivel2.jLabel21.getText();
        if (!p1.isEmpty() && !p2.isEmpty() && !p3.isEmpty()) {
            String formado = p1 + p2 + p3;
            if (formado.equalsIgnoreCase(silaba8 + silaba9 + silaba10)) {
                objAudio.reproducirAudio("camisa");
                deshabilitarGrupo4();
                verificarSiYaCompletasteTodo();
            } else JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
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

    private void actPuntaje() {
        terminoConExito = true;
        int idUsuario = modelo.Login.getIdUsuarioActivo();
        OperacionesBDCuenta op = new OperacionesBDCuenta();
        op.actualizarPuntajeYPalabras(idUsuario, 40, 4);
        JOptionPane.showMessageDialog(null,
            "¡Felicidades! Completaste las 4 palabras 🎉\nGanaste 40 puntos 🏆",
            "Nivel Completado", JOptionPane.INFORMATION_MESSAGE);
        new MenuOrdenar().setVisible(true);
        objOCartaNivel2.dispose();
    }

    private void verificarSiYaCompletasteTodo() {
        if (grupo1Completado && grupo2Completado && grupo3Completado && grupo4Completado) {
            actPuntaje();
        }
    }

    private void evaluarFinDeJuego() {
        if (terminoConExito) return;
        boolean todasLlenas =
            !objOCartaNivel2.jLabel4.getText().isEmpty() &&
            !objOCartaNivel2.jLabel5.getText().isEmpty() &&
            !objOCartaNivel2.jLabel8.getText().isEmpty() &&
            !objOCartaNivel2.jLabel9.getText().isEmpty() &&
            !objOCartaNivel2.jLabel13.getText().isEmpty() &&
            !objOCartaNivel2.jLabel14.getText().isEmpty() &&
            !objOCartaNivel2.jLabel15.getText().isEmpty() &&
            !objOCartaNivel2.jLabel19.getText().isEmpty() &&
            !objOCartaNivel2.jLabel20.getText().isEmpty() &&
            !objOCartaNivel2.jLabel21.getText().isEmpty();

        if (todasLlenas) {
            int opcion = JOptionPane.showOptionDialog(
                null,
                "¡Juego terminado!\n¿Deseas reiniciar o volver al menú?",
                "Fin de juego",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Reiniciar","Salir al menú"},
                "Reiniciar"
            );
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego();
            } else {
                new MenuOrdenar().setVisible(true);
                objOCartaNivel2.dispose();
            }
        }
    }

    private void reiniciarJuego() {
        // Limpiar destinos
        JLabel[] destinos = {
            objOCartaNivel2.jLabel4, objOCartaNivel2.jLabel5,
            objOCartaNivel2.jLabel8, objOCartaNivel2.jLabel9,
            objOCartaNivel2.jLabel13, objOCartaNivel2.jLabel14, objOCartaNivel2.jLabel15,
            objOCartaNivel2.jLabel19, objOCartaNivel2.jLabel20, objOCartaNivel2.jLabel21
        };
        for (JLabel lbl : destinos) {
            lbl.setText("");
            lbl.setEnabled(true);
            lbl.setForeground(Color.BLACK);
        }
        // Habilitar orígenes
        JLabel[] origenes = {
            objOCartaNivel2.jLabel2, objOCartaNivel2.jLabel3,
            objOCartaNivel2.jLabel6, objOCartaNivel2.jLabel7,
            objOCartaNivel2.jLabel10, objOCartaNivel2.jLabel11, objOCartaNivel2.jLabel12,
            objOCartaNivel2.jLabel16, objOCartaNivel2.jLabel17, objOCartaNivel2.jLabel18
        };
        for (JLabel lbl : origenes) {
            lbl.setEnabled(true);
        }
        grupo1Completado = grupo2Completado = grupo3Completado = grupo4Completado = false;
        terminoConExito = false;
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
