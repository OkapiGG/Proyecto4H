package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import modelo.ConexionBD;
import vista.InicioSesion;
import vista.MenuInicio;

public class ControladorInicioSesion implements ActionListener {

    private InicioSesion objInicioSesion;
    private ConexionBD objConexion;

    public ControladorInicioSesion(InicioSesion objInicioSesion) throws SQLException {
        this.objInicioSesion = objInicioSesion;
        this.objConexion = ConexionBD.getInstancia();
        this.objInicioSesion.jButton2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objInicioSesion.jButton2) {

            CambiarVista(e);

        }
    }

    private boolean auntenticarUsuario(String username, String password) {
        boolean autenticado = false;
        String sql = "SELECT * FROM usuario WHERE username = ? AND contrasena = ?";

        try {
            Connection ConexionBD = objConexion.getConexion(); // Obten la conexion sin cerrarla automaticamente
            PreparedStatement stmt = ConexionBD.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            autenticado = rs.next();
        } catch (SQLException e) {
            JPopupMenu.setDefaultLightWeightPopupEnabled(autenticado);
        }
        return autenticado;
    }

    public void CambiarVista(ActionEvent e) {
        if (e.getSource() == this.objInicioSesion.jButton2) {
            MenuInicio objMenuInicio = new MenuInicio();
            objMenuInicio.setVisible(true);
            this.objInicioSesion.dispose();

            String username = objInicioSesion.jTextField1.getText();
            String password = new String(objInicioSesion.jPasswordField1.getPassword());

            if (auntenticarUsuario(username, password)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                MenuInicio objMenuJuego = new MenuInicio();
                objMenuJuego.setVisible(true);
                this.objInicioSesion.dispose();
            } else {
                JOptionPane.showConfirmDialog(null, "Usuario o contraseña incorrectos");
            }
        }
    }
}
