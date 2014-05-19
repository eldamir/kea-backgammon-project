package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.piece.Piece;
import model.board.BoardState;

/**
 * 
 * Class functions as a controller for the graphical game board component 
 *
 */
public class BoardController 
{
    // Game fields position assignment
    public static final int FIRST_SPIKE_NR = 0;
    public static final int LAST_SPIKE_NR = 23;
    private static final int FIRST_TOP_SPIKE_NR = 12;
    private static final int BLACK_PRISON = 24;
    private static final int WHITE_PRISON = 25;
    private static final int BLACK_HOME = 26;
    private static final int WHITE_HOME = 27;
    
    private static final int SPIKE_HEIGHT = 5;
    private static final int SPIKE_WIDTH = 6;

    // spikes
    @FXML
    private GridPane spikesNE;
    @FXML
    private GridPane spikesNW;
    @FXML
    private GridPane spikesSE;
    @FXML
    private GridPane spikesSW;

    // Prisons
    @FXML
    private AnchorPane blackPrison;
    @FXML
    private AnchorPane whitePrison;
    
    // Homes
    @FXML
    private AnchorPane blackHome;
    @FXML
    private AnchorPane whiteHome;
    
	
	private BoardState currentBoardState;
	
	
	/** Adds a piece to the gameboard
	 * @param nodeToAdd The piece node to add to board
	 * @param fieldNumber The spike the piece is added to
	 * @param spikeRow Row on which piece is to be added
	 */
	public void addPieceToField(Node nodeToAdd, int fieldNumber, int spikeRow)
	{
	    if (fieldNumber >= FIRST_SPIKE_NR && fieldNumber <= LAST_SPIKE_NR)
	    {
	        findSpikeGroup(fieldNumber).add(nodeToAdd, internalSpikeGroupPosition(fieldNumber), spikeRow);
	    }
	    else 
	    {
	        switch(fieldNumber)
	        {
	        case BLACK_PRISON: blackPrison.getChildren().add(nodeToAdd); break;
	        case WHITE_PRISON: whitePrison.getChildren().add(nodeToAdd); break;
	        case BLACK_HOME: blackHome.getChildren().add(nodeToAdd); break;
	        case WHITE_HOME: whiteHome.getChildren().add(nodeToAdd); break;
	        default: throw new IllegalArgumentException(fieldNumber + " is negative or greater than number of fields");
	        }
	    }
	}
	
	/** Removes a node (a game piece) from the GridPane of spikes
	 * @param removee The node to be removed
	 * @param gridPane The GridPane from which the node will be removed
	 */
	public void removePieceFromField(Node removee, GridPane gridPane)
	{
	    gridPane.getChildren().remove(removee);
	}
	
	// Helper methods for GridPane handling
	private int internalSpikeGroupPosition(int fieldNumber)
	{
	    int internalSpike = fieldNumber%SPIKE_WIDTH;
	    if (fieldNumber < FIRST_TOP_SPIKE_NR)
	    {
	        internalSpike = Math.abs(internalSpike - (SPIKE_WIDTH - 1));
	    }
	    return internalSpike;
	}
	
	
    /** Translates a spike number to its containing spike group
     * @param spikeNumber The number of the spike in question
     * @return Gridpane Spike is contained in
     */
    private GridPane findSpikeGroup(int spikeNumber)
    {
        int spikeGroup = spikeNumber/SPIKE_WIDTH;
        switch(spikeGroup)
        {
        case 0: return spikesSE;
        case 1: return spikesSW;
        case 2: return spikesNW;
        case 3: return spikesNE;
        }
        throw new IllegalArgumentException("Argument " + spikeNumber + " is greater number of spikes");
    }
	
	
	/** Sets up the game-board for a new game
	 * 	Clears board, gets method that creates pieces.
	 * 	Then sets pieces to their position on game-board.
	 */
	public void startBoard()
	{
	    clearAllSpikes();
	    currentBoardState = new BoardState();
		updateBoard();
	}

