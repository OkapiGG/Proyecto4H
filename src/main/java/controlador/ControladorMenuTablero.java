// ControladorMenuTablero.java
package controlador;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import modelo.ConexionBD;
import modelo.Login;
import modelo.ProgresoJugador;
import vista.MenuInicio;
import vista.MenuTablero;

public class ControladorMenuTablero implements ActionListener {

    private final MenuTablero objMenuTablero;
    private final ProgresoJugador progresoJugador;
    private final Map<String, JButton> mapaBotones;
    private final Map<JButton, String> botonesPorNivel;
    private final Map<String, ConfiguracionNivelTablero> niveles;

    public ControladorMenuTablero(MenuTablero objMenuTablero) {
        this.objMenuTablero = objMenuTablero;
        this.progresoJugador = new ProgresoJugador();
        this.mapaBotones = new HashMap<>();

        registrarListeners();
        inicializarMapaBotones();
        this.niveles = inicializarConfiguracionesNivel();
        this.botonesPorNivel = mapearBotonesConNombreNivel();
        cargarProgresoJugador();
        actualizarEstadoBotones();
    }

    private void registrarListeners() {
        objMenuTablero.jButton1.addActionListener(this);
        objMenuTablero.jButton2.addActionListener(this);
        objMenuTablero.jButton3.addActionListener(this);
        objMenuTablero.jButton4.addActionListener(this);
        objMenuTablero.jButton5.addActionListener(this);
        objMenuTablero.jButton6.addActionListener(this);
        objMenuTablero.jButton7.addActionListener(this);
        objMenuTablero.jButton8.addActionListener(this);
        objMenuTablero.jButton9.addActionListener(this);
        objMenuTablero.jButton10.addActionListener(this);
        objMenuTablero.jButton11.addActionListener(this);
        objMenuTablero.jButton12.addActionListener(this);
        objMenuTablero.jButton13.addActionListener(this);
    }

    private void inicializarMapaBotones() {
        mapaBotones.put("pizza", objMenuTablero.jButton2);
        mapaBotones.put("perro", objMenuTablero.jButton3);
        mapaBotones.put("silla", objMenuTablero.jButton4);
        mapaBotones.put("hamburguesa", objMenuTablero.jButton5);
        mapaBotones.put("manzana", objMenuTablero.jButton6);
        mapaBotones.put("mesa", objMenuTablero.jButton7);
        mapaBotones.put("billete", objMenuTablero.jButton8);
        mapaBotones.put("foco", objMenuTablero.jButton9);
        mapaBotones.put("conejo", objMenuTablero.jButton10);
        mapaBotones.put("zapato", objMenuTablero.jButton11);
        mapaBotones.put("leon", objMenuTablero.jButton12);
        mapaBotones.put("cama", objMenuTablero.jButton13);
    }

    private Map<String, ConfiguracionNivelTablero> inicializarConfiguracionesNivel() {
        // Arreglo con todas las configuraciones
        ConfiguracionNivelTablero[] configs = new ConfiguracionNivelTablero[]{
            new ConfiguracionNivelTablero("pizza", new String[]{"PA", "TO"}, "/imagenes/tpizza.png", "pi", "perro", 13),
            new ConfiguracionNivelTablero("perro", new String[]{"RA", "CA"}, "/imagenes/tperro.png", "pe", "silla", 1),
            new ConfiguracionNivelTablero("silla", new String[]{"CA", "TI"}, "/imagenes/tsilla.png", "si", "hamburguesa", 20),
            new ConfiguracionNivelTablero("hamburguesa", new String[]{"BU", "TO"}, "/imagenes/thamburguesa.png", "ha", "manzana", 14),
            new ConfiguracionNivelTablero("manzana", new String[]{"ZA", "NA"}, "/imagenes/tmanzana.png", "ma", "mesa", 15),
            new ConfiguracionNivelTablero("mesa", new String[]{"MU", "PO"}, "/imagenes/tmesa.png", "me", "billete", 19),
            new ConfiguracionNivelTablero("billete", new String[]{"DO", "VI"}, "/imagenes/tbillete.png", "bi", "foco", 18),
            new ConfiguracionNivelTablero("foco", new String[]{"PE", "FA"}, "/imagenes/tfoco.png", "fo", "conejo", 17),
            new ConfiguracionNivelTablero("conejo", new String[]{"CA", "NU"}, "/imagenes/tconejo.png", "co", "zapato", 16),
            new ConfiguracionNivelTablero("zapato", new String[]{"TO", "SA"}, "/imagenes/tzapato.png", "za", "leon", 21),
            new ConfiguracionNivelTablero("leon", new String[]{"RA", "LI"}, "/imagenes/tleon.png", "le", "cama", 22),
            new ConfiguracionNivelTablero("cama", new String[]{"MA", "TA"}, "/imagenes/tcama.png", "ca", null, 23)
        };

        Map<String, ConfiguracionNivelTablero> mapa = new HashMap<>();
        for (ConfiguracionNivelTablero cfg : configs) {
            mapa.put(cfg.nombreNivel, cfg);
        }
        return mapa;
    }

    private Map<JButton, String> mapearBotonesConNombreNivel() {
        Map<JButton, String> m = new HashMap<>();
        for (Map.Entry<String, JButton> e : mapaBotones.entrySet()) {
            m.put(e.getValue(), e.getKey());
        }
        return m;
    }

    private void cargarProgresoJugador() {
        try (Connection conexion = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = conexion.prepareStatement("SELECT obtenernivelactual(?)")) {
            ps.setInt(1, Login.getIdUsuarioActivo());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    progresoJugador.setNivelActual(rs.getString(1));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarEstadoBotones() {
        String[] nivelesOrdenados = {
            "pizza", "perro", "silla", "hamburguesa",
            "manzana", "mesa", "billete", "foco",
            "conejo", "zapato", "leon", "cama"
        };

        objMenuTablero.jButton1.setEnabled(true);
        boolean activar = true;

        for (String nivel : nivelesOrdenados) {
            JButton boton = mapaBotones.get(nivel);
            if (activar) {
                boton.setEnabled(true);
                boton.setContentAreaFilled(false);
                boton.setForeground(Color.BLACK);
                boton.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + nivel + ".png")));
            } else {
                boton.setEnabled(false);
                boton.setContentAreaFilled(false);
            }
            if (nivel.equals(progresoJugador.getNivelActual())) {
                activar = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objMenuTablero.jButton1) {
            new MenuInicio().setVisible(true);
            objMenuTablero.dispose();
            return;
        }
        for (Map.Entry<JButton, String> entry : botonesPorNivel.entrySet()) {
            if (e.getSource() == entry.getKey()) {
                ConfiguracionNivelTablero cfg = niveles.get(entry.getValue());
                abrirNivelGenerico(cfg);
                break;
            }
        }
    }

    private void abrirNivelGenerico(ConfiguracionNivelTablero cfg) {
        new ControladorNivelUnico(cfg);
        objMenuTablero.dispose();
    }
}
