package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import modelo.Palabra;
import vista.CartaGato;
import vista.MenuJuego;

public class CartaGatoAbstractaPrueba extends ControladorBaseSilaba implements ActionListener {
    
    CartaGato objCartaGato;
    
    public CartaGatoAbstractaPrueba(CartaGato objCartaGato){
        super();
        this.objCartaGato = objCartaGato;
        cargarPalabra();
        //configurarDragAndDrop();
    }
    
    @Override
    protected void procesarPalabra(Palabra palabra) {
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    
}
