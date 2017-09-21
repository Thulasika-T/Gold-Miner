package goldMiner;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Enemy
 * 
 * This class controls the behaviour of enemies.
 *  Enemies move in a straight line toward the top of the screen
 */
public class Enemy {
	
	//x and y position of enemy
	private double x,y;
	
	//initializing random value
	Random random= new Random();
	
	//Importing textures class inorder to draw enemies
	public Textures textures;
	
	//Intializing a rectangle to be drawn around enemy in order to detect collision
	public Rectangle enemy = null;
	
	//initializing random speeds for the enemies to travel at
	private int speed= random.nextInt(5)+1;
	
	//creating constructor
	public Enemy(double x, double y, Textures textures){
		this.x=x;
		this.y=y;
		this.textures=textures;
	}
	
	/*
	 * Purpose: Changes position of enemy the top of the screen with each update. 
	 *No Parameters
	 *No retrun value
	 * 
	 */
	public void tick(){
		//Changing y coordinate of enemy at random speeds
		y=y-speed;
		
		//When the enemies reach the miner, the enemies will be sent back to the bottom 
		//When they are reset to come from the bottom, they will be placed in a random x position
		if(y<145){
			y=820;
			x=random.nextInt(Game.WIDTH*Game.SCALE);
		}
	}
	
	
	/*
	 * Purpose: To draw the enemies and check for collisions with miner
	 * Pre: 1 Parameter of graphics is needed
	 * Post: no return value
	 */
	public void render(Graphics g){
		//Draw enemy
		g.drawImage(textures.enemy, (int)x, (int)y, null);
		//Create rectangle around enemy in order to detect for any collision
		enemy = new Rectangle ((int)x,(int)y,80,80);
		
		//Check for collision between enemy and miner
		if (enemy.intersects(Miner.person)){
			//If collision occurs, decrease 10 points
			Game.setScore(-10);
		}
		
	}
	
	/* 
	 * Purpose:Helper Method, Allows for other classes to get the y value of the enemy
	 * Pre: no parameters
	 * Post: returns 1 double value
	 */
	public double getY() {
		return y;
	}
	
	/* 
	 * Purpose:Modifired Method,Allows for other classes to change y value of the enemy
	 * Pre: 1 double value, the y position
	 * Post: no return value
	 */
	public void setY(double y) {
		this.y=y;
	}
}
