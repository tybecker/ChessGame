package chess.gamestate;

import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager {
	
	//This is the stack of games states, starting with the main menu state.
	//When you wish to move states forward, push a state.  When you wish
	//to move states back, pop the current state.  I can't help but wonder
	//if there's a better way to do this.  I'll need to think on this a bit.
	public Stack<GameState> states;
	
	/**
	 * The constructor method, it makes the stack, then pushes on the main
	 * menu state, since that's the first one everyone will see.
	 */
	public GameStateManager(){
		states = new Stack<GameState>();
		states.push(new MenuState(this));
	}
	
	/**
	 * The tick method calls on anything the current state needs to do
	 * 60 times per second.  Basically this is for any continuous type
	 * of updating.
	 */
	public void tick(){
		states.peek().tick();
	}
	
	/**
	 * Makes the current gamestate draw to the GamePanel
	 * 
	 * @param g The graphics object from the GamePanel
	 */
	public void draw(Graphics g){
		states.peek().draw(g);
	}
	
	/**
	 * Activates key presses for the game state
	 * 
	 * @param k The number of the key that has been pressed.
	 */
	public void keyPressed(int k){
		states.peek().keyPressed(k);
	}
	
	/**
	 * Activates key releases for the game state
	 * 
	 * @param k The number of the key that has been released.
	 */
	public void keyReleased(int k){
		states.peek().keyReleased(k);
	}
	
	/**
	 * Activates mouse movement for the game state
	 * 
	 * @param x The x location of the cursor
	 * @param y The y location of the cursor
	 */
	public void mouseMoved(int x, int y){
		states.peek().mouseMoved(x, y);
	}
	
	/**
	 * Activates mouse clicks for the game state
	 * 
	 * @param x The x location of the cursor
	 * @param y The y location of the cursor
	 */
	public void mouseClicked(int x, int y){
		states.peek().mouseClicked(x, y);
	}
}
