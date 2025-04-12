package controlador;

import java.awt.event.ActionListener;
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
import vista.TCartaPerro;

public class ControladorTCartaPerro implements MouseListener {

    private TCartaPerro objTCartaPerro;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaPerro(TCartaPerro objTCartaPerro) {
        this.objTCartaPerro = objTCartaPerro;
        this.objTCartaPerro.jButton1.addMouseListener(this);
        this.objTCartaPerro.jButton2.addMouseListener(this);
        this.objTCartaPerro.jButton3.addMouseListener(this);
        this.objTCartaPerro.jButton4.addMouseListener(this);
        inicializacionDeCon();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.objTCartaPerro.jButton1) {
            this.silabaSeleccionada = objTCartaPerro.jButton1.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaPerro.jButton2) {
            this.silabaSeleccionada = objTCartaPerro.jButton2.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaPerro.jButton3) {
            this.silabaSeleccionada = objTCartaPerro.jButton3.getText();
            verificarPalabra(silabaSeleccionada);
        }
        if (e.getSource() == this.objTCartaPerro.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaPerro.dispose();
        }

    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(1);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaPerro.jButton1.setText("PO");
                objTCartaPerro.jButton2.setText(silabaCorrecta);
                objTCartaPerro.jButton3.setText("PU");
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
