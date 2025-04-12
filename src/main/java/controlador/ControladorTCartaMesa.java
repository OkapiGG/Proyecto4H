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
import vista.TCartaMesa;

public class ControladorTCartaMesa implements MouseListener {

    private TCartaMesa objTCartaMesa;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaMesa(TCartaMesa objTCartaMesa) {
        this.objTCartaMesa = objTCartaMesa;
        this.objTCartaMesa.jButton1.addMouseListener(this);
        this.objTCartaMesa.jButton2.addMouseListener(this);
        this.objTCartaMesa.jButton3.addMouseListener(this);
        this.objTCartaMesa.jButton4.addMouseListener(this);
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

        if (e.getSource() == this.objTCartaMesa.jButton1) {
            this.silabaSeleccionada = objTCartaMesa.jButton1.getText();
            objAudio.reproducirAudio("sa");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaMesa.jButton2) {
            this.silabaSeleccionada = objTCartaMesa.jButton2.getText();
            objAudio.reproducirAudio("me");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaMesa.jButton3) {
            this.silabaSeleccionada = objTCartaMesa.jButton3.getText();
            objAudio.reproducirAudio("za");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaMesa.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaMesa.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(19);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaMesa.jButton1.setText("SA");
                objTCartaMesa.jButton2.setText(silabaCorrecta);
                objTCartaMesa.jButton3.setText("ZA");
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
