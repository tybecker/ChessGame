package chess.main;

import java.awt.Graphics;
import java.awt.Point;

import chess.resources.Images;

public class Knight implements ChessPiece{

	//The colour of the piece.  0 is white, 1 is black.
	public int color;
			
	//The location refers to the square the piece is in
	public int xLocation;
	public int yLocation;
			
	//The coordinate refers the the pieces position on screen.
	//This is important for the draw method.
	public int xCoordinate;
	public int yCoordinate;

	/**
	 * The constructor method makes the piece, requiring the color, xLocation, and yLocation.
	 * Because the constructor can be called upon before there are coordinates to insert, they
	 * are set to 0.  The setPiece method for the square set with this piece will set the
	 * correct coordinates when invoked.
	 * 
	 * @param color The color of the piece, with 0 being white, and 1 being black.
	 * @param xLoc The xLocation of the square, a value from 0 to 7, being from left to right.
	 * @param yLoc The yLocation of the square, a value from 0 to 7, beinf from down to up.
	 */
	public Knight(int color, int xLoc, int yLoc){
		this.color = color;
		xLocation = xLoc;
		yLocation = yLoc;
		xCoordinate = 0;
		yCoordinate = 0;
	}
	
	/**
	 * A method for setting the color of the piece.
	 * 
	 * @param c The color of the piece, with 0 being white and 1 being black.
	 */
	@Override
	public void setColor(int c) {
		color = c;
	}

	/**
	 * A method for getting the color of the piece.
	 * 
	 * @return an integer representing the color of the piece, with 0 being
	 * white and 1 being black.
	 */
	@Override
	public int getColor() {
		return color;
	}

