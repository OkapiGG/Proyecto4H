/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;
import vista.MenuTablero;

/**
 *
 * @author alancervantes
 */
public class ControladorMenuTablero implements ActionListener{
    
    MenuTablero objMenuTablero;
    
    public ControladorMenuTablero(MenuTablero objMenuTablero){
        this.objMenuTablero=objMenuTablero;
        objMenuTablero.jButton1.addActionListener(this);
        objMenuTablero.jButton2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objMenuTablero.jButton1) {
            MenuInicio objMenuInicio = new MenuInicio();
            objMenuInicio.setVisible(true);
            this.objMenuTablero.dispose();
        }
        
        if (e.getSource() == this.objMenuTablero.jButton2) {
            System.out.println("Nivel 1");
        }
    }
    
}
