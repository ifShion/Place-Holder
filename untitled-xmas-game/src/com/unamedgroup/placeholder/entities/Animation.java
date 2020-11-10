package com.unamedgroup.placeholder.entities;

/**
 * 
 * @author Nathan
 */
public abstract class Animation {
    protected int width;          // Largura do sprite
    protected int height;         // Altura do sprite
	protected double spriteX = 0; // Coordenada X dentro do arquivo de Imagem
    protected double spriteY = 0; // Coordenada Y dentro do arquivo de Imagem
    private int animationSpeed;   // Velocidade da troca de sprites em sprites/seconds
    private int numSpritesX;      // Numero de sprites horizontalmente
    private int numSpritesY;      // Numero de sprites verticalmente
    private int currentSpriteX;   // Indice do sprite horizontal que est� sendo desenhado
    private int currentSpriteY;   // Indice do sprite vertical que est� sendo desenhado

    /**
     * Entidade dinamica � usada para instanciar entitys com anima��es
     *
     * @param animationSpeed Velocidade da anima��o
     * @param width Largura do Sprite
     * @param height Altura do Sprite
     * @param numSpritesX Número de sprites horizontalmente
     * @param numSpritesY Número de sprites verticalmente
     */
    public Animation(int animationSpeed, int width,int height, int numSpritesX, int numSpritesY) {
       
        this.animationSpeed = animationSpeed;

        this.width = width;
        this.height = height;

        this.numSpritesX = numSpritesX;
        this.numSpritesY = numSpritesY;

    }

    private int tick=0; // Tick é atualização de tela
    
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
        spriteX=(int)width*currentSpriteX;
    }
    /**
     * Pula para o pr�ximo sprite da coluna
     */
    public void nextSpriteY(){
        currentSpriteY++;
        if(currentSpriteY>=numSpritesY){
            currentSpriteY=0;
        }
        spriteY=(int)height*currentSpriteY;
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
        spriteX=(int)width*i;
        spriteY=(int)height*j;
    }

    public int getSpriteVeloticy() {
        return animationSpeed;
    }
    public void setSpriteVeloticy(int spriteVeloticy) {
        this.animationSpeed = spriteVeloticy;
    }

    
}
