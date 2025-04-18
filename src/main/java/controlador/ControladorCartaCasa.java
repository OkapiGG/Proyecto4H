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
import vista.CartaCasa;
import vista.MenuJuego;

public class ControladorCartaCasa extends ControladorClaseDragDrop {

    private CartaCasa objCartaCasa;

    public ControladorCartaCasa(CartaCasa objCartaCasa) {
        this.objCartaCasa = objCartaCasa;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        //        Ahora getPalabras() Devuelve una lista de objetos de palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(2);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(2, 4);
                String complemento = palabraCompleta.substring(0, 2);
                objCartaCasa.jLabel2.setText(silabaCorrecta);
                objCartaCasa.jLabel6.setText(complemento);
//                Silabas Falsas
                objCartaCasa.jLabel3.setText("ZA");
                objCartaCasa.jLabel4.setText("PA");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    @Override
    protected void arrastrarSoltar() {
        final String textoOriginal = objCartaCasa.jLabel5.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jlabel2
        ds.createDefaultDragGestureRecognizer(objCartaCasa.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaCasa.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCartaCasa.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaCasa.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaCasa.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaCasa.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar Jlabel5 como receptor o drop
        new DropTarget(objCartaCasa.jLabel5, new DropTargetListener() {
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
                    objCartaCasa.jLabel5.setText(droppedText);

                    if ((objCartaCasa.jLabel6.getText() + droppedText).equals("CASA")) {
                        objAudio.reproducirAudio("casa");
                        JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es CASA");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaCasa.jLabel5.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaCasa.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaCasa.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaCasa.jButton1.addActionListener(this);
    }
}
