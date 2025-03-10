package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Carta1;
import vista.InicioJuego;

public class ControladorInicioJuego implements ActionListener {

    InicioJuego objInicioJuego;
    Carta1 objCarta1;

    public ControladorInicioJuego(InicioJuego objInicioJuego) {
        this.objInicioJuego = objInicioJuego;
        this.objCarta1 = objCarta1;
        objInicioJuego.jButton1.addActionListener(this);
        objInicioJuego.jButton2.addActionListener(this);
        objInicioJuego.jButton3.addActionListener(this);
        objInicioJuego.jButton4.addActionListener(this);
        objInicioJuego.jButton5.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.objInicioJuego.jButton1) {
            System.out.println("Boton 1");
            Carta1 objCarta1 = new Carta1();
            objCarta1.setVisible(true);
            this.objInicioJuego.dispose();
        }
        if (e.getSource() == this.objInicioJuego.jButton2) {
            System.out.println("Boton 2");
        }
        if (e.getSource() == this.objInicioJuego.jButton3) {
            System.out.println("Boton 3");
        }
        if (e.getSource() == this.objInicioJuego.jButton4) {
            System.out.println("Boton 4");
        }
        if (e.getSource() == this.objInicioJuego.jButton5) {
            System.out.println("Boton 5");
        }
    }
}
