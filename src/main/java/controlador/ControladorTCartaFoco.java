package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaFoco;

public class ControladorTCartaFoco extends ControladorClaseBase {

    private TCartaFoco objTCartaFoco;
    protected String palabraCompleta;

    public ControladorTCartaFoco(TCartaFoco objTCartaFoco) {
        this.objTCartaFoco = objTCartaFoco;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(17);
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
        this.objTCartaFoco = objTCartaFoco;
        this.objTCartaFoco.jButton1.addMouseListener(this);
        this.objTCartaFoco.jButton2.addMouseListener(this);
        this.objTCartaFoco.jButton3.addMouseListener(this);
        this.objTCartaFoco.jButton4.addMouseListener(this);
        this.objTCartaFoco.jButton5.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaFoco.jButton1.setText("PE");
        objTCartaFoco.jButton2.setText("FA");
        objTCartaFoco.jButton3.setText(silabaCorrecta);
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaFoco.jButton1) {
            this.silabaSeleccionada = objTCartaFoco.jButton1.getText();
            objAudio.reproducirAudio("pe");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaFoco.jButton2) {
            this.silabaSeleccionada = objTCartaFoco.jButton2.getText();
            objAudio.reproducirAudio("fa");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaFoco.jButton3) {
            this.silabaSeleccionada = objTCartaFoco.jButton3.getText();
            objAudio.reproducirAudio("fo");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaFoco.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaFoco.dispose();
        }
        if (boton == this.objTCartaFoco.jButton5) {
            objAudio.reproducirAudio("instrucciones2");
        }
    }
    
    @Override
    protected void cerrarVistaActual() {
        this.objTCartaFoco.dispose();
    }

    @Override
    protected void mostrarPalabraCompleta() {
        this.objTCartaFoco.jLabel2.setText(palabraCompleta);
    }
}
