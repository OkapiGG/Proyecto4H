package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaPizza;

public class ControladorTCartaPizza extends ControladorClaseBase {

    private TCartaPizza objTCartaPizza;
    protected String palabraCompleta;

    public ControladorTCartaPizza(TCartaPizza objTCartaPizza) {
        this.objTCartaPizza = objTCartaPizza;
        this.siguienteNivel = "perro";
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objTCartaPizza.jButton1.addMouseListener(this);
        this.objTCartaPizza.jButton2.addMouseListener(this);
        this.objTCartaPizza.jButton3.addMouseListener(this);
        this.objTCartaPizza.jButton4.addMouseListener(this);
        this.objTCartaPizza.jButton5.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaPizza.jButton1.setText(silabaCorrecta);
        objTCartaPizza.jButton2.setText("HU");
        objTCartaPizza.jButton3.setText("KI");
    }

    @Override
    protected void manejarEvento(Object e) {
        if (e == this.objTCartaPizza.jButton1) {
            this.silabaSeleccionada = objTCartaPizza.jButton1.getText();
            objAudio.reproducirAudio("PI");
            verificarPalabra(silabaSeleccionada);
        } else if (e == this.objTCartaPizza.jButton2) {
            this.silabaSeleccionada = objTCartaPizza.jButton2.getText();
            objAudio.reproducirAudio("HU");
            verificarPalabra(silabaSeleccionada);
        } else if (e == this.objTCartaPizza.jButton3) {
            this.silabaSeleccionada = objTCartaPizza.jButton3.getText();
            objAudio.reproducirAudio("KI");
            verificarPalabra(silabaSeleccionada);
        } else if (e == this.objTCartaPizza.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaPizza.dispose();
        } else if (e == this.objTCartaPizza.jButton5) {
            objAudio.reproducirAudio("instrucciones2");
        }
    }

    @Override
    protected void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(13);
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
    protected void cerrarVistaActual() {
        this.objTCartaPizza.dispose();
    }

    @Override
    protected void mostrarPalabraCompleta() {
        this.objTCartaPizza.jLabel2.setText(palabraCompleta);
    }
}
