package controller;


import javafx.scene.Cursor;
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
public class PieceHandler extends ImageView
{

    /* -- Even though static, this will still load on every new PieceController instantiated, so nothing gained here yet... 
     *  ++ This is untrue, a class's static initialisation happens just prior to the very first time it is used,
     *  ++ unrelated to whether it's a static call or instantiation of a new object, so this should be fine /Illum */
    private static final Image whitePiece = new Image("/resources/piece/backgammongreenpiece.png");
    private static final Image blackPiece = new Image("/resources/piece/backgammonredpiece.png");

    private static final DropShadow pieceDragShadow = new DropShadow(25.0, 0.0, 0.0, Color.LIME); 


    /** Constructor of a new piece
     * @param white If piece is white
     */
    public PieceHandler(boolean white)
    {
        if(white) setImage(whitePiece);
        else setImage(blackPiece);
        setupMouseHandlers();
    }

    public PieceHandler(Image incImage)
    {
        setImage(incImage);
        setupMouseHandlers();
    }


    /** Anchor for cursor
     *
     */
    private class DragInfo
    {
        private double originalX;
        private double originalY;
    }



    /** Adds event handlers to the piece
     * 
     */
    private void setupMouseHandlers()
    {
        final DragInfo dragInfo = new DragInfo();
        addEventFilter
        (
                MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent mouseDrag)
                    {
                        translateXProperty().set(dragInfo.originalX + mouseDrag.getX());
                        translateYProperty().set(dragInfo.originalY + mouseDrag.getY());
                    }
                }
                );

        addEventFilter
        (
                MouseEvent.DRAG_DETECTED,
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent dragDetected)
                    {
                        Dragboard pieceDragBoard = startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent pieceAsContent = new ClipboardContent();
                        pieceAsContent.putImage(getImage());
                        pieceDragBoard.setContent(pieceAsContent);
                        //                        dragDetected.consume();
                    }

                }
                );         

        addEventFilter
        (
                MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent mousePress)
                    {
                        // This node is still below the cursor. By setting node as mouse transparent,
                        // other nodes underneath will receive mouse events.
                        setMouseTransparent(true);   
                        //                        setCursor(Cursor.NONE);   // Piece "becomes" cursor during press
                        setEffect(pieceDragShadow); // For some reason, during this mouse press event, the piece moves if a shadow is set...
                        toFront(); // So piece will move above the rest of gui components, including other pieces
                        dragInfo.originalX = getTranslateX() - mousePress.getX();
                        dragInfo.originalY = getTranslateY() - mousePress.getY();
                    }
                }
                );

        // TODO Figure out why drag events interferes with MOUSE_RELEASED eventfilter
        addEventFilter
        (
                MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent mouseRelease)
                    {
                        // Mouse transparency set to false again to make node visible for new events.
                        setMouseTransparent(false);
                        setCursor(Cursor.OPEN_HAND); // Cursor back to normal
                        setEffect(null);  // Removes shadow
                    }
                }
                );

        addEventFilter
        (
                DragEvent.DRAG_DONE, 
                new EventHandler<DragEvent>()
                {
                    public void handle(DragEvent dragDone)
                    {
                        // Mouse transparency set to false again to make node visible for new events.
//                        setMouseTransparent(false);
                        setCursor(Cursor.OPEN_HAND); // Cursor back to normal
                        setEffect(null);  // Removes shadow
                        ((GridPane) getParent()).getChildren().remove(dragDone.getSource());
                    }

                }
                );


        addEventFilter
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
