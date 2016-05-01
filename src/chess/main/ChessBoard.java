package chess.main;

import java.awt.Color;
import java.awt.Graphics;

public class ChessBoard {

	//The squares, arranged so that the first number is the x square,
	//and the second number is the y square
	public ChessSquare[][] squares;
	
	//The x and y coordinates, telling the board where to draw itself on the screen.
	public int xCoor;
	public int yCoor;
	
	/**
	 * The blank constructor method, which makes a default board.
	 */
	public ChessBoard(){
		xCoor = 0;
		yCoor = 0;
		
		squares = new ChessSquare[8][8];
		Color currentColor = Color.BLACK;
		
		for(int i = 0; i < squares.length; i++){
			for(int j = 0; j < squares[0].length; i++){
				squares[i][j] = new ChessSquare(currentColor, i, j);
				if(currentColor.getRGB() == Color.BLACK.getRGB()){
					currentColor = Color.WHITE;
				}else{
					currentColor = Color.BLACK;
				}
				squares[i][j].setXCoor(xCoor + 560 - i * 80);
				squares[i][j].setYCoor(yCoor + 560 - i * 80);
			}
		}
		setupBoard();
	}
	
	/**
	 * This constructor method builds a board.  The squares should
	 * alternate color, with the lower left square being black, and
	 * at the 0,0 coordinate.
	 * 
	 * @param x The x coordinate of the upper left corner of the board.
	 * @param y The y coordinate of the upper left corner of the board.
	 */
	public ChessBoard(int x, int y){
		
		xCoor = x;
		yCoor = y;
		
		squares = new ChessSquare[8][8];
		Color currentColor = Color.WHITE;
		
		for(int i = 0; i < squares.length; i++){
			if(currentColor.getRGB() == Color.BLACK.getRGB()){
				currentColor = Color.WHITE;
			}else{
				currentColor = Color.BLACK;
			}
			for(int j = 0; j < squares[0].length; j++){
				squares[i][j] = new ChessSquare(currentColor, i, j);
				if(currentColor.getRGB() == Color.BLACK.getRGB()){
					currentColor = Color.WHITE;
				}else{
					currentColor = Color.BLACK;
				}
				squares[i][j].setXCoor(xCoor + i * 80);
				squares[i][j].setYCoor(yCoor + 560 - j * 80);
			}
		}
		setupBoard();
	}
	
	/**
	 * Sets the board up in the starting position.
	 */
	public void setupBoard(){
		for(int i = 0; i < squares.length; i++){
			squares[i][1].setPiece(new Pawn(1, i, 1));
			squares[i][6].setPiece(new Pawn(0, i, 6));
		}
		squares[0][0].setPiece(new Rook(1, 0, 0));
		squares[1][0].setPiece(new Knight(1, 1, 0));
		squares[2][0].setPiece(new Bishop(1, 2, 0));
		squares[3][0].setPiece(new King(1, 3, 0));
		squares[4][0].setPiece(new Queen(1, 4, 0));
		squares[5][0].setPiece(new Bishop(1, 5, 0));
		squares[6][0].setPiece(new Knight(1, 6, 0));
		squares[7][0].setPiece(new Rook(1, 7, 0));
		
		squares[0][7].setPiece(new Rook(0, 0, 7));
		squares[1][7].setPiece(new Knight(0, 1, 7));
		squares[2][7].setPiece(new Bishop(0, 2, 7));
		squares[3][7].setPiece(new King(0, 3, 7));
		squares[4][7].setPiece(new Queen(0, 4, 7));
		squares[5][7].setPiece(new Bishop(0, 5, 7));
		squares[6][7].setPiece(new Knight(0, 6, 7));
		squares[7][7].setPiece(new Rook(0, 7, 7));
	}
	
	/**
	 * Returns a square from the chessboard, since each square is its own object.
	 * 
	 * @param x The x position of the square, with 0 being on the left, and 7 on the right.
	 * @param y The y position of the square, with 0 being on the bottom, and 7 on the top.
	 * @return The square requested when the method was called.
	 */
	public ChessSquare getSquare(int x, int y){
		return squares[x][y];
	}
	
	/**
	 * The method called upon to draw the board.  This method itself simply calls upon the
	 * squares to draw themselves, one by one.  The squares, if they contain pieces, call
	 * upon those pieces to draw themselves.
	 * 
	 * @param g The graphics object, used to draw to the game panel.
	 */
	public void draw(Graphics g){
		for(int i = 0; i < squares.length; i++){
			for(int j = 0; j < squares[0].length; j++){
				squares[i][j].draw(g);
			}
		}
	}
	
	/**
	 * Returns a copy of the chessboard in its current state.  This is necessary
	 * for the ai, because it looks at the board in a large number of arrangements.
	 * If it all used the same board, it wouldn't know when it was done, and
	 * wouldn't be able to save the original layout.
	 * 
	 * @return A copy of the board, with copies of the pieces.
	 */
	public ChessBoard copy(){
		ChessBoard cloneBoard = new ChessBoard(xCoor, yCoor);
		
		for(int i = 0; i < squares.length; i++){
			for(int j = 0; j < squares[0].length; j ++){
				cloneBoard.squares[i][j].removePiece();
				if(this.squares[i][j].hasPiece()){
					cloneBoard.squares[i][j].setPiece(this.squares[i][j].getPiece().copy());
				}
			}
		}
		
		return cloneBoard;
	}

	/**
	 * This method should get all of the chess pieces still on the board,
	 * making it easy to get a headcount, and a list of places that the
	 * pieces can move to.
	 * 
	 * @return The array with all of the pieces on the board.
	 */
	public ChessPiece[] getPieces(){
		
		//Start with making a 32 piece array.  32 is the largest number of pieces
		//you'll have on the board.
		ChessPiece[] pieces = new ChessPiece[32];
		int currentPiece = 0;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(squares[i][j].hasPiece()){
					pieces[currentPiece] = squares[i][j].getPiece();
					currentPiece++;
				}
			}
		}
		
		//Now make a shorter array to return, this one only large enough to contain
		//all the pieces.
		ChessPiece[] piecesShort = new ChessPiece[currentPiece];
		
		for(int i = 0; i < currentPiece; i++){
			piecesShort[i] = pieces[i];
		}
		
		return piecesShort;
	}
}
