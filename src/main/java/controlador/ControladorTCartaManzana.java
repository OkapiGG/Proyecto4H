package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaManzana;

public class ControladorTCartaManzana extends ControladorClaseBase {

    private TCartaManzana objTCartaManzana;
    protected String palabraCompleta;

    public ControladorTCartaManzana(TCartaManzana objTCartaManzana) {
        this.objTCartaManzana = objTCartaManzana;
        this.siguienteNivel = "mesa";
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objeto Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(15);
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
        this.objTCartaManzana.jButton1.addMouseListener(this);
        this.objTCartaManzana.jButton2.addMouseListener(this);
        this.objTCartaManzana.jButton3.addMouseListener(this);
        this.objTCartaManzana.jButton4.addMouseListener(this);
        this.objTCartaManzana.jButton5.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaManzana.jButton1.setText("NA");
        objTCartaManzana.jButton2.setText("ZA");
        objTCartaManzana.jButton3.setText(silabaCorrecta);
    }

    @Override
    protected void manejarEvento(Object boton) {

        if (boton == this.objTCartaManzana.jButton1) {
            this.silabaSeleccionada = objTCartaManzana.jButton1.getText();
            objAudio.reproducirAudio("na");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaManzana.jButton2) {
            this.silabaSeleccionada = objTCartaManzana.jButton2.getText();
            objAudio.reproducirAudio("za");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaManzana.jButton3) {
            this.silabaSeleccionada = objTCartaManzana.jButton3.getText();
            objAudio.reproducirAudio("ma");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaManzana.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaManzana.dispose();
        }
        if (boton == this.objTCartaManzana.jButton5) {
            objAudio.reproducirAudio("instrucciones2");
        }
    }
    
    @Override
    protected void cerrarVistaActual() {
        this.objTCartaManzana.dispose();
    }

    @Override
    protected void mostrarPalabraCompleta() {
        this.objTCartaManzana.jLabel2.setText(palabraCompleta);
    }
}
