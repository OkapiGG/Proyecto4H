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
import vista.Carta4;

public class ControladorCarta4 implements ActionListener {

    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private Carta4 objCarta4;

    public ControladorCarta4(Carta4 objCarta4) {
        this.objCarta4 = objCarta4;
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

    }

    // Método que utiliza el modeloGuardaPalabras para cargar la palabra en la vista
    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(5);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(3, 5);
                String complemento = palabraCompleta.substring(0, 3);

                objCarta4.jLabel3.setText(silabaCorrecta);
                objCarta4.jLabel5.setText(complemento);
                // Sílabas falsas en otros JLabel
                objCarta4.jLabel2.setText("JA");
                objCarta4.jLabel4.setText("TU");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    // Configuración de Drag & Drop
    private void arrastrarSoltar() {
        final String textoOriginal = objCarta4.jLabel6.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCarta4.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta4.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel5
        ds.createDefaultDragGestureRecognizer(objCarta4.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta4.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel6
        ds.createDefaultDragGestureRecognizer(objCarta4.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta4.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar jLabel3 como zona de drop
        new DropTarget(objCarta4.jLabel6, new DropTargetListener() {
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
                    objCarta4.jLabel6.setText(droppedText);

                    if ((objCarta4.jLabel5.getText() + droppedText ).equals("MANGO")) {
                            objAudio.iniciarAudio("/audio/mango.wav");
                            JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es MANGO");
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                            objCarta4.jLabel6.setText(textoOriginal);
                        }
                    }catch (Exception ex) {
                    ex.printStackTrace();
                }
                }
            }
        );
    }
}

