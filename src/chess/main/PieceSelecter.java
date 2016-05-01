package chess.main;

import java.awt.Color;
import java.awt.Graphics;

public class PieceSelecter {
	
	//A series of squares containing pieces.
	public ChessSquare[] squares;
	
	//The x and y coordinates, indicating where the selector should be drawn.
	public int xCoor;
	public int yCoor;
	
	//The colors of the square and pieces, 0 being white, 1 being black.
	public int pieceColor;
	public int squareColor;
	
	/**
	 * The constructor method, which creates five squares, and a piece for
	 * each one.  Only five, since the king cannot be copied (though that
	 * could make for an interesting game).
	 */
	public PieceSelecter(){
		squares = new ChessSquare[5];
		squares[0] = new ChessSquare(Color.WHITE,0,0);
		squares[1] = new ChessSquare(Color.WHITE,0,0);
		squares[2] = new ChessSquare(Color.WHITE,0,0);
		squares[3] = new ChessSquare(Color.WHITE,0,0);
		squares[4] = new ChessSquare(Color.WHITE,0,0);

		squares[0].setPiece(new Pawn(pieceColor,0,0));
		squares[1].setPiece(new Rook(pieceColor,0,0));
		squares[2].setPiece(new Knight(pieceColor,0,0));
		squares[3].setPiece(new Bishop(pieceColor,0,0));
		squares[4].setPiece(new Queen(pieceColor,0,0));
		
		xCoor = 0;
		yCoor = 0;
		pieceColor = 0;
	}
	
	/**
	 * Sets the coordinates of every square, based on the upper left corner
	 * of the selecter.
	 * 
	 * @param x The left edge of the selecter.
	 * @param y The upper edge of the selecter.
	 */
	public void setCoordinates(int x, int y){
		xCoor = x;
		yCoor = y;
		
		squares[0].setXCoor(x);
		squares[1].setXCoor(x);
		squares[2].setXCoor(x);
		squares[3].setXCoor(x);
		squares[4].setXCoor(x);
		
		squares[0].setYCoor(y);
		squares[1].setYCoor(y + 80);
		squares[2].setYCoor(y + 160);
		squares[3].setYCoor(y + 240);
		squares[4].setYCoor(y + 320);
		
		squares[0].getPiece().setCoordinates(x, y);
		squares[1].getPiece().setCoordinates(x, y + 80);
		squares[2].getPiece().setCoordinates(x, y + 160);
		squares[3].getPiece().setCoordinates(x, y + 240);
		squares[4].getPiece().setCoordinates(x, y + 320);
	}
	
	
	/**
	 * This sets the color of the pieces and the squares of the selecter, matching
	 * the color to the color of the player.
	 * <p>
	 * Note, you screwed up this method a little to get it to work with the current
	 * ChessGameState class.  Remember to unscrew it if you fix the ChessGameState
	 * class.
	 * 
	 * @param c The color of the player.
	 */
	public void setPieceColor(int c){
		pieceColor = c;
		if(pieceColor == 1){
			pieceColor = 0;
		}else{
			pieceColor = 1;
		}
		if(pieceColor == 1){
			squareColor = 1;
		}else{
			squareColor = 0;
		}
		Color sColor = Color.WHITE;
		Color pColor = Color.BLACK;
		if(pieceColor == 0){
			sColor = Color.BLACK;
			pColor = Color.WHITE;
		}
		squares[0].setColor(sColor);
		squares[1].setColor(sColor);
		squares[2].setColor(sColor);
		squares[3].setColor(sColor);
		squares[4].setColor(sColor);
		
		squares[0].getPiece().setColor(pieceColor);
		squares[1].getPiece().setColor(pieceColor);
		squares[2].getPiece().setColor(pieceColor);
		squares[3].getPiece().setColor(pieceColor);
		squares[4].getPiece().setColor(pieceColor);
	}
	
	/**
	 * The draw method of the selector.  It just calls on the draw methods of
	 * the squares.
	 * 
	 * @param g The graphics object, allowing the method to draw to the game panel.
	 */
	public void draw(Graphics g){
		for(int i = 0; i < squares.length; i++){
			squares[i].draw(g);
		}
	}
	
	/**
	 * When the selecter is clicked on, this returns the piece that was selected.
	 * 
	 * @param x The x coordinate where the mouse was clicked.
	 * @param y The y coordinate where the mouse was clicked.
	 * @return The piece that's been selected.
	 */
	public ChessPiece getSelectedPiece(int x, int y){
		ChessPiece returnPiece = null;
		if(x > squares[0].getXCoor() && x <  squares[0].getXCoor() + 80 && y > squares[0].getYCoor() && y < squares[0].getYCoor() + 80){
			returnPiece = new Pawn(pieceColor, 0, 0);
		}
		if(x > squares[1].getXCoor() && x <  squares[1].getXCoor() + 80 && y > squares[1].getYCoor() && y < squares[1].getYCoor() + 80){
			returnPiece = new Rook(pieceColor, 0, 0);
		}
		if(x > squares[2].getXCoor() && x <  squares[2].getXCoor() + 80 && y > squares[2].getYCoor() && y < squares[2].getYCoor() + 80){
			returnPiece = new Knight(pieceColor, 0, 0);
		}
		if(x > squares[3].getXCoor() && x <  squares[3].getXCoor() + 80 && y > squares[3].getYCoor() && y < squares[3].getYCoor() + 80){
			returnPiece = new Bishop(pieceColor, 0, 0);
		}
		if(x > squares[4].getXCoor() && x <  squares[4].getXCoor() + 80 && y > squares[4].getYCoor() && y < squares[4].getYCoor() + 80){
			returnPiece = new Queen(pieceColor, 0, 0);
		}
		return returnPiece;
	}
	
	/**
	 * Gets the left edge of the selecter.
	 * 
	 * @return The left edge of the selecter.
	 */
	public int getXCoor(){
		return xCoor;
	}
	
	/**
	 * Gets the upper edge of the selecter.
	 * 
	 * @return The upper edge of the selecter.
	 */
	public int getYCoor(){
		return yCoor;
	}
}
