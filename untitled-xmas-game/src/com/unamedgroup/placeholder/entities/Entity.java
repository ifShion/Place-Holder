package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import com.unamedgroup.placeholder.graphics.Animation;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Node;
import com.unamedgroup.placeholder.world.Vector2i;
import com.unamedgroup.placeholder.world.World;

public class Entity {
	/* Inicializar os sprites iniciais de todas entidades aqui */
    protected double x;           // Coordenada X na tela
	protected double y;           // Coordenada Y na tela
	public double speed;
	protected List<Node> path;	  // caminho feito pelo algoritmo de A*
	
	private Animation animation;
	private SpriteSheet sprite;
	private boolean animated;

	protected int width;
	protected int height;

	private int maskX;
	private int maskY;
	private int maskW;
	private int maskH;

	public int depth;

	protected Handler handler;
	/**
	 * Detalhes dos parÂmetros:
	 * @param x					posição de x da entidade
	 * @param y					posição de y da entidade
	 * @param width				a largura inicial da entidade, importante para o render (pode mudar sua máscara em SetMask())
	 * @param height			a altura inicial da entidade, importante para o render (pode mudar sua máscara em SetMask())
	 * @param spriteSheet		a sprite sheet de onde o sprite é recortado
	 * @param depth				a profundidade da entidade no conjunto de entidades (útil pra saber quem será renderizado por cima)
	 * @param speed				velocidade da entidade
	 * @param animationSpeed	Detalhes em Animation
	 * @param numSpritesX		Detalhes em Animation
	 * @param numSpritesY		Detalhes em Animation
	 * @param initPosX			Detalhes em Animation
	 * @param initPosY			Detalhes em Animation
	 * @param handler  			para comunicar com outras classes
	 */
	public Entity(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler){
		animation = new Animation(animationSpeed, width, height, numSpritesX, numSpritesY, initPosX, initPosY);
		this.x = x;
		this.y = y;
		
		this.sprite = spriteSheet;
		this.animated = true;

		this.width = width; 	//Esses valores são importantes para o render da entidade
		this.height = height; 	//Esses valores são importantes para o render da entidade

		this.maskX = 0;			//Esses valores são importantes para a colisão da entidade
		this.maskY = 0;			//Esses valores são importantes para a colisão da entidade
		this.maskW = width;		//Esses valores são importantes para a colisão da entidade
		this.maskH = height;	//Esses valores são importantes para a colisão da entidade
		
		this.depth = depth;
		this.speed = speed;

		this.handler = handler;
	}

	/**
	 * Construtor para fazer entidades invisíveis, como spawners ou hitboxes
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Entity(double x, double y, int width, int height, Handler handler) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.handler = handler;
	}


	/*Getters e Setters*/

	public SpriteSheet getSprite() {
		return sprite;
	}

	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public int getWidth() {
		return (int)width;
	}
	
	public int getHeight() {
		return (int)height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getMaskX() {
		return maskX;
	}
	
	public int getMaskY() {
		return maskY;
	}
	
	public int getMaskW() {
		return maskW;
	}
	
	public int getMaskH() {
		return maskH;
	}
	 

	public Animation getAnimation() {
		return this.animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}


	 public void setMask(int maskX , int maskY , int maskW , int maskH) {
		this.maskX = maskX;
		this.maskY = maskY;
		this.maskW = maskW;
		this.maskH = maskH;
	 }
	 
	 /**
	  * Calcula a distância euclidiana entre dois pontos
	  * @param x1
	  * @param x2
	  * @param y1
	  * @param y2
	  * @return
	  */
	 public double calculateDistance(int x1 , int x2 , int y1 , int y2) {
		 double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2 , 2));
		 return distance;
	 }
	 /**
	  * Verifica se duas entidades se colidem
	  * @param e1
	  * @param e2
	  * @return se duas entidades estão se colidindo
	  */
	public static boolean isColliding(Entity e1,Entity e2){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.getMaskX() , e1.getY() + e1.getMaskY() , e1.getMaskW() , e1.getMaskH());
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.getMaskX() , e2.getY() + e2.getMaskY() , e2.getMaskW() , e2.getMaskH());
		
		return e1Mask.intersects(e2Mask);
	}
	
	/**
	 * Executa o algoritmo A* e procura um caminho favorável até o jogador
	 * @param path
	 */
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
				if(x < target.x * World.TILE_SIZE && handler.getGame().room.isFree((int)x + 1 , (int)y, maskW, maskH)) {
					x+=speed;
				}else if(x > target.x * World.TILE_SIZE && handler.getGame().room.isFree(this.getX() - 1 , this.getY(),maskW, maskH)) {
					x-=speed;
				}
				
				if(y < target.y * World.TILE_SIZE && handler.getGame().room.isFree(this.getX() , this.getY() + 1, maskW, maskH)) {
					y+=speed;
				}else if(y > target.y * World.TILE_SIZE && handler.getGame().room.isFree(this.getX() , this.getY() - 1, maskW, maskH)) {
					y-=speed;
				}
				
				if(x == target.x * World.TILE_SIZE && y == target.y * World.TILE_SIZE) {
					path.remove(path.size() - 1);
				}
				
			}
		}
	}
	
	public void tick(){
		if(animated) animation.tick();
	}

	public void render(Graphics g) {
		if(animated)
			g.drawImage(sprite.getSpriteSheet(),
				this.getX() + animation.getOffX() - handler.getCamera().getX(),							// Coordenada X na tela
				this.getY() + animation.getOffY() - handler.getCamera().getY(),							// Coordenada Y na tela
				(int) (this.getX() + animation.getOffX() - handler.getCamera().getX()+width),			// Largura do Sprite
				(int) (this.getY() + animation.getOffY() - handler.getCamera().getY()+height),			// Altura do sprite
				(int) animation.getSpriteX() + animation.getInitPosX(),									// Coordenada X1 na imagem
				(int) animation.getSpriteY() + animation.getInitPosY(),									// Coordenada Y1 na imagem
				(int) (animation.getSpriteX() + animation.getInitPosX()+width),							// Coordenada X2 na imagem
				(int) (animation.getSpriteY() + animation.getInitPosY()+height),						// Coordenada Y2 na imagem
				null);
	}
	
}
