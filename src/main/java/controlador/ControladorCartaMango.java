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
import vista.CartaMango;
import vista.MenuJuego;

public class ControladorCartaMango extends ControladorClaseDragDrop {

    private CartaMango objCartaMango;

    public ControladorCartaMango(CartaMango objCartaMango) {
        this.objCartaMango = objCartaMango;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(5);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 3);
                String complemento = palabraCompleta.substring(3, 5);
                objCartaMango.jLabel3.setText(silabaCorrecta);
                objCartaMango.jLabel6.setText(complemento);
                // Sílabas falsas en otros JLabel
                objCartaMango.jLabel2.setText("KO");
                objCartaMango.jLabel4.setText("TU");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    @Override
    protected void arrastrarSoltar() {
        final String textoOriginal = objCartaMango.jLabel5.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jLabel2
        ds.createDefaultDragGestureRecognizer(objCartaMango.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaMango.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCartaMango.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaMango.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaMango.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaMango.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar jLabel5 como zona de drop
        new DropTarget(objCartaMango.jLabel5, new DropTargetListener() {
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
                    objCartaMango.jLabel5.setText(droppedText);

                    if ((droppedText + objCartaMango.jLabel6.getText()).equals("MANGO")) {
                        objAudio.reproducirAudio("mango");

                        int idUsuario = modelo.Login.getIdUsuarioActivo();
                        OperacionesBDCuenta operacionesCuenta = new OperacionesBDCuenta();
                        operacionesCuenta.actualizarPuntajeYPalabras(idUsuario, 10, 1);

                        JOptionPane.showMessageDialog(
                                null,
                                "¡Correcto!\nGanaste 10 puntos 🏆",
                                "Nivel completado",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        MenuJuego objMenuJuego = new MenuJuego();
                        objMenuJuego.setVisible(true);
                        objCartaMango.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaMango.jLabel5.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaMango.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaMango.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaMango.jButton1.addActionListener(this);
    }
}
