package goldMiner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * KeyInput
 * 
 * This class will be called whenever a key is pressed
 * Contains miner behaviour
 * 
 */
public class KeyInput extends KeyAdapter{
	
	//Imports game class
	Game game;
	
	//create constructor
	public KeyInput(Game game){
		this.game=game;
	}
	
	/**
	 *Key handler method that determines behaviour whenever a key is pressed
	 *Pre: 1 parameter, key that is pressed
	 *Post: no return value
	 */
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
	}
	
	/**
	 *Key handler method that determines behaviour whenever a key is released
	 *Pre: 1 parameter, key that is released
	 *Post: no return value
	 */
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}
}
