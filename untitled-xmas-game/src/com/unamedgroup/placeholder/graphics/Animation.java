package com.unamedgroup.placeholder.graphics;

/**
 * 
 * @author Nathan
 * @author Daniel
 */
public class Animation {
    private boolean play;           

    protected int width;          	// Largura do sprite
    protected int height;         	// Altura do sprite
    
    private int initPosX;			// Posição inicial em X da sequência de Sprites
    private int initPosY;			// Posição inicial em Y da sequência de Sprites
	private double spriteX = 0; 	// Coordenada X dentro do arquivo de Imagem
    private double spriteY = 0; 	// Coordenada Y dentro do arquivo de Imagem
    private int animationSpeed;		// Velocidade da troca de sprites em sprites/seconds
    private int numSpritesX;      	// Numero de sprites horizontalmente
    private int numSpritesY;      	// Numero de sprites verticalmente
    private int currentSpriteX;   	// Indice do sprite horizontal que est� sendo desenhado
    private int currentSpriteY;   	// Indice do sprite vertical que est� sendo desenhado
    
    private int frame=0; // Tick é atualização de tela

    /**
     * A classe recebe parâmetros para gerenciar a animação de entidade ou tile. Recebe a sprite sheet necessário, a 
     * recorta e desenha na tela as sprites recortadas em ordem com o frequência dada (FPS)
     *
     * @param animationSpeed 	//Velocidade da animação (em FPS)
     * @param width 			//Largura do Sprite
     * @param height 			//Altura do Sprite
     * @param numSpritesX 		//Número de sprites horizontalmente
     * @param numSpritesY 		//Número de sprites verticalmente
     * @param initPosX			//Offset de X para começo do recorte da sprite sheet
     * @param initPosY			//Offset de Y para começo do recorte de sprite sheet
     */
    public Animation(int animationSpeed, int width,int height, int numSpritesX, int numSpritesY, int initPosX, int initPosY) {
       
        this.animationSpeed = animationSpeed;

        this.width = width;
        this.height = height;

        this.initPosX = initPosX;
        this.initPosY = initPosY;
        this.numSpritesX = numSpritesX;
        this.numSpritesY = numSpritesY;

    }
    
    public int getInitPosX() {
		return initPosX;
	}

	public int getInitPosY() {
		return initPosY;
	}
	
	public int getNumSpritesX() {
		return numSpritesX;
	}
	
	public int getNumSpritesY() {
		return numSpritesY;
	}

	public double getSpriteX() {
		return spriteX;
	}
	
	public double getSpriteY() {
		return spriteY;
    }
    
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setNumSpritesX(int numSpritesX) {
		this.numSpritesX = numSpritesX;
	}
	
	public void setNumSpritesY(int numSpritesY) {
		this.numSpritesY = numSpritesY;
	}

    public boolean isPlay() {
        return this.play;
    }

    public boolean getPlay() {
        return this.play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }


	public void tick(){
        /**
         * ESCREVA AQUI O PADR�O DE ANIMA��O DA ENTIDADE,
         * RECOMENDO USARMOS JAVA REFLECTION PARA PODER PASSAR 
         * O COMPORTAMENTO POR PARAMETRO NO CONSTRUTOR E N�O
         * CRIAR CLASSES PARA CADA ENTIDADE DINAMICA 
         */

        //-----EXEMPLO DE ALGORITMO PARA ANIMAR-----//
        if(play){
            if(frame>60/animationSpeed){
                frame = 0;
            }else if(frame == 0){
                nextSpriteX();
                frame++;
            }else{
                frame++;
            }
        }else{
            frame = 0;
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

    public void setSpriteX(int i){
        if(i>numSpritesX){
            i=i%numSpritesX;
        }
        spriteX=(int)width*i;
    }

    public void setSpriteY(int j){
        if(j>numSpritesY){
            j=j%numSpritesY;
        }
        spriteY=(int)height*j;
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
