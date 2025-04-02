package controlador;

import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import modelo.ConexionBD;
import modelo.Login;
import modelo.ModeloGuardaPalabras;
import modelo.ModeloPalabra;
import modelo.ModeloUsuario;
import modelo.Palabra;
import vista.CartaGato;
import vista.InicioSesion;
import vista.MenuJuego;

public class ControladorCartaGato implements ActionListener {

    private ModeloPalabra modeloPalabra;
    private ModeloGuardaPalabras modeloGuardaPalabras;
    private Connection conexion;
    private ControladorAudios objAudio;
    private CartaGato objCarta1;

    public ControladorCartaGato(CartaGato objCarta1) {
        this.objCarta1 = objCarta1;
        this.objCarta1.jButton1.addActionListener(this);
        try {
            this.conexion = ConexionBD.getInstancia().getConexion();
            modeloPalabra = new ModeloPalabra();
            modeloGuardaPalabras = new ModeloGuardaPalabras(modeloPalabra);
            cargarPalabraDelNivel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        arrastrarSoltar();
        objAudio = new ControladorAudios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objCarta1.jButton1) {
            MenuJuego objMenuJuego = new MenuJuego();
            objMenuJuego.setVisible(true);
            this.objCarta1.dispose();
        }
    }

    // Método que utiliza el modeloGuardaPalabras para cargar la palabra en la vista
    private void cargarPalabraDelNivel() {
        // getPalabras() devuelve una lista de objetos Palabra
        List<Palabra> lista = modeloGuardaPalabras.getPalabras();
        if (!lista.isEmpty()) {
            Palabra palabraActual = lista.get(0);
            String palabraCompleta = palabraActual.getPalabra();

            if (palabraCompleta.length() >= 2) {
                String silabaCorrecta = palabraCompleta.substring(0, 2);
                String complemento = palabraCompleta.substring(2);

                objCarta1.jLabel4.setText(silabaCorrecta);
                objCarta1.jLabel2.setText(complemento);
                // Sílabas falsas en otros JLabel
                objCarta1.jLabel5.setText("PA");
                objCarta1.jLabel6.setText("LO");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron palabras en la base de datos.");
        }
    }

    // Configuración de Drag & Drop
    private void arrastrarSoltar() {
        final String textoOriginal = objCarta1.jLabel3.getText();
        DragSource ds = new DragSource();

        // Configurar arrastre para jLabel4
        ds.createDefaultDragGestureRecognizer(objCarta1.jLabel4, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta1.jLabel4.getText());
                ds.startDrag(dge, DragSource.DefaultMoveDrop, objTransferible, null);
            }
        });

        // Configurar arrastre para jLabel5
        ds.createDefaultDragGestureRecognizer(objCarta1.jLabel5, DnDConstants.ACTION_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                Transferable objTransferible = new StringSelection(objCarta1.jLabel5.getText());
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
                        int puntos = 10; 
                        int palabras = 1; 
                        int idUsuario = Login.getIdUsuarioActivo(); // Se obtiene el id del usuario activo para hacerle el update en la BD
                        ModeloUsuario modeloUsuario = new ModeloUsuario();
                        modeloUsuario.actualizarPuntajeYContador(idUsuario, puntos, palabras);
                        objAudio.iniciarAudio("/audio/Gato.wav");
                        JOptionPane.showMessageDialog(null, "Correcto la palabra es GATO obtuviste: " + puntos + " puntos");
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
