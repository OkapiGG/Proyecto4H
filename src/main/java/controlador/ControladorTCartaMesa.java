package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaMesa;

public class ControladorTCartaMesa extends ControladorClaseBase {

    private TCartaMesa objTCartaMesa;
    protected String palabraCompleta;

    public ControladorTCartaMesa(TCartaMesa objTCartaMesa) {
        this.objTCartaMesa = objTCartaMesa;
        this.siguienteNivel = "billete";
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(19);
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
        this.objTCartaMesa.jButton1.addMouseListener(this);
        this.objTCartaMesa.jButton2.addMouseListener(this);
        this.objTCartaMesa.jButton3.addMouseListener(this);
        this.objTCartaMesa.jButton4.addMouseListener(this);
        this.objTCartaMesa.jButton5.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaMesa.jButton1.setText("SA");
        objTCartaMesa.jButton2.setText(silabaCorrecta);
        objTCartaMesa.jButton3.setText("ZA");
    }

    @Override
    protected void manejarEvento(Object boton) {

        if (boton == this.objTCartaMesa.jButton1) {
            this.silabaSeleccionada = objTCartaMesa.jButton1.getText();
            objAudio.reproducirAudio("sa");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaMesa.jButton2) {
            this.silabaSeleccionada = objTCartaMesa.jButton2.getText();
            objAudio.reproducirAudio("me");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaMesa.jButton3) {
            this.silabaSeleccionada = objTCartaMesa.jButton3.getText();
            objAudio.reproducirAudio("za");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaMesa.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaMesa.dispose();
        }
        if (boton == this.objTCartaMesa.jButton5) {
            objAudio.reproducirAudio("instrucciones2");
        }
    }
    
    @Override
    protected void cerrarVistaActual() {
        this.objTCartaMesa.dispose();
    }

    @Override
    protected void mostrarPalabraCompleta() {
        this.objTCartaMesa.jLabel2.setText(palabraCompleta);
    }
}
