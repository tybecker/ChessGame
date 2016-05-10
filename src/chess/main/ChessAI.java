package chess.main;

public class ChessAI {
	
	/**
	 * The constructor method.  Right now, it doesn't actually need to
	 * do anything.  I could probably turn these into static methods.
	 */
	public ChessAI(){
		
	}
	
	/**
	 * Return a board with the pieces in the new configuration.  Remember,
	 * 0 is white, 1 is black.  Currently this algorithm just evaluates
	 * boards numerically, then picks the top scoring move.  With a little
	 * tweaking, I'd like to modify this algorithm to look down future
	 * branches.  I have made certain that the ChessBoard and ChessPiece
	 * classes can handle it.
	 * 
	 * @param board The board layout being evaluated by the ai
	 * @param player The color of the player the board is getting the next
	 * move for.  This can be the color of the ai.
	 * @return A new chessboard, this time with the pieces arranged so that
	 * one piece has moved.
	 */
	public ChessBoard getNextMove(ChessBoard board, int player){
		
		//This gets a list of the pieces, used so that the ai can look at each one
		//in turn, and examine the possible moves of each.
		ChessPiece[] pieces = board.getPieces();
		
		//Each side has a possible 138 moves.  This assumes a theoretical best case
		//scenario which probably doesn't exist.  Use the moves array to store
		//boards with possible moves on them, the currentPieceMoves array to store
		//the moves of the current piece (28 max for queens) and the currentMove
		//number to keep track of how many moves you've looked at for the purpose
		//of keeping track of where in the moves array you're currently looking.
		ChessBoard[] moves = new ChessBoard[138];
		ChessBoard[] currentPieceMoves = new ChessBoard[28];
		int currentMove = 0;
		
		//Look at each piece, and for each piece put all possible moves into the
		//moves array.
		for(int i = 0; i < pieces.length; i++){
			if(pieces[i].getColor() == player){
				currentPieceMoves = pieces[i].getMoves(board);
				for(int j = 0; j < currentPieceMoves.length; j++){
					moves[currentMove] = currentPieceMoves[j];
					currentMove++;
				}
			}
		}
		
		//Clip the moves array to a smaller array where the length is equal to
		//the number of moves available.
		ChessBoard[] movesShort = new ChessBoard[currentMove];
		for(int i = 0; i < currentMove; i++){
			movesShort[i] = moves[i];
		}
		
		//An array holding the values of the boards, determined heuristically
		//by the evaluateBoard method.
		int[] moveValues = new int[movesShort.length];
		for(int i = 0; i < movesShort.length; i++){
			moveValues[i] = evaluateBoard(movesShort[i], player);
		}
		
		//The max value known and the index number of the max board known.
		//Remember, values for boards can be below zero, so initialize the
		//value to a very large negative number.
		int maxValue = -1000000000;
		int maxBoardKnown = 0;
		
		//This loop goes through the array of values, and picks out the best
		//board by max value.
		for(int i = 0; i < moveValues.length; i++){
			if(moveValues[i] > maxValue){
				maxValue = moveValues[i];
				maxBoardKnown = i;
			}
		}
		
		//Return the best board known.  In the case of ties, this returns the
		//first board.
		if(movesShort.length == 0){
			return null;
		}else{
			return movesShort[maxBoardKnown];
		}
	}
	
