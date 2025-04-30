package controlador;

import javax.swing.ImageIcon;
import vista.VistaCartaArrastra;

public class GestorNiveles {

    public static void iniciarNivelGato() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            0,
            "GA",
            new String[]{"PA", "LO"},
            "gato",
            new ImageIcon("src/main/resources/imagenes/gato.png"),
            "__TO"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelCasa() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            2,
            "SA",
            new String[]{"ZA", "PA"},
            "casa",
            new ImageIcon("src/main/resources/imagenes/casa.png"),
            "CA__"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelCarro() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            3,
            "CA",
            new String[]{"FO", "MU"},
            "carro",
            new ImageIcon("src/main/resources/imagenes/carro.png"),
            "__RRO"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelArbol() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            4,
            "BOL",
            new String[]{"NO", "QUE"},
            "arbol",
            new ImageIcon("src/main/resources/imagenes/arbol.png"),
            "AR__"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelMango() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            5,
            "MAN",
            new String[]{"TU", "KO"},
            "mango",
            new ImageIcon("src/main/resources/imagenes/mango.png"),
            "__GO"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelSandia() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            6,
            "DI",
            new String[]{"YE", "NA"},
            "sandia",
            new ImageIcon("src/main/resources/imagenes/sandia.png"),
            "SAN__A"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelPajaro() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            8,
            "PA",
            new String[]{"WI", "BE"},
            "pajaro",
            new ImageIcon("src/main/resources/imagenes/pajaro.png"),
            "__JARO"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelJirafa() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            7,
            "RA",
            new String[]{"XA", "SI"},
            "jirafa",
            new ImageIcon("src/main/resources/imagenes/jirafa.png"),
            "JI__FA"
        );
        vista.setVisible(true);
    }

    public static void iniciarNivelBicicleta() {
        VistaCartaArrastra vista = new VistaCartaArrastra();
        new ControladorVistaArrastre(
            vista,
            12,
            "CLE",
            new String[]{"CLO", "JE"},
            "bicicleta",
            new ImageIcon("src/main/resources/imagenes/bicicleta.png"),
            "BICI__TA"
        );
        vista.setVisible(true);
    }
}
