package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.MenuInicio;

public class ControladorMenuInicio implements ActionListener {

    MenuInicio objMenuIncio;

    public ControladorMenuInicio(MenuInicio objMenuIncio) {
        this.objMenuIncio = objMenuIncio;
        this.objMenuIncio.jButton1.addActionListener(this);
        this.objMenuIncio.jButton2.addActionListener(this);
        this.objMenuIncio.jButton3.addActionListener(this);
        this.objMenuIncio.jButton4.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objMenuIncio.jButton1) {
            // Arrastra la silaba
            
        }

        if (e.getSource() == this.objMenuIncio.jButton2) {
            // Tablero
        }

        if (e.getSource() == this.objMenuIncio.jButton3) {
            // Ordena
        }

        if (e.getSource() == this.objMenuIncio.jButton4) {
            // Cuenta
        }

    }

}
