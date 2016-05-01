package chess.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import chess.main.ChessAI;
import chess.main.ChessBoard;
import chess.main.ChessPiece;
import chess.main.ChessSquare;
import chess.main.PieceSelecter;

public class ChessGameState extends GameState{

	//Players playing the game, which determines whether an AI is needed.
	//The board being used by the game.
	//The current piece selected for a move.
	public int players;
	public ChessBoard board;
	public ChessPiece currentPiece;
	public ChessAI ai;
	
	//Where the board is supposed to be.
	public int boardXCoor = 130;
	public int boardYCoor = 25;
	
	//0 for white, 1 for black
	public int currentColor = 0;
	//Necessary for AI matches
	public int playerColor;
	
	//This integer will describe what state the action is in and make the game act
	//accordingly.  The numbers are as follows:
	//0: nothing selected.
	//1: piece selected.
	//2: pawn has made it to the far end.
	//3: castle has been moved into the castling position.
	//4: it's the ai's turn.
	//That's all I have so far, but I have a feeling there will be more.
	int actionState = 0;
	
	//Specifically for use in selecting a replacement piece when a pawn reaches the end of the board.
	PieceSelecter ps = null;
	
	/**
	 * The constructor class.  Insert the game state manager for the ability to call on
	 * it for the superconstructor, and so that you can call on it for exiting to menu
	 * once that functionality is installed.
	 * 
	 * @param gsm The game state manager which is running the game.
	 */
	public ChessGameState(GameStateManager gsm){
		super(gsm);
	}
	
