package chess.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import chess.resources.Images;

public class Pawn implements ChessPiece{

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
	public Pawn(int color, int xLoc, int yLoc){
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
	 * Gets an array of boards, each with a possible move for the piece in question.
	 * <p>
	 * This method takes a chessboard, and using the known location of the piece,
	 * calculates all of the possible moves, excluding those blocked by other pieces
	 * of the edges of the board, and then returns an array of boards with the new
	 * positions on them.  It's important that you copy the current board before
	 * creating the new position, or you'll just end up with an array of the same board
	 * over and over, and possibly not with a plausible move on it.
	 * <p>
	 * ALSO!  Clone the piece before moving it, or you could have a problem with
	 * the xLocation and yLocation variables.  You've been warned!
	 * <p>
	 * @param the board you are starting with, as currently positioned.
	 * @return an array of boards, each with a different move made by this piece.
	 */
	@Override
	public ChessBoard[] getMoves(ChessBoard board) {
		ChessBoard[] moveSet = new ChessBoard[124];
		int currentMove = 0;
		
		ChessBoard movedBoard;
		
		ChessSquare currentSquare;
		
		//Moves for white
		if(color == 0 && yLocation > 0){
			//Standard move for white.
			if(board.getSquare(xLocation, yLocation - 1).hasPiece() == false){
				//When the yLocation is one, you'll need a special event for moving forwards
				//to turn the pawn into another piece.
				if(yLocation == 1){
					movedBoard = board.copy();
					movedBoard.getSquare(xLocation, yLocation - 1).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}else{
					movedBoard = board.copy();
					movedBoard.getSquare(xLocation, yLocation - 1).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
			}
			//Two space move from initial position
			if(yLocation == 6 && board.getSquare(xLocation, yLocation - 1).hasPiece() == false && board.getSquare(xLocation, yLocation - 2).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(xLocation, yLocation - 2).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				moveSet[currentMove] = movedBoard;
				currentMove++;
			}
			//Forward left move, should only be available if an enemy piece is in that place.
			if(xLocation > 0 && board.getSquare(xLocation - 1, yLocation - 1).hasPiece()){
				if(board.getSquare(xLocation - 1, yLocation - 1).getPiece().getColor() == 1){
					//When the yLocation is one, you'll need a special event for moving forwards
					//to turn the pawn into another piece.
					if(yLocation == 1){
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation - 1, yLocation - 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}else{
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation - 1, yLocation - 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
					
				}
			}
			//Forward right move, should only be available if an enemy piece is in that place.
			if(xLocation < 7 && board.getSquare(xLocation + 1, yLocation - 1).hasPiece()){
				if(board.getSquare(xLocation + 1, yLocation - 1).getPiece().getColor() == 1){
					//When the yLocation is one, you'll need a special event for moving forwards
					//to turn the pawn into another piece.
					if(yLocation == 1){
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation + 1, yLocation - 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}else{
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation + 1, yLocation - 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}
		}else if(color == 1 && yLocation < 7){
			if(board.getSquare(xLocation, yLocation + 1).hasPiece() == false){
				//When the yLocation is six, you'll need a special event for moving forwards
				//to turn the pawn into another piece.
				if(yLocation == 6){
					movedBoard = board.copy();
					movedBoard.getSquare(xLocation, yLocation + 1).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}else{
					movedBoard = board.copy();
					movedBoard.getSquare(xLocation, yLocation + 1).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
			}
			if(yLocation == 1 && board.getSquare(xLocation, yLocation + 1).hasPiece() == false && board.getSquare(xLocation, yLocation + 2).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(xLocation, yLocation + 2).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				moveSet[currentMove] = movedBoard;
				currentMove++;
			}
			if(xLocation > 0 && board.getSquare(xLocation - 1, yLocation + 1).hasPiece()){
				if(board.getSquare(xLocation - 1, yLocation + 1).getPiece().getColor() == 0){
					//When the yLocation is six, you'll need a special event for moving forwards
					//to turn the pawn into another piece.
					if(yLocation == 6){
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation - 1, yLocation + 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}else{
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation - 1, yLocation + 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}
			if(xLocation < 7 && board.getSquare(xLocation + 1, yLocation + 1).hasPiece()){
				if(board.getSquare(xLocation + 1, yLocation + 1).getPiece().getColor() == 0){
					//When the yLocation is six, you'll need a special event for moving forwards
					//to turn the pawn into another piece.
					if(yLocation == 6){
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation + 1, yLocation + 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}else{
						movedBoard = board.copy();
						movedBoard.getSquare(xLocation + 1, yLocation + 1).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
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
		// TODO Auto-generated method stub
		return xLocation;
	}

	/**
	 * A method for getting only the y position of the piece.
	 * 
	 * @return The y position of the piece.
	 */
	@Override
	public int getYPosition() {
		// TODO Auto-generated method stub
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
			g.drawImage(Images.pieces[0], xCoordinate, yCoordinate, 78, 78, null);
		}else{
			g.drawImage(Images.pieces[1], xCoordinate, yCoordinate, 78, 78, null);
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
		ChessPiece newPawn = new Pawn(color, xLocation, yLocation);
		newPawn.setCoordinates(xCoordinate, yCoordinate);
		return newPawn;
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
		
		Point[] movePoints = new Point[4];
		
		int currentPoint = 0;
		
		ChessSquare currentSquare;
		
		if(color == 0){
			if(yLocation > 0){
				if(board.getSquare(xLocation, yLocation - 1).hasPiece() == false){
					movePoints[currentPoint] = new Point(xLocation, yLocation - 1);
					currentPoint++;
					if(yLocation == 6){
						if(board.getSquare(xLocation, yLocation - 2).hasPiece() == false){
							movePoints[currentPoint] = new Point(xLocation, yLocation - 2);
							currentPoint++;
						}
					}
				}
				if(xLocation < 7){
					if(board.getSquare(xLocation + 1, yLocation - 1).hasPiece()){
						if(board.getSquare(xLocation + 1, yLocation - 1).getPiece().getColor() != color){
							movePoints[currentPoint] = new Point(xLocation + 1, yLocation - 1);
							currentPoint++;
						}
					}
				}
				if(xLocation > 0){
					if(board.getSquare(xLocation - 1, yLocation - 1).hasPiece()){
						if(board.getSquare(xLocation - 1, yLocation - 1).getPiece().getColor() != color){
							movePoints[currentPoint] = new Point(xLocation - 1, yLocation - 1);
							currentPoint++;
						}
					}
				}
			}
		}else if(color == 1){
			if(yLocation < 7){
				if(board.getSquare(xLocation, yLocation + 1).hasPiece() == false){
					movePoints[currentPoint] = new Point(xLocation, yLocation + 1);
					currentPoint++;
					if(yLocation == 1){
						if(board.getSquare(xLocation, yLocation + 2).hasPiece() == false){
							movePoints[currentPoint] = new Point(xLocation, yLocation + 2);
							currentPoint++;
						}
					}
				}
				if(xLocation < 7){
					if(board.getSquare(xLocation + 1, yLocation + 1).hasPiece()){
						if(board.getSquare(xLocation + 1, yLocation + 1).getPiece().getColor() != color){
							movePoints[currentPoint] = new Point(xLocation + 1, yLocation + 1);
							currentPoint++;
						}
					}
				}
				if(xLocation > 0){
					if(board.getSquare(xLocation - 1, yLocation + 1).hasPiece()){
						if(board.getSquare(xLocation - 1, yLocation + 1).getPiece().getColor() != color){
							movePoints[currentPoint] = new Point(xLocation - 1, yLocation + 1);
							currentPoint++;
						}
					}
				}
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
		return "pawn";
	}
}
