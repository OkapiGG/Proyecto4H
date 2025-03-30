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
import vista.Carta3;

public class ControladorCarta3 implements ActionListener {

    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private Carta3 objCarta3;

    public ControladorCarta3(Carta3 objCarta3) {
        this.objCarta3 = objCarta3;
//        this.objCarta3.jButton1.addActionListener(this);
        try {

            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras();
//            cargarPalabraDelNivel();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        arrastrarSoltar();
        objAudio = new ControladorAudios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
// Aquí puedes manejar otros eventos, si es necesario
    }

    private void cargarPalabraDelNivel() {
//        Ahora getPalabras() Devuelve una lista de objetos de palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(0);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 2);
                String complemento = palabraCompleta.substring(2);

                objCarta3.jLabel2.setText(silabaCorrecta);
                objCarta3.jLabel6.setText(complemento);
//                Silabas Falsas
                objCarta3.jLabel3.setText("ZA");
                objCarta3.jLabel4.setText("PA");

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    // Configuración de Drag & Drop
    private void arrastrarSoltar() {
        final String textoOriginal = objCarta3.jLabel5.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jlabel2
        ds.createDefaultDragGestureRecognizer(objCarta3.jLabel2, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta3.jLabel2.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel3
        ds.createDefaultDragGestureRecognizer(objCarta3.jLabel3, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta3.jLabel3.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCarta3.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta3.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar Jlabel5 como receptor o drop
        new DropTarget(objCarta3.jLabel5, new DropTargetListener() {
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
                    objCarta3.jLabel5.setText(droppedText);

                    if ((droppedText + objCarta3.jLabel6.getText()).equals("CASA")) {
//                            objAudio.iniciarAudio("/audio/gato.wav");
                        //JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es GATO");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCarta3.jLabel5.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        );
    }
}
