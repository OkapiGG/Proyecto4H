/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.MenuInicio;
import vista.TCartaPizza;

/**
 *
 * @author alancervantes
 */
public class ControladorTCartaPizza implements MouseListener{

    private TCartaPizza objTCartaPizza;
    
    public ControladorTCartaPizza(TCartaPizza objTCartaPizza){
        this.objTCartaPizza = objTCartaPizza;
        this.objTCartaPizza.jButton1.addMouseListener(this);
        this.objTCartaPizza.jButton2.addMouseListener(this);
        this.objTCartaPizza.jButton3.addMouseListener(this);
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == this.objTCartaPizza.jButton1){
            System.out.println("Click boton 1");
        }
        
        if(e.getSource() == this.objTCartaPizza.jButton2){
            System.out.println("Click boton 2");
        }
        
        if(e.getSource() == this.objTCartaPizza.jButton3){
            System.out.println("Click boton 3");
        }
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
}
