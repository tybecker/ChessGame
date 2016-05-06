package chess.main;

import java.awt.Graphics;
import java.awt.Point;

import chess.resources.Images;

public class King implements ChessPiece{

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
	public King(int color, int xLoc, int yLoc){
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
		
		ChessSquare currentSquare;
		
		//Move the king left
		if(xLocation > 0){
			if(!isThreatened(board, xLocation - 1, yLocation)){
				if(board.getSquare(xLocation - 1, yLocation).hasPiece()){
					if(board.getSquare(xLocation - 1, yLocation).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation - 1, yLocation).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation - 1, yLocation).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}
		}
		
		//Move the king down
		if(yLocation > 0){
			if(!isThreatened(board, xLocation, yLocation - 1)){
				if(board.getSquare(xLocation, yLocation - 1).hasPiece()){
					if(board.getSquare(xLocation, yLocation - 1).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation, yLocation - 1).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation, yLocation - 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}
		}
		
		//Move king right
		if(xLocation < 7){
			if(!isThreatened(board, xLocation + 1, yLocation)){
				if(board.getSquare(xLocation + 1, yLocation).hasPiece()){
					if(board.getSquare(xLocation + 1, yLocation).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation + 1, yLocation).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation + 1, yLocation).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}
		}
		
		//Move king up
		if(yLocation < 7){
			if(!isThreatened(board, xLocation, yLocation + 1)){
				if(board.getSquare(xLocation, yLocation + 1).hasPiece()){
					if(board.getSquare(xLocation, yLocation + 1).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation, yLocation + 1).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation, yLocation + 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}
		}
		
		//Move king down and left
		if(xLocation > 0 && yLocation > 0){
			if(!isThreatened(board, xLocation - 1, yLocation - 1)){
				if(board.getSquare(xLocation - 1, yLocation - 1).hasPiece()){
					if(board.getSquare(xLocation - 1, yLocation - 1).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation - 1, yLocation - 1).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation - 1, yLocation - 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}
		}
		
		//Move the king up and left
		if(xLocation > 0 && yLocation < 7){
			if(!isThreatened(board, xLocation - 1, yLocation + 1)){
				if(board.getSquare(xLocation - 1, yLocation + 1).hasPiece()){
					if(board.getSquare(xLocation - 1, yLocation + 1).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation - 1, yLocation + 1).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation - 1, yLocation + 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}
		}
		
		//Move the king down and right
		if(xLocation < 7 && yLocation > 0){
			if(!isThreatened(board, xLocation + 1, yLocation - 1)){
				if(board.getSquare(xLocation + 1, yLocation - 1).hasPiece()){
					if(board.getSquare(xLocation + 1, yLocation - 1).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation + 1, yLocation - 1).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation + 1, yLocation - 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
				}
			}
		}
		
		//Move the king up and right
		if(xLocation < 7 && yLocation < 7){
			if(!isThreatened(board, xLocation + 1, yLocation + 1)){
				if(board.getSquare(xLocation + 1, yLocation + 1).hasPiece()){
					if(board.getSquare(xLocation + 1, yLocation + 1).getPiece().getColor() != color){
						moveSet[currentMove] = board.copy();
						moveSet[currentMove].getSquare(xLocation + 1, yLocation + 1).setPiece(this.copy());
						moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
						currentMove++;
					}
				}else{
					moveSet[currentMove] = board.copy();
					moveSet[currentMove].getSquare(xLocation + 1, yLocation + 1).setPiece(this.copy());
					moveSet[currentMove].getSquare(xLocation, yLocation).removePiece();
					currentMove++;
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
	 * This method looks at the board and the specified location.  It returns true
	 * if the sqaure is threatened, and false if the square is unthreatened.  In
	 * this context, it should be called on to ensure that the king can't move
	 * into a threatened square.
	 * 
	 * Bug to be fixed:  Currently considers the board as it is.  If the king moves
	 * to a space covered by the king in its current location, then it is not
	 * considered threatened.
	 * 
	 * @param board The board with its layout.
	 * @param xSquare The x number of the square, from 0 to 7, left to right
	 * @param ySquare The x number of the square, from 0 to 7, bottom to top
	 * @return A boolean value, true if the square is threatened, false otherwise.
	 */
	public boolean isThreatened(ChessBoard inputBoard, int xSquare, int ySquare){
		ChessBoard board = inputBoard.copy();
		board.getSquare(xLocation, yLocation).removePiece();
		ChessSquare square = board.getSquare(xSquare, ySquare);
		
		//Logic for checking for enemy pawns
		if(color == 0){
			if(xSquare > 0 && ySquare > 0){
				if(board.getSquare(xSquare - 1, ySquare - 1).hasPiece()){
					if(board.getSquare(xSquare - 1, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("pawn") && board.getSquare(xSquare - 1, ySquare - 1).getPiece().getColor() == 1){
						return true;
					}
				}
			}
			if(xSquare < 7 && ySquare > 0){
				if(board.getSquare(xSquare + 1, ySquare - 1).hasPiece()){
					if(board.getSquare(xSquare + 1, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("pawn") && board.getSquare(xSquare + 1, ySquare - 1).getPiece().getColor() == 1){
						return true;
					}
				}
			}
		}else{
			if(xSquare > 0 && ySquare < 7){
				if(board.getSquare(xSquare - 1, ySquare + 1).hasPiece()){
					if(board.getSquare(xSquare - 1, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("pawn") && board.getSquare(xSquare - 1, ySquare + 1).getPiece().getColor() == 0){
						return true;
					}
				}
			}
			if(xSquare < 7 && ySquare < 7){
				if(board.getSquare(xSquare + 1, ySquare + 1).hasPiece()){
					if(board.getSquare(xSquare + 1, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("pawn") && board.getSquare(xSquare + 1, ySquare + 1).getPiece().getColor() == 0){
						return true;
					}
				}
			}
		}
		
		//Logic for checking for enemy knights
		if(xSquare > 0 && ySquare > 1){
			if(board.getSquare(xSquare - 1, ySquare - 2).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare - 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 1, ySquare - 2).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare > 1 && ySquare > 0){
			if(board.getSquare(xSquare - 2, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare - 2, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 2, ySquare - 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare < 6 && ySquare > 0){
			if(board.getSquare(xSquare + 2, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare + 2, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 2, ySquare - 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare > 1){
			if(board.getSquare(xSquare + 1, ySquare - 2).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare - 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 1, ySquare - 2).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare < 6){
			if(board.getSquare(xSquare + 1, ySquare + 2).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare + 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 1, ySquare + 2).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare < 6 && ySquare < 7){
			if(board.getSquare(xSquare + 2, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare + 2, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 2, ySquare + 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare > 1 && ySquare < 7){
			if(board.getSquare(xSquare - 2, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare - 2, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 2, ySquare + 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare > 0 && ySquare < 6){
			if(board.getSquare(xSquare - 1, ySquare + 2).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare + 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 1, ySquare + 2).getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Logic for checking for enemy rooks and queens
		
		//Check left
		if(xSquare > 0){
			square = board.getSquare(xSquare - 1, ySquare);
			while(square.getXSquare() > 0 && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare() - 1, square.getYSquare());
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Check right
		if(xSquare < 7 ){
			square = board.getSquare(xSquare + 1, ySquare);
			while(square.getXSquare() < 7  && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare() + 1, square.getYSquare());
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Check up
		if(ySquare < 7 ){
			square = board.getSquare(xSquare, ySquare + 1);
			while(square.getYSquare() < 7  && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare(), square.getYSquare() + 1);
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Check down
		if(ySquare > 0 ){
			square = board.getSquare(xSquare, ySquare - 1);
			while(square.getYSquare() > 0  && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare(), square.getYSquare() - 1);
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Logic for checking for enemy bishops and queens
		
		//Check up right
		if(xSquare < 7 && ySquare < 7){
			square = board.getSquare(xSquare + 1, ySquare + 1);
			while(square.getXSquare() < 7 && square.getYSquare() < 7 && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare() + 1, square.getYSquare() + 1);
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Check up left
		if(xSquare > 0 && ySquare < 7){
			square = board.getSquare(xSquare - 1, ySquare + 1);
			while(square.getXSquare() > 0 && square.getYSquare() < 7 && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare() - 1, square.getYSquare() + 1);
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Check down right
		if(xSquare < 7 && ySquare > 0){
			square = board.getSquare(xSquare + 1, ySquare - 1);
			while(square.getXSquare() < 7 && square.getYSquare() > 0 && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare() + 1, square.getYSquare() - 1);
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Check down left
		if(xSquare > 0 && ySquare > 0){
			square = board.getSquare(xSquare - 1, ySquare - 1);
			while(square.getXSquare() > 0 && square.getYSquare() > 0 && square.hasPiece() == false){
				square = board.getSquare(square.getXSquare() - 1, square.getYSquare() - 1);
			}
			if(square.hasPiece()){
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		//Logic for checking for enemy kings
		if(xSquare > 0){
			if(board.getSquare(xSquare - 1, ySquare).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare - 1, ySquare).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare < 7){
			if(board.getSquare(xSquare + 1, ySquare).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare + 1, ySquare).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(ySquare > 0){
			if(board.getSquare(xSquare, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare, ySquare - 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(ySquare < 7){
			if(board.getSquare(xSquare, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare, ySquare + 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare > 0 && ySquare > 0){
			if(board.getSquare(xSquare - 1, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare - 1, ySquare - 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare > 0 && ySquare < 7){
			if(board.getSquare(xSquare - 1, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare - 1, ySquare + 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare > 0){
			if(board.getSquare(xSquare + 1, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare + 1, ySquare - 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare < 7){
			if(board.getSquare(xSquare + 1, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare + 1, ySquare + 1).getPiece().getColor() != color){
					return true;
				}
			}
		}
		
		return false;
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
			g.drawImage(Images.pieces[8], xCoordinate, yCoordinate, 78, 78, null);
		}else{
			g.drawImage(Images.pieces[9], xCoordinate, yCoordinate, 78, 78, null);
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
		ChessPiece newKing = new King(color, xLocation, yLocation);
		newKing.setCoordinates(xCoordinate, yCoordinate);
		return newKing;
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
		
		if(xLocation > 0){
			currentSquare = board.getSquare(xLocation - 1, yLocation);
			if(!isThreatened(board, xLocation - 1, yLocation)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		if(yLocation > 0){
			currentSquare = board.getSquare(xLocation, yLocation - 1);
			if(!isThreatened(board, xLocation, yLocation - 1)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		if(xLocation < 7){
			currentSquare = board.getSquare(xLocation + 1, yLocation);
			if(!isThreatened(board, xLocation + 1, yLocation)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		if(yLocation < 7){
			currentSquare = board.getSquare(xLocation, yLocation + 1);
			if(!isThreatened(board, xLocation, yLocation + 1)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		if(xLocation > 0 && yLocation > 0){
			currentSquare = board.getSquare(xLocation - 1, yLocation - 1);
			if(!isThreatened(board, xLocation - 1, yLocation - 1)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		if(xLocation > 0 && yLocation < 7){
			currentSquare = board.getSquare(xLocation - 1, yLocation + 1);
			if(!isThreatened(board, xLocation - 1, yLocation + 1)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		if(xLocation < 7 && yLocation > 0){
			currentSquare = board.getSquare(xLocation + 1, yLocation - 1);
			if(!isThreatened(board, xLocation + 1, yLocation - 1)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
					movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
					currentPoint++;
				}
			}
		}
		
		if(xLocation < 7 && yLocation < 7){
			currentSquare = board.getSquare(xLocation + 1, yLocation + 1);
			if(!isThreatened(board, xLocation + 1, yLocation + 1)){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() != color){
						movePoints[currentPoint] = new Point(currentSquare.getXSquare(), currentSquare.getYSquare());
						currentPoint++;
					}
				}else{
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
		return "king";
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
