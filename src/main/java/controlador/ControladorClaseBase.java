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
        if (silabaSeleccionada.equals(silabaCorrecta)) {
            objAudio.reproducirAudio(silabaCorrecta.toLowerCase());
            
            int idUsuario = modelo.Login.getIdUsuarioActivo();
            OperacionesBDCuenta operacionesCuenta = new OperacionesBDCuenta();
            operacionesCuenta.actualizarPuntajeYPalabras(idUsuario, 10, 1);
            mostrarPalabraCompleta();
            JOptionPane.showMessageDialog(
                    null,
                    "¡Correcto!\nGanaste 10 puntos 🏆",
                    "Nivel completado",
                    JOptionPane.INFORMATION_MESSAGE
            );
            vista.MenuTablero menuTablero = new vista.MenuTablero();
            menuTablero.setVisible(true);
            cerrarVistaActual();

        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Incorrecto, intenta de nuevo",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    protected abstract void mostrarPalabraCompleta();

    protected abstract void cargarPalabraDelNivel();

    protected abstract void agregarAccionadorEventos();

    protected abstract void accionBotones();

    protected abstract void manejarEvento(Object boton);

    protected abstract void cerrarVistaActual();

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
