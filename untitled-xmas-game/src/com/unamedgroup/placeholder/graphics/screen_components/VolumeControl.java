package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

/**
 * @author Daniel Nogueira Essa classe servirá para mover o volume no menu principal
 * 
 * OBS: Para chamar efeitos sonoros, use handler.getGameVolume() para setar o volume que será unico por todo jogo
 */
public class VolumeControl extends Component {

    private float[] volumeSelection;
    private int selectionIndex=3;
    private SpriteSheet selectionBox;
    private String[] numbers;
    private final Font font;
    private Color color;
    private Handler handler;

    public VolumeControl(float x, float y, Font font, Color color, Handler handler) {
        init();
        this.xComponent = x;
        this.yComponent = y;
        this.font = font;
        this.color = color;
        this.handler = handler;
    }

    @Override
    public void init() {
        numbers = new String[11];
        selectionBox = new SpriteSheet("/spritesheet/SelectionBox.png");
        volumeSelection = new float[11];

        float value = 0.0f;
        for (int i = 0; i < 11; i++) {
            volumeSelection[i] = value;
            numbers[i] = String.valueOf(i);
            value += 0.1f;
        }
    }

    public void btnRight(){
        if (selectionIndex==10) return;
        else selectionIndex++;
        handler.setGameVolume(volumeSelection[selectionIndex]);
        handler.updateMusic();
    }

    public void btnLeft(){
        if (selectionIndex==0) return;
        else selectionIndex--;
        handler.setGameVolume(volumeSelection[selectionIndex]);
        handler.updateMusic();
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        int count = 0;
        g.setFont(font);
        for (int i=0; i<11; i++){
            g.drawImage(selectionBox.getSpriteSheet(), (int)xComponent+count, (int)yComponent, null);
            if (i == selectionIndex)  g.setColor(Color.YELLOW);
            else g.setColor(Color.WHITE);
            if (i<10) {
                g.drawString(numbers[i], (int) (xComponent + count + 4) , (int) yComponent+13);
            }
            else {
                g.drawString(numbers[i], (int) (xComponent + count + 1) , (int) yComponent+13);
            }
            count+=16;
        }
        
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    
}
