package main;

import java.awt.Graphics;

import Levels.LevelHandler;
import entities.Player;

public class Game implements Runnable {

	private GameWindow Gwin;
	private GamePanel Gpanel;
	private Thread Gthread;	
	private final int SetFPS = 60;
	private final int SetUPS = 100;
	private Player player;
	private LevelHandler levelHandler;
	
	public final static int TILES_DEF_SIZE = 32; //Tiles Default Size
	public final static float SCALE = 1.5f;
	public final static int TILES_WIDTH = 26;
	public final static int TILES_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEF_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGHT;
	
	public Game() {

		initClasses();//initialize player, enemies, handlers, etc.
		Gpanel = new GamePanel(this);
		Gwin = new GameWindow(Gpanel);
		Gpanel.requestFocus();
		Gloop();
		 

	}

	private void initClasses() {
		levelHandler = new LevelHandler (this);
		player = new Player(50, 145, (int) (64 * SCALE), (int) (40* SCALE));
		player.LoadlevelData(levelHandler.getCurrentlvl().getLvlData());
		
	}

	private void Gloop() {
		Gthread = new Thread(this);
		Gthread.start();
	}
	
	public void update()
	{
		player.update();
		levelHandler.update();
	}
	
	public void render(Graphics game) {
		levelHandler.draw(game);
		player.render(game);
		
	}
	
	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / SetFPS;
		double timePerUpdate = 1000000000.0 / SetUPS;
		
		long precedingTime = System.nanoTime();
		long lastCheck = System.currentTimeMillis();
		
		int frames = 0;
		int updates = 0;
		
		double UpdateTime = 0;
		double FrameTime = 0;
		
		while (true) {
			long presentTime = System.nanoTime();
			
			UpdateTime += (presentTime - precedingTime)/timePerUpdate;
			FrameTime += (presentTime - precedingTime)/timePerFrame;
			precedingTime = presentTime;
			if(UpdateTime >= 1)
			{
				update();
				updates++;
				UpdateTime--;
			}
			
			if(FrameTime >= 1)
			{
				Gpanel.repaint();
				FrameTime--;
				frames++;
			}
			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS" + updates);
				frames = 0;
				updates = 0;
			}
		}

	}
	
	public void windowFocusLost() {
		player.resetDirBool();//reset boolean when windows lost focus
	}
	
	public Player getPlayer() {
		return player;//to get the player
	}
	
	
}
