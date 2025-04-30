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
import vista.CartaArbol;
import vista.MenuJuego;

public class ControladorCartaArbol extends ControladorClaseDragDrop {
    
    private CartaArbol objCartaArbol;
    private ControladorAudios objControladorAudios;
    
    public ControladorCartaArbol(CartaArbol objCartaArbol) {
        this.objCartaArbol = objCartaArbol;
        objControladorAudios = new ControladorAudios();
    }
    
    @Override
    protected void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(4); // Cambia a la palabra que necesitas
            String palabraCompleta = palabraActual.getPalabra();
            
            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(2, 5);
                String complemento = palabraCompleta.substring(0, 2);
                objCartaArbol.jLabel2.setText(silabaCorrecta);
                objCartaArbol.jLabel5.setText(complemento);
                objCartaArbol.jLabel3.setText("NO");
                objCartaArbol.jLabel4.setText("QUE");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }
    
    @Override
    protected void arrastrarSoltar() {
        final String textoOriginal = objCartaArbol.jLabel6.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para cada JLabel
        ds.createDefaultDragGestureRecognizer(objCartaArbol.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaArbol.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objControladorAudios.reproducirAudio("bol");
            }
        });
        
        ds.createDefaultDragGestureRecognizer(objCartaArbol.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaArbol.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objControladorAudios.reproducirAudio("no");
            }
        });
        
        ds.createDefaultDragGestureRecognizer(objCartaArbol.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCartaArbol.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
                objControladorAudios.reproducirAudio("que");
            }
        });

        // Configurar la zona de drop
        new DropTarget(objCartaArbol.jLabel6, new DropTargetListener() {
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
                    objCartaArbol.jLabel6.setText(droppedText);
                    if ((objCartaArbol.jLabel5.getText() + droppedText).equals("ARBOL")) {
                        objAudio.reproducirAudio("arbol");
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
                        objCartaArbol.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCartaArbol.jLabel6.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        });
    }
    
    @Override
    protected void manejarEvento(Object boton) {
        if (boton == this.objCartaArbol.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCartaArbol.dispose();
            objCartaArbol.setEnabled(false);
        }
    }
    
    @Override
    protected void agregarAccionadorEventos() {
        this.objCartaArbol.jButton1.addActionListener(this);
    }
}
