package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


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
    

    @FXML
	private AnchorPane fullBoard;
    
    
	private List<Node> boardSpikes;
	
	
	/** Adds a piece to the gameboard
	 * @param nodeToAdd The piece node to add to board
	 * @param fieldNumber The spike the piece is added to
	 */
	public void addPieceToField(Node nodeToAdd, int fieldNumber)
	{
	    if (fieldNumber >= FIRST_SPIKE_NR && fieldNumber <= LAST_SPIKE_NR)
	    {
	        findSpikeGroup(fieldNumber).add(nodeToAdd, internalSpikeGroupPosition(fieldNumber), topSpikePosition(fieldNumber));
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

	// TODO Fix when boardState/game class is ready. Responsibility of getting the first available free position lies in the model layer, not here
	int tempFieldNr = -1;
	int tempSpikeCount = 0;
	private int topSpikePosition(int fieldNumber)
	{
//	    int freeYPosition = currentGame.getBoardState.getNumberOfPieces(fieldNumber)%SPIKE_HEIGHT;
	    if (tempFieldNr == fieldNumber) tempSpikeCount++;
	    else
	    {
	        tempFieldNr = fieldNumber;
	        tempSpikeCount = 0;
	    }
	    int freeYPosition = tempSpikeCount;
	    // Reversing the int for bottom spikes to fit the GridPane row ordering
	    if (fieldNumber < FIRST_TOP_SPIKE_NR) freeYPosition = Math.abs(freeYPosition - (SPIKE_HEIGHT - 1));
	    return freeYPosition;
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
	
    
    
	List<PieceController> pieceArrayBlack = new ArrayList<PieceController>();
	List<PieceController> pieceArrayWhite = new ArrayList<PieceController>();
	
	/**
	 * Creating and adding pieces to lists 
	 */
	public void addPieces(){
	    tempPieceInsertion();
	}
	
	

	
	/** Sets up the game-board for a new game
	 * 	Clears board, gets method that creates pieces.
	 * 	Then sets pieces to their position on game-board.
	 */
	public void startBoard()
	{
		addPieces();
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
        /* TODO This ImageView of piece should be a custom node and maybe visitor pattern should be 
         * applied if many other types should also be handled by spike groups. */
        /* TODO Implement legality of move */
        if (dragDrop.getGestureSource() instanceof ImageView /* && dragEvent.getDragboard().hasImage() - maybe? */)
        {
            GridPane currentSpike = (GridPane) dragDrop.getGestureTarget();
            double spikeColumnWidth = currentSpike.getWidth()/SPIKE_WIDTH;
            double mousePosInSpike = dragDrop.getX();
            int hoverColumnNr = (int) (mousePosInSpike/spikeColumnWidth);
            // Oh lord, what a mess
            int tempSpikeGroupNr = tempWhichSpikeGroup(currentSpike);
            if (tempSpikeGroupNr < 2) hoverColumnNr = Math.abs(hoverColumnNr - (SPIKE_WIDTH - 1));
            int boardFieldNr = tempSpikeGroupNr*SPIKE_WIDTH + hoverColumnNr;
            
            System.out.println("\nSpikeWidth: " + spikeColumnWidth + " Mouse Pos in group: " + mousePosInSpike);
            System.out.println("Hovering over column: " + hoverColumnNr + " FieldNr on Board: " + boardFieldNr);
            System.out.print("Spikegroupnr: " + tempWhichSpikeGroup(currentSpike) + "   ");
            // TODO correct inserting when data can be retrieved from boardstate
            // TODO Transfer PieceControllers ImageView, not just create from its picture  - addPieceToField((ImageView) dragDrop.getSource(), boardFieldNr);
            addPieceToField(new ImageView(dragDrop.getDragboard().getImage()), boardFieldNr);
            dragDrop.setDropCompleted(true);
        }
//        dragDrop.consume();   // TODO Consume or not here?
    }
    
	public AnchorPane getFullBoard()
	{
	    return fullBoard;
	}
	
	public List<Node> getBoardSpikes()
	{
	    return boardSpikes;
	}
	
	@FXML
	public void initialize()
	{   

	}
	
	
	
	

    // Temp method that inserts pieces directly in GUI until game logic is up and running
    private void tempPieceInsertion()
    {
        tempClearAllSpikes();
        Map<Integer, Integer> blackMap = new HashMap<Integer, Integer>()
        {
            { put(5, 5); put(7, 3); put(12, 5); put(23, 2); }
        };
        Map<Integer, Integer> whiteMap = new HashMap<Integer, Integer>()
        {
            { put(0, 2); put(11, 5); put(16, 3); put(18, 5); }
        };
        PieceController currentPieceController;
        for (Map.Entry<Integer, Integer> entry: blackMap.entrySet())
        {
            for (int i = 0; i<entry.getValue(); i++)
            {
                currentPieceController = new PieceController(false, this, entry.getKey());
                pieceArrayBlack.add(currentPieceController);
                addPieceToField(currentPieceController.getPieceNode(), entry.getKey());
            }
        }
        for (Map.Entry<Integer, Integer> entry: whiteMap.entrySet())
        {
            for (int i = 0; i<entry.getValue(); i++)
            {
                currentPieceController = new PieceController(true, this, entry.getKey());
                pieceArrayWhite.add(currentPieceController);
                addPieceToField(currentPieceController.getPieceNode(), entry.getKey());
            }
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
    
    // Temp method until game logic is up and running
    private void tempClearAllSpikes()
    {
        for (int i = 0; i<=LAST_SPIKE_NR; i = i+SPIKE_WIDTH)
        {
            ((GridPane) findSpikeGroup(i)).getChildren().clear();
        }
    }
    
}