package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

/**
 * @author Daniel Nogueira
 * Essa classe serve para criar uma check box 
 */
public class CheckBox extends Component {

    private boolean isChecked;
    private boolean changed;
    private SpriteSheet checkBoxSprite;
    private String text;

    private Handler handler;

    public CheckBox(String text, Handler handler){
        this.text = text;
        this.handler = handler;
        init();
    }

    @Override
    public void init() {
        checkBoxSprite = new SpriteSheet("/spritesheet/checkBox.png");
    }

    @Override
    public void tick() {
        if (handler.getInputHandler().prime.clicked){
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            isChecked = !isChecked;
            changed = true;
        }
    }

    @Override
    public void render(Graphics g) {
        if (isChecked){
            g.drawImage(checkBoxSprite.getSprite(16,0,16,16), (int)xComponent, (int)yComponent, null);
            g.setColor(Color.YELLOW);
        }
        else {
            g.drawImage(checkBoxSprite.getSprite(0,0,16,16), (int)xComponent, (int)yComponent, null);
            g.setColor(Color.WHITE);
        }
        g.drawString(text, (int)xComponent+20, (int)yComponent+14);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
