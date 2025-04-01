/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.CartaArbol;
import vista.CartaBicicleta;
import vista.CartaCarro;
import vista.CartaCasa;
import vista.CartaGato;
import vista.CartaJirafa;
import vista.CartaMango;
import vista.CartaManzana;
import vista.CartaPajaro;
import vista.CartaPerro;
import vista.CartaSandia;
import vista.MenuInicio;
import vista.MenuJuego;

/**
 *
 * @author ep712
 */
public class ControladorMenuJuego implements ActionListener {

    MenuJuego objMenuJuego;

    public ControladorMenuJuego(MenuJuego objMenuJuego) {
        this.objMenuJuego = objMenuJuego;
        this.objMenuJuego.jButton1.addActionListener(this);
        this.objMenuJuego.jButton2.addActionListener(this);
        this.objMenuJuego.jButton3.addActionListener(this);
        this.objMenuJuego.jButton4.addActionListener(this);
        this.objMenuJuego.jButton5.addActionListener(this);
        this.objMenuJuego.jButton6.addActionListener(this);
        this.objMenuJuego.jButton7.addActionListener(this);
        this.objMenuJuego.jButton8.addActionListener(this);
        this.objMenuJuego.jButton9.addActionListener(this);
        this.objMenuJuego.jButton10.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objMenuJuego.jButton1) {
            System.out.println("Presionado");
            CartaArbol objCartaArbol = new CartaArbol();
            objCartaArbol.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton2) {
            System.out.println("Presionado");
            CartaBicicleta objCartaBicicleta = new CartaBicicleta();
            objCartaBicicleta.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton3) {
            System.out.println("Presionado");
            CartaCarro objCartaCarro = new CartaCarro();
            objCartaCarro.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton4) {
            System.out.println("Presionado");
            CartaCasa objCartaCasa = new CartaCasa();
            objCartaCasa.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton5) {
            System.out.println("Presionado");
            CartaGato objCartaGato = new CartaGato();
            objCartaGato.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton6) {
            System.out.println("Presionado");
            CartaJirafa objCartaJirafa = new CartaJirafa();
            objCartaJirafa.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton7) {
            System.out.println("Presionado");
            CartaMango objCartaMango = new CartaMango();
            objCartaMango.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton8) {
            System.out.println("Presionado");
            CartaSandia objCartaSandia = new CartaSandia();
            objCartaSandia.setVisible(true);
            this.objMenuJuego.dispose();
        }
        if (e.getSource() == this.objMenuJuego.jButton9) {
            System.out.println("Presionado");
            CartaPajaro objCartaPajaro = new CartaPajaro();
            objCartaPajaro.setVisible(true);
            this.objMenuJuego.dispose();

        }
//        if (e.getSource() == this.objMenuJuego.jButton10) {
//            System.out.println("Presionado");
//            MenuInicio objMenuInicio = new MenuInicio();
//            objMenuInicio.setVisible(true);
//            this.objMenuJuego.dispose();
//        }
    }
}
