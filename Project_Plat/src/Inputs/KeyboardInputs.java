package Inputs;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import static utilities.char_constants.Directions.*;

public class KeyboardInputs implements KeyListener {

	private GamePanel gPanel;

	public KeyboardInputs(GamePanel gPanel) {
		this.gPanel = gPanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gPanel.getGame().getPlayer().setUp(false);
			break;
		case KeyEvent.VK_A:
			gPanel.getGame().getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S:
			gPanel.getGame().getPlayer().setDown(false);
			break;
		case KeyEvent.VK_D:
			gPanel.getGame().getPlayer().setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			gPanel.getGame().getPlayer().setJump(false);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gPanel.getGame().getPlayer().setUp(true);
			break;
		case KeyEvent.VK_A:
			gPanel.getGame().getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S:
			gPanel.getGame().getPlayer().setDown(true);
			break;
		case KeyEvent.VK_D:
			gPanel.getGame().getPlayer().setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			gPanel.getGame().getPlayer().setJump(true);
			break;
		}

	}

}
