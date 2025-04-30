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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuArrastre;
import vista.VistaCartaArrastra;

public class ControladorVistaArrastre extends ControladorClaseDragDrop {

    private final VistaCartaArrastra vista;
    private final String silabaCorrecta;
    private String palabraEsperada;
    private final String[] silabasFalsas;
    private final String plantillaPalabra;
    private final int indicePalabra;

    public ControladorVistaArrastre(
            VistaCartaArrastra vista,
            int indicePalabra,
            String silabaCorrecta,
            String[] silabasFalsas,
            String audioCorrecto, // se mantiene para reproducir al acertar
            ImageIcon imagen,
            String plantillaPalabra
    ) {
        this.vista = vista;
        this.indicePalabra = indicePalabra;
        this.silabaCorrecta = silabaCorrecta;
        this.silabasFalsas = silabasFalsas;
        this.plantillaPalabra = plantillaPalabra;
        this.objAudio = new ControladorAudios();

        // icono de la imagen grande
        this.vista.jLabel6.setIcon(imagen);

        inicializar();  // monta UI, listeners, llama a cargarPalabraDelNivel() y arrastrarSoltar()
    }

    @Override
    protected void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (lista.isEmpty() || indicePalabra >= lista.size()) {
            JOptionPane.showMessageDialog(null, "No se encontró la palabra en la base de datos.");
            return;
        }

        Palabra palabraActual = lista.get(indicePalabra);
        palabraEsperada = palabraActual.getPalabra().toUpperCase();

        // 1) Prepara la lista de sílabas (2 falsas + 1 correcta)
        List<String> opciones = new ArrayList<>();
        opciones.add(silabasFalsas[0]);
        opciones.add(silabaCorrecta);
        opciones.add(silabasFalsas[1]);

        // 2) Barájala para que la posición sea aleatoria
        Collections.shuffle(opciones);

        // 3) Asigna a cada JLabel
        vista.jLabel2.setText(opciones.get(0));
        vista.jLabel3.setText(opciones.get(1));
        vista.jLabel4.setText(opciones.get(2));

        // 4) Pinta la plantilla con guiones
        vista.jLabel5.setText(plantillaPalabra);
    }

    @Override
    protected void arrastrarSoltar() {
        DragSource ds = new DragSource();
        JLabel[] silabas = {
            vista.jLabel2,
            vista.jLabel3,
            vista.jLabel4
        };

        for (JLabel label : silabas) {
            ds.createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_MOVE,
                    new DragGestureListener() {
                @Override
                public void dragGestureRecognized(DragGestureEvent dge) {
                    try {
                        String texto = label.getText();
                        Transferable t = new StringSelection(texto);
                        ds.startDrag(dge, DragSource.DefaultMoveDrop, t, null);
                        // reproduce audio según el texto arrastrado
                        objAudio.reproducirAudio(texto.toLowerCase());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            );
        }

        new DropTarget(vista.jLabel5, new DropTargetListener() {
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
                    Transferable t = dtde.getTransferable();
                    String silaba = (String) t.getTransferData(DataFlavor.stringFlavor);

                    String plantillaActual = vista.jLabel5.getText();
                    if (plantillaActual.contains("__")) {
                        String resultado = plantillaActual.replaceFirst("__", silaba).toUpperCase();
                        vista.jLabel5.setText(resultado);

                        if (resultado.equals(palabraEsperada)) {
                            objAudio.reproducirAudio(silabaCorrecta.toLowerCase());
                            int idUsuario = modelo.Login.getIdUsuarioActivo();
                            new OperacionesBDCuenta()
                                    .actualizarPuntajeYPalabras(idUsuario, 10, 1);

                            JOptionPane.showMessageDialog(
                                    null,
                                    "¡Correcto!\nGanaste 10 puntos 🏆",
                                    "Nivel completado",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            new MenuArrastre().setVisible(true);
                            vista.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto, intenta de nuevo");
                            vista.jLabel5.setText(plantillaPalabra);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void manejarEvento(Object boton) {
        if (boton == vista.jButton1) {
            new MenuArrastre().setVisible(true);
            vista.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        vista.jButton1.addActionListener(this);
    }
}
