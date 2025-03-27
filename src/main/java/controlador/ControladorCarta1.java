package controlador;

import java.awt.datatransfer.*;
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
import java.sql.*;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;
import modelo.Palabra;
import vista.Carta1;

public class ControladorCarta1 implements ActionListener {

    private Carta1 objCarta1;
    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;

    public ControladorCarta1(Carta1 objCarta1) {
        this.objCarta1 = objCarta1;
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Aquí puedes manejar otros eventos, si es necesario
    }

    // Metodo que utiliza el modeloGuardaPalabras para cargar la palabra en la vista
    private void cargarPalabraNivel() {
        // Ahora getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(0);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 2);
                String complemento = palabraCompleta.substring(2);

                objCarta1.jLabel4.setText(silabaCorrecta);
                objCarta1.jLabel2.setText(complemento);
//                Ejemplo de silabas falsas en otros Jlabel
                objCarta1.jLabel5.setText("PE");
                objCarta1.jLabel6.setText("CA");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos");
        }
    }

//    Configuracion de Drag & Drop 
    private void arrastrarSoltar() {
        final String textoOriginal = objCarta1.jLabel3.getText();
        DragSource ds = new DragSource();

//         Configurar arrastre para Jlabel4
        ds.createDefaultDragGestureRecognizer(objCarta1.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta1.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel6
        ds.createDefaultDragGestureRecognizer(objCarta1.jLabel6, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta1.jLabel6.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar jLabel3 como zona de drop
        new DropTarget(objCarta1.jLabel3, new DropTargetListener() {
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
                    objCarta1.jLabel3.setText(droppedText);

                    if ((droppedText + objCarta1.jLabel2.getText()).equals("GATO")) {
                        objAudio.iniciarAudio("/audio/gato.wav");
                        //JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es GATO");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        objCarta1.jLabel3.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        );
    }
}
