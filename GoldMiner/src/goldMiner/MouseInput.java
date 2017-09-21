package goldMiner;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Mouse Input
 * 
 * This class will be called whenever the mouse is clicked
 *   
 */
public class MouseInput implements MouseListener{

	/**
	 *Mouse handler method that determines behaviour whenever a mouse is clicked
	 *Pre: 1 parameter
	 *Post: no return value
	 */
	public void mousePressed(MouseEvent e) {
		//Variable to determine the location of mouse
		int mx=e.getX();
		int my=e.getY();
		
		//Play Button, setting bounds for where button is
		if (mx >= 175 && mx<=415){
			if (my >= 165 && my<=265){
				//behaviour when button is click
				Game.state = Game.STATE.GAME;
			}
		}
		
		//quit Button, setting bounds for where button is
		if (mx >= 150 && mx<=350){
			if (my >= 600 && mx<=670){
				//behaviour when quit button is clicked
				//exit game
				System.exit(1);
			}
		}
		
		//instructions Button, setting bounds for where button is
		if (mx >= 150 && mx<=350){
			if (my >= 450 && mx<=520){
				//Opening up a dialog box that displays a message
				JOptionPane.showMessageDialog( null, "RULES.\n" +
						"The miner can shoot bullets every 2 seconds in order to get points from the gold and rocks that are found underground. \n" +
						"Use the keys on your keyboard to move the miner back and forth. \n"+ "The bigger gold will be worth more than the smaller gold pieces and rocks.\n" +
						"\n Be careful of the spiders though. Try to dodge them because if they catch you they'll take 10-20 points away.\n" +
						"Your objective is to get as many points as you can within 60 SECONDS!"+
						"\nGOOD LUCK!!! "+
						"\n\nCONTOLS\n"+
						"Click the space button to shoot bullets and hit the gold in order to gain points.\n" +
						"Use the right and left arrow keys to move the miner to your desired position.",
						"Instructions",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
		
	}
	
	//Class inherits abstract methods that have not been implemented even though they are not being used
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
