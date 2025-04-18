package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaPerro;

public class ControladorTCartaPerro extends ControladorClaseBase {

    private TCartaPerro objTCartaPerro;

    public ControladorTCartaPerro(TCartaPerro objTCartaPerro) {
        this.objTCartaPerro = objTCartaPerro;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(1);
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
        this.objTCartaPerro.jButton1.addMouseListener(this);
        this.objTCartaPerro.jButton2.addMouseListener(this);
        this.objTCartaPerro.jButton3.addMouseListener(this);
        this.objTCartaPerro.jButton4.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaPerro.jButton1.setText("PO");
        objTCartaPerro.jButton2.setText(silabaCorrecta);
        objTCartaPerro.jButton3.setText("PU");
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaPerro.jButton1) {
            this.silabaSeleccionada = objTCartaPerro.jButton1.getText();
            objAudio.reproducirAudio("po");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaPerro.jButton2) {
            this.silabaSeleccionada = objTCartaPerro.jButton2.getText();
            objAudio.reproducirAudio("pe");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaPerro.jButton3) {
            this.silabaSeleccionada = objTCartaPerro.jButton3.getText();
            objAudio.reproducirAudio("pu");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaPerro.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaPerro.dispose();
        }
    }
}
