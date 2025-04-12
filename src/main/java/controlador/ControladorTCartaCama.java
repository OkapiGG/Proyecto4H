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
import vista.TCartaCama;
import vista.TCartaManzana;

public class ControladorTCartaCama implements MouseListener{
    
    private TCartaCama objTCartaCama;
     private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaCama(TCartaCama objTCartaCama) {
        this.objTCartaCama = objTCartaCama;
        this.objTCartaCama.jButton1.addMouseListener(this);
        this.objTCartaCama.jButton2.addMouseListener(this);
        this.objTCartaCama.jButton3.addMouseListener(this);
        this.objTCartaCama.jButton4.addMouseListener(this);
        inicializacionDeCon();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.objTCartaCama.jButton1) {
            this.silabaSeleccionada = objTCartaCama.jButton1.getText();
            objAudio.reproducirAudio("na");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaCama.jButton2) {
            this.silabaSeleccionada = objTCartaCama.jButton2.getText();
            objAudio.reproducirAudio("da");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaCama.jButton3) {
            this.silabaSeleccionada = objTCartaCama.jButton3.getText();
            objAudio.reproducirAudio("ca");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaCama.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaCama.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(23);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaCama.jButton1.setText("NA");
                objTCartaCama.jButton2.setText("DA");
                objTCartaCama.jButton3.setText(silabaCorrecta);
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
