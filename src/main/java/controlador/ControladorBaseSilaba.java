package controlador;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import modelo.*;

public abstract class ControladorBaseSilaba {

    protected Connection conexion;
    protected ModeloPalabra modeloPalabra;
    protected ModeloGuardaPalabras modeloGuardaPalabras;
    protected ControladorAudios objAudio;

    public ControladorBaseSilaba() {
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            this.modeloPalabra = new ModeloPalabra();
            this.modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.objAudio = new ControladorAudios();
    }

    public void cargarPalabra() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabra = lista.get(getIndicePalabra());
            procesarPalabra(palabra);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    public void configurarDragAndDrop(
        JLabel[] arrastrables,
        JLabel dropZone,
        JLabel complemento,
        String palabraCorrecta,
        String audioPalabra
    ) {
        String textoOriginal = dropZone.getText();
        DragSource ds = new DragSource();

        for (JLabel label : arrastrables) {
            ds.createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_MOVE, dge -> {
                Transferable t = new StringSelection(label.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, t, null);
            });
        }

        new DropTarget(dropZone, new DropTargetListener() {
            @Override public void dragEnter(DropTargetDragEvent dtde) {}
            @Override public void dragOver(DropTargetDragEvent dtde) {}
            @Override public void dropActionChanged(DropTargetDragEvent dtde) {}
            @Override public void dragExit(DropTargetEvent dte) {}

            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                    String dropped = (String) dtde.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    dropZone.setText(dropped);

                    String resultado = dropped + complemento.getText();
                    if (resultado.equalsIgnoreCase(palabraCorrecta)) {
                        objAudio.reproducirAudio(audioPalabra);
                        JOptionPane.showMessageDialog(null, "¡Correcto! La palabra es " + palabraCorrecta);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                        dropZone.setText(textoOriginal);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    protected int getIndicePalabra() {
        return 0;
    }

    protected abstract void procesarPalabra(Palabra palabra);
}
