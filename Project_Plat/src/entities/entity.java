package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class entity {
	
	protected float x, y;//only classes that extends entity can use x and y
	protected int width, height;
	protected Rectangle2D.Float hitBox;//Rectangle class
	
	public entity(float x, float y, int width, int height) {
		this.x = y;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	protected void drawHB(Graphics game) {
		//To show hitbox and for debugging it
		game.setColor(Color.RED);
		game.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
	}
	
	protected void initHBox(float x, float y, float width, float height) {//initialize hit box
		hitBox = new Rectangle2D.Float ( x, y, width, height);//width and height will always be the same
	}
	
//	protected void hBoxUpdate () {//whatever the entity extends to it will only update
//		hitBox.x =  (int) x;
//		hitBox.y = (int) y;
//	}
	
	public Rectangle2D.Float getHBox() {
		return hitBox;
	}
}
