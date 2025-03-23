package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Login;
import vista.Registro;

public class ControladorRegistro implements ActionListener {

    Registro objRegistro;
    Login objLogin;
    OperacionesBDLogin objOperacionesBDLogin;
    OperacionesListaLogin objOperacionesListaLogin;

    public ControladorRegistro(Registro objRegistro) {
        this.objRegistro = objRegistro;
        objLogin = new Login();
        objOperacionesBDLogin = new OperacionesBDLogin();
        objOperacionesListaLogin = new OperacionesListaLogin();
        this.objRegistro.jButton1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objRegistro.jButton1) {
             System.out.println("Click en el boton");

            Login objLogin = new Login();
            //objLogin.setIdusaurio(objLoginAlta.jTextField1.getX());
            objLogin.setUsername(objRegistro.jTextField1.getText());
            objLogin.setContrasena(objRegistro.jTextField2.getText());

            objOperacionesListaLogin.setObjLogin(objLogin);
            objOperacionesListaLogin.create();
            
            objOperacionesBDLogin.setObjLogin(objLogin);
            objOperacionesBDLogin.create();
        }
    }

}
