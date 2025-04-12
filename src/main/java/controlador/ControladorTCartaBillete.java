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
import vista.TCartaBillete;

public class ControladorTCartaBillete implements MouseListener {

    private TCartaBillete objTCartaBillete;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaBillete(TCartaBillete objTCartaBillete) {
        this.objTCartaBillete = objTCartaBillete;
        this.objTCartaBillete.jButton1.addMouseListener(this);
        this.objTCartaBillete.jButton2.addMouseListener(this);
        this.objTCartaBillete.jButton3.addMouseListener(this);
        this.objTCartaBillete.jButton4.addMouseListener(this);
        inicializacionDeConstructor();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.objTCartaBillete.jButton1) {
            this.silabaSeleccionada = objTCartaBillete.jButton1.getText();
            objAudio.reproducirAudio("bi");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaBillete.jButton2) {
            this.silabaSeleccionada = objTCartaBillete.jButton2.getText();
            objAudio.reproducirAudio("do");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaBillete.jButton3) {
            this.silabaSeleccionada = objTCartaBillete.jButton3.getText();
            objAudio.reproducirAudio("vi");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaBillete.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaBillete.dispose();
        }

    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(18);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaBillete.jButton1.setText(silabaCorrecta);
                objTCartaBillete.jButton2.setText("DO");
                objTCartaBillete.jButton3.setText("VI");
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
