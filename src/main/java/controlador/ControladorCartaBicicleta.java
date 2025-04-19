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
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.CartaBicicleta;
import vista.MenuJuego;

public class ControladorCartaBicicleta extends ControladorClaseDragDrop {

    private CartaBicicleta objCartaBicicleta;

    public ControladorCartaBicicleta(CartaBicicleta objCartaBicicleta) {
        this.objCartaBicicleta = objCartaBicicleta;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        //        Ahora getPalabras() Devuelve una lista de objetos de palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(12);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(4, 7);
                String complemento = palabraCompleta.substring(0, 4);
                String complemento2 = palabraCompleta.substring(7, 9);
                objCartaBicicleta.jLabel2.setText(silabaCorrecta);
                objCartaBicicleta.jLabel5.setText(complemento);
                objCartaBicicleta.jLabel7.setText(complemento2);
//                Silabas Falsas
                objCartaBicicleta.jLabel3.setText("CLO");
                objCartaBicicleta.jLabel4.setText("JE");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }

    }

    @Override
    protected void arrastrarSoltar() {
        final String textoOriginal = objCartaBicicleta.jLabel6.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jlabel2
        ds.createDefaultDragGestureRecognizer(objCartaBicicleta.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaBicicleta.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCartaBicicleta.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaBicicleta.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaBicicleta.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaBicicleta.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar Jlabel5 como receptor o drop
        new DropTarget(objCartaBicicleta.jLabel6, new DropTargetListener() {
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
                    objCartaBicicleta.jLabel6.setText(droppedText);
                    if ((objCartaBicicleta.jLabel5.getText() + droppedText + objCartaBicicleta.jLabel7.getText()).equals("BICICLETA")) {
                        objAudio.reproducirAudio("bicicleta");
                        JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es BICICLETA");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaBicicleta.jLabel6.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaBicicleta.jButton2) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaBicicleta.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaBicicleta.jButton2.addActionListener(this);
    }
}
