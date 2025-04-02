/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;
import modelo.Palabra;
import vista.CartaSandia;
import vista.MenuJuego;

/**
 *
 * @author alancervantes
 */
public class ControladorCartaSandia implements ActionListener {

    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private CartaSandia objCartaSandia;

    public ControladorCartaSandia(CartaSandia objCartaSandia) {
        this.objCartaSandia = objCartaSandia;
        this.objCartaSandia.jButton1.addActionListener(this);
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        arrastrarSoltar();
        objAudio = new ControladorAudios();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objCartaSandia.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaSandia.dispose();
        }
    }

    private void cargarPalabraDelNivel() {
//        Ahora getPalabras() Devuelve una lista de objetos de palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(6);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(3, 5);
                String complemento = palabraCompleta.substring(0, 3);
                String complemento2 = palabraCompleta.substring(5, 6);

                objCartaSandia.jLabel4.setText(silabaCorrecta);
                objCartaSandia.jLabel5.setText(complemento);
                objCartaSandia.jLabel7.setText(complemento2);
//                Silabas Falsas
                objCartaSandia.jLabel2.setText("NA");
                objCartaSandia.jLabel3.setText("YE");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    // Configuración de Drag & Drop
    private void arrastrarSoltar() {
        final String textoOriginal = objCartaSandia.jLabel6.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jlabel2
        ds.createDefaultDragGestureRecognizer(objCartaSandia.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaSandia.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCartaSandia.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaSandia.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaSandia.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaSandia.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar Jlabel5 como receptor o drop
        new DropTarget(objCartaSandia.jLabel6, new DropTargetListener() {
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
                    objCartaSandia.jLabel6.setText(droppedText);

                    if ((objCartaSandia.jLabel5.getText() + droppedText + objCartaSandia.jLabel7.getText()).equals("SANDIA")) {
                        objAudio.reproducirAudio("sandia");
                        JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es SANDIA");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaSandia.jLabel6.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        );
    }

}
