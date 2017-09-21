package goldMiner;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Bullet
 * 
 * This class includes the main frame for where the game will be running. It runs a thread in order for the game to start
 * The game has two states for the menu and the actual game
 * The game is set to have the Menu as its default state where you will be able to press buttons using event handlers from the mouseInput class
 *It also contains even handlers to control behaviour of miner and bullets using keyPressed 
 *This class also contains tick() and render () methods in order to update the game 60 times per second
 *These methods determine when to add enemies and change states 
 */

public class Game extends Canvas implements Runnable{
	//Creating dimensions for the game
	public static final int WIDTH=500;
	public static final int HEIGHT=400;
	public static final int SCALE=2;
	public final String TITLE= "Gold Miner";
	
	//Creating score and time variable and initializing it with values
	public static int score=0;
	int time=60;
	
	//Creating a thread for the game
	public boolean running=false;
	private Thread thread;

	//Buffering the window
	private BufferedImage image= new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	//Buffering spritesheet so all images can be updated
	private BufferedImage spriteSheet= null;
	
	//Buffering background and menu and game
	private BufferedImage levelbackground=null;
	private BufferedImage menubackground=null;

	//Creating rectangles around each object in order to check if collision occurs with bullet
	public static Rectangle largegold1 = new Rectangle (100,600,200,200);
	public static Rectangle largegold2 = new Rectangle (800,400,200,200);
	public static Rectangle smallgold1 = new Rectangle (300,500,80,80);
	public static Rectangle smallgold2 = new Rectangle (400,550,80,80);
	public static Rectangle smallgold3 = new Rectangle (500,200,80,80);
	public static Rectangle largerock1 = new Rectangle (350,700,80,80);
	public static Rectangle largerock2 = new Rectangle (600,550,80,80);
	public static Rectangle smallrock1 = new Rectangle (100,200,80,80);
	public static Rectangle smallrock2 = new Rectangle (700,300,80,80);
	
	//Creating a boolean variable that keeps track of whether player is holding shooting button
	//Being able to hold the shoot button makes the game to easy for the player so this function is taken out
	private boolean is_shooting=false;
	
	//Setting a variable for the number of enemies that should be on the screen
	private int enemies=10;
	
	//Creating instances of other classes so they can be used within game class
	private Miner miner;
	private Controller c;
	private Textures textures;
	private Menu menu;
	
	//Getting objects from textures class so they can be draw on the game screen
	private Textures largegold;
	private Textures smallgold;
	private Textures largerock;
	private Textures smallrock;

	//creating different states for game
	public static enum STATE{
		MENU,GAME, GAMEOVER};
	
	//Setting the default state to the menu
	public static STATE state= STATE.MENU;
	

	/**
	 * Initialize game settings, loads bufferedImages, and adds enemies to game
	 * Pre: no parameters
	 * Post: not returning value
	 */
	
