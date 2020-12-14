package com.unamedgroup.placeholder.sound;

import java.io.File;
import java.io.IOException;
import java.time.temporal.UnsupportedTemporalTypeException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Essa classe serve para buscar o arquivo de áudio
 * 
 * @author Daniel Nogueira
 * @author Ryan
 */
public class SoundInput implements LineListener {

    /**
     * Busca um arquivo de áudio e o tranforma em Clip
     * 
     * @param path
     * @return
     * @throws UnsupportedAudioFileException
     */
    public Clip getAudio( File hitf) throws UnsupportedAudioFileException {
       // File audioFile = new File(path);
        if (!hitf.exists()) {
            System.out.println("Não recebeu nada\n");
            return null;
        }

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(hitf);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);


            //O gainValue estava excedendo o valor máximo, por isso o jogo não iniciava, eu mudei o valor e agora está funcionando normalmente.
            FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            float gainValue = 6;/* (((float) volume()) * 40f / 100f) - 35f; */
            gainControl.setValue(gainValue);

            return audioClip;
        } catch (IOException | LineUnavailableException | UnsupportedTemporalTypeException e) {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa deu erro");
            // log.warn("Error opening audiostream from " + audioFile, e);
            return null;
        }
    }

    @Override
    public void update(LineEvent event) {
        // TODO Auto-generated method stub

    }
}
