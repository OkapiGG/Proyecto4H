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
import vista.CartaCarro;
import vista.MenuJuego;

public class ControladorCartaCarro extends ControladorClaseDragDrop {

    private CartaCarro objCartaCarro;
    private ControladorAudios objControladorAudios;

    public ControladorCartaCarro(CartaCarro objCartaCarro) {
        this.objCartaCarro = objCartaCarro;
        objControladorAudios = new ControladorAudios();
    }

    @Override
    protected void cargarPalabraDelNivel() {
        //        Ahora getPalabras() Devuelve una lista de objetos de palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(3);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 2);
                String complemento = palabraCompleta.substring(2, 5);
                objCartaCarro.jLabel2.setText(silabaCorrecta);
                objCartaCarro.jLabel6.setText(complemento);
//                Silabas Falsas
                objCartaCarro.jLabel3.setText("MU");
                objCartaCarro.jLabel4.setText("FO");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    @Override
    protected void arrastrarSoltar() {
        final String textoOriginal = objCartaCarro.jLabel5.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jlabel2
        ds.createDefaultDragGestureRecognizer(objCartaCarro.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaCarro.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objControladorAudios.reproducirAudio("ca");
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCartaCarro.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaCarro.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objControladorAudios.reproducirAudio("mu");
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaCarro.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaCarro.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objControladorAudios.reproducirAudio("fo");
            }
        });

        // Configurar Jlabel5 como receptor o drop
        new DropTarget(objCartaCarro.jLabel5, new DropTargetListener() {
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
                    objCartaCarro.jLabel5.setText(droppedText);

                    if ((droppedText + objCartaCarro.jLabel6.getText()).equals("CARRO")) {
                        objAudio.reproducirAudio("carro");

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
                        objCartaCarro.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaCarro.jLabel5.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaCarro.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaCarro.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaCarro.jButton1.addActionListener(this);
    }
}
