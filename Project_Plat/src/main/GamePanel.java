package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import Inputs.KeyboardInputs;
import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;;

public class GamePanel extends JPanel {

	private Game g;
	public GamePanel(Game g) {
		this.g = g;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
	}


	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		System.out.println("Size : " + GAME_WIDTH + " x " + GAME_HEIGHT);
	}

	
	public void gameUpdate()
	{
	
	}

	public void paintComponent(Graphics game) {
		super.paintComponent(game);
		g.render(game);

	}
	
	public Game getGame() {
		return g;
	}
	

}
