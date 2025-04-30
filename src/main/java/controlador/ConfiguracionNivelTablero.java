/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author alancervantes
 */
public class ConfiguracionNivelTablero {
    public final String nombreNivel;
    public final String[] silabasFalsas;
    public final String rutaImagen;
    public final String audioCorrecto;
    public final String siguienteNivel;
    public final int indiceEnBD;

    public ConfiguracionNivelTablero(
        String nombreNivel,
        String[] silabasFalsas,
        String rutaImagen,
        String audioCorrecto,
        String siguienteNivel,
        int indiceEnBD
    ) {
        this.nombreNivel = nombreNivel;
        this.silabasFalsas = silabasFalsas;
        this.rutaImagen = rutaImagen;
        this.audioCorrecto = audioCorrecto;
        this.siguienteNivel = siguienteNivel;
        this.indiceEnBD = indiceEnBD;
    }
}


