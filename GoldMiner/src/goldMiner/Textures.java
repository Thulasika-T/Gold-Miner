package goldMiner;

import java.awt.image.BufferedImage;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 *Textures
 * 
 * This class assigns bufferedImages from cells in a spritesheet
 * 
 */

public class Textures {
	//Assigning variables for all the images on sprite sheet
	public BufferedImage miner, bullet, largegold, smallgold, largerock, smallrock, enemy;
	
	//importing spritesheet class in order to upload image
	private SpriteSheet ss;
	
	//Creating constructor
	public Textures(Game game){
		ss=new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	/**
	 *Assigning buffered images from spritesheet
	 *Pre: no parameters
	 *Post: no return value
	 */
	private void getTextures(){
		miner=ss.grabImage(1,1,100,100);
		bullet=ss.grabImage(2,1,50,80);
		largegold=ss.grabImage(3,1,200,200);
		smallgold=ss.grabImage(5,2,80,80);
		largerock=ss.grabImage(5,1,80,80);
		smallrock=ss.grabImage(6,1,80,80);
		enemy=ss.grabImage(7,1,80,80);
	}

}
