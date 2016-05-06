package chess.main;

import java.awt.Graphics;
import java.awt.Point;

import chess.resources.Images;

public class Queen implements ChessPiece{
	
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
	public Queen(int color, int xLoc, int yLoc){
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
	 * queen can legally perform.  This method is primarily for the use of
	 * the AI.  The player will use the getMovePoints method.
	 * 
	 * @param board The board as it currently is laid out.
	 * @return an array of boards with the possible moves of this piece.
	 */
	@Override
	public ChessBoard[] getMoves(ChessBoard board) {
		ChessBoard[] moveSet = new ChessBoard[124];
		int currentMove = 0;
		
		ChessSquare currentSquare;
		
		ChessBoard movedBoard;
		
		//The corrected statement for moving down and to the left.
		if(xLocation > 0 && yLocation > 0){
			currentSquare = board.getSquare(xLocation - 1, yLocation - 1);
			
			while(currentSquare.getXSquare() > 0 && currentSquare.getYSquare() > 0 && board.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() - 1, currentSquare.getYSquare() - 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
			}
		}
		
		//Corrected statement for moving down and right.
		if(xLocation < 7 && yLocation > 0){
			currentSquare = board.getSquare(xLocation + 1, yLocation - 1);
			
			while(currentSquare.getXSquare() < 7 && currentSquare.getYSquare() > 0 && board.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() + 1, currentSquare.getYSquare() - 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
			}
		}
		
		//Corrected statement for moving up and left.
		if(xLocation > 0 && yLocation < 7){
			currentSquare = board.getSquare(xLocation - 1, yLocation + 1);
				
			while(currentSquare.getXSquare() > 0 && currentSquare.getYSquare() < 7 && board.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() - 1, currentSquare.getYSquare() + 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
			}
		}
		
		//Corrected statement for moving up and left.
		if(xLocation < 7 && yLocation < 7){
			currentSquare = board.getSquare(xLocation + 1, yLocation + 1);
				
			while(currentSquare.getXSquare() < 7 && currentSquare.getYSquare() < 7 && board.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() + 1, currentSquare.getYSquare() + 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
			}
		}
		
		//For moving left, if there is left to move.
		if(xLocation > 0){
			currentSquare = board.getSquare(xLocation - 1, yLocation);
			
			//Add moves as long as the space to the left is empty, and not the final square.
			while(currentSquare.getXSquare() > 0 && board.getSquare(currentSquare.getXSquare(), yLocation).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() - 1, yLocation);
			}
			
			//If it's at the zero location, check for a piece, and if one exists only move
			//the rook left if the piece is of the opposite colour.
			if(currentSquare.getXSquare() == 0){
				if(currentSquare.hasPiece()){
					if(color != currentSquare.getPiece().getColor()){
						movedBoard = board.copy();
						movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						if(!kingThreatened(movedBoard)){
							moveSet[currentMove] = movedBoard;
							currentMove++;
						}
					}
				}else{
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				if(color != currentSquare.getPiece().getColor()){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}
		}
		
		//For moving right
		if(xLocation < 7){
			
			currentSquare = board.getSquare(xLocation + 1, yLocation);
			
			while(currentSquare.getXSquare() < 7 && board.getSquare(currentSquare.getXSquare(), yLocation).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() + 1, yLocation);
			}
			
			if(currentSquare.getXSquare() == 7){
				if(currentSquare.hasPiece()){
					if(color != currentSquare.getPiece().getColor()){
						movedBoard = board.copy();
						movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						if(!kingThreatened(movedBoard)){
							moveSet[currentMove] = movedBoard;
							currentMove++;
						}
					}
				}else{
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				if(color != currentSquare.getPiece().getColor()){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), yLocation).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}
		}
		
		//For moving down
		if(yLocation > 0){
			
			currentSquare = board.getSquare(xLocation, yLocation - 1);
			
			while(currentSquare.getYSquare() > 0 && board.getSquare(xLocation, currentSquare.getYSquare()).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(xLocation, currentSquare.getYSquare() - 1);
			}
			
			if(currentSquare.getYSquare() == 0){
				if(currentSquare.hasPiece()){
					if(color != currentSquare.getPiece().getColor()){
						movedBoard = board.copy();
						movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						if(!kingThreatened(movedBoard)){
							moveSet[currentMove] = movedBoard;
							currentMove++;
						}
					}
				}else{
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				if(color != currentSquare.getPiece().getColor()){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}
		}
		
		//For moving up
		if(yLocation < 7){
			
			currentSquare = board.getSquare(xLocation, yLocation + 1);
			
			while(currentSquare.getYSquare() < 7 && board.getSquare(xLocation, currentSquare.getYSquare()).hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					moveSet[currentMove] = movedBoard;
					currentMove++;
				}
				currentSquare = board.getSquare(xLocation, currentSquare.getYSquare() + 1);
			}
			
			if(currentSquare.getYSquare() == 7){
				if(currentSquare.hasPiece()){
					if(color != currentSquare.getPiece().getColor()){
						movedBoard = board.copy();
						movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
						movedBoard.getSquare(xLocation, yLocation).removePiece();
						if(!kingThreatened(movedBoard)){
							moveSet[currentMove] = movedBoard;
							currentMove++;
						}
					}
				}else{
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						moveSet[currentMove] = movedBoard;
						currentMove++;
					}
				}
			}else{
				if(color != currentSquare.getPiece().getColor()){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
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
			g.drawImage(Images.pieces[10], xCoordinate, yCoordinate, 78, 78, null);
		}else{
			g.drawImage(Images.pieces[11], xCoordinate, yCoordinate, 78, 78, null);
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
		ChessPiece newQueen = new Queen(color, xLocation, yLocation);
		newQueen.setCoordinates(xCoordinate, yCoordinate);
		return newQueen;
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
		Point[] movePoints = new Point[28];
		
		int currentPoint = 0;
		
		ChessSquare currentSquare;
		
		ChessBoard movedBoard;
		
		//Statements for moving left
		if(xLocation > 0){
			currentSquare = board.getSquare(xLocation - 1, yLocation);
			while(currentSquare.getXSquare() > 0 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() - 1, currentSquare.getYSquare());
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		//Statements for moving right
		if(xLocation < 7){
			currentSquare = board.getSquare(xLocation + 1, yLocation);
			while(currentSquare.getXSquare() < 7 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() + 1, currentSquare.getYSquare());
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		//Statements for moving down
		if(yLocation > 0){
			currentSquare = board.getSquare(xLocation, yLocation - 1);
			while(currentSquare.getYSquare() > 0 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare() - 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		//Statements for moving up.
		if(yLocation < 7){
			currentSquare = board.getSquare(xLocation, yLocation + 1);
			while(currentSquare.getYSquare() < 7 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare() + 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		//Get all movements down and to the left.
		if(xLocation > 0 && yLocation > 0){
			currentSquare = board.getSquare(xLocation - 1, yLocation - 1);
			while(currentSquare.getXSquare() > 0 && currentSquare.getYSquare() > 0 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() - 1, currentSquare.getYSquare() - 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		//Get all movements down and to the right.
		if(xLocation < 7 && yLocation > 0){
			currentSquare = board.getSquare(xLocation + 1, yLocation - 1);
			while(currentSquare.getXSquare() < 7 && currentSquare.getYSquare() > 0 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() + 1, currentSquare.getYSquare() - 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		//Get all movements up and to the left.
		if(xLocation > 0 && yLocation < 7){
			currentSquare = board.getSquare(xLocation - 1, yLocation + 1);
			while(currentSquare.getXSquare() > 0 && currentSquare.getYSquare() < 7 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() - 1, currentSquare.getYSquare() + 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		//Get all movements up and to the right.
		if(xLocation < 7 && yLocation < 7){
			currentSquare = board.getSquare(xLocation + 1, yLocation + 1);
			while(currentSquare.getXSquare() < 7 && currentSquare.getYSquare() < 7 && currentSquare.hasPiece() == false){
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
				currentSquare = board.getSquare(currentSquare.getXSquare() + 1, currentSquare.getYSquare() + 1);
			}
			if(currentSquare.hasPiece()){
				if(currentSquare.getPiece().getColor() != color){
					movedBoard = board.copy();
					movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
					movedBoard.getSquare(xLocation, yLocation).removePiece();
					if(!kingThreatened(movedBoard)){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}
			}else{
				movedBoard = board.copy();
				movedBoard.getSquare(currentSquare.getXSquare(), currentSquare.getYSquare()).setPiece(this.copy());
				movedBoard.getSquare(xLocation, yLocation).removePiece();
				if(!kingThreatened(movedBoard)){
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
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
		return "queen";
	}

	/**
	 * This will find out if the king is threatened in any given case.  This
	 * does not strike me as the best way to do things.  Still, whatever
	 * serves the purpose.
	 * 
	 * @param board The board with the layout to be considered.
	 * @return Whether or not the king is threatened in this layout.
	 */
	public boolean kingThreatened(ChessBoard board){
		King king = null;
		
		//First... find the king.
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board.getSquare(i, j).hasPiece()){
					if(board.getSquare(i, j).getPiece().getColor() == color && board.getSquare(i, j).getPiece().getDescription().equalsIgnoreCase("king")){
						king = (King)board.getSquare(i, j).getPiece();
					}
				}
			}
		}
		
		if(king != null){
			if(king.isThreatened(board, king.getXPosition(), king.getYPosition())){
				return true;
			}
		}
		return false;
	}
}
