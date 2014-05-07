package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
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
	 * @param piece The piece node to add to board
	 * @param spikeNumber The spike the piece is added to 
	 */
	public void addPieceToField(Node piece, int spikeNumber)
	{
		((VBox) boardSpikes.get(spikeNumber)).getChildren().add(piece);
	}

	public List<Node> getBoardSpikes()
	{
		return boardSpikes;
	}

	
	/** Sets up the gameboard for a new game
	 * 
	 */
	public void startBoard()
	{
		addPieceToField(new PieceController(true, this).getPieceView(), 0);
		addPieceToField(new PieceController(true, this).getPieceView(), 0);

		addPieceToField(new PieceController(false, this).getPieceView(), 5);
		addPieceToField(new PieceController(false, this).getPieceView(), 5);
		addPieceToField(new PieceController(false, this).getPieceView(), 5);
		addPieceToField(new PieceController(false, this).getPieceView(), 5);
		addPieceToField(new PieceController(false, this).getPieceView(), 5);
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