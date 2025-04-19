/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import modelo.Login;
import vista.MenuInicio;
import vista.VistaMenuCuenta;

/**
 *
 * @author alancervantes
 */
public class ControladorMenuCuenta implements ActionListener {

    VistaMenuCuenta objVistaMenuCuenta;
    OperacionesBDCuenta operacionesBDCuenta;

    public ControladorMenuCuenta(VistaMenuCuenta objVistaMenuCuenta) {
        this.objVistaMenuCuenta = objVistaMenuCuenta;
        this.operacionesBDCuenta = new OperacionesBDCuenta();
        cargarDatosCuenta();
        this.objVistaMenuCuenta.jButton1.addActionListener(this);
    }

    private void cargarDatosCuenta() {
        try {
            int idUsuario = Login.getIdUsuarioActivo();
            ResultSet rs = operacionesBDCuenta.obtenerDatosCuenta(idUsuario);
            if (rs != null && rs.next()) {
                objVistaMenuCuenta.jLabel7.setText(rs.getString("perfil").toUpperCase());
                objVistaMenuCuenta.jLabel8.setText(String.valueOf(rs.getInt("puntaje")));
                objVistaMenuCuenta.jLabel9.setText(String.valueOf(rs.getInt("palabrascompletadas")));
                String fechaCompleta = rs.getString("fechaultimasesion");
                String fechaSolo = fechaCompleta.split(" ")[0];
                objVistaMenuCuenta.jLabel10.setText(fechaSolo);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar datos de cuenta: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.objVistaMenuCuenta.jButton1){
            MenuInicio menuInicio = new MenuInicio();
            menuInicio.setVisible(true);
            this.objVistaMenuCuenta.dispose();
        }
    }
}
