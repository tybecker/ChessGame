package chess.main;

import java.awt.Graphics;
import java.awt.Point;

//This is the interface for the ChessPiece.  All chesspieces will need these methods.
//I think in the future I should use an abstract class for this.  There are some parts
//universal to chess pieces that could have been implemented here.
public interface ChessPiece {
	
	public void setColor(int c);
	
	public int getColor();
	
	public ChessBoard[] getMoves(ChessBoard board);
	
	public Point[] getMovePoints(ChessBoard board);
	
	public void setPosition(int x, int y);
	
	public int[] getPosition();
	
	public int getXPosition();
	
	public int getYPosition();
	
	public void setCoordinates(int x, int y);
	
	public int getXCoordinate();
	
	public int getYCoordinate();
	
	public void draw(Graphics g);
	
	public ChessPiece copy();
	
	public String getDescription();
}
