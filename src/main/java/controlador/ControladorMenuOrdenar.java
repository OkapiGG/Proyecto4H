/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;
import vista.MenuOrdenar;
import vista.OCartaNivel1;
import vista.OCartaNivel2;
import vista.OCartaNivel3;
import vista.OCartaNivel4;
import vista.OCartaNivel5;
import vista.OCartaNivel6;
import vista.OCartaNivel7;
import vista.OCartaNivel8;
import vista.OCartaNivel9;

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
        if(e.getSource() == this.objMenuOrdenar.jButton1){
            OCartaNivel1 objCartaNivel1 = new OCartaNivel1();
            objCartaNivel1.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton2){
            OCartaNivel2 oCartaNivel2 = new OCartaNivel2();
            oCartaNivel2.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton3){
            OCartaNivel3 oCartaNivel3 = new OCartaNivel3();
            oCartaNivel3.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton4){
            OCartaNivel4 oCartaNivel4 = new OCartaNivel4();
            oCartaNivel4.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton5){
            OCartaNivel5 oCartaNivel5 = new OCartaNivel5();
            oCartaNivel5.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton6){
            OCartaNivel6 oCartaNivel6 = new OCartaNivel6();
            oCartaNivel6.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton7){
            OCartaNivel7 oCartaNivel7 = new OCartaNivel7();
            oCartaNivel7.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton8){
            OCartaNivel8 oCartaNivel8 = new OCartaNivel8();
            oCartaNivel8.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton9){
            OCartaNivel9 oCartaNivel9 = new OCartaNivel9();
            oCartaNivel9.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
        if(e.getSource() == this.objMenuOrdenar.jButton10){
            MenuInicio objMenuInicio = new MenuInicio();
            objMenuInicio.setVisible(true);
            this.objMenuOrdenar.dispose();
        }
    } 
}
