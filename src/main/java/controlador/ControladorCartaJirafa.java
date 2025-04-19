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
import vista.CartaJirafa;
import vista.MenuJuego;

public class ControladorCartaJirafa extends ControladorClaseDragDrop {

    private CartaJirafa objCartaJirafa;

    public ControladorCartaJirafa(CartaJirafa objCartaJirafa) {
        this.objCartaJirafa = objCartaJirafa;
    }

    @Override
    protected void cargarPalabraDelNivel() {
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

    @Override
    protected void arrastrarSoltar() {
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
                        objAudio.reproducirAudio("jirafa");
                        JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es JIRAFA");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaJirafa.jLabel6.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaJirafa.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaJirafa.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaJirafa.jButton1.addActionListener(this);
    }
}
