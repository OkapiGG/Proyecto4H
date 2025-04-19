package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuTablero;
import vista.TCartaBillete;

public class ControladorTCartaBillete extends ControladorClaseBase {

    private TCartaBillete objTCartaBillete;

    public ControladorTCartaBillete(TCartaBillete objTCartaBillete) {
        this.objTCartaBillete = objTCartaBillete;
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objTCartaBillete.jButton1.addMouseListener(this);
        this.objTCartaBillete.jButton2.addMouseListener(this);
        this.objTCartaBillete.jButton3.addMouseListener(this);
        this.objTCartaBillete.jButton4.addMouseListener(this);
    }

    @Override
    protected void accionBotones() {
        objTCartaBillete.jButton1.setText(silabaCorrecta);
        objTCartaBillete.jButton2.setText("DO");
        objTCartaBillete.jButton3.setText("VI");
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objTCartaBillete.jButton1) {
            this.silabaSeleccionada = objTCartaBillete.jButton1.getText();
            objAudio.reproducirAudio("bi");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaBillete.jButton2) {
            this.silabaSeleccionada = objTCartaBillete.jButton2.getText();
            objAudio.reproducirAudio("do");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaBillete.jButton3) {
            this.silabaSeleccionada = objTCartaBillete.jButton3.getText();
            objAudio.reproducirAudio("vi");
            verificarPalabra(silabaSeleccionada);
        }

        if (boton == this.objTCartaBillete.jButton4) {
            MenuTablero menuTablero = new MenuTablero();
            menuTablero.setVisible(true);
            this.objTCartaBillete.dispose();
        }
    }

    @Override
    protected void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(18);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                this.silabaCorrecta = palabraCompleta.substring(0, 2);
                accionBotones();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }
}
