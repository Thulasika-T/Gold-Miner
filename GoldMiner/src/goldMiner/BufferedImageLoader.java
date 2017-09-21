package goldMiner;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Gold Miner
 * January 22, 2017
 * Thulasika Thiyaageswaran
 * 
 * BufferedImageLoader
 * 
 * This class loads BufferedImages.Used in Game class.
 * BufferedImages loads the image before the image is project which prevents lag in your game.
 * 
 */

public class BufferedImageLoader {
	private BufferedImage image;
	
	//Load buffered Images
	public BufferedImage loadImage(String path) throws IOException{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}

}
