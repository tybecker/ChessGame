package chess.main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

import chess.gamestate.GameStateManager;
import chess.resources.Images;


public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	//The width and height of the game panel.
	public static final int WIDTH = 900;
	public static final int HEIGHT = 700;
	
	//The thread that keeps the whole thing running.
	private Thread thread;
	private boolean isRunning = false;
	
	//The number of frames per second.  I don't need 60 FPS for a chess game,
	//but I'll admit, something tickles me about having this frame rate.
	private int FPS = 60;
	private long targetTime = 1000/FPS;
	
	//The game state manager, this is what determines what the game panel is
	//doing at any given point.
	private GameStateManager gsm;
	
	/**
	 * The constructor method, which sets the size, adds all the listeners, and
	 * starts everything.  Remember, instantiating the graphics goes here!
	 */
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		setFocusable(true);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		new Images();
		
		start();
	}
	
	/**
	 * The start method, begins the thread, and sets the game to running.
	 */
	private void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * The method invoked when the key is pressed.  Activates a method in the
	 * game state manager, which will pass it on to the gamestate.
	 * 
	 * @param e Information concerning the key pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * The method at the start of it all.  While it's running, this what
	 * keeps it doing things at a rate of 60 times a second.
	 */
	@Override
	public void run() {
		long start, elapsed, wait;
		
		gsm = new GameStateManager();
		
		while(isRunning) {
			start = System.nanoTime();
			
			tick();
			repaint();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if(wait <= 0){
				wait = 5;
			}
			
			try{
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Called by the run method 60 times per second.  Makes the gsm do
	 * its thing that often.  The gsm, again, does this to the game
	 * state currently active.
	 */
	public void tick(){
		gsm.tick();
		
		//Uncomment for testing the tick method.
		//System.out.println("Running");
	}
	
	/**
	 * Calls on the gsm to draw the current gamestate.
	 */
	public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			gsm.draw(g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	/**
	 * The event that activates whenever the mouse has been moved.
	 * 
	 * @param e The event that describes what has happened with the mouse.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e.getX(), e.getY());
	}

	/**
	 * The event that activates whenever someone clicks the mouse.
	 * 
	 * @param e The event that describes what has happened with the mouse.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e.getX(), e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
}
