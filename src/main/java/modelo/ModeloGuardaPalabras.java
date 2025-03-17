/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;

/**
 *
 * @author Alan
 */

public class ModeloGuardaPalabras {

    private List<Palabra> palabras;
    private ModeloPalabra modeloPalabra;

    public ModeloGuardaPalabras(ModeloPalabra modeloPalabra) {
        this.modeloPalabra = modeloPalabra;
        palabras = cargarPalabras();
    }
 
    public ModeloGuardaPalabras() {
    }

    private List<Palabra> cargarPalabras() {
        return modeloPalabra.cargarPalabrasComoObjetos();
    }

    public List<Palabra> getPalabras() {
        return palabras;
    }
}

