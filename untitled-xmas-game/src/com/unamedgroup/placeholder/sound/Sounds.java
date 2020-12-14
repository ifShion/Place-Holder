package com.unamedgroup.placeholder.sound;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Criei essa classe para armazenar todos os audios usando o HashMap, já que não
 * deu certo usar ArrayList e Array. Danielzinho já explicou como funciona o
 * HashMap na classe Maps, então não irei explicar de novo.
 * 
 * @author Ryan
 */

public class Sounds {

        private Map<String, Clip> soundmap;

        private static boolean eclipse = false;
        

        public Sounds() throws UnsupportedAudioFileException {

                soundmap = new LinkedHashMap<>();

                SoundInput soundinput = new SoundInput();

                soundmap.put("Loop", soundinput.getAudio("untitled-xmas-game/res/audio/Loop.wav"));
                soundmap.put("ExpFall", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosionQueda4.wav"));
                soundmap.put("Exp1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosion1.wav"));
                soundmap.put("Exp2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosion2.wav"));
                soundmap.put("Exp3", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosion3.wav"));
                soundmap.put("Hit", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoHit_Hurt.wav"));
                soundmap.put("HitFall2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoHit_HurtQueda2.wav"));
                soundmap.put("HitFall1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoHit_Queda1.wav"));
                soundmap.put("Jump1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoJump1.wav"));
                soundmap.put("Jump2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoJump2.wav"));
                soundmap.put("Jump3", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoJump3.wav"));
                soundmap.put("pCoin1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPick_upCoin1.wav"));
                soundmap.put("pCoin2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPick_upCoin2.wav"));
                soundmap.put("pCoin3", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPickup_Coin3.wav"));
                soundmap.put("pCoin4", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPickup_Coin4.wav"));

                if (eclipse == true) {// Usei esse if com uma variável static para funcionar no eclipse também e não
                                      // ter que ficar mudando isso aqui toda hora.
                        soundmap.put("ExpFall", soundinput.getAudio("res/audio/EfeitoExplosionQueda4.wav"));
                        soundmap.put("Exp1", soundinput.getAudio("res/audio/EfeitoExplosion1.wav"));
                        soundmap.put("Exp2", soundinput.getAudio("res/audio/EfeitoExplosion2.wav"));
                        soundmap.put("Exp3", soundinput.getAudio("res/audio/EfeitoExplosion3.wav"));
                        soundmap.put("Hit", soundinput.getAudio("res/audio/EfeitoHit_Hurt.wav"));
                        soundmap.put("HitFall2", soundinput.getAudio("res/audio/EfeitoHit_HurtQueda2.wav"));
                        soundmap.put("HitFall1", soundinput.getAudio("res/audio/EfeitoHit_Queda1.wav"));
                        soundmap.put("Jump1", soundinput.getAudio("res/audio/EfeitoJump1.wav"));
                        soundmap.put("Jump2", soundinput.getAudio("res/audio/EfeitoJump2.wav"));
                        soundmap.put("Jump3", soundinput.getAudio("res/audio/EfeitoJump3.wav"));
                        soundmap.put("pCoin1", soundinput.getAudio("res/audio/EfeitoPick_upCoin1.wav"));
                        soundmap.put("pCoin2", soundinput.getAudio("res/audio/EfeitoPick_upCoin2.wav"));
                        soundmap.put("pCoin3", soundinput.getAudio("res/audio/EfeitoPickup_Coin3.wav"));
                        soundmap.put("pCoin4", soundinput.getAudio("res/audio/EfeitoPickup_Coin4.wav"));
                }

        }

        boolean playCompleted;

        public void play(String key, float volume) {

                FloatControl gainControl = (FloatControl) soundmap.get(key).getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);

                soundmap.get(key).setFramePosition(0);
                soundmap.get(key).start();
                        }
        
        public Clip getSounds(String key){
                return soundmap.get(key);
        }

        public static void setEclipse (boolean eclipse){
                Sounds.eclipse = eclipse;
        }

        public void tick(String key, float volume){
                FloatControl gainControl = (FloatControl) soundmap.get(key).getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);
                soundmap.get(key).loop(Clip.LOOP_CONTINUOUSLY);                
        }
}