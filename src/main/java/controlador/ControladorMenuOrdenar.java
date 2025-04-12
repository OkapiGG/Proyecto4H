/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.CartaJirafa;
import vista.MenuInicio;
import vista.MenuOrdenar;

/**
 *
 * @author alancervantes
 */
public class ControladorMenuOrdenar implements ActionListener{

    MenuOrdenar objMenuOrdenar;
    
    public ControladorMenuOrdenar(MenuOrdenar objMenuOrdenar){
        this.objMenuOrdenar = objMenuOrdenar;
        this.objMenuOrdenar.jButton1.addActionListener(this);
        this.objMenuOrdenar.jButton2.addActionListener(this);
        this.objMenuOrdenar.jButton3.addActionListener(this);
        this.objMenuOrdenar.jButton4.addActionListener(this);
        this.objMenuOrdenar.jButton5.addActionListener(this);
        this.objMenuOrdenar.jButton6.addActionListener(this);
        this.objMenuOrdenar.jButton7.addActionListener(this);
        this.objMenuOrdenar.jButton8.addActionListener(this);
        this.objMenuOrdenar.jButton9.addActionListener(this);
        this.objMenuOrdenar.jButton10.addActionListener(this);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.objMenuOrdenar.jButton10){
            MenuInicio objMenuInicio = new MenuInicio();
            objMenuInicio.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        
    }
    
}
