package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaConejo;

public class ControladorTCartaConejo extends ControladorClaseBase {

    private TCartaConejo objTCartaConejo;
    protected String palabraCompleta;

    public ControladorTCartaConejo(TCartaConejo objTCartaConejo) {
        this.objTCartaConejo = objTCartaConejo;
        this.siguienteNivel = "zapato";
        
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(16);
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
        this.objTCartaConejo.jButton1.addMouseListener(this);
        this.objTCartaConejo.jButton2.addMouseListener(this);
        this.objTCartaConejo.jButton3.addMouseListener(this);
        this.objTCartaConejo.jButton4.addMouseListener(this);
        this.objTCartaConejo.jButton5.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaConejo.jButton1.setText("RA");
        objTCartaConejo.jButton2.setText(silabaCorrecta);
        objTCartaConejo.jButton3.setText("PO");
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaConejo.jButton1) {
            this.silabaSeleccionada = objTCartaConejo.jButton1.getText();
            objAudio.reproducirAudio("ra");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaConejo.jButton2) {
            this.silabaSeleccionada = objTCartaConejo.jButton2.getText();
            objAudio.reproducirAudio("co");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaConejo.jButton3) {
            this.silabaSeleccionada = objTCartaConejo.jButton3.getText();
            objAudio.reproducirAudio("po");
            verificarPalabra(silabaSeleccionada);
        }
        if (boton == this.objTCartaConejo.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaConejo.dispose();
        }
        if (boton == this.objTCartaConejo.jButton5) {
            objAudio.reproducirAudio("instrucciones2");
        }
    }
    
    @Override
    protected void cerrarVistaActual() {
        this.objTCartaConejo.dispose();
    }

    @Override
    protected void mostrarPalabraCompleta() {
        this.objTCartaConejo.jLabel2.setText(palabraCompleta);
    }

}