	/** An attempt to utilise boardstate to update the gui board
	 * 
	 */
	private void updateBoard()
	{
	    int currentRow;
	    for (ArrayList<Piece> spike : currentBoardState.getBoard())
	    {
	        for (int i = 0; i<spike.size(); i++)
	        {
	            currentRow = i;
	            if (currentRow < FIRST_TOP_SPIKE_NR) currentRow = Math.abs(currentRow - (SPIKE_HEIGHT - 1));
	            addPieceToField(new PieceHandler(spike.get(i).isWhite()), spike.get(i).getBoardPlacement(), currentRow);
	        }
	    }
	}

    @FXML
    void fireSpikeGroupDragOver(DragEvent dragOver) 
    {
     /* TODO This ImageView of piece should be a custom node and maybe visitor pattern should be 
      * applied if many other types should also be handled by spike groups. */
        if (dragOver.getGestureSource() instanceof ImageView /* && dragEvent.getDragboard().hasImage() - maybe? */)
        {
            dragOver.acceptTransferModes(TransferMode.MOVE);
        }
    }
    

    @FXML
    void fireSpikeGroupDragDrop(DragEvent dragDrop) 
    {
        /* TODO Implement legality of move */
        
        boolean mouseLocationDebug = true;
        
        if (dragDrop.getGestureSource() instanceof PieceHandler /* && dragEvent.getDragboard().hasImage() - needed? */)
        {
            GridPane currentSpike = (GridPane) dragDrop.getGestureTarget();
            double spikeColumnWidth = currentSpike.getWidth()/SPIKE_WIDTH;
            double spikeRowHeight = currentSpike.getHeight()/SPIKE_HEIGHT;
            double mouseXPosInSpike = dragDrop.getX();
            double mouseYPosInSpike = dragDrop.getY();
            int hoverColumnNr = (int) (mouseXPosInSpike/spikeColumnWidth);
            int hoverRowNr = (int) (mouseYPosInSpike/spikeRowHeight);
            int tempSpikeGroupNr = tempWhichSpikeGroup(currentSpike);
            if (tempSpikeGroupNr < 2) hoverColumnNr = Math.abs(hoverColumnNr - (SPIKE_WIDTH - 1)); 
            int boardFieldNr = tempSpikeGroupNr*SPIKE_WIDTH + hoverColumnNr;     
            addPieceToField(new PieceHandler(dragDrop.getDragboard().getImage()), boardFieldNr, hoverRowNr); 
            dragDrop.setDropCompleted(true);
            
            if (mouseLocationDebug)
            {
                System.out.println("\nSpikeWidth: " + spikeColumnWidth + " X Mouse Pos: " + mouseXPosInSpike);
                System.out.println("SpikeHeight: " + spikeRowHeight + " Y Mouse Pos: " + mouseYPosInSpike);
                System.out.println("Hovering over column: " + hoverColumnNr + " row: " + hoverRowNr);
                System.out.println("FieldNr on Board: " + boardFieldNr);
                System.out.print("Spikegroupnr: " + tempWhichSpikeGroup(currentSpike) + "   ");
            }
            
        }
//        dragDrop.consume();   // TODO Consume or not here?
    }
    
    
    /** Clears nodes from spike groups.
     * 
     */
    private void clearAllSpikes()
    {
        for (int i = 0; i<=LAST_SPIKE_NR; i = i+SPIKE_WIDTH)
        {
            ((GridPane) findSpikeGroup(i)).getChildren().clear();
        }
    }

    // Temp method until game logic is up and running
    private int tempWhichSpikeGroup(GridPane mystery)
    {
        if (mystery.equals(spikesSE)) return 0;
        else if (mystery.equals(spikesSW)) return 1;
        else if (mystery.equals(spikesNW)) return 2;
        else if (mystery.equals(spikesNE)) return 3;
        throw new IllegalArgumentException("Not a known spike group.");
    }
    
    
    
    @FXML
    public void initialize()
    {   
        
    }
}