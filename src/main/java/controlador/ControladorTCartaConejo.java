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
import vista.TCartaConejo;
import vista.TCartaPerro;

public class ControladorTCartaConejo implements MouseListener {

    private TCartaConejo objTCartaConejo;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaConejo(TCartaConejo objTCartaConejo) {
        this.objTCartaConejo = objTCartaConejo;
        this.objTCartaConejo.jButton1.addMouseListener(this);
        this.objTCartaConejo.jButton2.addMouseListener(this);
        this.objTCartaConejo.jButton3.addMouseListener(this);
        this.objTCartaConejo.jButton4.addMouseListener(this);
        inicializacionDeCon();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.objTCartaConejo.jButton1) {
            this.silabaSeleccionada = objTCartaConejo.jButton1.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaConejo.jButton2) {
            this.silabaSeleccionada = objTCartaConejo.jButton2.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaConejo.jButton3) {
            this.silabaSeleccionada = objTCartaConejo.jButton3.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaConejo.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaConejo.dispose();
        }

    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(16);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaConejo.jButton1.setText("RA");
                objTCartaConejo.jButton2.setText(silabaCorrecta);
                objTCartaConejo.jButton3.setText("PO");
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
