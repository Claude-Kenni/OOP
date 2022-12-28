package entities;

import static utilities.char_constants.CharConstants.*;
import static utilities.Checker.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.SaveLoad;

public class Player extends entity{

	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerMovement = IdleStance;
	private boolean movement = false;
	private boolean up, left, down, right, jump;
	private float playerSpeed = 2.0f;
	private int [][] levelData;// this stores what tiles to be draw, etc.
	private float OffSetDrawX =  17 * Game.SCALE;//this two offset of x and are is the starting position of hitbox based on the player sprite's pixel
	private float OffSetDrawY =  5 * Game.SCALE;
	
	//jumping and gravity
	private float airSpeed = 0f; //the speed when traveling through the air whether it's up or down
	private float gravity = 0.04f * Game.SCALE;// the lower the gravity the higher the player can jump
	private float jumpSpeed = -2.25f * Game.SCALE;//it's negative because it's going up to the y direction
	private float fallSpeedAftCollision = 0.5f * Game.SCALE; //this will be the new air speed value when the player hits the roof 
	private boolean midAir = false;//if player is in mid air
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);//it is pass to the entity class
		loadAnimations();
		initHBox(x, y, 31 * Game.SCALE, 28 * Game.SCALE );// width and height of hitbox
	
	}

	public void update() {
		
		posLoc();
		AnimationTick();
		setAnimation();
		
		
	}
	
	public void render(Graphics game) {//render player
		
		game.drawImage(animations[playerMovement][aniIndex], (int) (hitBox.x - OffSetDrawX), (int)(hitBox.y - OffSetDrawY), width, height, null);
		drawHB(game);
	}
	


	private void AnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= CharAmount(playerMovement))
				aniIndex = 0;
		}

	}

	private void setAnimation() {//to set the animation if the player is moving or not
		if (movement)
			playerMovement = Running;
		else
			playerMovement = IdleStance;
		
		if (midAir) {
			if (airSpeed < 0)
				playerMovement = Jumping;
			else
				playerMovement = Falling;
		}
	}

	private void posLoc() {//left and right will cancel each other if they are both pressed
		
		movement = false;// to reset movement by default
		
		
		if (jump)
			jump();
		if (!left && !right && !midAir)//not moving
			return;
		
		if (!midAir)
			if(!IsEntityOnGround (hitBox, levelData))
				midAir = true;
		
		float SpeedX = 0;//temp storage of the speed in x
		
		if (left) // go left
			SpeedX -= playerSpeed;
			
		if (right) // go right
			SpeedX += playerSpeed;
		
		if (midAir) {//in Air

			if (CanMove(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
				hitBox.y += airSpeed;
				airSpeed += gravity;//if it's negative value, it's gonna slow down because the player is going up and if it's going down it's going to increase the speed
				posXUpdate(SpeedX);
				
			}else {
				hitBox.y = EntityUnderRoofOrAboveGroundPosY(hitBox, airSpeed);
				if (airSpeed > 0) //0 meaning the character is going down or when it collide to something
					midAirReset();
				else
					airSpeed = fallSpeedAftCollision;
				posXUpdate(SpeedX);
			}
		
		} else 
			posXUpdate (SpeedX);//update position on x
		
		movement = true; //the player is moving
		
		
	}
	

	private void jump() {
		if (midAir)
			return;
		midAir = true;
		airSpeed = jumpSpeed;
		jump = false;
	}

	private void midAirReset() {
		midAir = false;
		airSpeed = 0;
		
	}

	private void posXUpdate(float SpeedX) {
		if (CanMove(hitBox.x + SpeedX, hitBox.y, hitBox.width, hitBox.height, levelData)) {//if can move to a position then update it
		hitBox.x += SpeedX;	
		}else {//if it can't move to that position(colliding with an object) there is still a space between a player and wall
			hitBox.x = EntityNextToWallPosX(hitBox, SpeedX);//take the hitBox and the SpeedX and checks if the wall/platform is on next or right
		
		}
	}


	private void loadAnimations() {
			BufferedImage img = SaveLoad.GetSpriteAtlas(SaveLoad.PLAYER_ATLAS);
			
			animations = new BufferedImage[5][6];
			for (int j = 0; j < animations.length; j++)
				for (int i = 0; i < animations[j].length; i++)
					animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
			
	}
	
	public void LoadlevelData (int [][] levelData) {//to load level data
		this.levelData = levelData;
		if (!IsEntityOnGround(hitBox, levelData))
			midAir = true;
	}
	
	public void resetDirBool() {//reset direction to false
		up = false;
		left = false;
		down = false;
		right = false;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
}