	/**
	 * This method is meant to be called on to get a list of moves that the
	 * knight can legally perform.  This method is primarily for the use of
	 * the AI.  The player will use the getMovePoints method.
	 * 
	 * @param board The board as it currently is laid out.
	 * @return an array of boards with the possible moves of this piece.
	 */
	@Override
	public ChessBoard[] getMoves(ChessBoard board) {
		ChessBoard[] moveSet = new ChessBoard[124];
		int currentMove = 0;
		
		//Position 1 of 8
		if(xLocation > 1 && yLocation > 0){
			if(board.getSquare(xLocation - 2, yLocation - 1).hasPiece()){
				if(board.getSquare(xLocation - 2, yLocation - 1).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation - 2, yLocation - 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation - 2, yLocation - 1).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		//Position 2 of 8
		if(xLocation > 0 && yLocation > 1){
			if(board.getSquare(xLocation - 1, yLocation - 2).hasPiece()){
				if(board.getSquare(xLocation - 1, yLocation - 2).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation - 1, yLocation - 2).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation - 1, yLocation - 2).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		//Position 3 of 8
		if(xLocation < 6 && yLocation > 0){
			if(board.getSquare(xLocation + 2, yLocation - 1).hasPiece()){
				if(board.getSquare(xLocation + 2, yLocation - 1).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation + 2, yLocation - 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation + 2, yLocation - 1).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		//Position 4 of 8
		if(xLocation < 7 && yLocation > 1){
			if(board.getSquare(xLocation + 1, yLocation - 2).hasPiece()){
				if(board.getSquare(xLocation + 1, yLocation - 2).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation + 1, yLocation - 2).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation + 1, yLocation - 2).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		//Position 5 of 8
		if(xLocation < 7 && yLocation < 6){
			if(board.getSquare(xLocation + 1, yLocation + 2).hasPiece()){
				if(board.getSquare(xLocation + 1, yLocation + 2).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation + 1, yLocation + 2).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation + 1, yLocation + 2).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		//Position 6 of 8
		if(xLocation < 6 && yLocation < 7){
			if(board.getSquare(xLocation + 2, yLocation + 1).hasPiece()){
				if(board.getSquare(xLocation + 2, yLocation + 1).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation + 2, yLocation + 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation + 2, yLocation + 1).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		//Position 7 of 8
		if(xLocation > 1 && yLocation < 7){
			if(board.getSquare(xLocation - 2, yLocation + 1).hasPiece()){
				if(board.getSquare(xLocation - 2, yLocation + 1).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation - 2, yLocation + 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation - 2, yLocation + 1).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		//Position 8 of 8
		if(xLocation > 0 && yLocation < 6){
			if(board.getSquare(xLocation - 1, yLocation + 2).hasPiece()){
				if(board.getSquare(xLocation - 1, yLocation + 2).getPiece().getColor() != color){
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation - 1, yLocation + 2).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}else{
				moveSet[currentMove] = board.copy();
				moveSet[currentMove].getSquare(xLocation - 1, yLocation + 2).setPiece(this.copy());
				moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
				currentMove++;
			}
		}
		
		ChessBoard[] returnArray = new ChessBoard[currentMove];
		
		for(int i = 0; i < currentMove; i++){
			returnArray[i] = moveSet[i];
		}
		
		return returnArray;
	}

	/**
	 * This method sets the position of the piece on the board, with 0,0 being
	 * the bottom left, and 7,7 being the top right.  The x position moves from
	 * left to right, while the y position moves from bottom to top.
	 * 
	 * @param x The x position you want the piece to move to.
	 * @param y The y position you want the piece to move to.
	 */
	@Override
	public void setPosition(int x, int y) {
		xLocation = x;
		yLocation = y;
	}

	/**
	 * A method for getting the x and y location of the piece.
	 * 
	 * @return a two space integer array, where the first spot is the x location,
	 * and the second is the y location.
	 */
	@Override
	public int[] getPosition() {
		int[] location = new int[2];
		location[0] = xLocation;
		location[1] = yLocation;
		return location;
	}

	/**
	 * A method for getting only the x position of the piece.
	 * 
	 * @return The x position of the piece.
	 */
	@Override
	public int getXPosition() {
		return xLocation;
	}

	/**
	 * A method for getting only the y position of the piece.
	 * 
	 * @return The y position of the piece.
	 */
	@Override
	public int getYPosition() {
		return yLocation;
	}

	/**
	 * The draw method, which should draw the piece at the location set for it
	 * by the square it belongs to.  Remember, use the xCoordinate and xCoordinate,
	 * and not the xLocation and the yLocation.  The coordinates show where it is
	 * on the screen, while the locations show where the piece is on the board.
	 * 
	 * @param g The graphics object which will draw to the game panel.
	 */
	@Override
	public void draw(Graphics g) {
		if(color == 0){
			g.drawImage(Images.pieces[4], xCoordinate, yCoordinate, 78, 78, null);
		}else{
			g.drawImage(Images.pieces[5], xCoordinate, yCoordinate, 78, 78, null);
		}
	}

	/**
	 * This method sets the coordinates, determining where the piece will show up
	 * on the screen.
	 * 
	 * @param x The x coordinate of the piece
	 * @param y The y coordinate of the piece
	 */
	@Override
	public void setCoordinates(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
	}

	/**
	 * This method gets the xCoordinate
	 * 
	 * @return An integer value for where the piece shows up on the screen from
	 * left to right.
	 */
	@Override
	public int getXCoordinate() {
		return xCoordinate;
	}

	/**
	 * This method gets the yCoordinate
	 * 
	 * @return An integer value for where the piece shows up on the screen from
	 * bottom to top.
	 */
	@Override
	public int getYCoordinate() {
		return yCoordinate;
	}

	/**
	 * Used to get a copy of this chess piece, for when copying a board, when you
	 * don't want the same piece on multiple boards.  Probably a good decision,
	 * because the way it's been designed, a piece can only be in one place at a
	 * time.
	 * 
	 * @return a copy of this chess piece.
	 */
	@Override
	public ChessPiece copy() {
		ChessPiece newKnight = new Knight(color, xLocation, yLocation);
		newKnight.setCoordinates(xCoordinate, yCoordinate);
		return newKnight;
	}

	/**
	 * A method returning an array of all the points which the piece could
	 * move to from its current location.  This is meant for use by the players,
	 * but could also be called upon by the AI in some cases.
	 * 
	 * @param board The board with its current layout.
	 * @return An array of all points to which the piece could move.
	 */
	@Override
	public Point[] getMovePoints(ChessBoard board) {
		
		Point[] movePoints = new Point[14];
		
		int currentPoint = 0;
		
		ChessSquare currentSquare;
		
		if(xLocation > 0 && yLocation > 1){
			if(board.getSquare(xLocation - 1, yLocation - 2).hasPiece()){
				if(board.getSquare(xLocation - 1, yLocation - 2).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation - 1, yLocation - 2);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation - 1, yLocation - 2);
				currentPoint++;
			}
		}
		
		if(xLocation > 1 && yLocation > 0){
			if(board.getSquare(xLocation - 2, yLocation - 1).hasPiece()){
				if(board.getSquare(xLocation - 2, yLocation - 1).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation - 2, yLocation - 1);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation - 2, yLocation - 1);
				currentPoint++;
			}
		}
		
		if(xLocation < 6 && yLocation > 0){
			if(board.getSquare(xLocation + 2, yLocation - 1).hasPiece()){
				if(board.getSquare(xLocation + 2, yLocation - 1).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation + 2, yLocation - 1);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation + 2, yLocation - 1);
				currentPoint++;
			}
		}
		
		if(xLocation < 7 && yLocation > 1){
			if(board.getSquare(xLocation + 1, yLocation - 2).hasPiece()){
				if(board.getSquare(xLocation + 1, yLocation - 2).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation + 1, yLocation - 2);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation + 1, yLocation - 2);
				currentPoint++;
			}
		}
		
		if(xLocation < 7 && yLocation < 6){
			if(board.getSquare(xLocation + 1, yLocation + 2).hasPiece()){
				if(board.getSquare(xLocation + 1, yLocation + 2).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation + 1, yLocation + 2);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation + 1, yLocation + 2);
				currentPoint++;
			}
		}
		
		if(xLocation < 6 && yLocation < 7){
			if(board.getSquare(xLocation + 2, yLocation + 1).hasPiece()){
				if(board.getSquare(xLocation + 2, yLocation + 1).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation + 2, yLocation + 1);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation + 2, yLocation + 1);
				currentPoint++;
			}
		}
		
		if(xLocation > 1 && yLocation < 7){
			if(board.getSquare(xLocation - 2, yLocation + 1).hasPiece()){
				if(board.getSquare(xLocation - 2, yLocation + 1).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation - 2, yLocation + 1);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation - 2, yLocation + 1);
				currentPoint++;
			}
		}
		
		if(xLocation > 0 && yLocation < 6){
			if(board.getSquare(xLocation - 1, yLocation + 2).hasPiece()){
				if(board.getSquare(xLocation -1, yLocation + 2).getPiece().getColor() != color){
					movePoints[currentPoint] = new Point(xLocation - 1, yLocation + 2);
					currentPoint++;
				}
			}else{
				movePoints[currentPoint] = new Point(xLocation - 1, yLocation + 2);
				currentPoint++;
			}
		}
		
		Point[] returnArray = new Point[currentPoint];
		
		for(int i = 0; i < returnArray.length; i++){
			returnArray[i] = movePoints[i];
		}
		
		return returnArray;
	}

	/**
	 * A string description of the piece.  Necessary whenever you need to know
	 * what type of piece you're dealing with.  Particularly important for the
	 * king so that he doesn't move into a threatened spot.
	 * 
	 *@return A string saying that this piece is a bishop.
	 */
	@Override
	public String getDescription() {
		return "knight";
	}

}
