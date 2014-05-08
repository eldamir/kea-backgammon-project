package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import model.piece.Piece;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class BoardController 
{

	// End homes for pieces
	@FXML
	private VBox blackHome;

	@FXML
	private VBox whiteHome;

	// The 4 Groups of spikes
	@FXML
	private HBox pieceChamberNE;

	@FXML
	private HBox pieceChamberSE;

	@FXML
	private HBox pieceChamberNW;

	@FXML
	private HBox pieceChamberSW;



	@FXML
	private AnchorPane prisonField;


	// Spikeboxes
	@FXML
	private VBox spikeNE19;

	@FXML
	private VBox spikeNE20;

	@FXML
	private VBox spikeNE21;

	@FXML
	private VBox spikeNE22;

	@FXML
	private VBox spikeNE23;

	@FXML
	private VBox spikeNE24;

	@FXML
	private VBox spikeNW13;

	@FXML
	private VBox spikeNW14;

	@FXML
	private VBox spikeNW15;

	@FXML
	private VBox spikeNW16;

	@FXML
	private VBox spikeNW17;

	@FXML
	private VBox spikeNW18;

	@FXML
	private VBox spikeSE1;

	@FXML
	private VBox spikeSE2;

	@FXML
	private VBox spikeSE3;

	@FXML
	private VBox spikeSE4;

	@FXML
	private VBox spikeSE5;

	@FXML
	private VBox spikeSE6;

	@FXML
	private VBox spikeSW10;

	@FXML
	private VBox spikeSW11;

	@FXML
	private VBox spikeSW12;

	@FXML
	private VBox spikeSW7;

	@FXML
	private VBox spikeSW8;

	@FXML
	private VBox spikeSW9;

	private List<Node> boardSpikes;
	
	/** Adds a piece to the gameboard
	 * @param p1 The piece node to add to board
	 * @param spikeNumber The spike the piece is added to 
	 */
	public void addPieceToField(Node p1, int spikeNumber)
	{
		((VBox) boardSpikes.get(spikeNumber)).getChildren().add(p1);
	}

	public List<Node> getBoardSpikes()
	{
		return boardSpikes;
	}
	
	
	List<PieceController> pieceArrayBlack = new ArrayList<PieceController>();
	List<PieceController> pieceArrayWhite = new ArrayList<PieceController>();
	
	public void addPieces(){
		
		for(int i = 1; i <= 15; i++) {
		    pieceArrayWhite.add(new PieceController(true, this));
		}
		
		for(int i = 1; i <= 15; i++) {
		    pieceArrayBlack.add(new PieceController(false, this));
		}

	}
	
	
	/** Sets up the gameboard for a new game
	 * 
	 */
	public void startBoard()
	{
		for (int i = 0; i < 24; i++) {
			((VBox) boardSpikes.get(i)).getChildren().clear();
		}
		
		//ImageView brick1 = (new PieceController(true, this).getPieceView());
		if(!(pieceArrayBlack.size() >=15) && !(pieceArrayWhite.size() >=15)){
			addPieces();
		}
		
		addPieceToField(pieceArrayWhite.get(0).getPieceView(), 11);
		addPieceToField(pieceArrayWhite.get(1).getPieceView(), 11);
		addPieceToField(pieceArrayWhite.get(2).getPieceView(), 11);
		addPieceToField(pieceArrayWhite.get(3).getPieceView(), 11);
		addPieceToField(pieceArrayWhite.get(4).getPieceView(), 11);
		
		addPieceToField(pieceArrayWhite.get(5).getPieceView(), 0);
		addPieceToField(pieceArrayWhite.get(6).getPieceView(), 0);
		
		addPieceToField(pieceArrayWhite.get(7).getPieceView(), 18);
		addPieceToField(pieceArrayWhite.get(8).getPieceView(), 18);
		addPieceToField(pieceArrayWhite.get(9).getPieceView(), 18);
		addPieceToField(pieceArrayWhite.get(10).getPieceView(), 18);
		addPieceToField(pieceArrayWhite.get(11).getPieceView(), 18);
		
		addPieceToField(pieceArrayWhite.get(12).getPieceView(), 16);
		addPieceToField(pieceArrayWhite.get(13).getPieceView(), 16);
		addPieceToField(pieceArrayWhite.get(14).getPieceView(), 16);
		
		addPieceToField(pieceArrayBlack.get(0).getPieceView(), 12);
		addPieceToField(pieceArrayBlack.get(1).getPieceView(), 12);
		addPieceToField(pieceArrayBlack.get(2).getPieceView(), 12);
		addPieceToField(pieceArrayBlack.get(3).getPieceView(), 12);
		addPieceToField(pieceArrayBlack.get(4).getPieceView(), 12);
		
		addPieceToField(pieceArrayBlack.get(5).getPieceView(), 23);
		addPieceToField(pieceArrayBlack.get(6).getPieceView(), 23);
		
		addPieceToField(pieceArrayBlack.get(7).getPieceView(), 5);
		addPieceToField(pieceArrayBlack.get(8).getPieceView(), 5);
		addPieceToField(pieceArrayBlack.get(9).getPieceView(), 5);
		addPieceToField(pieceArrayBlack.get(10).getPieceView(), 5);
		addPieceToField(pieceArrayBlack.get(11).getPieceView(), 5);
		
		addPieceToField(pieceArrayBlack.get(12).getPieceView(), 7);
		addPieceToField(pieceArrayBlack.get(13).getPieceView(), 7);
		addPieceToField(pieceArrayBlack.get(14).getPieceView(), 7);
	}
	
	
	@FXML
	public void initialize()
	{	
		boardSpikes = new ArrayList<Node>();
		boardSpikes.addAll(pieceChamberSE.getChildren());
		boardSpikes.addAll(pieceChamberSW.getChildren());
		Collections.reverse(boardSpikes.subList(0, 6)); // Lower 2 chambers are in reversed order of what we need
		Collections.reverse(boardSpikes.subList(6,12));
		boardSpikes.addAll(pieceChamberNW.getChildren());
		boardSpikes.addAll(pieceChamberNE.getChildren());
	}


}