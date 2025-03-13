/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.VistaArrastraSilaba;

/**
 *
 * @author Alan
 */
public class ControladorArrastraSilaba implements ActionListener{

    private VistaArrastraSilaba objVistaArrastraSilaba;
    
    public ControladorArrastraSilaba(VistaArrastraSilaba objVistaArrastraSilaba){
        this.objVistaArrastraSilaba = objVistaArrastraSilaba;
        arrastrarSoltar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
    private void arrastrarSoltar() {
        
        String textoOriginal = objVistaArrastraSilaba.jLabel3.getText();

        DragSource ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(objVistaArrastraSilaba.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objVistaArrastraSilaba.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });
        ds.createDefaultDragGestureRecognizer(objVistaArrastraSilaba.jLabel5, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objVistaArrastraSilaba.jLabel5.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });
        ds.createDefaultDragGestureRecognizer(objVistaArrastraSilaba.jLabel6, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objVistaArrastraSilaba.jLabel6.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        //Convierte a jlabel3 en el receptor del jlabel4
        new DropTarget(objVistaArrastraSilaba.jLabel3, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                    Transferable transferable = dtde.getTransferable();
                    String droppedText = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                    objVistaArrastraSilaba.jLabel3.setText(droppedText); // Remplaza el espacio en blanco del jlabel3 en la silaba del jlabel4

                    if ((droppedText + objVistaArrastraSilaba.jLabel2.getText()).equals("GATO")) {
                        JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es GATO");                       
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");  
                        objVistaArrastraSilaba.jLabel3.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

}
