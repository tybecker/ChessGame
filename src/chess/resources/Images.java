package chess.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	//The background images and piece images of the chess board.
	public static BufferedImage[] backgrounds;
	public static BufferedImage[] pieces;
	
	/**
	 * The constructor method, which stuffs the image files (pngs each) into their arrays.
	 * Since the arrays are public, they can be drawn on directly.
	 */
	public Images() {
		backgrounds = new BufferedImage[1];
		try {
			backgrounds[0] = ImageIO.read(getClass().getResourceAsStream("/backgrounds/ChessBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		pieces = new BufferedImage[12];
		
		try {
			pieces[0] = ImageIO.read(getClass().getResourceAsStream("/pieces/WhitePawn.png"));
			pieces[1] = ImageIO.read(getClass().getResourceAsStream("/pieces/BlackPawn.png"));
			pieces[2] = ImageIO.read(getClass().getResourceAsStream("/pieces/WhiteRook.png"));
			pieces[3] = ImageIO.read(getClass().getResourceAsStream("/pieces/BlackRook.png"));
			pieces[4] = ImageIO.read(getClass().getResourceAsStream("/pieces/WhiteKnight.png"));
			pieces[5] = ImageIO.read(getClass().getResourceAsStream("/pieces/BlackKnight.png"));
			pieces[6] = ImageIO.read(getClass().getResourceAsStream("/pieces/WhiteBishop.png"));
			pieces[7] = ImageIO.read(getClass().getResourceAsStream("/pieces/BlackBishop.png"));
			pieces[8] = ImageIO.read(getClass().getResourceAsStream("/pieces/WhiteKing.png"));
			pieces[9] = ImageIO.read(getClass().getResourceAsStream("/pieces/BlackKing.png"));
			pieces[10] = ImageIO.read(getClass().getResourceAsStream("/pieces/WhiteQueen.png"));
			pieces[11] = ImageIO.read(getClass().getResourceAsStream("/pieces/BlackQueen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
