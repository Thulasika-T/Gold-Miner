package goldMiner;

import java.awt.image.BufferedImage;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * Sprite Sheet
 * 
 *This class grabs the image from one cell (100X100) 
 *The image will be located at col(column number), row(row number) in the sprite sheet.
 * 
 */

public class SpriteSheet {
	
	//Creating variable for images that are going to be uploaded
	private BufferedImage image;
	
	//Creating Constructor
	public SpriteSheet(BufferedImage image){
		this.image=image;
	}
	
	/**
	 *Returns buffered image that is sampled from another buffered image
	 *Pre: 4 parametes, location and size of image
	 *Post: returns image
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img=image.getSubimage((col*100)-100, (row*100)-100, width, height);
		return img;
	}
}
