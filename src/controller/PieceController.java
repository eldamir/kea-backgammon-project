package controller;

import model.piece.Piece;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;

public class PieceController
{

	// Even though static, this will still load on every new PieceController instantiated, so nothing gained here yet...
	private static final Image whitePiece = new Image("/resources/piece/backgammongreenpiece.png");
	private static final Image blackPiece = new Image("/resources/piece/backgammonredpiece.png");
	
	private static final DropShadow pieceDragShadow = new DropShadow(25.0, 0.0, 0.0, Color.LIME); 
	
	private ImageView pieceView;
	
	private Piece piece;
	private DragInfo dragInfo;


	/** Constructor of a new piece
	 * @param white If piece is white
	 * @param boardController BoardController
	 */
	public PieceController(boolean white, BoardController boardController)
	{
		piece = new Piece(white, 1); // Where to place these logical pieces and how to connect the ImageView with it, without breaking the model/view/control layers?
		if(white)
		{
			pieceView = new ImageView(whitePiece);
		} 
		else 
		{
			pieceView = new ImageView(blackPiece);
		}
		setupMousePress();
		setupMouseRelease();
		setupMouseDrag();
		consumeRest();
	}
	
	/** Returns the ImageView
	 * @return The ImageView
	 */
	public ImageView getPieceView()
	{
		return pieceView;
	}
	
	
	
	
	// Next part handles mouse events

	private class DragInfo
	{
		public double originalX;
		public double originalY;
		
		public DragInfo(MouseEvent mousePress)
		{
			originalX = pieceView.getTranslateX() - mousePress.getX();
			originalY = pieceView.getTranslateY() - mousePress.getY();
		}

		public double getOriginalX()
		{
			return originalX;
		}

		public double getOriginalY()
		{
			return originalY;
		}		

//		public double XPosPiece;
//		public double YPosPiece;
//    public double Xmouse;
//    public double Ymouse;
 
	}

	int count = 0;
	
	private void setupMouseDrag()
	{
		pieceView.addEventFilter
		(
				MouseEvent.MOUSE_DRAGGED, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mouseDrag)
					{
						pieceView.setTranslateX(dragInfo.originalX + mouseDrag.getX());
						pieceView.setTranslateY(dragInfo.originalY + mouseDrag.getY());
						count++;
						mouseDrag.consume();
					}
				}
		);
	}
	
	private void setupMouseClick()
	{
		pieceView.addEventFilter
		(
				MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mouseClick)
					{
						
					}
				}
		);
	}
	
	private void setupMousePress()
	{
		pieceView.addEventFilter
		(
				MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mousePress)
					{
						pieceView.setCursor(Cursor.NONE);	// Piece "becomes" cursor during press
						pieceView.setEffect(pieceDragShadow);	
						pieceView.toFront(); // So piece will move above the rest of gui components, including other pieces
						dragInfo = new DragInfo(mousePress);
//						mousePress.consume(); ?
					}
				}
		);
	}
	
	private void setupMouseRelease()
	{
		pieceView.addEventFilter
		(
				MouseEvent.MOUSE_RELEASED, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mouseRelease)
					{
						pieceView.setCursor(Cursor.OPEN_HAND); // Cursor back to normal
						pieceView.setEffect(null);	// Removes shadow
						System.out.println(count);
//						mouseRelease.consume(); ?
					}
				}
		);
	}
	
	private void consumeRest()
	{
		pieceView.addEventFilter
		(
				MouseEvent.ANY, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mouseEvent)
					{
						mouseEvent.consume();
					}
				}
		);
	}
	
	
}
