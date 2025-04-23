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
import vista.OCartaNivel1;

public class OControladorCartaNivel1 implements MouseListener {

    private OCartaNivel1 objOCartaNivel1;
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

    public OControladorCartaNivel1(OCartaNivel1 objOCartaNivel1) {
        this.objOCartaNivel1 = objOCartaNivel1;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        objAudio = new ControladorAudios();
        
        objOCartaNivel1.jButton1.addMouseListener(this);

        // TOPO
        objOCartaNivel1.jLabel2.addMouseListener(this);
        objOCartaNivel1.jLabel3.addMouseListener(this);
        objOCartaNivel1.jLabel4.addMouseListener(this);
        objOCartaNivel1.jLabel5.addMouseListener(this);
        // PILA
        objOCartaNivel1.jLabel6.addMouseListener(this);
        objOCartaNivel1.jLabel7.addMouseListener(this);
        objOCartaNivel1.jLabel8.addMouseListener(this);
        objOCartaNivel1.jLabel9.addMouseListener(this);
        // TOMATE
        objOCartaNivel1.jLabel10.addMouseListener(this);
        objOCartaNivel1.jLabel11.addMouseListener(this);
        objOCartaNivel1.jLabel12.addMouseListener(this);
        objOCartaNivel1.jLabel13.addMouseListener(this);
        objOCartaNivel1.jLabel14.addMouseListener(this);
        objOCartaNivel1.jLabel15.addMouseListener(this);
        // PILOTO
        objOCartaNivel1.jLabel16.addMouseListener(this);
        objOCartaNivel1.jLabel17.addMouseListener(this);
        objOCartaNivel1.jLabel18.addMouseListener(this);
        objOCartaNivel1.jLabel19.addMouseListener(this);
        objOCartaNivel1.jLabel20.addMouseListener(this);
        objOCartaNivel1.jLabel21.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if(source == objOCartaNivel1.jButton1){
            MenuOrdenar menuOrdenar = new MenuOrdenar();
            menuOrdenar.setVisible(true);
            this.objOCartaNivel1.dispose();
        }
        if (!(source instanceof JLabel)) return;
        JLabel label = (JLabel) source;
        if (!label.isEnabled()) return;

        // Orígenes
        if (label == objOCartaNivel1.jLabel2 || label == objOCartaNivel1.jLabel3
         || label == objOCartaNivel1.jLabel6 || label == objOCartaNivel1.jLabel7
         || label == objOCartaNivel1.jLabel10 || label == objOCartaNivel1.jLabel11
         || label == objOCartaNivel1.jLabel12 || label == objOCartaNivel1.jLabel16
         || label == objOCartaNivel1.jLabel17 || label == objOCartaNivel1.jLabel18) {
            silabaSeleccionada = label.getText();
            labelOrigenSeleccionada = label;
            objAudio.reproducirAudio(silabaSeleccionada.toLowerCase());
            return;
        }

        // Destino TOPO
        if (!grupo1Completado && (label == objOCartaNivel1.jLabel4 || label == objOCartaNivel1.jLabel5)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                if (label == objOCartaNivel1.jLabel4) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba1) ? VERDE : Color.RED);
                } else {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba2) ? VERDE : Color.RED);
                }
                silabaSeleccionada = null;
                labelOrigenSeleccionada = null;
                verificarGrupo1();
            }
        }
        // Destino PILA
        else if (!grupo2Completado && (label == objOCartaNivel1.jLabel8 || label == objOCartaNivel1.jLabel9)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                if (label == objOCartaNivel1.jLabel8) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba3) ? VERDE : Color.RED);
                } else {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba4) ? VERDE : Color.RED);
                }
                silabaSeleccionada = null;
                labelOrigenSeleccionada = null;
                verificarGrupo2();
            }
        }
        // Destino TOMATE
        else if (!grupo3Completado && (label == objOCartaNivel1.jLabel13 || label == objOCartaNivel1.jLabel14 || label == objOCartaNivel1.jLabel15)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                if (label == objOCartaNivel1.jLabel13) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba5) ? VERDE : Color.RED);
                } else if (label == objOCartaNivel1.jLabel14) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba6) ? VERDE : Color.RED);
                } else {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba7) ? VERDE : Color.RED);
                }
                silabaSeleccionada = null;
                labelOrigenSeleccionada = null;
                verificarGrupo3();
            }
        }
        // Destino PILOTO
        else if (!grupo4Completado && (label == objOCartaNivel1.jLabel19 || label == objOCartaNivel1.jLabel20 || label == objOCartaNivel1.jLabel21)) {
            if (silabaSeleccionada != null && label.getText().isEmpty()) {
                label.setText(silabaSeleccionada);
                labelOrigenSeleccionada.setEnabled(false);
                if (label == objOCartaNivel1.jLabel19) {
                    label.setForeground(silabaSeleccionada.equalsIgnoreCase(silaba8) ? VERDE : Color.RED);
                } else if (label == objOCartaNivel1.jLabel20) {
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
            // TOPO
            Palabra p1 = lista.get(24);
            String pc1 = p1.getPalabra();
            if (pc1.length() >= 4) {
                silaba1 = pc1.substring(0,2);
                silaba2 = pc1.substring(2,4);
                objOCartaNivel1.jLabel2.setText(silaba1);
                objOCartaNivel1.jLabel3.setText(silaba2);
            } else JOptionPane.showMessageDialog(null,"No válida TOPO");
            // PILA
            Palabra p2 = lista.get(25);
            String pc2 = p2.getPalabra();
            if (pc2.length() >= 4) {
                silaba3 = pc2.substring(0,2);
                silaba4 = pc2.substring(2,4);
                objOCartaNivel1.jLabel6.setText(silaba3);
                objOCartaNivel1.jLabel7.setText(silaba4);
            } else JOptionPane.showMessageDialog(null,"No válida PILA");
            // TOMATE
            Palabra p3 = lista.get(26);
            String pc3 = p3.getPalabra();
            if (pc3.length() >= 6) {
                silaba5 = pc3.substring(0,2);
                silaba6 = pc3.substring(2,4);
                silaba7 = pc3.substring(4,6);
                objOCartaNivel1.jLabel10.setText(silaba5);
                objOCartaNivel1.jLabel11.setText(silaba6);
                objOCartaNivel1.jLabel12.setText(silaba7);
            } else JOptionPane.showMessageDialog(null,"No válida TOMATE");
            // PILOTO
            Palabra p4 = lista.get(27);
            String pc4 = p4.getPalabra();
            if (pc4.length() >= 6) {
                silaba8 = pc4.substring(0,2);
                silaba9 = pc4.substring(2,4);
                silaba10 = pc4.substring(4,6);
                objOCartaNivel1.jLabel16.setText(silaba8);
                objOCartaNivel1.jLabel17.setText(silaba9);
                objOCartaNivel1.jLabel18.setText(silaba10);
            } else JOptionPane.showMessageDialog(null,"No válida PILOTO");
        }
    }

    private void verificarGrupo1() {
        String p1 = objOCartaNivel1.jLabel4.getText();
        String p2 = objOCartaNivel1.jLabel5.getText();
        if (!p1.isEmpty() && !p2.isEmpty()) {
            String formado = p1 + p2;
            if (formado.equalsIgnoreCase(silaba1 + silaba2)) {
                objAudio.reproducirAudio("topo");
                deshabilitarGrupo1();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
            }
        }
    }

    private void verificarGrupo2() {
        String p1 = objOCartaNivel1.jLabel8.getText();
        String p2 = objOCartaNivel1.jLabel9.getText();
        if (!p1.isEmpty() && !p2.isEmpty()) {
            String formado = p1 + p2;
            if (formado.equalsIgnoreCase(silaba3 + silaba4)) {
                objAudio.reproducirAudio("pila");
                deshabilitarGrupo2();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
            }
        }
    }

    private void verificarGrupo3() {
        String p1 = objOCartaNivel1.jLabel13.getText();
        String p2 = objOCartaNivel1.jLabel14.getText();
        String p3 = objOCartaNivel1.jLabel15.getText();
        if (!p1.isEmpty() && !p2.isEmpty() && !p3.isEmpty()) {
            String formado = p1 + p2 + p3;
            if (formado.equalsIgnoreCase(silaba5 + silaba6 + silaba7)) {
                objAudio.reproducirAudio("tomate");
                deshabilitarGrupo3();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
            }
        }
    }

    private void verificarGrupo4() {
        String p1 = objOCartaNivel1.jLabel19.getText();
        String p2 = objOCartaNivel1.jLabel20.getText();
        String p3 = objOCartaNivel1.jLabel21.getText();
        if (!p1.isEmpty() && !p2.isEmpty() && !p3.isEmpty()) {
            String formado = p1 + p2 + p3;
            if (formado.equalsIgnoreCase(silaba8 + silaba9 + silaba10)) {
                objAudio.reproducirAudio("piloto");
                deshabilitarGrupo4();
                verificarSiYaCompletasteTodo();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto: " + formado);
            }
        }
    }

    private void deshabilitarGrupo1() {
        grupo1Completado = true;
        objOCartaNivel1.jLabel4.setEnabled(false);
        objOCartaNivel1.jLabel5.setEnabled(false);
    }
    private void deshabilitarGrupo2() {
        grupo2Completado = true;
        objOCartaNivel1.jLabel8.setEnabled(false);
        objOCartaNivel1.jLabel9.setEnabled(false);
    }
    private void deshabilitarGrupo3() {
        grupo3Completado = true;
        objOCartaNivel1.jLabel13.setEnabled(false);
        objOCartaNivel1.jLabel14.setEnabled(false);
        objOCartaNivel1.jLabel15.setEnabled(false);
    }
    private void deshabilitarGrupo4() {
        grupo4Completado = true;
        objOCartaNivel1.jLabel19.setEnabled(false);
        objOCartaNivel1.jLabel20.setEnabled(false);
        objOCartaNivel1.jLabel21.setEnabled(false);
    }

    private void actPuntaje() {
        terminoConExito = true;

        int idUsuario = modelo.Login.getIdUsuarioActivo();
        OperacionesBDCuenta op = new OperacionesBDCuenta();
        op.actualizarPuntajeYPalabras(idUsuario, 40, 4);

        JOptionPane.showMessageDialog(null,
            "¡Felicidades! Completaste las 4 palabras 🎉\nGanaste 40 puntos 🏆",
            "Nivel Completado", JOptionPane.INFORMATION_MESSAGE);

        new vista.MenuOrdenar().setVisible(true);
        objOCartaNivel1.dispose();
    }

    private void verificarSiYaCompletasteTodo() {
        if (grupo1Completado && grupo2Completado && grupo3Completado && grupo4Completado) {
            actPuntaje();
        }
    }

    private void evaluarFinDeJuego() {
        if (terminoConExito) return;

        boolean todasLlenas =
            !objOCartaNivel1.jLabel4.getText().isEmpty() &&
            !objOCartaNivel1.jLabel5.getText().isEmpty() &&
            !objOCartaNivel1.jLabel8.getText().isEmpty() &&
            !objOCartaNivel1.jLabel9.getText().isEmpty() &&
            !objOCartaNivel1.jLabel13.getText().isEmpty() &&
            !objOCartaNivel1.jLabel14.getText().isEmpty() &&
            !objOCartaNivel1.jLabel15.getText().isEmpty() &&
            !objOCartaNivel1.jLabel19.getText().isEmpty() &&
            !objOCartaNivel1.jLabel20.getText().isEmpty() &&
            !objOCartaNivel1.jLabel21.getText().isEmpty();

        if (todasLlenas) {
            int opcion = JOptionPane.showOptionDialog(
                null,
                "¡Juego terminado!\n¿Deseas reiniciar o volver al menu?",
                "Fin de juego",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[] { "Reiniciar", "Salir al menu" },
                "Reiniciar"
            );
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego();
            } else {
                new vista.MenuOrdenar().setVisible(true);
                objOCartaNivel1.dispose();
            }
        }
    }

    private void reiniciarJuego() {
        // limpiar destinos
        JLabel[] destinos = {
            objOCartaNivel1.jLabel4, objOCartaNivel1.jLabel5,
            objOCartaNivel1.jLabel8, objOCartaNivel1.jLabel9,
            objOCartaNivel1.jLabel13, objOCartaNivel1.jLabel14, objOCartaNivel1.jLabel15,
            objOCartaNivel1.jLabel19, objOCartaNivel1.jLabel20, objOCartaNivel1.jLabel21
        };
        for (JLabel lbl : destinos) {
            lbl.setText("");
            lbl.setEnabled(true);
            lbl.setForeground(Color.BLACK);
        }
        // habilitar orígenes
        JLabel[] origenes = {
            objOCartaNivel1.jLabel2, objOCartaNivel1.jLabel3,
            objOCartaNivel1.jLabel6, objOCartaNivel1.jLabel7,
            objOCartaNivel1.jLabel10, objOCartaNivel1.jLabel11, objOCartaNivel1.jLabel12,
            objOCartaNivel1.jLabel16, objOCartaNivel1.jLabel17, objOCartaNivel1.jLabel18
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
