package com.unamedgroup.placeholder.sound;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Criei essa classe para armazenar todos os audios usando o HashMap, já que não deu certo usar
 * ArrayList e Array. Danielzinho já explicou como funciona o HashMap na classe Maps, então não
 * irei explicar de novo.
 * @author Ryan
 */

public class Sounds {

        private Map<String, Clip> soundmap;

        public Sounds() throws UnsupportedAudioFileException {
		
        soundmap = new LinkedHashMap<>();

        SoundInput soundinput = new SoundInput();

        String hits = "untitled-xmas-game/res/audioteste/EfeitoHit_Hurt.wav";
        File hitf = new File(hits);

        soundmap.put("Hit", soundinput.getAudio(hitf));

        /*soundmap.put("ExpFall", soundinput.getAudio("res/audio/EfeitoExplosionQueda4.wav"));
        soundmap.put("Exp1", soundinput.getAudio("res/audio/EfeitoExplosion1.wav"));
        soundmap.put("Exp2", soundinput.getAudio("res/audio/EfeitoExplosion2.wav"));
        soundmap.put("Exp3", soundinput.getAudio("res/audio/EfeitoExplosion3.wav"));

        soundmap.put("HitFall2", soundinput.getAudio("res/audio/EfeitoHit_HurtQueda2.wav"));
        soundmap.put("HitFall1", soundinput.getAudio("res/audio/EfeitoHit_Queda1.wav"));
        soundmap.put("Jump1", soundinput.getAudio("res/audio/EfeitoJump1.wav"));
        soundmap.put("Jump2", soundinput.getAudio("res/audio/EfeitoJump2.wav"));
        soundmap.put("Jump3", soundinput.getAudio("res/audio/EfeitoJump3.wav"));
        soundmap.put("pCoin1", soundinput.getAudio("res/audio/EfeitoPick_upCoin1.wav"));
        soundmap.put("pCoin2", soundinput.getAudio("res/audio/EfeitoPick_upCoin2.wav"));
        soundmap.put("pCoin3", soundinput.getAudio("res/audio/EfeitoPick_upCoin3.wav"));
        soundmap.put("pCoin4", soundinput.getAudio("res/audio/EfeitoPick_upCoin4.wav"));*/

        }
        
        public Clip getSounds(String key){
                return soundmap.get(key);
        }
}