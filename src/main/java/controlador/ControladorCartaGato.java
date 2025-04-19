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
import vista.CartaGato;
import vista.MenuJuego;

public class ControladorCartaGato extends ControladorClaseDragDrop {

    private CartaGato objCartaGato;

    public ControladorCartaGato(CartaGato objCartaGato) {
        this.objCartaGato = objCartaGato;
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(0);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 2);
                String complemento = palabraCompleta.substring(2);
                objCartaGato.jLabel4.setText(silabaCorrecta);
                objCartaGato.jLabel2.setText(complemento);
                // Sílabas falsas en otros JLabel
                objCartaGato.jLabel5.setText("PA");
                objCartaGato.jLabel6.setText("LO");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    @Override
    protected void arrastrarSoltar() {
        final String textoOriginal = objCartaGato.jLabel3.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaGato.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaGato.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel5
        ds.createDefaultDragGestureRecognizer(objCartaGato.jLabel5, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaGato.jLabel5.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel6
        ds.createDefaultDragGestureRecognizer(objCartaGato.jLabel6, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaGato.jLabel6.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar jLabel3 como zona de drop
        new DropTarget(objCartaGato.jLabel3, new DropTargetListener() {
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
                    objCartaGato.jLabel3.setText(droppedText);
                    if ((droppedText + objCartaGato.jLabel2.getText()).equals("GATO")) {
                    objAudio.reproducirAudio("gato");
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
                    objCartaGato.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                    objCartaGato.jLabel3.setText(textoOriginal);
                }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaGato.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaGato.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaGato.jButton1.addActionListener(this);
    }
}
