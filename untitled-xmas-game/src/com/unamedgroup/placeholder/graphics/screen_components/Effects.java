package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.*;

import com.unamedgroup.placeholder.main.Game;

/**
 * Classe para utilizar todos os efeitos de tela;
 */
public abstract class Effects {


    //================= TRANSIÇÃO ENTRE TELAS =================/
    public static float transparencia=1;

    public static boolean TransitionOpen(){
        if (transparencia >= 0.1){
            transparencia -= 0.1f;
            if(transparencia<=0.1f){
                return true;
            }
        }
        return false;
    }

    public static boolean TransitionDispose(){
        if(transparencia < 1){
            transparencia += 0.1f;
                if(transparencia>=1){
                    transparencia = 1;
                    return true;
                }
            }
        return false;
    }

    public static void TransitionRender(Graphics g){
        g.setColor(Color.black);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
    }

}
