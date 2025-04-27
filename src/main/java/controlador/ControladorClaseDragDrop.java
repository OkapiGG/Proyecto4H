package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.ConexionBD;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;
import vista.MenuJuego;

public abstract class ControladorClaseDragDrop implements ActionListener {

    protected ModeloPalabra modeloPalabra;
    protected ModeloGuardaPalabras modeloGuardaPalabras;
    protected Connection conexion;
    protected ControladorAudios objAudio;

    public ControladorClaseDragDrop() {
        inicializarConexion();
        objAudio = new ControladorAudios();
    }

    public void inicializar() {
        cargarPalabraDelNivel();
        agregarAccionadorEventos();
        arrastrarSoltar();
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

    protected abstract void cargarPalabraDelNivel();

    protected abstract void arrastrarSoltar();

    protected abstract void manejarEvento(Object boton);

    protected abstract void agregarAccionadorEventos();

    @Override
    public void actionPerformed(ActionEvent e) {
        manejarEvento(e.getSource());
    }
}
