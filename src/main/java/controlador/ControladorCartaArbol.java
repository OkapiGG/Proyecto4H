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
import vista.CartaArbol;

public class ControladorCartaArbol implements ActionListener {

    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    CartaArbol objCartaArbol;

    public ControladorCartaArbol(CartaArbol objCartaArbol) {
        this.objCartaArbol = objCartaArbol;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        arrastrarSoltar();
        objAudio = new ControladorAudios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//       Aqui puedes manejar otros eventos del ActionEvent
    }
//      Metodo que se utiliza en el modeloGuardaPalabras

    private void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(0);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 2);
                String Complemento = palabraCompleta.substring(2);

                objCartaArbol.jLabel2.setText(silabaCorrecta);
                objCartaArbol.jLabel5.setText(Complemento);

                objCartaArbol.jLabel3.setText("PA");
                objCartaArbol.jLabel4.setText("CA");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos");
        }
    }

//    Configuracion de Drag y Drop
    private void arrastrarSoltar() {
        final String textoOriginal = objCartaArbol.jLabel6.getText();
        DragSource ds = new DragSource();

        //        Configurar arrastre para Jlabel2
        ds.createDefaultDragGestureRecognizer(objCartaArbol.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaArbol.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

//        Arrastra pa Jlabel3
        ds.createDefaultDragGestureRecognizer(objCartaArbol.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaArbol.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

//        Arrastre para Jlabel4
        ds.createDefaultDragGestureRecognizer(objCartaArbol.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaArbol.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });
        
//        Configurar jLabel6 como zona drop
        
           new DropTarget(objCartaArbol.jLabel6, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                 try {
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                    Transferable transferable = dtde.getTransferable();
                    String droppedText = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                    objCartaArbol.jLabel6.setText(droppedText);

                    if ((droppedText + objCartaArbol.jLabel2.getText()).equals("ARBOL")) {
//                            objAudio.iniciarAudio("/audio/gato.wav");
                            //JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es GATO");
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                            objCartaArbol.jLabel6.setText(textoOriginal);
                        }
                    }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
           });
    }
}
