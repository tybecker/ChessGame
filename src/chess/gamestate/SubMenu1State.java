package chess.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import chess.resources.Images;

public class SubMenu1State extends GameState{

	//The options in the different menus.  These allow the player
	//to choose how many people are playing, which color they want
	//to play as, and whether they want to start the game, or 
	//head back to the main menu.
	private String[] options1 = {"1 Player", "2 Player"};
	private String[] options2 = {"Black", "White", "Random"};
	private String[] options3 = {"Back", "Start"};
	
	//The current selection on all the menus.  3 means no selection.
	//Don't forget to raise this if you end up with more than 3
	//options on a menu.
	private int currentSelection1 = 3;
	private int currentSelection2 = 3;
	private int currentSelection3 = 3;
	
	//The current mouse over option on a menu, 3 meaning nothing is
	//being moused over.  Once again, don't forget to raise this if
	//you put in more options.
	private int currentMouseover1 = 3;
	private int currentMouseover2 = 3;
	private int currentMouseover3 = 3;
	
	//The current menu that is moused over, so that the menu can be highlighted.
	private int menuSelection = 3;
	
	/**
	 * The constructor, calling on the superconstructor, which stores the gsm to be
	 * called upon by this object.  Pop to return to previous state, push to move
	 * to new state.
	 * 
	 * @param gsm The game state manager, used to manage what the game is doing.
	 */
	public SubMenu1State(GameStateManager gsm){
		super(gsm);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void tick() {
		
	}

	/**
	 * The draw method, needs to draw the options, the rectangles behind the options,
	 * and the background.
	 * 
	 * @param g The graphics object, used to draw to the game panel
	 */
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(Images.backgrounds[0], 0, 0, 900, 700, null);
		
		if(menuSelection == 0){
			g.setColor(Color.WHITE);
		}else{
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillRect(150, 50, 250, 160);
		g.setColor(Color.BLACK);
		g.drawRect(150, 50, 250, 160);
		
		for(int i = 0; i < options1.length; i++){
			if(i == currentSelection1){
				g.setColor(Color.RED);
			}else if(i == currentMouseover1){
				g.setColor(Color.BLUE);
			}else{
				g.setColor(Color.BLACK);
			}
			
			g.setFont(new Font("Arial", Font.PLAIN, 36));
			g.drawString(options1[i], 158, 90 + i * 50);
		}
		
		if(menuSelection == 1){
			g.setColor(Color.WHITE);
		}else{
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillRect(450, 50, 250, 160);
		g.setColor(Color.BLACK);
		g.drawRect(450, 50, 250, 160);
		
		for(int i = 0; i < options2.length; i++){
			if(i == currentSelection2){
				g.setColor(Color.RED);
			}else if(i == currentMouseover2){
				g.setColor(Color.BLUE);
			}else{
				g.setColor(Color.BLACK);
			}
			
			g.setFont(new Font("Arial", Font.PLAIN, 36));
			g.drawString(options2[i], 458, 90 + i * 50);
		}
		
		if(menuSelection == 2){
			g.setColor(Color.WHITE);
		}else{
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillRect(240, 250, 400, 100);
		g.setColor(Color.BLACK);
		g.drawRect(240, 250, 400, 100);
		
		for(int i = 0; i < options3.length; i++){
			if(i == currentMouseover3){
				g.setColor(Color.BLUE);
			}else{
				g.setColor(Color.BLACK);
			}
			g.setFont(new Font("Arial", Font.PLAIN, 36));
			g.drawString(options3[i], 266 + i * 250, 310);
		}
	}

	/**
	 * This selects the option that the mouse is hovered over.  Now that I can see
	 * what I did, I realize there's a better way to do this.  You might want to
	 * consider changing the innermost if statements to something like
	 * currentSelection1 = currentMouseover1.  The outer if statements are fine
	 * though.  As with previous instances of this, the mouse click will call on
	 * what the enter key does.
	 * 
	 * @param k The number of the key pressed.
	 */
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			if(menuSelection == 0){
				if(currentMouseover1 == 0){
					currentSelection1 = 0;
				}else if(currentMouseover1 == 1){
					currentSelection1 = 1;
				}
			}else if(menuSelection == 1){
				if(currentMouseover2 == 0){
					currentSelection2 = 0;
				}else if(currentMouseover2 == 1){
					currentSelection2 = 1;
				}else if(currentMouseover2 == 2){
					currentSelection2 = 2;
				}
			}else if(menuSelection == 2){
				if(currentMouseover3 == 0){
					gsm.states.pop();
				}else if(currentMouseover3 == 1){
					if(currentSelection1 > -1 && currentSelection1 < 2 && currentSelection2 > -1 && currentSelection2 < 3){
						gsm.states.push(new ChessGameState(gsm, currentSelection1, currentSelection2));
					}else{
						//Do nothing unless the user has actually selected options from the other two menus.
					}
				}
			}
		}
	}

	/**
	 * What happens when a key is released.
	 * 
	 * @param k The number of the key that has been released.
	 */
	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Keeps track of what options are being moused over at any given moment.
	 * Has an error where moving the mouse quickly enough leaves them moused
	 * over, even when they aren't.
	 * 
	 * @param x The x position of the mouse.
	 * @param y The y position of the mouse.
	 */
	@Override
	public void mouseMoved(int x, int y) {
		if(x >= 150 && x <= 400 && y >= 50 && y <= 210){
			menuSelection = 0;
			if(x >= 158 && x <= 310 && y >= 62 && y <= 90){
				currentMouseover1 = 0;
			}else if(x >= 158 && x <= 310 && y >= 112 && y <= 140){
				currentMouseover1 = 1;
			}else{
				currentMouseover1 = 2;
			}
		}else if(x >= 450 && x <= 700 && y >= 50 && y <= 210){
			menuSelection = 1;
			if(x >= 458 && x <= 560 && y >= 62 && y <= 90){
				currentMouseover2 = 0;
			}else if(x >= 458 && x <= 560 && y >= 112 && y <= 140){
				currentMouseover2 = 1;
			}else if(x >= 458 && x <= 560 && y >= 162 && y <= 190){
				currentMouseover2 = 2;
			}else{
				currentMouseover2 = 3;
			}
		}else if(x >= 240 && x <= 640 && y >= 250 && y <= 350){
			menuSelection = 2;
			if(x >= 266 && x <= 360 && y >= 282 && y <= 310){
				currentMouseover3 = 0;
			}else if(x >= 516 && x <= 610 && y >= 282 && y <= 310){
				currentMouseover3 = 1;
			}else{
				currentMouseover3 = 2;
			}
		}else{
			menuSelection = 3;
		}
		
		
	}

	/**
	 * Activated when the mouse is clicked.  Like on the previous menu, this
	 * will just activate the same thing that would happen if the enter key
	 * were pressed.
	 * 
	 * @param x The x position of the mouse.
	 * @param y The y position of the mouse.
	 */
	@Override
	public void mouseClicked(int x, int y) {
		this.keyPressed(KeyEvent.VK_ENTER);
	}

}