	/**
	 * The constructor class.  Insert the game state manager for the ability to call on
	 * it for the superconstructor, and so that you can call on it for exiting to menu
	 * once that functionality is installed.  This particular version of the constructor
	 * is the one you should call on when creating a new game of chess.
	 * 
	 * @param gsm The game state manager which is running the game.
	 * @param players The number of players playing.  If 1, then the ai should be given
	 * a turn.
	 * @param playerColor The color of the player, 0 for white, 1 for black.  If there is
	 * no ai, start as white and let the player color switch.  I had to mess around with
	 * the initialization on this one to get it to work properly, for some reason.
	 */
	public ChessGameState(GameStateManager gsm, int players, int playerColor){
		super(gsm);
		this.players = players + 1;
		if(playerColor == 0){
			this.playerColor = 1;
		}else{
			this.playerColor = 0;
		}
		board = new ChessBoard(boardXCoor, boardYCoor);
		ai = new ChessAI();
		//If it's a one player game and the player is playing as black, start as the ai.
		if(this.playerColor == 1 && this.players == 1){
			actionState = 4;
		}
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * The tick method, called upon from the gsm 60 times a second (despite not really
	 * needing it.  It's a chess game for crying out loud!).  If the ai has been
	 * activated, it automatically clicks to make the ai move.  There's probably
	 * better ways of doing it, but I felt that this left me with the nicest looking
	 * code.
	 */
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(actionState == 4){
			mouseClicked(0,0);
		}
	}

	/**
	 * The draw method, usually just calls on the board's built-in draw method.  In
	 * action states 2 and 3 it also draws options for selecting what piece a pawn
	 * turns into, and buttons for castling respectively.
	 */
	@Override
	public void draw(Graphics g) {
		board.draw(g);
		if(actionState == 2){
			ps = new PieceSelecter();
			ps.setCoordinates(790, 80);
			ps.setPieceColor(currentColor);
			ps.draw(g);
		}else if(actionState == 3){
			g.setColor(Color.WHITE);
			g.fillRect(795, 35, 85, 20);
			g.setColor(Color.BLACK);
			g.drawRect(795, 35, 85, 20);
			g.drawString("Castle", 800, 50);
			g.setColor(Color.WHITE);
			g.fillRect(795, 85, 85, 20);
			g.setColor(Color.BLACK);
			g.drawRect(795, 85, 85, 20);
			g.drawString("Don't Castle", 800, 100);
		}
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * The mouseClicked method, called whenever the mouse is clicked, and a few other
	 * times, is where all the action happens.  When it's clicked, it will check for
	 * what state the board is in, and then take an action depending on both that, and
	 * where the mouse was clicked.
	 * 
	 * @param x The x coordinates of the mouse when it's clicked.
	 * @param y The y coordinates of the mouse when it's clicked.
	 */
	@Override
	public void mouseClicked(int x, int y) {
		//If nothing is selected.
		if(actionState == 0){
			
			//Begin block for when a square is clicked.
			ChessSquare currentSquare = null;
			for(int i = 0; i < board.squares.length; i++){
				for(int j = 0; j < board.squares[0].length; j++){
					if(x > board.squares[i][j].getXCoor() && x < board.squares[i][j].getXCoor() + 79 && y > board.squares[i][j].getYCoor() && y < board.squares[i][j].getYCoor() + 79){
						currentSquare = board.squares[i][j];
					}
				}
			}
			if(currentSquare != null){
				if(currentSquare.hasPiece()){
					if(currentSquare.getPiece().getColor() == currentColor){
						currentSquare.toggleSelect();
						Point[] movePoints = currentSquare.getPiece().getMovePoints(board);
						for(int k = 0; k < movePoints.length; k++){
							Point p = movePoints[k];
							board.getSquare((int)p.getX(), (int)p.getY()).toggleSelect();
							actionState = 1;
						}
						currentPiece = currentSquare.getPiece();
					}
				}
			}
			//End block for when a square is clicked.
		//If a piece is selected.
		}else if(actionState == 1){
			//Begin block for when a square is clicked
			ChessSquare currentSquare = null;
			ChessSquare oldSquare = board.getSquare(currentPiece.getXPosition(), currentPiece.getYPosition());
			for(int i = 0; i < board.squares.length; i++){
				for(int j = 0; j < board.squares[0].length; j++){
					if(x > board.squares[i][j].getXCoor() && x < board.squares[i][j].getXCoor() + 79 && y > board.squares[i][j].getYCoor() && y < board.squares[i][j].getYCoor() + 79){
						currentSquare = board.squares[i][j];
					}
				}
			}
			if(currentSquare != null){
				if(currentSquare.isSelected()){
					for(int i = 0; i < board.squares.length; i++){
						for(int j = 0; j < board.squares[0].length; j++){
							if(board.squares[i][j].isSelected()){
								board.squares[i][j].toggleSelect();
							}						
						}
					}
					if(currentSquare.getPiece() != currentPiece){
						currentSquare.setPiece(currentPiece);
						oldSquare.removePiece();
						if(currentColor == 1){
							currentColor = 0;
						}else if(currentColor == 0){
							currentColor = 1;
						}
					}
					//Start of trying to determine whether a piece was moved, and what that piece was,
					//and what should come next.
					if(currentPiece.getDescription().equalsIgnoreCase("pawn")){
						if(currentPiece.getColor() == 0){
							if(currentPiece.getYPosition() == 0){
								actionState = 2;
							}else{
								if(players == 1){
									actionState = 4;
								}else{
									actionState = 0;
								}
							}
						}else{
							if(currentPiece.getYPosition() == 7){
								actionState = 2;
							}else{
								if(players == 1){
									actionState = 4;
								}else{
									actionState = 0;
								}
							}
						}
					}else if(currentPiece.getDescription().equalsIgnoreCase("rook")){
						if(currentPiece.getColor() == 0){
							if(currentSquare.getXSquare() == 2 && currentSquare.getYSquare() == 7 && oldSquare.getXSquare() == 0  && oldSquare.getYSquare() == 7){
								if(board.getSquare(3, 7).hasPiece()){
									if(board.getSquare(3, 7).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(3, 7).getPiece().getColor() == 0){
										actionState = 3;
									}
								}
							}else if(currentSquare.getXSquare() == 4 && currentSquare.getYSquare() == 7 && oldSquare.getXSquare() == 7 && oldSquare.getYSquare() == 7){
								if(board.getSquare(3, 7).hasPiece()){
									if(board.getSquare(3, 7).getPiece().getDescription().equalsIgnoreCase("king")  && board.getSquare(3, 7).getPiece().getColor() == 0){
										actionState = 3;
									}
								}
							}
						}else if(currentPiece.getColor() == 1){
							if(currentSquare.getXSquare() == 2 && currentSquare.getYSquare() == 0 && oldSquare.getXSquare() == 0  && oldSquare.getYSquare() == 0){
								if(board.getSquare(3, 0).hasPiece()){
									if(board.getSquare(3, 0).getPiece().getDescription().equalsIgnoreCase("king") && board.getSquare(3, 0).getPiece().getColor() == 1){
										actionState = 3;
									}
								}
							}else if(currentSquare.getXSquare() == 4 && currentSquare.getYSquare() == 0 && oldSquare.getXSquare() == 7 && oldSquare.getYSquare() == 0){
								if(board.getSquare(3, 0).hasPiece()){
									if(board.getSquare(3, 0).getPiece().getDescription().equalsIgnoreCase("king")  && board.getSquare(3, 0).getPiece().getColor() == 1){
										actionState = 3;
									}
								}
							}
						}
						if(actionState != 3){
							if(players == 1){
								actionState = 4;
							}else{
								actionState = 0;
							}
						}
					}else{
						if(players == 1){
							actionState = 4;
						}else{
							actionState = 0;
						}
					}
					//End of section figuring out what comes next.
				}
			}
			//End block for when a square is clicked.
		//This block is for when a pawn has made it to the far end.
		}else if(actionState == 2){
			if(x > ps.getXCoor() && x < ps.getXCoor() + 80 && y > ps.getYCoor() && y < ps.getYCoor() + 400){
				ChessPiece newPiece = ps.getSelectedPiece(x, y);
				ChessSquare currentSquare = board.getSquare(currentPiece.getXPosition(), currentPiece.getYPosition());
				currentSquare.setPiece(newPiece);
				if(players == 1){
					actionState = 4;
				}else{
					actionState = 0;
				}
			}
		//This block is for when a rook has moved into a castling position with a king.
		//Some parts of the rules are not yet implemented for this.
		}else if(actionState == 3){
			if(x > 795 && x < 880 && y > 35 && y < 55){
				if(currentPiece.getYPosition() == 0){
					if(currentPiece.getXPosition() == 2){
						ChessPiece piece = board.getSquare(3, 0).getPiece();
						board.getSquare(1, 0).setPiece(piece);
						board.getSquare(3, 0).removePiece();
					}else if(currentPiece.getXPosition() == 4){
						ChessPiece piece = board.getSquare(3, 0).getPiece();
						board.getSquare(5, 0).setPiece(piece);
						board.getSquare(3, 0).removePiece();
					}
				}else if(currentPiece.getYPosition() == 7){
					if(currentPiece.getXPosition() == 2){
						ChessPiece piece = board.getSquare(3, 7).getPiece();
						board.getSquare(1, 7).setPiece(piece);
						board.getSquare(3, 7).removePiece();
					}else if(currentPiece.getXPosition() == 4){
						ChessPiece piece = board.getSquare(3, 7).getPiece();
						board.getSquare(5, 7).setPiece(piece);
						board.getSquare(3, 7).removePiece();
					}
				}
				if(players == 1){
					actionState = 4;
				}else{
					actionState = 0;
				}
			}
			if(x > 795 && x < 880 && y > 85 && y < 105){
				if(players == 1){
					actionState = 4;
				}else{
					actionState = 0;
				}
			}
		//This block is for making the ai take an action in a one player game.
		}else if(actionState == 4){
			
			if(currentColor != playerColor){
				int aiPlayer = 0;
				if(playerColor == 0){
					aiPlayer = 1;
				}else{
					aiPlayer = 0;
				}
				board = ai.getNextMove(board, aiPlayer);
				if(currentColor == 1){
					currentColor = 0;
				}else if(currentColor == 0){
					currentColor = 1;
				}
				actionState = 0;
			}else{
				actionState = 0;
			}
		}
	}
}
