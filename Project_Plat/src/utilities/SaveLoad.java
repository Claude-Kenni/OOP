package utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class SaveLoad {
	
	public static final String PLAYER_ATLAS = "updated.png";
	public static final String LEVEL_ATLAS = "block.png";
	public static final String LEVEL1_DATA = "level1_data.png";
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = SaveLoad.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		return img;
	}
	
	public static int[][] getLvlData(){// to get the level data means it the sprite will be draw according to the picture of the level
		int[][] levelData = new int [Game.TILES_HEIGHT][Game.GAME_WIDTH];
		BufferedImage img = GetSpriteAtlas(LEVEL1_DATA);
		
		for (int height=0; height < img.getHeight(); height ++)
			for (int width=0; width < img.getWidth(); width ++) {
				Color color = new Color(img.getRGB(width, height));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				levelData [height][width]= value;
			}
		return levelData;
		
	}
}
