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
import vista.TCartaZapato;

public class ControladorTCartaZapato implements MouseListener {

    private TCartaZapato objTCartaZapato;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private String silabaCorrecta;
    private String silabaSeleccionada;

    public ControladorTCartaZapato(TCartaZapato objTCartaZapato) {
        this.objTCartaZapato = objTCartaZapato;
        this.objTCartaZapato.jButton1.addMouseListener(this);
        this.objTCartaZapato.jButton2.addMouseListener(this);
        this.objTCartaZapato.jButton3.addMouseListener(this);
        this.objTCartaZapato.jButton4.addMouseListener(this);
        inicializacionDeCon();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.objTCartaZapato.jButton1) {
            this.silabaSeleccionada = objTCartaZapato.jButton1.getText();
            objAudio.reproducirAudio("to");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaZapato.jButton2) {
            this.silabaSeleccionada = objTCartaZapato.jButton2.getText();
            objAudio.reproducirAudio("sa");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaZapato.jButton3) {
            this.silabaSeleccionada = objTCartaZapato.jButton3.getText();
            objAudio.reproducirAudio("za");
            verificarPalabra(silabaSeleccionada);
        }

        if (e.getSource() == this.objTCartaZapato.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaZapato.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(21);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                objTCartaZapato.jButton1.setText("TO");
                objTCartaZapato.jButton2.setText("SA");
                objTCartaZapato.jButton3.setText(silabaCorrecta);
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
