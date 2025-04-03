/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;
import modelo.Palabra;
import vista.CartaJirafa;
import vista.MenuJuego;

public class ControladorCartaJirafa implements ActionListener {

    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private CartaJirafa objCartaJirafa;

    public ControladorCartaJirafa(CartaJirafa objCartaJirafa) {
        this.objCartaJirafa = objCartaJirafa;
        this.objCartaJirafa.jButton1.addActionListener(this);
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
        if (e.getSource() == this.objCartaJirafa.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaJirafa.dispose();
        }
    }

    // Método que utiliza el modeloGuardaPalabras para cargar la palabra en la vista
    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(7);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(2, 4);
                String complemento = palabraCompleta.substring(0, 2);
                String complemento2 = palabraCompleta.substring(4, 6);
                
                objCartaJirafa.jLabel4.setText(silabaCorrecta);
                objCartaJirafa.jLabel5.setText(complemento);
                objCartaJirafa.jLabel7.setText(complemento2);
                // Sílabas falsas en otros JLabel
                objCartaJirafa.jLabel2.setText("XA");
                objCartaJirafa.jLabel3.setText("SI");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    // Configuración de Drag & Drop
    private void arrastrarSoltar() {
        final String textoOriginal = objCartaJirafa.jLabel6.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jLabel2
        ds.createDefaultDragGestureRecognizer(objCartaJirafa.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaJirafa.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCartaJirafa.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaJirafa.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaJirafa.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaJirafa.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar jLabel5 como zona de drop
        new DropTarget(objCartaJirafa.jLabel6, new DropTargetListener() {
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
                    objCartaJirafa.jLabel6.setText(droppedText);

                    if ((objCartaJirafa.jLabel5.getText() + droppedText + objCartaJirafa.jLabel7.getText()).equals("JIRAFA")) {
                        objAudio.reproducirAudio("mango");
                        JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es JIRAFA");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaJirafa.jLabel6.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        );
    }
}
