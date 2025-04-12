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
import vista.TCartaSilla;

public class ControladorTCartaSilla implements MouseListener {

    private TCartaSilla objTCartaSilla;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaSilla(TCartaSilla objTCartaSilla) {
        this.objTCartaSilla = objTCartaSilla;
        this.objTCartaSilla.jButton1.addMouseListener(this);
        this.objTCartaSilla.jButton2.addMouseListener(this);
        this.objTCartaSilla.jButton3.addMouseListener(this);
        this.objTCartaSilla.jButton4.addMouseListener(this);
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
        if (e.getSource() == this.objTCartaSilla.jButton1) {
            this.silabaSeleccionada = objTCartaSilla.jButton1.getText();
            objAudio.reproducirAudio("pi");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaSilla.jButton2) {
            this.silabaSeleccionada = objTCartaSilla.jButton2.getText();
            objAudio.reproducirAudio("ci");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaSilla.jButton3) {
            this.silabaSeleccionada = objTCartaSilla.jButton3.getText();
            objAudio.reproducirAudio("si");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaSilla.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaSilla.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(20);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaSilla.jButton1.setText("PI");
                objTCartaSilla.jButton2.setText("CI");
                objTCartaSilla.jButton3.setText(silabaCorrecta);
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
