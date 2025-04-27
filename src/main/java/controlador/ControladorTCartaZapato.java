package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaZapato;

public class ControladorTCartaZapato extends ControladorClaseBase {

    private TCartaZapato objTCartaZapato;
    protected String palabraCompleta;

    public ControladorTCartaZapato(TCartaZapato objTCartaZapato) {
        this.objTCartaZapato = objTCartaZapato;
        this.siguienteNivel = "leon";
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(21);
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
        this.objTCartaZapato.jButton1.addMouseListener(this);
        this.objTCartaZapato.jButton2.addMouseListener(this);
        this.objTCartaZapato.jButton3.addMouseListener(this);
        this.objTCartaZapato.jButton4.addMouseListener(this);
        this.objTCartaZapato.jButton5.addMouseListener(this);

    }

    @Override
    protected void accionBotones() {
        objTCartaZapato.jButton1.setText("TO");
        objTCartaZapato.jButton2.setText("SA");
        objTCartaZapato.jButton3.setText(silabaCorrecta);
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaZapato.jButton1) {
            this.silabaSeleccionada = objTCartaZapato.jButton1.getText();
            objAudio.reproducirAudio("to");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaZapato.jButton2) {
            this.silabaSeleccionada = objTCartaZapato.jButton2.getText();
            objAudio.reproducirAudio("sa");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaZapato.jButton3) {
            this.silabaSeleccionada = objTCartaZapato.jButton3.getText();
            objAudio.reproducirAudio("za");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaZapato.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaZapato.dispose();
        }
        if (boton == this.objTCartaZapato.jButton5) {
            objAudio.reproducirAudio("instrucciones2");
        }
    }
    
    @Override
    protected void cerrarVistaActual() {
        this.objTCartaZapato.dispose();
    }

    @Override
    protected void mostrarPalabraCompleta() {
        this.objTCartaZapato.jLabel1.setText(palabraCompleta);
    }

}
