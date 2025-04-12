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
import vista.TCartaHamburguesa;

public class ControladorTCartaHamburguesa implements MouseListener {

    private TCartaHamburguesa objTCartaHamburguesa;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaHamburguesa(TCartaHamburguesa objTCartaHamburguesa) {
        this.objTCartaHamburguesa = objTCartaHamburguesa;
        this.objTCartaHamburguesa.jButton1.addMouseListener(this);
        this.objTCartaHamburguesa.jButton2.addMouseListener(this);
        this.objTCartaHamburguesa.jButton3.addMouseListener(this);
        this.objTCartaHamburguesa.jButton4.addMouseListener(this);
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
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == this.objTCartaHamburguesa.jButton1) {
            this.silabaSeleccionada = objTCartaHamburguesa.jButton1.getText();
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaHamburguesa.jButton2) {
            this.silabaSeleccionada = objTCartaHamburguesa.jButton2.getText();
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaHamburguesa.jButton3) {
            this.silabaSeleccionada = objTCartaHamburguesa.jButton3.getText();
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaHamburguesa.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaHamburguesa.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(14);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaHamburguesa.jButton1.setText(silabaCorrecta);
                objTCartaHamburguesa.jButton2.setText("BU");
                objTCartaHamburguesa.jButton3.setText("TO");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    public void verificarPalabra(String silabaSeleccionada) {
        System.out.println(silabaSeleccionada);

        if (silabaSeleccionada.equals(silabaCorrecta)) {
            JOptionPane.showMessageDialog(null, "¡Correcto!");
        } else {
            JOptionPane.showMessageDialog(null, "Inténtalo de nuevo.");
        }
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
