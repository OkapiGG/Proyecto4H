package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuJuego;

public class ControladorMenuJuego implements ActionListener {

    private MenuJuego vistaMenu;

    public ControladorMenuJuego(MenuJuego vistaMenu) {
        this.vistaMenu = vistaMenu;

        // Registrar listeners
        this.vistaMenu.jButton1.addActionListener(this);  // Gato
        this.vistaMenu.jButton2.addActionListener(this);  // Casa
        this.vistaMenu.jButton3.addActionListener(this);  // Carro
        this.vistaMenu.jButton4.addActionListener(this);  // Árbol
        this.vistaMenu.jButton5.addActionListener(this);  // Mango
        this.vistaMenu.jButton6.addActionListener(this);  // Sandía
        this.vistaMenu.jButton7.addActionListener(this);  // Pájaro
        this.vistaMenu.jButton8.addActionListener(this);  // Jirafa
        this.vistaMenu.jButton9.addActionListener(this);  // Bicicleta
        this.vistaMenu.jButton10.addActionListener(this); // Volver a inicio
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        // Cada botón invoca al Gestor de Niveles correspondiente
        if (src == vistaMenu.jButton1) {
            GestorNiveles.iniciarNivelGato();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton2) {
            GestorNiveles.iniciarNivelCasa();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton3) {
            GestorNiveles.iniciarNivelCarro();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton4) {
            GestorNiveles.iniciarNivelArbol();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton5) {
            GestorNiveles.iniciarNivelMango();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton6) {
            GestorNiveles.iniciarNivelSandia();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton7) {
            GestorNiveles.iniciarNivelPajaro();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton8) {
            GestorNiveles.iniciarNivelJirafa();
            vistaMenu.dispose();
        }
        else if (src == vistaMenu.jButton9) {
            GestorNiveles.iniciarNivelBicicleta();
            vistaMenu.dispose();
        }
        // Botón 10: volver al menú de inicio general
        else if (src == vistaMenu.jButton10) {
            new vista.MenuInicio().setVisible(true);
            vistaMenu.dispose();
        }
    }
}
