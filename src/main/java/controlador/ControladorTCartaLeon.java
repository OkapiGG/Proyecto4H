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
import vista.TCartaLeon;

public class ControladorTCartaLeon implements MouseListener {

    private TCartaLeon objTCartaLeon;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaLeon(TCartaLeon objTCartaLeon) {
        this.objTCartaLeon = objTCartaLeon;
        this.objTCartaLeon.jButton1.addMouseListener(this);
        this.objTCartaLeon.jButton2.addMouseListener(this);
        this.objTCartaLeon.jButton3.addMouseListener(this);
        this.objTCartaLeon.jButton4.addMouseListener(this);
        inicializacionDeCon();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.objTCartaLeon.jButton1) {
            this.silabaSeleccionada = objTCartaLeon.jButton1.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaLeon.jButton2) {
            this.silabaSeleccionada = objTCartaLeon.jButton2.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaLeon.jButton3) {
            this.silabaSeleccionada = objTCartaLeon.jButton3.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaLeon.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaLeon.dispose();
        }

    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(22);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaLeon.jButton1.setText("LI");
                objTCartaLeon.jButton2.setText(silabaCorrecta);
                objTCartaLeon.jButton3.setText("PU");
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

    private void inicializacionDeCon() {
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
