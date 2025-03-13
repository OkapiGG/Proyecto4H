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
        objInicioJuego.jButton6.addActionListener(this);
        objInicioJuego.jButton7.addActionListener(this);
        objInicioJuego.jButton8.addActionListener(this);
        objInicioJuego.jButton9.addActionListener(this);
        objInicioJuego.jButton10.addActionListener(this);
        objInicioJuego.jButton11.addActionListener(this);
        objInicioJuego.jButton12.addActionListener(this);
        objInicioJuego.jButton13.addActionListener(this);
        objInicioJuego.jButton14.addActionListener(this);
        objInicioJuego.jButton15.addActionListener(this);
        objInicioJuego.jButton16.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.objInicioJuego.jButton1) {
            System.out.println("Boton 1");
//            Carta1 objCarta1 = new Carta1();
//            objCarta1.setVisible(true);
//            this.objInicioJuego.dispose();
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
        if (e.getSource() == this.objInicioJuego.jButton6) {
            System.out.println("Boton 6");
        }
        if (e.getSource() == this.objInicioJuego.jButton7) {
            System.out.println("Boton 7");
        }
        if (e.getSource() == this.objInicioJuego.jButton8) {
            System.out.println("Boton 8");
        }
        if (e.getSource() == this.objInicioJuego.jButton9) {
            System.out.println("Boton 9");
        }
        if (e.getSource() == this.objInicioJuego.jButton10) {
            System.out.println("Boton 10");
        }
        if (e.getSource() == this.objInicioJuego.jButton11) {
            System.out.println("Boton 11");
        }
        if (e.getSource() == this.objInicioJuego.jButton12) {
            System.out.println("Boton 12");
        }
        if (e.getSource() == this.objInicioJuego.jButton13) {
            System.out.println("Boton 13");
        }
        if (e.getSource() == this.objInicioJuego.jButton14) {
            System.out.println("Boton 14");
        }
        if (e.getSource() == this.objInicioJuego.jButton15) {
            System.out.println("Boton 15");
        }
        if (e.getSource() == this.objInicioJuego.jButton16) {
            System.exit(0);
        }
    }
}
