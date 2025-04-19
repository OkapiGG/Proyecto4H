package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaCama;

/**
 *
 * @author ep712
 */
public class ControladorTCartaCama extends ControladorClaseBase {

    private TCartaCama objTCartaCama;

    public ControladorTCartaCama(TCartaCama objTCartaCama) {
        this.objTCartaCama = objTCartaCama;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(23);
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
        this.objTCartaCama.jButton1.addMouseListener(this);
        this.objTCartaCama.jButton2.addMouseListener(this);
        this.objTCartaCama.jButton3.addMouseListener(this);
        this.objTCartaCama.jButton4.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaCama.jButton1.setText("NA");
        objTCartaCama.jButton2.setText("DA");
        objTCartaCama.jButton3.setText(silabaCorrecta);
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaCama.jButton1) {
            this.silabaSeleccionada = objTCartaCama.jButton1.getText();
            objAudio.reproducirAudio("na");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaCama.jButton2) {
            this.silabaSeleccionada = objTCartaCama.jButton2.getText();
            objAudio.reproducirAudio("da");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaCama.jButton3) {
            this.silabaSeleccionada = objTCartaCama.jButton3.getText();
            objAudio.reproducirAudio("ca");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaCama.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaCama.dispose();
        }
    }
    
    @Override
    protected void cerrarVistaActual() {
        this.objTCartaCama.dispose();
    }
}
