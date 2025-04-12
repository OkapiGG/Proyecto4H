/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;
import vista.MenuTablero;
import vista.TCartaBillete;
import vista.TCartaCama;
import vista.TCartaConejo;
import vista.TCartaFoco;
import vista.TCartaHamburguesa;
import vista.TCartaLeon;
import vista.TCartaManzana;
import vista.TCartaMesa;
import vista.TCartaPerro;
import vista.TCartaPizza;
import vista.TCartaSilla;
import vista.TCartaZapato;

/**
 *
 * @author alancervantes
 */
public class ControladorMenuTablero implements ActionListener {

    MenuTablero objMenuTablero;

    public ControladorMenuTablero(MenuTablero objMenuTablero) {
        this.objMenuTablero = objMenuTablero;
        objMenuTablero.jButton1.addActionListener(this);
        objMenuTablero.jButton2.addActionListener(this);
        objMenuTablero.jButton3.addActionListener(this);
        objMenuTablero.jButton4.addActionListener(this);
        objMenuTablero.jButton5.addActionListener(this);
        objMenuTablero.jButton6.addActionListener(this);
        objMenuTablero.jButton7.addActionListener(this);
        objMenuTablero.jButton8.addActionListener(this);
        objMenuTablero.jButton9.addActionListener(this);
        objMenuTablero.jButton10.addActionListener(this);
        objMenuTablero.jButton11.addActionListener(this);
        objMenuTablero.jButton12.addActionListener(this);
        objMenuTablero.jButton13.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objMenuTablero.jButton1) {
            MenuInicio objMenuInicio = new MenuInicio();
            objMenuInicio.setVisible(true);
            this.objMenuTablero.dispose();
        }
//       Inicia los botones de juego
        if (e.getSource() == this.objMenuTablero.jButton2) {
            TCartaPizza tCartaPizza = new TCartaPizza();
            tCartaPizza.setVisible(true); // Carta Pizza
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton3) {
            TCartaPerro objTCartaPerro = new TCartaPerro();
            objTCartaPerro.setVisible(true); // Carta Perro
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton4) {
            TCartaSilla objTCartaSilla = new TCartaSilla();
            objTCartaSilla.setVisible(true); // Carta  silla
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton5) {
            TCartaHamburguesa objTCartaHambuerguesa = new TCartaHamburguesa();
            objTCartaHambuerguesa.setVisible(true); // Carta hamburguesa
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton6) {
            TCartaManzana objTCartaManzana = new TCartaManzana();
            objTCartaManzana.setVisible(true); // Carta manzana
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton7) {
            TCartaMesa objTCartaMesa = new TCartaMesa();
            objTCartaMesa.setVisible(true); // Carta mesa
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton8) {
            TCartaBillete objTCartaBillete = new TCartaBillete();
            objTCartaBillete.setVisible(true); // Carta billete
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton9) {
            TCartaFoco objTCartaFoco = new TCartaFoco();
            objTCartaFoco.setVisible(true);  // Carta foco
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton10) {
            TCartaConejo objTCartaConejo = new TCartaConejo();
            objTCartaConejo.setVisible(true); // Carta conejo
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton11) {
            TCartaZapato objTCartaZapato = new TCartaZapato();
            objTCartaZapato.setVisible(true); // Carta zapato
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton12) {
            TCartaLeon objTCartaLeon = new TCartaLeon();
            objTCartaLeon.setVisible(true); // Carta leon
            this.objMenuTablero.dispose();
        }

        if (e.getSource() == this.objMenuTablero.jButton13) {
            TCartaCama objTCartaCama = new TCartaCama();
            objTCartaCama.setVisible(true); // Carta cama
            this.objMenuTablero.dispose();
        }
    }
}
