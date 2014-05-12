package controller;


import model.piece.Piece;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;



 /** 
 *  Controller class of each game piece.
 *  Handles the connection between the piece model and its representation in the GUI
 * 
 * @since 7/5 2014
 */
public class PieceController
{
    
	 /* -- Even though static, this will still load on every new PieceController instantiated, so nothing gained here yet... 
	 *  ++ This is untrue, a class's static initialisation happens just prior to the very first time it is used,
	 *  ++ unrelated to whether it's a static call or instantiation of a new object, so this should be fine /Illum */
	private static final Image whitePiece = new Image("/resources/piece/backgammongreenpiece.png");
	private static final Image blackPiece = new Image("/resources/piece/backgammonredpiece.png");
	
	private static final DropShadow pieceDragShadow = new DropShadow(25.0, 0.0, 0.0, Color.LIME); 
	private static final DropShadow pieceSimpleDragShadow = new DropShadow();
	
	private ImageView pieceView;
	private Node pieceNode;
	private BoardController boardController;
	
	private Piece piece;


	/** Constructor of a new piece
	 * @param white If piece is white
	 * @param boardController BoardController
	 * @param fieldNumber Field number of game board of which the piece is being created upon
	 */
	public PieceController(boolean white, BoardController boardController, int fieldNumber)
	{
	    this.boardController = boardController;
		piece = new Piece(white, fieldNumber); // Where to place these logical pieces and how to connect the ImageView with it, without breaking the model/view/control layers?
		if(white)
		{
			pieceView = new ImageView(whitePiece);
		} 
		else 
		{
			pieceView = new ImageView(blackPiece);
		}

		pieceNode = setupMouseHandlers();
	}

	
	
	// Next part handles mouse events

	private class DragInfo
	{
		private double originalX;
		private double originalY;
	}

	int dragEventsCount = 0;
	
	
	// TODO Fix mouse handlers. It would seem a drag event eats these mouse events. Are they incompatible?
	private Node setupMouseHandlers()
	{
	    final DragInfo dragInfo = new DragInfo();
	    Group pieceAsGroup = new Group(pieceView);
//	    Group pieceAsGroup = new Group(pieceNode);
		pieceAsGroup.addEventFilter
		(
				MouseEvent.MOUSE_DRAGGED, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mouseDrag)
					{
	                    pieceView.translateXProperty().set(dragInfo.originalX + mouseDrag.getX());
	                    pieceView.translateYProperty().set(dragInfo.originalY + mouseDrag.getY());
	                    // If node is dragged instead of imageview, heavy jittering will follow
//					    pieceNode.translateXProperty().set(dragInfo.originalX + mouseDrag.getX());
//					    pieceNode.translateYProperty().set(dragInfo.originalY + mouseDrag.getY());
					    dragEventsCount++;
					}
				}
		);

		pieceAsGroup.addEventFilter
		(
		        MouseEvent.DRAG_DETECTED,
		        new EventHandler<MouseEvent>()
		        {
		            public void handle(MouseEvent dragDetected)
		            {
		                Dragboard pieceDragBoard = pieceView.startDragAndDrop(TransferMode.MOVE);
		                
		                ClipboardContent pieceAsContent = new ClipboardContent();
		                pieceAsContent.putImage(pieceView.getImage());
		                pieceDragBoard.setContent(pieceAsContent);
		                dragDetected.consume();
		            }

		        }
		);         
		
		pieceAsGroup.addEventFilter
		(
				MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mouseClick)
					{
						
					}
				}
		);

		pieceAsGroup.addEventFilter
		(
				MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent mousePress)
					{
					    // This node is still below the cursor. By setting node as mouse transparent,
					    // other nodes underneath will receive mouse events.
					    pieceNode.setMouseTransparent(true);   
					    dragEventsCount = 0;
//					    boardController.transferToBoard(pieceNode, 3);
//					    boardController.removeNode(pieceNode, 3);
//						pieceView.setCursor(Cursor.NONE);	// Piece "becomes" cursor during press
//						pieceView.setEffect(pieceSimpleDragShadow);	// For some reason, during this mouse press event, the piece moves if a shadow is set...
						pieceView.toFront(); // So piece will move above the rest of gui components, including other pieces
                        dragInfo.originalX = pieceView.getTranslateX() - mousePress.getX();
                        dragInfo.originalY = pieceView.getTranslateY() - mousePress.getY();
					}
				}
		);

		// TODO Figure out why drag events interferes with MOUSE_RELEASED eventfilter
//		pieceAsGroup.addEventFilter
//		(
//				MouseEvent.MOUSE_RELEASED, 
//				new EventHandler<MouseEvent>()
//				{
//					public void handle(MouseEvent mouseRelease)
//					{
//					    // Mouse transparency set to false again to make node visible for new events.
//					    pieceNode.setMouseTransparent(false);
//						pieceView.setCursor(Cursor.OPEN_HAND); // Cursor back to normal
//						pieceView.setEffect(null);	// Removes shadow
////						boardController.addPieceToField(pieceNode, 3);
//						System.out.println("number of drag events: " + dragEventsCount);
//					}
//				}
//		);

	       pieceAsGroup.addEventFilter
	        (
	                DragEvent.DRAG_DONE, 
	                new EventHandler<DragEvent>()
	                {
	                    public void handle(DragEvent dragDone)
	                    {
	                        // Mouse transparency set to false again to make node visible for new events.
	                        pieceNode.setMouseTransparent(false);
	                        pieceView.setCursor(Cursor.OPEN_HAND); // Cursor back to normal
//	                        pieceView.setEffect(null);  // Removes shadow
	                        boardController.removePieceFromField(pieceNode, (GridPane) pieceView.getParent().getParent());
	                        System.out.println("number of drag events: " + dragEventsCount);
	                    }

	                }
	        );
		        
     
		pieceAsGroup.addEventFilter
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
		
		return pieceAsGroup;
	}


    public Piece getPiece()
    {
        return piece;
    }


    public Node getPieceNode()
    {
        return pieceNode;
    }
	
	
}
