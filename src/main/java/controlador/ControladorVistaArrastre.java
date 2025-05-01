package controlador;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.Palabra;
import vista.MenuJuego;
import vista.VistaCartaArrastra;

public class ControladorVistaArrastre extends ControladorClaseDragDrop {

    private VistaCartaArrastra vista;
    private String silabaCorrecta;
    private String palabraEsperada;
    private String audioCorrecto;
    private String[] silabasFalsas;
    private String plantillaPalabra;
    private int indicePalabra;

    public ControladorVistaArrastre(
            VistaCartaArrastra vista,
            int indicePalabra,
            String silabaCorrecta,
            String[] silabasFalsas,
            String audioCorrecto,
            ImageIcon imagen,
            String plantillaPalabra
    ) {
        this.vista = vista;
        this.indicePalabra = indicePalabra;
        this.silabaCorrecta = silabaCorrecta;
        this.silabasFalsas = silabasFalsas;
        this.audioCorrecto = audioCorrecto;
        this.plantillaPalabra = plantillaPalabra;
        this.objAudio = new ControladorAudios();

        this.vista.jLabel6.setIcon(imagen);
        inicializar();
    }

    @Override
    protected void cargarPalabraDelNivel() {
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty() && indicePalabra < lista.size()) {
            Palabra palabraActual = lista.get(indicePalabra);
            palabraEsperada = palabraActual.getPalabra().toUpperCase();

            vista.jLabel2.setText(silabasFalsas[0]);
            vista.jLabel3.setText(silabaCorrecta);
            vista.jLabel4.setText(silabasFalsas[1]);

            vista.jLabel5.setText(plantillaPalabra); // Ej: SAN__A
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la palabra en la base de datos.");
        }
    }

    @Override
    protected void arrastrarSoltar() {
        DragSource ds = new DragSource();
        JLabel[] silabas = {vista.jLabel2, vista.jLabel3, vista.jLabel4};
        String[] audios = {
            silabasFalsas[0].toLowerCase(),
            silabaCorrecta.toLowerCase(),
            silabasFalsas[1].toLowerCase()
        };

        for (int i = 0; i < silabas.length; i++) {
            JLabel label = silabas[i];
            String audio = audios[i];
            ds.createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_MOVE, new DragGestureListener() {
                @Override
                public void dragGestureRecognized(DragGestureEvent dge) {
                    Transferable t = new StringSelection(label.getText());
                    ds.startDrag(dge, DragSource.DefaultMoveDrop, t, null);
                    objAudio.reproducirAudio(audio);
                }
            });
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
                            objAudio.reproducirAudio(audioCorrecto);
                            int idUsuario = modelo.Login.getIdUsuarioActivo();
                            OperacionesBDCuenta operaciones = new OperacionesBDCuenta();
                            operaciones.actualizarPuntajeYPalabras(idUsuario, 10, 1);

                            JOptionPane.showMessageDialog(null, "¡Correcto!\nGanaste 10 puntos 🏆", "Nivel completado", JOptionPane.INFORMATION_MESSAGE);
                            new MenuJuego().setVisible(true);
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
            new MenuJuego().setVisible(true);
            vista.dispose();
        }
    }

    @Override
    protected void agregarAccionadorEventos() {
        vista.jButton1.addActionListener(this);
    }
}
