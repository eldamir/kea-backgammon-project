package controller;

import model.piece.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceController
{
	// Even though static, this will still load on every new PieceController instantiated, so nothing gained here yet...
	private static final Image whitePiece = new Image("/resources/piece/backgammongreenpiece.png");
	private static final Image blackPiece = new Image("/resources/piece/backgammonredpiece.png");
	
	private ImageView pieceView;
	
	private Piece piece;


	/** Constructor of a new piece
	 * @param white If piece is white
	 * @param boardController BoardController
	 */
	public PieceController(boolean white, BoardController boardController)
	{
		new Piece(white); // Where to place these logical pieces and how to connect the ImageView with it, without breaking the model/view/control layers?
		if(white)
		{
			pieceView = new ImageView(whitePiece);
		} 
		else 
		{
			pieceView = new ImageView(blackPiece);
		}
	}
	
	/** Returns the ImageView
	 * @return The ImageView
	 */
	public ImageView getPieceView()
	{
		return pieceView;
	}
	
	
}
