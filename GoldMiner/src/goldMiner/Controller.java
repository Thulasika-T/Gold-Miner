package goldMiner;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Controller
 * 
 * This class contains Linked lists which will store all game objects 
 * LinkedList is updated through a tick method and drawn by a render method
 */
public class Controller {
	
	//LinkedList holds multiple objects 
	private static LinkedList<Bullet> b =new LinkedList<Bullet>();
	private LinkedList<Enemy> enemy =new LinkedList<Enemy>(); 
	
	//intializing random number
	Random ran= new Random();
	
	//Creating variables that will updates each bullet and enemy in the list
	public static Bullet TempBullet;
	static Enemy TempEnemy;
	
	//Importing game and textures class within controller so those values can be used
	Game game;
	
	//Importing textures class
	Textures textures;
	
	//Creating constructor
	public Controller(Game game,Textures textures){
		this.game=game;
		this.textures=textures;
		
		//Adding enemies in random positions
		addEnemy(new Enemy(ran.nextInt(1000),800,textures));
		
	}
	
	/*
	 * Purpose:Creating the number of enemies initialized in the game class in random x positions
	 * Pre: 1 int value, number of enemies
	 * Post: no return values
	 */
	public void createEnemy(int enemies){
		for(int i=0; i<enemies;i++){
			addEnemy(new Enemy(ran.nextInt(1000), 800, textures));
		}
	}
	
	/*
	 * Running the tick method and updating game for each item that is being drawn from linkedlist
	 */
	public void tick(){
		//Creating for loop that gets size of list and updates everytime a bullet is sent
		for (int i=0; i<b.size(); i++){
			TempBullet=b.get(i);
		
			//When bullet is greater than the height of the screen, the bullet will be removed
			if(TempBullet.getY()>800){
				removeBullet(TempBullet);
			}
			
			//Update tick method for bullets
			TempBullet.tick();
		}
		
		//Creating for loop that gets size of list and updates everytime an enemy is sent
		for (int i=0; i<enemy.size(); i++){
			TempEnemy=enemy.get(i);
			
			//When the enemy is greater than the height of the screen
			//it will be sent back to the bottom of the page to come back up again
			if(TempEnemy.getY()<100){
				TempEnemy.setY(800);
			}
			//Update tick method for enemies
			TempEnemy.tick();
		}
	}
	
	/*
	 * Purpose: To draw the bullet and enemy everytime the linkedlist sends out a new one
	 * Pre: 1 Parameter of graphics is needed
	 * Post: no return value
	 */
	public void render(Graphics g){
		for (int i=0; i<b.size(); i++){
			TempBullet=b.get(i);
			
			TempBullet.render(g);
		}
		
		for (int i=0; i<enemy.size(); i++){
			TempEnemy=enemy.get(i);
			
			TempEnemy.render(g);
		}
	}
	
	//Allowing bullet to be added on screen at anytime
	public void addBullet(Bullet block){
		b.add(block);
	}
	
	//Allowing bullet to be removed off screen whenever desired 
	public static void removeBullet(Bullet block){
		b.remove(block);
	}
	
	//Allowing enemy to be added on screen at anytime
	public void addEnemy(Enemy block){
		enemy.add(block);
	}
	
	//Allowing enemy to be removed off screen at anytime
	public void removeEnemy(Enemy block){
		enemy.remove(block);
	}
}
