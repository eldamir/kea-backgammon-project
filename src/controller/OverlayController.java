package controller;

import java.io.IOException;

import model.internationalization.LanguageResource;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.dice.Dice;

/**
 * 
 * Controller for the graphical overlay component
 *
 */
public class OverlayController implements Initializable
{

	@FXML
	private Label lblOverlayUserName;

	@FXML
	private Label lblOverlayDifficulty;

	@FXML
	private Label lblOverlayScore;
	
	@FXML
	private Label lblOverlayPlayerNameText;

	@FXML
	private Label lblOverlayDifficultyText;

	@FXML
	private Label lblOverlayScoreText;

	@FXML
	private Label lblOverlayDiceOne;

	@FXML
	private Label lblOverlayDiceTwo;

	@FXML
	private static Label btnOverlayRollDiceLabel;

	@FXML
	private static Label btnOverlayMenuLabel;

	@FXML
	private static Label btnOverlayHintLabel;	

	@FXML
	private static Button btnOverlayRollDice;

	@FXML
	private static Button btnOverlayMenu;

	@FXML
	private static Button btnOverlayHint;	
	
    @FXML
    private SplitPane overlayContainer;
    
    @FXML
    private AnchorPane boardContainer;

    
    private BoardController boardController;
    private OverlayGameMenuController gameMenuController;
    
    @FXML
    private AnchorPane gameMenuContainer;
    
	private Dice[] dice = new Dice[]{new Dice(),new Dice()};
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		loadExternText("da", "DK");
		try
		{
			loadBoard();
			loadGameMenu();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Simple Method to load the externalized Strings in the desired Language.
	 */
	private void loadExternText(String language, String country)
	{
		LanguageResource.setLocale(language, country);
		setBtnOverlayHint(LanguageResource.getText("btnOverlayHintText"));
		setBtnOverlayMenu(LanguageResource.getText("btnOverlayMenuText"));
		setBtnOverlayRollDice(LanguageResource.getText("btnOverlayRollDiceText"));
		setLblOverlayPlayerNameText(LanguageResource.getText("lblOverlayUserName"));
		setLblOverlayDifficultyText(LanguageResource.getText("lblOverlayDifficulty"));
		setLblOverlayScoreText(LanguageResource.getText("lblOverlayScore"));
		
	}		
	
	/**
	 * Triggered when the user presses the "Roll Dice" button.
	 * Rolls two die, and invokes the setDice() method
	 * 
	 * @param event
	 */
	public void rollDice(ActionEvent event)
	{	dice[0].roll();
		dice[1].roll();
		setDice(dice[0],dice[1]);
		System.out.println("dice rolled");
		

		// Disable button until players next turn
		// btnOverlayRollDice.setDisable(true);
	}

	/**
	 * Sets the dice labels with dice images, that corresponds to the values of the face values of the passed die 
	 * 
	 * @param dice1 
	 * @param dice2
	 */
	public void setDice(Dice dice1, Dice dice2)
	{

		Image image =
						new Image(
									getClass().getResourceAsStream(	"../resources/dice/"
																	+ Integer.toString(dice1.getValue())
																	+ "small.png"));
		lblOverlayDiceOne.setGraphic(new ImageView(image));

		Image image2 =
						new Image(
									getClass().getResourceAsStream(	"../resources/dice/"
																	+ Integer.toString(dice2.getValue())
																	+ "small.png"));
		lblOverlayDiceTwo.setGraphic(new ImageView(image2));
	}
	
	/**
	 * Enables and shows the graphical Menu component 
	 */
	public void toggleMenu(){
		if(gameMenuContainer.isVisible() == false 
				&& gameMenuContainer.isDisable() == true)
		{
			gameMenuContainer.setVisible(true);
			gameMenuContainer.setDisable(false);
			btnOverlayMenu.setDisable(true);
			btnOverlayHint.setDisable(true);
			btnOverlayRollDice.setDisable(true);
		}
		else 
		{
			gameMenuContainer.setVisible(false);
			gameMenuContainer.setDisable(true);
			btnOverlayMenu.setDisable(false);
			btnOverlayHint.setDisable(false);
			btnOverlayRollDice.setDisable(false);
		}
	}
	
	/**
	 * Opens the graphical menu component
	 * 
	 * @param event 
	 */
	public void menuPressed()
	{
	    toggleMenu();
	}
	
	public void openMenu(KeyEvent event)
	{
		System.out.println(event.getCode()==KeyCode.ESCAPE);
	    	if (event.getCode()==KeyCode.ESCAPE)
		{
		    System.out.println("toggle menu");
		    toggleMenu();
		}
	}

	/**
	 * Triggered when user presses the "Hint" button.
	 * No application logic implemented !!!
	 * @param event
	 */
	public void triggerHint(ActionEvent event)
	{
		System.out.println("hint triggered");
	}

	public void setLblOverlayUserName(String lblOverlayUserNameText)
	{
		lblOverlayUserName.setText(lblOverlayUserNameText);
	}

	public void setLblOverlayDifficulty(String lblOverlayDifficultyText)
	{
		lblOverlayDifficulty.setText(lblOverlayDifficultyText);
	}

	public void setLblOverlayScore(String lblOverlayScoreText)
	{
		lblOverlayScore.setText(lblOverlayScoreText);
	}
	
	public void setLblOverlayPlayerNameText(String lblOverlayPlayerNameTextText)
	{
		lblOverlayPlayerNameText.setText(lblOverlayPlayerNameTextText);
	}

	public void setLblOverlayDifficultyText(String lblOverlayDifficultyTextText)
	{
		lblOverlayDifficultyText.setText(lblOverlayDifficultyTextText);
	}

	public void setLblOverlayScoreText(String lblOverlayScoreTextText)
	{
		lblOverlayScoreText.setText(lblOverlayScoreTextText);
	}


	public void setBtnOverlayRollDice(String btnOverlayRollDiceText)
	{
		btnOverlayRollDiceLabel.setText(btnOverlayRollDiceText);
//		btnOverlayRollDice.setTranslateX(-1.0*);
	}

	public void setBtnOverlayMenu(String btnOverlayMenuText)
	{
		btnOverlayMenuLabel.setText(btnOverlayMenuText);
	}

	public void setBtnOverlayHint(String btnOverlayHintText)
	{
		btnOverlayHintLabel.setText(btnOverlayHintText);
	}

	public Dice[] getDice()
	{
		return Arrays.copyOf(dice, 2);
	}
	
	private void loadBoard() throws IOException
	{
		FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/view/gameboard/gameboard.fxml"));
		boardContainer.getChildren().add((Node) boardLoader.load());
		boardController = (BoardController) boardLoader.getController();
	}
	
	private void loadGameMenu() throws IOException
	{
		FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/view/overlay/OverlayGameMenu.fxml"));
		gameMenuContainer.getChildren().add((Node) menuLoader.load());
		gameMenuController = menuLoader.getController();
		gameMenuController.setOverlayController(this);
		gameMenuController.setBoardController(boardController);
		
	}

}
