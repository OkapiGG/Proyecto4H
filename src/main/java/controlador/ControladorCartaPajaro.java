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
import vista.CartaPajaro;
import vista.MenuJuego;
import vista.MenuTablero;

public class ControladorCartaPajaro extends ControladorClaseDragDrop {

    private CartaPajaro objCartaPajaro;
    private ControladorAudios objAudio;

    public ControladorCartaPajaro(CartaPajaro objCartaPajaro) {
        this.objCartaPajaro = objCartaPajaro;
        objAudio = new ControladorAudios();
    }

    @Override
    protected void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(8);
            String palabraCompleta = palabraActual.getPalabra();
            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 2);
                String complemento = palabraCompleta.substring(2, 6);
                objCartaPajaro.jLabel4.setText(silabaCorrecta);
                objCartaPajaro.jLabel6.setText(complemento);
                // Sílabas falsas en otros JLabel
                objCartaPajaro.jLabel2.setText("BE");
                objCartaPajaro.jLabel3.setText("WI");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    @Override
    protected void arrastrarSoltar() {
        final String textoOriginal = objCartaPajaro.jLabel5.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jLabel2
        ds.createDefaultDragGestureRecognizer(objCartaPajaro.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaPajaro.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objAudio.reproducirAudio("be");
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCartaPajaro.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaPajaro.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objAudio.reproducirAudio("wi");
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCartaPajaro.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaPajaro.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objAudio.reproducirAudio("pa");
            }
        });

        // Configurar jLabel3 como zona de drop
        new DropTarget(objCartaPajaro.jLabel5, new DropTargetListener() {
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
                    objCartaPajaro.jLabel5.setText(droppedText);

                    if ((droppedText + objCartaPajaro.jLabel6.getText()).equals("PAJARO")) {
                        objAudio.reproducirAudio("pajaro");

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
                        objCartaPajaro.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaPajaro.jLabel5.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaPajaro.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaPajaro.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaPajaro.jButton1.addActionListener(this);
    }
}
