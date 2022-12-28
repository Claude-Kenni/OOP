package Levels;

public class Level {

	private int [][] levelData;
	
	public Level (int[][] levelData) {
		this.levelData = levelData;
	}
	
	public int SpriteIndex(int x, int y) {
		return levelData[y][x];//gets what tile to be displayed
	}
	
	public int [][] getLvlData(){//to return the Level Data
		return levelData;
	}
}
