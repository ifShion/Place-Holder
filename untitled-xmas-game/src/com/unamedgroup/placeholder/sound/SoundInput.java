package com.unamedgroup.placeholder.sound;

import java.io.File;
import java.io.IOException;
import java.time.temporal.UnsupportedTemporalTypeException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Essa classe serve para buscar o arquivo de áudio
 * @author Daniel Nogueira
 */
public class SoundInput {

    /**
     * Busca um arquivo de áudio e o tranforma em Clip
     * @param path
     * @return
     * @throws UnsupportedAudioFileException
     */
    public static Clip getAudio(String path) throws UnsupportedAudioFileException {
        File audioFile = new File(path);
        if (!audioFile.exists()){
            System.out.println("Não recebeu nada\n");
            return null;
        }
            
    
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            float gainValue = 10;/*(((float) volume()) * 40f / 100f) - 35f;*/
            gainControl.setValue(gainValue);
    
            return audioClip;
        }
        catch (IOException | LineUnavailableException | UnsupportedTemporalTypeException e)
        {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa deu erro");
            //log.warn("Error opening audiostream from " + audioFile, e);
            return null;
        }
    }
}
