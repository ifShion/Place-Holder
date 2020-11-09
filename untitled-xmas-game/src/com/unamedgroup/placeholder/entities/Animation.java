package com.unamedgroup.placeholder.entities;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Nathan
 */
public class Animation extends Entity {
    private int animationSpeed; // Velocidade da troca de sprites em sprites/seconds
    private int numSpritesX;    // Numero de sprites horizontalmente
    private int numSpritesY;    // Numero de sprites verticalmente
    private int currentSpriteX; // Indice do sprite horizontal que est sendo desenhado
    private int currentSpriteY; // Indice do sprite vertical que est sendo desenhado
    

    /**
     * Entidade dinamica  usada para instanciar entitys com animaes
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param width Largura do sprite
     * @param height Altura do sprite
     * @param sprite Sprite (Carregue uma imagem em que cada sprite tenha o mesmo tamanho)
     * @param depth Profundidade
     * @param speed Velocidade do sprite
     * @param animationSpeed Velocidade da animao
     */
    public Animation(int x, int y, int width, int height, BufferedImage sprite, int depth, int speed, int animationSpeed) {
        super(x, y, width, height, sprite, depth, speed);
        init(animationSpeed, width, height);

    }
    /**
     * Entidade dinamica  usada para instanciar entitys com animaes
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param width Largura do sprite
     * @param height Altura do sprite
     * @param path Passe o caminho da imagem
     * @param depth Profundidade
     * @param speed Velocidade do sprite
     * @param animationSpeed Velocidade da animao
     */
    public Animation(int x, int y, int width, int height, String path, int depth, int speed, int animationSpeed) {
        super(x, y, width, height, path, depth, speed);
        init(animationSpeed, width, height);

    }

    private void init(int animationSpeed, int width, int height){
        this.animationSpeed = animationSpeed;
        numSpritesX = super.getSprite().getWidth(null)/width;
        numSpritesY = super.getSprite().getHeight(null)/height;
    }

    private int tick=0;
    @Override
    public void tick(){
        /**
         * ESCREVA AQUI O PADRÃO DE ANIMAÇÃO DA ENTIDADE,
         * RECOMENDO USARMOS JAVA REFLECTION PARA PODER PASSAR 
         * O COMPORTAMENTO POR PARAMETRO NO CONSTRUTOR E NÃO
         * CRIAR CLASSES PARA CADA ENTIDADE DINAMICA 
         */

        //-----EXEMPLO DE ALGORITMO PARA ANIMAR-----//
        if(tick>60/animationSpeed){
            nextSpriteX();
            tick = 0;
        }else{
            tick++;
        }
    }
    
    /**
     * Pula para o próximo sprite da linha
     */
    public void nextSpriteX(){
        currentSpriteX++;
        if(currentSpriteX>=numSpritesX){
            currentSpriteX=0;
        }
        sx=(int)width*currentSpriteX;
    }
    /**
     * Pula para o próximo sprite da coluna
     */
    public void nextSpriteY(){
        currentSpriteY++;
        if(currentSpriteY>=numSpritesY){
            currentSpriteY=0;
        }
        sy=(int)height*currentSpriteY;
    }
    /**
     * Pula para o sprite da linha e da coluna selecionada
     */
    public void updateSprite(int i, int j){
        if(i>numSpritesX){
            i=i%numSpritesX;
        }
        if(j>numSpritesY){
            j=j%numSpritesY;
        }
        sx=(int)width*i;
        sy=(int)height*j;
    }

    public int getSpriteVeloticy() {
        return animationSpeed;
    }
    public void setSpriteVeloticy(int spriteVeloticy) {
        this.animationSpeed = spriteVeloticy;
    }
}
