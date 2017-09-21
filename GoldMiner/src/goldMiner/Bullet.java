package goldMiner;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Bullet
 * 
 * This class determines the behaviour for bullets spawning from players and moving down the screen
 * 
 */
public class Bullet {

	private double x;//x position of each bullet
	private double y;//y position of each bullet
	
	//Importing textures class so images from that class can be used
	private Textures textures;
	
	//Initializing rectangle for bullet in order to detect collision
	public Rectangle bullet = null;
	
	//Create constructor for getting the position of bullet and image of bullet
	public Bullet(double x, double y, Textures textures){
		this.x= x;
		this.y= y;
		this.textures=textures;
		
	}
	
	/*
	 * Purpose: Changes position towards the bottom of the screen with each update. 
	 *No Parameters
	 *No retrun value
	 * 
	 */
	public void tick(){
		
		//Changing y-coordinate of bullet in order to move the bullet down the screen
		y+=5;
		
	}
	
	/*
	 * Purpose: To draw the bullet and check for any collisions between objects and bullet
	 * Pre: 1 Parameter of graphics is needed
	 * Post: no return value
	 */
	public void render(Graphics g){
		
		//drawing bullet
		g.drawImage(textures.bullet, (int)x, (int)y, null);
		
		//Creating a rectnagle around each bullet in order to determine if collision occurs
		bullet = new Rectangle ((int)x,(int)y,50,80);
		
		//Checking collision between bullets and objects
		if (bullet.intersects(Game.largegold1)){
			//Increasing score by 300, if this collision has been detected
			Game.setScore(300);
			//Removing the bullet from the screen as soon as collision has been detected
			//This prevents from it going through and hitting other objects as well
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.largegold2)){
			Game.setScore(300);
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.smallgold1)){
			Game.setScore(150);
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.smallgold2)){
			Game.setScore(150);
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.smallgold3)){
			Game.setScore(150);
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.largerock1)){
			Game.setScore(50);
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.largerock2)){
			Game.setScore(50);
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.smallrock1)){
			Game.setScore(10);
			Controller.removeBullet(Controller.TempBullet);
		}
		
		if (bullet.intersects (Game.smallrock2)){
			Game.setScore(10);
			Controller.removeBullet(Controller.TempBullet);
		}
		
	}
	
	/* 
	 * Purpose: Allows for other classes to get the y value of the bullet
	 * Pre: no parameters
	 * Post: returns 1 double value
	 */
	public double getY(){
		return y;
	}
	
}
