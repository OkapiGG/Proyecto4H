// ControladorNivelUnico.java
package controlador;

import modelo.*;
import vista.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ControladorNivelUnico extends ControladorClaseBase {

    private final VistaCartaTablero vista;
    private final ConfiguracionNivelTablero config;
    private String palabraCompleta;

    public ControladorNivelUnico(ConfiguracionNivelTablero config) {
        this.config = config;
        this.siguienteNivel = config.siguienteNivel;
        this.vista = new VistaCartaTablero();
        inicializar(); // viene de la clase base
        vista.setVisible(true);
    }

    @Override
    protected void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(config.indiceEnBD);
            this.palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                accionBotones();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        vista.jButton1.addMouseListener(this);
        vista.jButton2.addMouseListener(this);
        vista.jButton3.addMouseListener(this);
        vista.jButton5.addMouseListener(this);
        vista.jButton4.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        List<String> silabas = new ArrayList<>();
        silabas.add(silabaCorrecta);
        silabas.addAll(Arrays.asList(config.silabasFalsas));
        Collections.shuffle(silabas);

        vista.jButton1.setText(silabas.get(0));
        vista.jButton2.setText(silabas.get(1));
        vista.jButton3.setText(silabas.get(2));

        vista.jLabel2.setIcon(new ImageIcon(getClass().getResource(config.rutaImagen)));
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == vista.jButton5) {
            vista.dispose();
            new MenuTablero().setVisible(true);
            return;
        }
        if (boton == vista.jButton4) {
            objAudio.reproducirAudio("instrucciones2");
            return;
        }
        JButton btn = (JButton) boton;
        this.silabaSeleccionada = btn.getText();
        objAudio.reproducirAudio(silabaSeleccionada.toLowerCase());
        verificarPalabra(silabaSeleccionada);
    }

    @Override
    protected void mostrarPalabraCompleta() {
        vista.jLabel3.setText(palabraCompleta);
    }

    @Override
    protected void cerrarVistaActual() {
        vista.dispose();
    }
}
