package com.unamedgroup.placeholder.sound;

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

                soundMap = new LinkedHashMap<>();

                SoundInput soundinput = new SoundInput();
                
                try{
                    try{                       
                    	soundMap.put("close_door", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/close_door_1.wav"));
                        soundMap.put("Explosion", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Explosion_2.wav"));
                    	soundMap.put("Cannon", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Cannon_1.wav"));
                    	soundMap.put("close_door", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/close_door_1.wav"));
                        soundMap.put("Exp1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosion1.wav"));
                        soundMap.put("Exp2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosion2.wav"));
                        soundMap.put("Exp3", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosion3.wav"));
                        soundMap.put("ExpFall", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoExplosionQueda4.wav"));
                        soundMap.put("Fall", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/fall_sound.wav"));
                        soundMap.put("Hit", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoHit_Hurt.wav"));
                        soundMap.put("Hit_2", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Hit_1.wav"));
                        soundMap.put("Hit_3", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Hit_backsound.wav"));
                        soundMap.put("Hit_4", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Hit_Hurt.wav"));
                        soundMap.put("HitFall1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoHit_Queda1.wav"));
                        soundMap.put("HitFall2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoHit_HurtQueda2.wav"));
                        soundMap.put("Hit_Miss", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Hit_Miss.wav"));
                        soundMap.put("Jump1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoJump1.wav"));
                        soundMap.put("Jump2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoJump2.wav"));
                        soundMap.put("Jump3", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoJump3.wav"));
                        soundMap.put("Loop", soundinput.getAudio("untitled-xmas-game/res/audio/Loop.wav"));
                        soundMap.put("Menu_Navigate", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Menu_Navigate.wav"));
                        soundMap.put("Music", soundinput.getAudio("untitled-xmas-game/res/audio/Music_cut.wav"));
                        soundMap.put("pCoin1", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPick_upCoin1.wav"));
                        soundMap.put("pCoin2", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPick_upCoin2.wav"));
                        soundMap.put("pCoin3", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPickup_Coin3.wav"));
                        soundMap.put("pCoin4", soundinput.getAudio("untitled-xmas-game/res/audio/EfeitoPickup_Coin4.wav"));
                        soundMap.put("Pick_up_Stick", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Pick_Stick_1.wav"));
                        soundMap.put("Pick_up_Stick2", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Pick_Up_Stick_2.wav"));
                        soundMap.put("Pick_up_Stick3", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Pick_Up_Stick_3.wav"));
                        soundMap.put("Select", soundinput.getAudio("untitled-xmas-game/res/audio/Audios_0.2/Select.wav"));
                    }
                    catch (Exception e){
                    	soundMap.put("Cannon", soundinput.getAudio("res/audio/Audios_0.2/Cannon_1.wav"));
                        soundMap.put("close_door", soundinput.getAudio("res/audio/Audios_0.2/close_door_1.wav"));
                        soundMap.put("Exp1", soundinput.getAudio("res/audio/EfeitoExplosion1.wav"));
                        soundMap.put("Exp2", soundinput.getAudio("res/audio/EfeitoExplosion2.wav"));
                        soundMap.put("Exp3", soundinput.getAudio("res/audio/EfeitoExplosion3.wav"));
                        soundMap.put("ExpFall", soundinput.getAudio("res/audio/EfeitoExplosionQueda4.wav"));
                        soundMap.put("Explosion", soundinput.getAudio("res/audio/Audios_0.2/Explosion_2.wav"));
                        soundMap.put("Fall", soundinput.getAudio("res/audio/Audios_0.2/fall_sound.wav"));
                        soundMap.put("Hit", soundinput.getAudio("res/audio/EfeitoHit_Hurt.wav"));
                        soundMap.put("Hit_2", soundinput.getAudio("res/audio/Audios_0.2/Hit_1.wav"));
                        soundMap.put("Hit_3", soundinput.getAudio("res/audio/Audios_0.2/Hit_backsound.wav"));
                        soundMap.put("Hit_4", soundinput.getAudio("res/audio/Audios_0.2/Hit_Hurt.wav"));
                        soundMap.put("HitFall2", soundinput.getAudio("res/audio/EfeitoHit_HurtQueda2.wav"));
                        soundMap.put("HitFall1", soundinput.getAudio("res/audio/EfeitoHit_Queda1.wav"));
                        soundMap.put("Hit_Miss", soundinput.getAudio("res/audio/Audios_0.2/Hit_Miss.wav"));
                        soundMap.put("Jump1", soundinput.getAudio("res/audio/EfeitoJump1.wav"));
                        soundMap.put("Jump2", soundinput.getAudio("res/audio/EfeitoJump2.wav"));
                        soundMap.put("Jump3", soundinput.getAudio("res/audio/EfeitoJump3.wav"));
                        soundMap.put("Loop", soundinput.getAudio("res/audio/Loop.wav"));
                        soundMap.put("Menu_Navigate", soundinput.getAudio("res/audio/Audios_0.2/Menu_Navigate.wav"));
                        soundMap.put("Music",soundinput.getAudio("res/audio/Music_cut.wav"));
                        soundMap.put("pCoin1", soundinput.getAudio("res/audio/EfeitoPick_upCoin1.wav"));
                        soundMap.put("pCoin2", soundinput.getAudio("res/audio/EfeitoPick_upCoin2.wav"));
                        soundMap.put("pCoin3", soundinput.getAudio("res/audio/EfeitoPickup_Coin3.wav"));
                        soundMap.put("pCoin4", soundinput.getAudio("res/audio/EfeitoPickup_Coin4.wav"));
                        soundMap.put("Pick_up_Stick", soundinput.getAudio("res/audio/Audios_0.2/Pick_Stick_1.wav"));
                        soundMap.put("Pick_up_Stick2", soundinput.getAudio("res/audio/Audios_0.2/Pick_Up_Stick_2.wav"));
                        soundMap.put("Pick_up_Stick3", soundinput.getAudio("res/audio/Audios_0.2/Pick_Up_Stick_3.wav"));
                        soundMap.put("Select", soundinput.getAudio("res/audio/Audios_0.2/Select.wav"));
                    }
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