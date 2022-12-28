package utilities;

import java.awt.geom.Rectangle2D;

import main.Game;

public class Checker {//it helps by checking the collision between player and object, if it can move, etc.
	public static boolean CanMove(float x, float y, float width, float height, int [][] levelData) {//checks each corner if the player can move to that position
		
		if (!IsPlatform (x, y, levelData))// check top-left corner of hit box
			if (!IsPlatform(x + width, y + height, levelData))//check bottom-right
				if (!IsPlatform (x + width, y, levelData))// check top-right
					if (!IsPlatform (x, y + height, levelData))//check bottom-left
						return true;//can move
		return false;//can't move
		
	}
	private static boolean IsPlatform(float x, float y, int[][] levelData) {// Checks if a given position on the map is a solid object or not. It checks if the position is outside the boundaries of the game window, and if the tile at that position is a solid object or not.
		if (x <= 0 || x >= Game.GAME_WIDTH)
			return true;//is solid
		if (y <= 0 || y >= Game.GAME_HEIGHT)
			return true;//is solid
		
		float index_x = x / Game.TILES_SIZE;
		float index_y = y / Game.TILES_SIZE;
		
		int value =  levelData [(int)index_y][(int) index_x];//when passing the value to the levelData it will be casted as integer
		
		if (value != 11)// =48 available sprites and 11 is the transparent sprite
			return true;//the sprite is solid
		return false;
	}
	
	public static float EntityNextToWallPosX(Rectangle2D.Float hitBox, float SpeedX) {
		//SpeedX can't never be 0 because if it does then the character will not collide
		int tileCurrent = (int) (hitBox.x / Game.SCALE);
		//calculate what tile is the player in, currently.  
		if (SpeedX > 0) {
			//colliding on right
			int posXTile = tileCurrent * Game.TILES_SIZE; //position in pixels
			int OffsetX = (int)(Game.TILES_SIZE - hitBox.width);//offset between size of the player and the size of tile
			return posXTile + OffsetX - 1;//-1 to not overlap between hitBox the tiles on x
		} else {
			//it's on left
			return tileCurrent * Game.TILES_SIZE;
			
		}
	}
	
	public static float EntityUnderRoofOrAboveGroundPosY (Rectangle2D.Float hitBox, float airSpeed) {
		int tileCurrent = (int) (hitBox.y / Game.SCALE);
		if (airSpeed > 0) {
			//Falling - touching the ground
			int posYTile = tileCurrent * Game.TILES_SIZE;
			int OffsetY = (int)(Game.TILES_SIZE - hitBox.height);
			return posYTile + OffsetY - 1; //1 pixel above the floor Right above the floor
			
		}else 
			//Jumping
			return tileCurrent * Game.TILES_SIZE;
		
	}
	
	public static boolean IsEntityOnGround(Rectangle2D.Float hitBox, int[][] levelData) {
		//Check the pixel of hitbox below bottom-left and bottom-right
		if(!IsPlatform(hitBox.x, hitBox.y + hitBox.height + 1, levelData))//bottom r
			if (!IsPlatform(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData))
				return false;
		
		return true;
	}

	
}
