/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Alan
 */
public class ControladorVerificaSilaba {

    // Valida si la concatenación de la sílaba y el complemento es igual a la palabra esperada
    public static boolean isCorrectWord(String silaba, String complemento, String palabraCompleta) {
        return (silaba + complemento).equalsIgnoreCase(palabraCompleta);
    }

    // Separa la palabra completa en sílaba y complemento
    public static String[] splitWord(String palabraCompleta) {
        if (palabraCompleta != null && palabraCompleta.length() >= 2) {
            String silaba = palabraCompleta.substring(0, 2);
            String complemento = palabraCompleta.substring(2);
            return new String[]{silaba, complemento};
        }
        return new String[]{"", ""};
    }
}
