package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.SaveLoad;

public class LevelHandler {//this will be responsible for making levels

	private Game g;
	private BufferedImage[] levelSprite;
	private Level level1;
	
	public LevelHandler (Game g) {
		this.g = g;
//		levelSprite = SaveLoad.GetSpriteAtlas(SaveLoad.LEVEL_ATLAS);
		importOutsideSprites();
		level1 = new Level(SaveLoad.getLvlData());
	}
	
	private void importOutsideSprites() {//to import the outside tiles
		BufferedImage img = SaveLoad.GetSpriteAtlas(SaveLoad.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int row = 0; row < 4; row++)
			for (int col = 0; col <12; col++) {
				int index = row*12 + col;
				levelSprite[index] = img.getSubimage(col*32, row*32, 32, 32);
			}
		
	}

	public void draw(Graphics game) {
		for (int height = 0; height < Game.TILES_HEIGHT; height++)
			for (int width = 0; width < Game.TILES_WIDTH; width++) {
				int index = level1.SpriteIndex(width, height);
				game.drawImage(levelSprite[index], Game.TILES_SIZE * width , Game.TILES_SIZE * height, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}//to draw tiles based on the image of level data

	}
	
	public void update() {
		
	}
	
	public Level getCurrentlvl () {
		return level1;
	}
	
}
