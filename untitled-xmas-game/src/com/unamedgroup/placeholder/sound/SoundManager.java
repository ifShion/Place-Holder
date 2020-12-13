package com.unamedgroup.placeholder.sound;

import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Essa classe serve para adicionar sons ao jogo, os sons são adicionados a um arraylist
 * @author Daniel Nogueira
 */
public class SoundManager {

    private ArrayList<Clip> soundList = new ArrayList<>();

    public SoundManager() throws UnsupportedAudioFileException {
        soundList.add(SoundInput.getAudio("/audio/EfeitoExplosionQueda4.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoExplosion1.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoExplosion2.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoExplosion3.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoHit_Hurt.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoHit_HurtQueda2.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoHit_Queda1.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoJump1.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoJump2.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoJump3.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoPick_upCoin1.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoPick_upCoin2.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoPick_upCoin3.wav"));
        soundList.add(SoundInput.getAudio("/audio/EfeitoPick_upCoin4.wav"));
    }

    public Clip getSound(int index){
        return soundList.get(index);
    }
}