	/**
	 * Take in a board layout, and evaluate how good that layout is to the
	 * current player.  Needs to know the player color (0 for white, 1 for
	 * black) in order to figure out how good the position is.  Use the
	 * variables at the top to tweak how much a position is worth.
	 * 
	 * @param board The board layout being evaluated.
	 * @param player The player the board is being evaluated for.
	 * @return An integer describing how good the position is for the AI.
	 */
	public int evaluateBoard(ChessBoard board, int playerColor){

		//I'm adding a variable for the enemy's color.  It makes it simpler later.
		int opponentColor = (playerColor + 1)%2;
		
		//The final value of the board.
		int boardValue = 0;
		
		//The following values represent the point value of certain pieces,
		//whether they've been taken, threatened, or protected.
		int squareThreatened = 1;
		
		int pawnValue = 30;
		int pawnThreatened = 10;
		int pawnProtected = 5;
		int rookValue = 60;
		int rookThreatened = 40;
		int rookProtected = 35;
		int knightValue = 75;
		int knightThreatened = 55;
		int knightProtected = 40;
		int bishopValue = 60;
		int bishopThreatened = 40;
		int bishopProtected = 35;
		int queenValue = 100;
		int queenThreatened = 80;
		int queenProtected = 20;
		int kingValue = 1000000;
		int kingThreatened = 150;
		int kingAdjacentThreatened = 50;
		int kingProtected = 0;
		
		ChessPiece[] pieces = board.getPieces();
		
		//I'm starting with a for loop that gets the value of the pieces on the board.
		for(int i = 0; i < pieces.length; i++){
			if(pieces[i].getDescription().equalsIgnoreCase("pawn")){
				if(pieces[i].getColor() == playerColor){
					boardValue = boardValue + pawnValue;
				}else{
					boardValue = boardValue - pawnValue;
				}
			}
			if(pieces[i].getDescription().equalsIgnoreCase("rook")){
				if(pieces[i].getColor() == playerColor){
					boardValue = boardValue + rookValue;
				}else{
					boardValue = boardValue - rookValue;
				}
			}
			if(pieces[i].getDescription().equalsIgnoreCase("knight")){
				if(pieces[i].getColor() == playerColor){
					boardValue = boardValue + knightValue;
				}else{
					boardValue = boardValue - knightValue;
				}
			}
			if(pieces[i].getDescription().equalsIgnoreCase("bishop")){
				if(pieces[i].getColor() == playerColor){
					boardValue = boardValue + bishopValue;
				}else{
					boardValue = boardValue - bishopValue;
				}
			}
			if(pieces[i].getDescription().equalsIgnoreCase("queen")){
				if(pieces[i].getColor() == playerColor){
					boardValue = boardValue + queenValue;
				}else{
					boardValue = boardValue - queenValue;
				}
			}
			if(pieces[i].getDescription().equalsIgnoreCase("king")){
				if(pieces[i].getColor() == playerColor){
					boardValue = boardValue + kingValue;
				}else{
					boardValue = boardValue - kingValue;
				}
			}
		}
		
		//This next bit needs to add or subtract value based on what pieces are threatened.
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(isThreatened(board, playerColor, i, j)){
					if(board.squares[i][j].hasPiece()){
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("pawn")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue - pawnThreatened;
							}else{
								boardValue = boardValue - pawnProtected;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("rook")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue - rookThreatened;
							}else{
								boardValue = boardValue - rookProtected;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("knight")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue - knightThreatened;
							}else{
								boardValue = boardValue - knightProtected;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("bishop")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue - bishopThreatened;
							}else{
								boardValue = boardValue - bishopProtected;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("queen")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue - queenThreatened;
							}else{
								boardValue = boardValue - queenProtected;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("king")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue - kingThreatened;
							}else{
								boardValue = boardValue - kingProtected;
							}
						}
					}else{
						boardValue = boardValue - squareThreatened;
					}
				}
			}
		}
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(isThreatened(board, (playerColor + 1)%2, i, j)){
					if(board.squares[i][j].hasPiece()){
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("pawn")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue + pawnProtected;
							}else{
								boardValue = boardValue + pawnThreatened;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("rook")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue + pawnProtected;
							}else{
								boardValue = boardValue + pawnThreatened;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("knight")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue + pawnProtected;
							}else{
								boardValue = boardValue + pawnThreatened;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("bishop")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue + pawnProtected;
							}else{
								boardValue = boardValue + pawnThreatened;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("queen")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue + pawnProtected;
							}else{
								boardValue = boardValue + pawnThreatened;
							}
						}
						if(board.squares[i][j].getPiece().getDescription().equalsIgnoreCase("king")){
							if(board.squares[i][j].getPiece().getColor() == playerColor){
								boardValue = boardValue + pawnProtected;
							}else{
								boardValue = boardValue + pawnThreatened;
							}
						}
					}else{
						boardValue = boardValue + squareThreatened;
					}
				}
			}
		}
		
		//This part adds or subtracts values based on what pieces are protected.
		//Note:  I got rid of this part when I integrated piece protection with
		//the statements for judging how much it is worth when a piece is
		//threatened.  I maybe should get rid of the comments too?  In a later
		//update when I'm cleaning up maybe.
		
		//This part is for judging the value of squares adjacent to the king.
		//This is important in making the ai recognize a checkmate.
		ChessPiece playerKingPiece = null;
		ChessPiece opponentKingPiece = null;
		for(int i = 0; i < pieces.length; i++){
			if(pieces[i].getDescription().equalsIgnoreCase("king")){
				if(pieces[i].getColor() == playerColor){
					playerKingPiece = pieces[i];
				}else{
					opponentKingPiece = pieces[i];
				}
			}
		}
		
		//Now, one by one, evaluate the squares around the kings.
		if(playerKingPiece != null){
			if(playerKingPiece.getXPosition() > 0){
				if(!board.getSquare(playerKingPiece.getXPosition() - 1, playerKingPiece.getYPosition()).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition() - 1, playerKingPiece.getYPosition())){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
			if(playerKingPiece.getXPosition() < 7){
				if(!board.getSquare(playerKingPiece.getXPosition() + 1, playerKingPiece.getYPosition()).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition() + 1, playerKingPiece.getYPosition())){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
			if(playerKingPiece.getYPosition() > 0){
				if(!board.getSquare(playerKingPiece.getXPosition(), playerKingPiece.getYPosition() - 1).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition(), playerKingPiece.getYPosition() - 1)){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
			if(playerKingPiece.getYPosition() < 7){
				if(!board.getSquare(playerKingPiece.getXPosition(), playerKingPiece.getYPosition() + 1).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition(), playerKingPiece.getYPosition() + 1)){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
			if(playerKingPiece.getXPosition() > 0 && playerKingPiece.getYPosition() > 0){
				if(!board.getSquare(playerKingPiece.getXPosition() - 1, playerKingPiece.getYPosition() - 1).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition() - 1, playerKingPiece.getYPosition() - 1)){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
			if(playerKingPiece.getXPosition() > 0 && playerKingPiece.getYPosition() < 7){
				if(!board.getSquare(playerKingPiece.getXPosition() - 1, playerKingPiece.getYPosition() + 1).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition() - 1, playerKingPiece.getYPosition() + 1)){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
			if(playerKingPiece.getXPosition() < 7 && playerKingPiece.getYPosition() > 0){
				if(!board.getSquare(playerKingPiece.getXPosition() + 1, playerKingPiece.getYPosition() - 1).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition() + 1, playerKingPiece.getYPosition() - 1)){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
			if(playerKingPiece.getXPosition() < 7 && playerKingPiece.getYPosition() < 7){
				if(!board.getSquare(playerKingPiece.getXPosition() + 1, playerKingPiece.getYPosition() + 1).hasPiece()){
					if(isThreatened(board, playerColor, playerKingPiece.getXPosition() + 1, playerKingPiece.getYPosition() + 1)){
						boardValue = boardValue - kingAdjacentThreatened;
					}
				}
			}
		}
		if(opponentKingPiece != null){
			if(opponentKingPiece.getXPosition() > 0){
				if(!board.getSquare(opponentKingPiece.getXPosition() - 1, opponentKingPiece.getYPosition()).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition() - 1, opponentKingPiece.getYPosition())){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
			if(opponentKingPiece.getXPosition() < 7){
				if(!board.getSquare(opponentKingPiece.getXPosition() + 1, opponentKingPiece.getYPosition()).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition() + 1, opponentKingPiece.getYPosition())){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
			if(opponentKingPiece.getYPosition() > 0){
				if(!board.getSquare(opponentKingPiece.getXPosition(), opponentKingPiece.getYPosition() - 1).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition(), opponentKingPiece.getYPosition() - 1)){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
			if(opponentKingPiece.getYPosition() < 7){
				if(!board.getSquare(opponentKingPiece.getXPosition(), opponentKingPiece.getYPosition() + 1).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition(), opponentKingPiece.getYPosition() + 1)){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
			if(opponentKingPiece.getXPosition() > 0 && opponentKingPiece.getYPosition() > 0){
				if(!board.getSquare(opponentKingPiece.getXPosition() - 1, opponentKingPiece.getYPosition() - 1).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition() - 1, opponentKingPiece.getYPosition() - 1)){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
			if(opponentKingPiece.getXPosition() > 0 && opponentKingPiece.getYPosition() < 7){
				if(!board.getSquare(opponentKingPiece.getXPosition() - 1, opponentKingPiece.getYPosition() + 1).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition() - 1, opponentKingPiece.getYPosition() + 1)){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
			if(opponentKingPiece.getXPosition() < 7 && opponentKingPiece.getYPosition() > 0){
				if(!board.getSquare(opponentKingPiece.getXPosition() + 1, opponentKingPiece.getYPosition() - 1).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition() + 1, opponentKingPiece.getYPosition() - 1)){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
			if(opponentKingPiece.getXPosition() < 7 && opponentKingPiece.getYPosition() < 7){
				if(!board.getSquare(opponentKingPiece.getXPosition() + 1, opponentKingPiece.getYPosition() + 1).hasPiece()){
					if(isThreatened(board, opponentColor, opponentKingPiece.getXPosition() + 1, opponentKingPiece.getYPosition() + 1)){
						boardValue = boardValue + kingAdjacentThreatened;
					}
				}
			}
		}
		
		return boardValue;
	}
	
	/**
	 * Returns true if the square in question is threatened, and returns
	 * false if the square is not threatened.
	 * 
	 * @param board The board with the layout being evaluated.
	 * @param playerColor The color of the player you're evaluating the space for.
	 * @param xSquare The x position of the square being evaluated
	 * @param ySquare The y position of the square being evaluated
	 * @return Whether or not the square is threatened.
	 */
	public boolean isThreatened(ChessBoard board, int playerColor, int xSquare, int ySquare){
		
		ChessSquare square = board.getSquare(xSquare, ySquare);
		
		//Logic for checking for enemy pawns
		if(playerColor == 0){
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
				if(board.getSquare(xSquare - 1, ySquare - 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 1, ySquare - 2).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare > 1 && ySquare > 0){
			if(board.getSquare(xSquare - 2, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare - 2, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 2, ySquare - 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare < 6 && ySquare > 0){
			if(board.getSquare(xSquare + 2, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare + 2, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 2, ySquare - 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare > 1){
			if(board.getSquare(xSquare + 1, ySquare - 2).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare - 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 1, ySquare - 2).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare < 6){
			if(board.getSquare(xSquare + 1, ySquare + 2).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare + 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 1, ySquare + 2).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare < 6 && ySquare < 7){
			if(board.getSquare(xSquare + 2, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare + 2, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare + 2, ySquare + 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare > 1 && ySquare < 7){
			if(board.getSquare(xSquare - 2, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare - 2, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 2, ySquare + 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare > 0 && ySquare < 6){
			if(board.getSquare(xSquare - 1, ySquare + 2).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare + 2).getPiece().getDescription().equalsIgnoreCase("knight") && board.getSquare(xSquare - 1, ySquare + 2).getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("rook") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
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
				if((square.getPiece().getDescription().equalsIgnoreCase("bishop") || square.getPiece().getDescription().equalsIgnoreCase("queen")) && square.getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		
		//Logic for checking for enemy kings
		if(xSquare > 0){
			if(board.getSquare(xSquare - 1, ySquare).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare - 1, ySquare).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare < 7){
			if(board.getSquare(xSquare + 1, ySquare).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare + 1, ySquare).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(ySquare > 0){
			if(board.getSquare(xSquare, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare, ySquare - 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(ySquare < 7){
			if(board.getSquare(xSquare, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare, ySquare + 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare > 0 && ySquare > 0){
			if(board.getSquare(xSquare - 1, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare - 1, ySquare - 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare > 0 && ySquare < 7){
			if(board.getSquare(xSquare - 1, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare - 1, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare - 1, ySquare + 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare > 0){
			if(board.getSquare(xSquare + 1, ySquare - 1).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare - 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare + 1, ySquare - 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		if(xSquare < 7 && ySquare < 7){
			if(board.getSquare(xSquare + 1, ySquare + 1).hasPiece()){
				if(board.getSquare(xSquare + 1, ySquare + 1).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(xSquare + 1, ySquare + 1).getPiece().getColor() != playerColor){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Alright, it's time for a slightly more advanced chess algorithm.  This
	 * one will take the value after it's predicted the enemy's move a few
	 * steps forwards.  Let's see how this does.
	 * 
	 * @param board The board under consideration.
	 * @param playerColor The color of the player that the board is being
	 * evaluated for.
	 * @return An integer value for how good the board is.
	 */
	public int advancedEvaluateBoard(ChessBoard board, int playerColor){
		
		

		
		return 0;
	}
}