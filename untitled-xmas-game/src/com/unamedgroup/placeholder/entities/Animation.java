package com.unamedgroup.placeholder.entities;

/**
 * 
 * @author Nathan
 */
public class Animation {
    private int animationSpeed; // Velocidade da troca de sprites em sprites/seconds
    Entity entity;

    private int numSpritesX;    // Numero de sprites horizontalmente
    private int numSpritesY;    // Numero de sprites verticalmente
    private int currentSpriteX; // Indice do sprite horizontal que est� sendo desenhado
    private int currentSpriteY; // Indice do sprite vertical que est� sendo desenhado

    private int width;
    private int height;
    

    /**
     * Entidade dinamica � usada para instanciar entitys com anima��es
     * @param entity Entidade que está executando a animação
     * @param animationSpeed Velocidade da anima��o
     */
    public Animation(Entity entity, int animationSpeed, int numSpritesX, int numSpritesY) {
        init(animationSpeed, entity, numSpritesX, numSpritesY);

    }

    private void init(int animationSpeed, Entity entity, int numSpritesX, int numSpritesY){
        this.animationSpeed = animationSpeed;
        this.entity = entity;

        this.width = entity.getWidth();
        this.height = entity.getHeight();

        this.numSpritesX = numSpritesX;
        this.numSpritesY = numSpritesY;
    }

    private int tick=0;
    
    public void tick(){
        /**
         * ESCREVA AQUI O PADR�O DE ANIMA��O DA ENTIDADE,
         * RECOMENDO USARMOS JAVA REFLECTION PARA PODER PASSAR 
         * O COMPORTAMENTO POR PARAMETRO NO CONSTRUTOR E N�O
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
     * Pula para o pr�ximo sprite da linha
     */
    public void nextSpriteX(){
        currentSpriteX++;
        if(currentSpriteX>=numSpritesX){
            currentSpriteX=0;
        }
        entity.spriteX=(int)width*currentSpriteX;
    }
    /**
     * Pula para o pr�ximo sprite da coluna
     */
    public void nextSpriteY(){
        currentSpriteY++;
        if(currentSpriteY>=numSpritesY){
            currentSpriteY=0;
        }
        entity.spriteY=(int)height*currentSpriteY;
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
        entity.spriteX=(int)width*i;
        entity.spriteY=(int)height*j;
    }

    public int getSpriteVeloticy() {
        return animationSpeed;
    }
    public void setSpriteVeloticy(int spriteVeloticy) {
        this.animationSpeed = spriteVeloticy;
    }
}