	public void init(){
		//Brings focus to game frame so screen does not need to be clicked in order for game to start
		requestFocus();
		BufferedImageLoader loader= new BufferedImageLoader();
		try{
			//buffering backgrounds to be used in the game
			spriteSheet=loader.loadImage("/sprite_sheet.png");
			levelbackground=loader.loadImage("/levelbackground.png");
			menubackground=loader.loadImage("/menubackground.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//enabling Key and Mouse listeners methods so they can be used in this class
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		//Importing textures class to game class in order to access images
		textures=new Textures(this);
		
		//spawn player
		miner=new Miner(300,50, textures);
		
		c=new Controller(this, textures);
		menu=new Menu();
		
		//starting the game with 10 enemies (enemies=10)
		c.createEnemy(enemies);
	}
	
	// start the game thread, calls run method
	public synchronized void start(){
		if (running)
			return;

		running=true;
		thread= new Thread(this);
		thread.start();
	}
	
	// Stop the thread
	public synchronized void stop(){
		if (!running)
			return;

		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	/**
	 * Purpose: While game is running controls the number of times it updates
	 * Pre: no parameters needed
	 * Post: no return value
	 */
	public void run(){
		init();
		long initialTime =System.nanoTime();
		//updates game 60 times (60 frames per second)
		final double amountofTicks=60.0;
		double ns=1000000000/amountofTicks;
		//Time that's changed since run method was entered
		double delta=0;
		int updates=0;
		int frames=0;
		//system time in milliseconds
		long timer=System.currentTimeMillis();

		while (running){
			long now=System.nanoTime();
			//elapsed time in nanoseconds in 60 frames
			delta+=(now-initialTime)/ns;
			initialTime=now;
			//when elapsed time is greater or equal to 1
			//every one second is given to the value of 60
			//limites game to 60 frames per second so fast computer won't run game too fast
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis()- timer>1000){
				timer+=1000;
				//Output information to console
				System.out.println(updates+"Ticks,Fps"+ frames);
				//Resetting updates and frames per second counter
				updates=0;
				frames=0;
				if(state ==STATE.GAME){
					if(time>0)
					time--;
					
					}
			} 
		}
		stop();
	}
	
	//Everything in the game that updates
	/**
	 * This is run 60 times per second
	 */
	private void tick(){
		//Run tick method methods when in game state
		if(state ==STATE.GAME){
			miner.tick();
			c.tick();
			
		}
	}

	//Everything in the game that renders
	/**
	 * Paint/draw images per second for each game state.
	 */
	private void render(){
		//initializing bufferstrategy so null will be returned if buffer hasn't been loaded yet
		BufferStrategy bs= this.getBufferStrategy();
		if(bs==null){
			//3 means that there will be three buffers 
			//behind the screen there is going to be 3 different images loading up before its projected
			createBufferStrategy(3);
			return;
		}
		
		//Creating graphics context for drawing buffers
		Graphics g= bs.getDrawGraphics();
		//////////////////////////////////////////////
		//Images can be drawn here
		g.drawImage(image,0,0, getWidth(),getHeight(),this);
		g.drawImage(levelbackground,0,0,this);
		
		if(state ==STATE.MENU){
			//Draw menu background if state is menu
			g.drawImage(menubackground,0,0,this);
			menu.render(g);
		}
		//render game
		if(state ==STATE.GAME){
		miner.render(g);
		c.render(g);
		
		//draw all objects 
		g.drawImage(textures.largegold, (int)100,(int)600,null);
		g.drawImage(textures.largegold, (int)800,(int)400,null);
		g.drawImage(textures.smallgold, (int)300,(int)500,null);
		g.drawImage(textures.smallgold, (int)400,(int)550,null);
		g.drawImage(textures.smallgold, (int)500,(int)200,null);
		g.drawImage(textures.largerock, (int)350,(int)700,null);
		g.drawImage(textures.largerock, (int)600,(int)550,null);
		g.drawImage(textures.smallrock, (int)100,(int)200,null);
		g.drawImage(textures.smallrock, (int)700,(int)300,null);
		
		//Ensuring that score does not go into negative
		if (score<0){
			score=0;
		}
		
		//Ends game when time ends
		//Displays a message with score and returns back to menu
		if(time==0){
				JOptionPane.showMessageDialog(null, "Congratualations, You ended the game with "+ score+" points!!!");
				state=STATE.MENU;
				//Resetting score and time
				time=60;
				score=0;
			}
		
		//Displaying score and time on screen
		Font displayFont2 = new Font("Arial Black", Font.BOLD,25);
		g.setFont(displayFont2);
		g.setColor(Color.white);
		g.drawString("SCORE: "+score, 10,35);
		g.setColor(Color.black);
		g.drawString("TIMER: "+time, 780,35);

		}
		////////////////////////////////////////////////
		//disposing bufferstrategy because next time the game loops around there will be a null if not disposed
		g.dispose();
		bs.show();
	}

	/**
	 * Event handler method determines behaviour for when a key is pressed during game play 
	 */
	public void keyPressed(KeyEvent e){
		//Ensuring user in game state and not menu
		if(state ==STATE.GAME){
		int key=e.getKeyCode();
		
		//Moves user right if right arrow key is pressed
		if(key==KeyEvent.VK_RIGHT){
			miner.setvelocityX(5);
		}
		
		//Moves user left if left arrow key is pressed
		else if(key==KeyEvent.VK_LEFT){
			miner.setvelocityX(-5);
		}
		
		//Releases bullets if space key is pressed
		//only allow bullet to shoot every 2 seconds
		else if(key==KeyEvent.VK_SPACE && !is_shooting && time%2==0){
			is_shooting=true;
			c.addBullet(new Bullet(miner.getX(), miner.getY(),textures));
			
		}
		}
	}

	/**
	 * Event handler method determines behaviour for when a key is released during game play 
	 */
	public void keyReleased(KeyEvent e){
		if(state ==STATE.GAME){
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_RIGHT){
			miner.setvelocityX(0);
		}

		else if(key==KeyEvent.VK_LEFT){
			miner.setvelocityX(0);
		}
		
		//ensures user cannot hold key to shoot bullets
		//key must be released and pressed again in order to shoot another bullet
		else if(key==KeyEvent.VK_SPACE){
			is_shooting=false;
		}
		}
	}
	
	//constructor method
	public Game() { 
	}

	
	public static void main(String args[]){
		//creating game
		Game game=new Game();

		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		//adding game and game title to frame
		JFrame frame=new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//starting thread
		game.start();
	}
	
	//allowing other classes to access sprite sheet
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	/**
	 * Helper/getter methods
	 */
	public static int getScore(){
		return score;
	}
	
	public int getEnemies() {
		return enemies;
	}

	/**
	 * modifier/setter methods
	 */
	public static void setScore(int x){
		score=score+x;
	}
	
	public void setEnemies(int enemies) {
		this.enemies = enemies;
	}

}
