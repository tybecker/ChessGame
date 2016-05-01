package chess.gamestate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.resources.Images;

public class MenuState extends GameState{
	
	//The options that will display on the main menu, as well as the number
	//of the current selection.  Default selection is 0 (play chess)
	private String[] options = {"Play Chess", "Zerg Rush", "About", "Quit"};
	private int currentSelection = 0;
	
	/**
	 * The constructor method
	 * 
	 * @param gsm the game state manager overseeing the whole deal.  Push onto this
	 * to change game states, pop off to move back.
	 */
	public MenuState(GameStateManager gsm){
		super(gsm);
	}

	/**
	 * Runs once on startup
	 */
	@Override
	public void init() {
	}

	/**
	 * Runs 60 times a second.
	 */
	@Override
	public void tick() {
	}

	/**
	 * Called on to draw what the main menu looks like.  The selected option
	 * should be highlighted.
	 * 
	 * @param g The graphics object from the GamePanel, used to draw the menu
	 * screen.
	 */
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(Images.backgrounds[0], 0, 0, 900, 700, null);
		
		for(int i = 0; i < options.length; i++){
			if(i == currentSelection){
				g.setColor(Color.BLUE);
			}else{
				g.setColor(Color.BLACK);
			}
			
			g.setFont(new Font("Arial", Font.PLAIN, 36));
			g.drawString(options[i], 50, 70 + i* 50);
		}
	}

	/**
	 * The method activated when a key has been pressed.  This should be used
	 * for selecting and activating options.
	 * 
	 * @param k The number of the key that has been pressed.
	 */
	@Override
	public void keyPressed(int k) {
		//If the down key is pressed, move the selection down one.
		if(k == KeyEvent.VK_DOWN){
			currentSelection++;
			if(currentSelection >= options.length){
				currentSelection = 0;
			}
		//If the up key is pressed, move the selection up one. 
		}else if (k == KeyEvent.VK_UP){
			currentSelection--;
			if(currentSelection < 0){
				currentSelection = options.length - 1;
			}
		}
		//If the enter key is pressed, activate the option associated with the
		//current selection
		if(k == KeyEvent.VK_ENTER){
			
			if(currentSelection  == 0){
				gsm.states.push(new SubMenu1State(gsm));
			}else if(currentSelection == 1){
			
			}else if(currentSelection == 2){
				JFrame aboutFrame = new JFrame("About");
				aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				aboutFrame.setResizable(false);
				aboutFrame.setLayout(new BorderLayout());
				
				JPanel aboutPanel = new JPanel();
				aboutPanel.setSize(300, 200);
				
				JLabel aboutLabel = new JLabel("I made a chess game.");
				aboutPanel.add(aboutLabel);
				
				aboutFrame.add(aboutPanel, BorderLayout.CENTER);
				aboutFrame.pack();
				aboutFrame.setLocationRelativeTo(null);
				aboutFrame.setVisible(true);
			}else if(currentSelection == 3){
				System.exit(0);
			}
		}
	}

	/**
	 * Called if a key has been released.
	 * 
	 * @param k The number of the key that has been released.
	 */
	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Called if the mouse is moved.  Currently used to determine current selection
	 * based on where the mouse is.
	 * 
	 * @param x The x location of the mouse
	 * @param y The y location of the mouse
	 */
	@Override
	public void mouseMoved(int x, int y) {
		if(x >= 50 && x <= 242 && y >= 42 && y <= 70){
			currentSelection = 0;
		}else if(x >= 50 && x <= 150 && y >= 92 && y <= 120){
			currentSelection = 1;
		}else if(x >= 50 && x <= 150 && y >= 142 && y <= 170){
			currentSelection = 2;
		}else if(x >= 50 && x <= 150 && y >= 192 && y <= 220){
			currentSelection = 3;
		}
		
	}

	/**
	 * Called whenever the mouse is clicked, for our purposes it has the exact
	 * same effect as hitting the enter key.
	 * 
	 * @param x The x location of the mouse
	 * @param y The y location of the mouse
	 */
	@Override
	public void mouseClicked(int x, int y) {
		this.keyPressed(KeyEvent.VK_ENTER);
	}
	
}
