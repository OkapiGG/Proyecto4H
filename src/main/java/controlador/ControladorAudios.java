/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Alan
 */
public class ControladorAudios {

    private Clip clip;
    
    public ControladorAudios(){
        
    }
    
    public void reproducirAudio(String silaba) {
        String ruta = "/audio/" + silaba + ".wav";
        iniciarAudio(ruta);
    }

    public void iniciarAudio(String url) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(url));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void pararAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
