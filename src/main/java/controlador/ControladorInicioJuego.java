
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.InicioJuego;

public class ControladorInicioJuego implements ActionListener{

    InicioJuego objInicioJuego;

    public ControladorInicioJuego(InicioJuego objInicioJuego) {
        this.objInicioJuego = objInicioJuego;
        this.objInicioJuego.jButton1.addActionListener(this);
        this.objInicioJuego.jButton2.addActionListener(this);
        this.objInicioJuego.jButton3.addActionListener(this);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==this.objInicioJuego.jButton1) {
            System.out.println("Inicio Sesion");    
        }
        if (e.getSource()==this.objInicioJuego.jButton2) {
            System.out.println("Registrarse");
        }
        if (e.getSource()==this.objInicioJuego.jButton3) {
            System.out.println("Invitado");
        }        
    }    
}
