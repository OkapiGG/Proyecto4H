package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;

public abstract class ControladorClaseBase implements MouseListener {

    protected ModeloPalabra modeloPalabra;
    protected ModeloGuardaPalabras modeloGuardaPalabras;
    protected Connection conexion;
    protected ControladorAudios objAudio;
    protected String silabaCorrecta;
    protected String silabaSeleccionada;
    protected String siguienteNivel;

    public ControladorClaseBase() {
        inicializarConexion();          
        objAudio = new ControladorAudios();
    }

    public void inicializar() {
        cargarPalabraDelNivel();    
        agregarAccionadorEventos();
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

            actualizarNivelSiguiente();
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

    protected void actualizarNivelSiguiente() {
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
           
            PreparedStatement psSelect = conexion.prepareStatement("SELECT obtenernivelactual(?)");
            psSelect.setInt(1, modelo.Login.getIdUsuarioActivo());
            ResultSet rs = psSelect.executeQuery();
            
            String nivelActualBD = null;
            if (rs.next()) {
                nivelActualBD = rs.getString(1);
            }
            rs.close();
            psSelect.close();

            if (nivelActualBD == null) {
                System.out.println("No se encontró el nivel actual en BD.");
                return;
            }

            String[] nivelesOrdenados = {
                "pizza", "perro", "silla", "hamburguesa", "manzana",
                "mesa", "billete", "foco", "conejo", "zapato",
                "leon", "cama"
            };

            int posicionActual = buscarPosicionNivel(nivelActualBD, nivelesOrdenados);
            int posicionSiguiente = buscarPosicionNivel(siguienteNivel, nivelesOrdenados);

            if (posicionSiguiente > posicionActual) {
                PreparedStatement ps = conexion.prepareStatement("SELECT actualizarnivelactual(?, ?)");
                ps.setInt(1, modelo.Login.getIdUsuarioActivo());
                ps.setString(2, siguienteNivel);
                ps.executeQuery();
                ps.close();
            } else {
                System.out.println("No se actualiza porque el nivel no es más avanzado.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int buscarPosicionNivel(String nivel, String[] niveles) {
        for (int i = 0; i < niveles.length; i++) {
            if (niveles[i].equalsIgnoreCase(nivel)) {
                return i;
            }
        }
        return -1;
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
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
