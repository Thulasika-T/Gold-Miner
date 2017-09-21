package goldMiner;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Miner
 * 
 * The class determines the behaviour for the miner 
 * Miner is a sprite object draw in render method
 */
public class Miner {

	//x and y position of enemy
	private double x,y;
	
	//creating horizontal velocity values
	private double velocityX=0;
	
	private BufferedImage miner;//Creating buffered image for miner
	
	//Importing textures class
	private Textures textures;
	
	//Creating rectangle around miner to account for any collision
	public static Rectangle person = null;
	
	//Creating constructor
	public Miner(double x, double y, Textures textures){
		this.x= x;
		this.y= y;
		this.textures=textures;
	}
	
	 /* Purpose: Changes position of miner from side to side with each update. 
	 *No Parameters
	 *No retrun value
	 * 
	 */
	public void tick(){
		//Changing position of miner without accounting for where the miner is
		//Using velocity makes movement of miner more smoother
		x=x+velocityX;
		
		//Ensuring that miner does not go off screen on left side of screen
		if(x<=0){
			x=0;
		}
		
		//Ensuring that miner does not go off screen on right side of screen
		if(x>=1000-90){
			x=1000-90;
		}		
	}
	
	/*
	 * Purpose: To draw the miner
	 * Pre: 1 Parameter of graphics is needed
	 * Post: no return value
	 */
	public void render(Graphics g){
		g.drawImage(textures.miner, (int)x,(int)y,null);
		//Updating the rectangle around miner everytime it is moved
		person = new Rectangle ((int)x,(int)y,100,100);
		
	}
	
	/* 
	 * Purpose: Helper Method, Allows for other classes to get the x & y value of miner
	 * Pre: no parameters
	 * Post: returns 1 double value
	 */
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	/* 
	 * Purpose: Modifired Method,Allows for other classes to change X value of the enemy
	 * Pre: 1 double value, the x position
	 * Post: no return value
	 */
	public void setvelocityX(double velocityX){
		this.velocityX=velocityX; 
	}
}
