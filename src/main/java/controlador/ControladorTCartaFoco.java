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
import vista.TCartaFoco;

public class ControladorTCartaFoco implements MouseListener {

    private TCartaFoco objTCartaFoco;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaFoco(TCartaFoco objTCartaFoco) {
        this.objTCartaFoco = objTCartaFoco;
        this.objTCartaFoco.jButton1.addMouseListener(this);
        this.objTCartaFoco.jButton2.addMouseListener(this);
        this.objTCartaFoco.jButton3.addMouseListener(this);
        this.objTCartaFoco.jButton4.addMouseListener(this);
        inicializacionDeConstructor();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.objTCartaFoco.jButton1) {
            this.silabaSeleccionada = objTCartaFoco.jButton1.getText();
            objAudio.reproducirAudio("pe");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaFoco.jButton2) {
            this.silabaSeleccionada = objTCartaFoco.jButton2.getText();
            objAudio.reproducirAudio("fa");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaFoco.jButton3) {
            this.silabaSeleccionada = objTCartaFoco.jButton3.getText();
            objAudio.reproducirAudio("fo");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaFoco.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaFoco.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(17);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaFoco.jButton1.setText("PE");
                objTCartaFoco.jButton2.setText("FA");
                objTCartaFoco.jButton3.setText(silabaCorrecta);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
            }
        }
    }

    public void verificarPalabra(String silaSeleccionada) {
        System.out.println(silaSeleccionada);
        if (silaSeleccionada.equals(silabaCorrecta)) {
            JOptionPane.showMessageDialog(null, "¡Correcto!");
        } else {
            JOptionPane.showMessageDialog(null, "Inténtalo de nuevo.");
        }
    }

    private void inicializacionDeConstructor() {
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
