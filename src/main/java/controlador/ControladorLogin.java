/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaLogin;

/**
 *
 * @author Alan
 */
public class ControladorLogin implements ActionListener{
    
    VistaLogin objVistaLogin;

    public ControladorLogin(VistaLogin objVistaLogin) {
        this.objVistaLogin = objVistaLogin;
        this.objVistaLogin.jButton1.addActionListener(this);
        this.objVistaLogin.jButton2.addActionListener(this);
        this.objVistaLogin.jButton3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objVistaLogin.jButton1) {
            System.out.println("INICIAR SESION");
        }
        if (e.getSource() == objVistaLogin.jButton2) {
            System.out.println("REGISTRAR");
        }
        if (e.getSource() == objVistaLogin.jButton3) {
            System.out.println("INVITADO");
        }
    }
}
