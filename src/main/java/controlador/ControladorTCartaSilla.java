package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaSilla;

public class ControladorTCartaSilla extends ControladorClaseBase {

    private TCartaSilla objTCartaSilla;
    protected String palabraCompleta;

    public ControladorTCartaSilla(TCartaSilla objTCartaSilla) {
        this.objTCartaSilla = objTCartaSilla;
        this.siguienteNivel = "hamburguesa";
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(20);
            this.palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                accionBotones();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
            }
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objTCartaSilla.jButton1.addMouseListener(this);
        this.objTCartaSilla.jButton2.addMouseListener(this);
        this.objTCartaSilla.jButton3.addMouseListener(this);
        this.objTCartaSilla.jButton4.addMouseListener(this);
        this.objTCartaSilla.jButton5.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaSilla.jButton1.setText("PI");
        objTCartaSilla.jButton2.setText("CI");
        objTCartaSilla.jButton3.setText(silabaCorrecta);
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaSilla.jButton1) {
            this.silabaSeleccionada = objTCartaSilla.jButton1.getText();
            objAudio.reproducirAudio("pi");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaSilla.jButton2) {
            this.silabaSeleccionada = objTCartaSilla.jButton2.getText();
            objAudio.reproducirAudio("ci");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaSilla.jButton3) {
            this.silabaSeleccionada = objTCartaSilla.jButton3.getText();
            objAudio.reproducirAudio("si");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaSilla.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaSilla.dispose();
        }
        if (boton == this.objTCartaSilla.jButton5) {
            objAudio.reproducirAudio("instrucciones2");
        }
    }

    @Override
    protected void cerrarVistaActual() {
        this.objTCartaSilla.dispose();
    }

    @Override
    protected void mostrarPalabraCompleta() {
        this.objTCartaSilla.jLabel2.setText(palabraCompleta);
    }
    
}
