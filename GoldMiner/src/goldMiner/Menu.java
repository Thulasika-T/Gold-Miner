package goldMiner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JButton;
import javax.swing.JComponent;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Menu
 * 
 * This class renders the main menu for whenever the game is in STATE.MENU
 * Draw buttons in order to start, ask for instructions and quit game
 * No listeners because it is handled in the game class
 *   
 */

public class Menu {
	//Creating rectangles for buttons and setting bounds for each one
	public Rectangle start = new Rectangle (175,165,240,100);
	public Rectangle instructions = new Rectangle (150,450,200,70);
	public Rectangle quit = new Rectangle (150,600,200,70);
	
	/*
	 * Purpose: Rendering the main menu
	 * Pre: 1 Parameter of graphics is needed
	 * Post: no return value
	 */
	public void render(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		
		g.setColor(new Color(204,153,0));
		//Drawing start button
		g2d.draw(start);
		//Setting font and color for text on the buttons
		Font displayFont2 = new Font("Serif", Font.BOLD,25);
		g.setFont(displayFont2);
		g.setColor(new Color(204,153,0));
		
		//Setting outline and drawing text for instructions button
		g.drawString("Instructions", instructions.x+20,instructions.y+35);
		
		//Setting outline and drawing text for instructions button
		g.drawString("Quit Game", quit.x+30,quit.y+35);
		
		//Drawing button
		g2d.draw(quit);
		g2d.draw(instructions);
	}
}