package com.unamedgroup.placeholder.sound;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Criei essa classe para armazenar todos os audios usando o HashMap, já que não
 * deu certo usar ArrayList e Array. Danielzinho já explicou como funciona o
 * HashMap na classe Maps, então não irei explicar de novo.
 * 
 * @author Ryan
 */

public class Sounds {

        private Map<String, Clip> soundMap;
        

        public Sounds() throws UnsupportedAudioFileException {
                String diretorio;
                soundMap = new LinkedHashMap<>();
                SoundInput soundinput = new SoundInput();

                if (!new File("untitled-xmas-game/res").exists()) { 
                        System.out.println("Path para: Eclipse"); 
                        diretorio = "";
                } else { 
                        System.out.println("Path para: Vs Code");
                        diretorio = "untitled-xmas-game/";
                }
                
                try{
                        soundMap.put("Cannon", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Cannon_1.wav"));
                        soundMap.put("Door_locked", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Door_locked.wav"));
                        soundMap.put("Door_locked_2", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Door_locked2.wav"));
                        soundMap.put("close_door", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/close_door_1.wav"));
                        soundMap.put("Exp1", soundinput.getAudio(diretorio+"res/audio/EfeitoExplosion1.wav"));
                        soundMap.put("Exp2", soundinput.getAudio(diretorio+"res/audio/EfeitoExplosion2.wav"));
                        soundMap.put("Exp3", soundinput.getAudio(diretorio+"res/audio/EfeitoExplosion3.wav"));
                        soundMap.put("ExpFall", soundinput.getAudio(diretorio+"res/audio/EfeitoExplosionQueda4.wav"));
                        soundMap.put("Explosion", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Explosion_2.wav"));
                        soundMap.put("Fall", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/fall_sound.wav"));
                        soundMap.put("Hit", soundinput.getAudio(diretorio+"res/audio/EfeitoHit_Hurt.wav"));
                        soundMap.put("Hit_2", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Hit_1.wav"));
                        soundMap.put("Hit_3", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Hit_backsound.wav"));
                        soundMap.put("Hit_4", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Hit_Hurt.wav"));
                        soundMap.put("HitFall2", soundinput.getAudio(diretorio+"res/audio/EfeitoHit_HurtQueda2.wav"));
                        soundMap.put("HitFall1", soundinput.getAudio(diretorio+"res/audio/EfeitoHit_Queda1.wav"));
                        soundMap.put("Hit_Miss", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Hit_Miss.wav"));
                        soundMap.put("Interruptor1", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/interruptor_1.wav"));
                        soundMap.put("Interruptor2", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/interruptor_2.wav"));
                        soundMap.put("Jump1", soundinput.getAudio(diretorio+"res/audio/EfeitoJump1.wav"));
                        soundMap.put("Jump2", soundinput.getAudio(diretorio+"res/audio/EfeitoJump2.wav"));
                        soundMap.put("Jump3", soundinput.getAudio(diretorio+"res/audio/EfeitoJump3.wav"));
                        soundMap.put("Letter", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/letter.wav"));
                        soundMap.put("Loop", soundinput.getAudio(diretorio+"res/audio/Loop.wav"));
                        soundMap.put("Menu_Navigate", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Menu_Navigate.wav"));
                        soundMap.put("Music",soundinput.getAudio(diretorio+"res/audio/Music_cut.wav"));
                        soundMap.put("Music_alpha", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Music_alpha.wav"));
                        soundMap.put("pCoin1", soundinput.getAudio(diretorio+"res/audio/EfeitoPick_upCoin1.wav"));
                        soundMap.put("pCoin2", soundinput.getAudio(diretorio+"res/audio/EfeitoPick_upCoin2.wav"));
                        soundMap.put("pCoin3", soundinput.getAudio(diretorio+"res/audio/EfeitoPickup_Coin3.wav"));
                        soundMap.put("pCoin4", soundinput.getAudio(diretorio+"res/audio/EfeitoPickup_Coin4.wav"));
                        soundMap.put("Pick_up_Stick", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Pick_Stick_1.wav"));
                        soundMap.put("Pick_up_Stick2", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Pick_Up_Stick_2.wav"));
                        soundMap.put("Pick_up_Stick3", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Pick_Up_Stick_3.wav"));
                        soundMap.put("Select", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/Select.wav")); 
                        soundMap.put("Snow_walking", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/snow_walking_wind.wav"));
                        soundMap.put("Snow_walking2", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/snow_walking_wind_2.wav")); 
                        soundMap.put("Snow_walking3", soundinput.getAudio(diretorio+"res/audio/Audios_0.2/snow_walking_wind_3.wav"));  
                }
                catch (Exception e) {
                        //TODO: handle exception
                }
                

        }

        boolean playCompleted;

        public void play(String key, float volume) {
                FloatControl gainControl = (FloatControl) soundMap.get(key).getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);

                soundMap.get(key).setFramePosition(0);
                soundMap.get(key).start();
        }
        
        public Clip getSounds(String key){
                return soundMap.get(key);
        }

        public void tick(String key, float volume){
                FloatControl gainControl = (FloatControl) soundMap.get(key).getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
                soundMap.get(key).loop(Clip.LOOP_CONTINUOUSLY);                
        }
        
        public void stop(String key){
                soundMap.get(key).stop();
                soundMap.get(key).close();
        }
}