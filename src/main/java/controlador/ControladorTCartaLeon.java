package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaLeon;

public class ControladorTCartaLeon extends ControladorClaseBase {

    private TCartaLeon objTCartaLeon;

    public ControladorTCartaLeon(TCartaLeon objTCartaLeon) {
        this.objTCartaLeon = objTCartaLeon;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(22);
            String palabraCompleta = palabraActual.getPalabra();
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
        this.objTCartaLeon.jButton1.addMouseListener(this);
        this.objTCartaLeon.jButton2.addMouseListener(this);
        this.objTCartaLeon.jButton3.addMouseListener(this);
        this.objTCartaLeon.jButton4.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaLeon.jButton1.setText("LI");
        objTCartaLeon.jButton2.setText(silabaCorrecta);
        objTCartaLeon.jButton3.setText("PU");
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaLeon.jButton1) {
            this.silabaSeleccionada = objTCartaLeon.jButton1.getText();
            objAudio.reproducirAudio("li");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaLeon.jButton2) {
            this.silabaSeleccionada = objTCartaLeon.jButton2.getText();
            objAudio.reproducirAudio("le");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaLeon.jButton3) {
            this.silabaSeleccionada = objTCartaLeon.jButton3.getText();
            objAudio.reproducirAudio("pu");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaLeon.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaLeon.dispose();
        }

    }

}
