package chess.main;

import java.awt.Color;
import java.awt.Graphics;

public class ChessSquare {

	//The chess square needs to know what piece is currently contained in it,
	//and what color the square is.
	public ChessPiece currentPiece;
	public Color squareColor;
	
	//These tell the square where it is in the board, with 0,0 being the bottom
	//left, and 7,7 being the top right.
	public int xSquare;
	public int ySquare;
	
	//These tell the square where it should draw itself on the screen.  0,0 is
	//the upper left.  Java... amirite? :)
	public int xCoor;
	public int yCoor;
	
	//This keeps track of whether the square is selected.
	public boolean selected;
	
	/**
	 * This constructor method simply makes the square in it default state.
	 * 
	 * @param c The color of the square, in this case represented by an
	 * actual color object.
	 * @param x The x position of the square, from 0 to 7
	 * @param y The y position of the square, from 0 to 7
	 */
	public ChessSquare(Color c, int x, int y){
		squareColor = c;
		xSquare = x;
		ySquare = y;
		xCoor = 0;
		yCoor = 0;
		selected = false;
	}
	
	/**
	 * The draw method.  It draws the square as it's own color, unless
	 * the square is selected, in which case the square is yellow.  Then
	 * it checks to see if it has a piece, and if it does it draws that
	 * piece.
	 * 
	 * @param g The graphics object, which allows you to draw to the game panel.
	 */
	public void draw(Graphics g){
		g.setColor(squareColor);
		if(selected){
			g.setColor(Color.YELLOW);
		}
		g.fillRect(xCoor, yCoor, 80, 80);
		g.setColor(Color.BLACK);
		g.drawRect(xCoor, yCoor, 80, 80);
		
		//Use this if you forget which square is what.  Otherwise, comment it out.
		//g.setColor(Color.BLUE);
		//g.drawString("x=" + xSquare + " y=" + ySquare, xCoor + 10, yCoor + 40);
		
		if(hasPiece()){
			getPiece().draw(g);
		}
	}
	
	/**
	 * This places a new piece in the square.  It also tells the piece
	 * it's new position on the board, and its coordinates on the screen.
	 * the +1 on the coordinates is there because the square is 80*80
	 * pixels, while the piece is 78*78 pixels.
	 * 
	 * @param c The piece being placed in the sqaure.
	 */
	public void setPiece(ChessPiece c){
		currentPiece = c;
		currentPiece.setCoordinates(xCoor + 1, yCoor + 1);
		currentPiece.setPosition(xSquare, ySquare);
	}
	
	/**
	 * Returns true if the square has a piece in it, and false if
	 * there is no piece.  Turns out this is a pretty important method
	 * if I don't want to be throwing ALL the null pointer exceptions.
	 * 
	 * @return the boolean return value for whether the square currently
	 * has a piece.
	 */
	public boolean hasPiece(){
		if(currentPiece == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Gets the chesspiece currently contained in the square.
	 * 
	 * @return The piece being requested when the method is invoked.
	 */
	public ChessPiece getPiece(){
		return currentPiece;
	}
	
	/**
	 * Sets the xCoordinate of the square.
	 * 
	 * @param x The left side of where the square is drawn.
	 */
	public void setXCoor(int x){
		xCoor = x;
	}
	
	/**
	 * Sets the yCoordinate of the square.
	 * 
	 * @param y The upper edge of where the square is drawn.
	 */
	public void setYCoor(int y){
		yCoor = y;
	}
	
	/**
	 * Gets the xCoordinate of the square.
	 * 
	 * @return The left edge of where the square is drawn.
	 */
	public int getXCoor(){
		return xCoor;
	}
	
	/**
	 * Gets the yCoordinate of the square.
	 * 
	 * @return The upper edge of where the square is drawn.
	 */
	public int getYCoor(){
		return yCoor;
	}
	
	/**
	 * Sets where from left to right the square is on the chess board.
	 * 
	 * @param x The x location of the square on the board.
	 */
	public void setXSquare(int x){
		xSquare = x;
	}
	
	/**
	 * Sets where from bottom to top the square is on the chess board.
	 * 
	 * @param x The y location of the square on the board.
	 */
	public void setYSquare(int y){
		ySquare = y;
	}
	
	/**
	 * Gets the x location of the square on the board.
	 * 
	 * @return A number from 0 to 7, representing the columns of the board
	 */
	public int getXSquare(){
		return xSquare;
	}
	
	/**
	 * Gets the y location of the square on the board.
	 * 
	 * @return A number from 0 to 7, representing the rows of the board
	 */
	public int getYSquare(){
		return ySquare;
	}
	
	/**
	 * Removes the piece from the square.  Needed when a piece is moved.
	 */
	public void removePiece(){
		currentPiece = null;
	}
	
	/**
	 * Toggles the selected status of a square between true and false
	 */
	public void toggleSelect(){
		if(selected){
			selected = false;
		}else{
			selected = true;
		}
	}
	
	/**
	 * Returns true if the square is selected, returns false otherwise.
	 * 
	 * @return The selection state of the square.
	 */
	public boolean isSelected(){
		return selected;
	}
	
	/**
	 * Sets the color of the square.
	 * 
	 * @param c The color of the square, as a Color object.
	 */
	public void setColor(Color c){
		squareColor = c;
	}
}
