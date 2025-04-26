/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;
import vista.MenuMain;
import vista.VistaInicioSesion;
import vista.VistaRegistro;

/**
 *
 * @author alancervantes
 */
public class ControladorMenuMain implements ActionListener{

    MenuMain objMenuMain;
    
    public ControladorMenuMain(MenuMain objMenuMain){
        this.objMenuMain = objMenuMain;
        
        this.objMenuMain.jButton1.addActionListener(this);
        this.objMenuMain.jButton2.addActionListener(this);
        this.objMenuMain.jButton3.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.objMenuMain.jButton1){
            VistaInicioSesion vistaInicioSesion = new VistaInicioSesion();
            vistaInicioSesion.setVisible(true);
            this.objMenuMain.dispose();
        }
        if(e.getSource() == this.objMenuMain.jButton2){
            VistaRegistro vistaRegistro = new VistaRegistro();
            vistaRegistro.setVisible(true);
            this.objMenuMain.dispose();
        }
        if(e.getSource() == this.objMenuMain.jButton3){
            MenuInicio menuInicio = new MenuInicio();
            menuInicio.setVisible(true);
            this.objMenuMain.dispose();
        }
    }
    
}
