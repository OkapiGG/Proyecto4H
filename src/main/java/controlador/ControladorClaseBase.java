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

public abstract class ControladorClaseBase implements MouseListener {

    protected ModeloPalabra modeloPalabra;
    protected ModeloGuardaPalabras modeloGuardaPalabras;
    protected Connection conexion;
    protected ControladorAudios objAudio;
    protected String silabaCorrecta;
    protected String silabaSeleccionada;

    public ControladorClaseBase() {
        inicializarConexion();          // Solo configura conexión y modelos
        objAudio = new ControladorAudios();
    }

    public void inicializar() {
        cargarPalabraDelNivel();     // Aquí sí, ya está la vista lista
        agregarAccionadorEventos(); // Conecta los eventos de los botones
    }

    private void inicializarConexion() {
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void verificarPalabra(String silabaSeleccionada) {
        System.out.println(silabaSeleccionada);
        if (silabaSeleccionada.equals(silabaCorrecta)) {
            JOptionPane.showMessageDialog(null, "¡Correcto!");
        } else {
            JOptionPane.showMessageDialog(null, "Inténtalo de nuevo.");
        }
    }

    protected abstract void cargarPalabraDelNivel();

    protected abstract void agregarAccionadorEventos();

    protected abstract void accionBotones();

    protected abstract void manejarEvento(Object boton);

    @Override
    public void mouseClicked(MouseEvent e) {
        manejarEvento(e.getSource());
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
