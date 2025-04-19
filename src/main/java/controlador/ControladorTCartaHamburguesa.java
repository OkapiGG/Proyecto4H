package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaHamburguesa;

public class ControladorTCartaHamburguesa extends ControladorClaseBase {

    private TCartaHamburguesa objTCartaHamburguesa;

    public ControladorTCartaHamburguesa(TCartaHamburguesa objTCartaHamburguesa) {
        this.objTCartaHamburguesa = objTCartaHamburguesa;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(14);
            String palabraCompleta = palabraActual.getPalabra();

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
        this.objTCartaHamburguesa.jButton1.addMouseListener(this);
        this.objTCartaHamburguesa.jButton2.addMouseListener(this);
        this.objTCartaHamburguesa.jButton3.addMouseListener(this);
        this.objTCartaHamburguesa.jButton4.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaHamburguesa.jButton1.setText(silabaCorrecta);
        objTCartaHamburguesa.jButton2.setText("BU");
        objTCartaHamburguesa.jButton3.setText("TO");
    }

    @Override
    protected void manejarEvento(Object boton) {

        if (boton == this.objTCartaHamburguesa.jButton1) {
            this.silabaSeleccionada = objTCartaHamburguesa.jButton1.getText();
            objAudio.reproducirAudio("ha");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaHamburguesa.jButton2) {
            this.silabaSeleccionada = objTCartaHamburguesa.jButton2.getText();
            objAudio.reproducirAudio("bu");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaHamburguesa.jButton3) {
            this.silabaSeleccionada = objTCartaHamburguesa.jButton3.getText();
            objAudio.reproducirAudio("to");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaHamburguesa.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaHamburguesa.dispose();
        }
    }
    
    @Override
    protected void cerrarVistaActual() {
        this.objTCartaHamburguesa.dispose();
    }
}
