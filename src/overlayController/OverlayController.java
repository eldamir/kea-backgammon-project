package overlayController;

import java.io.IOException;

import internationalization.LanguageResource;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import dice.Dice;


public class OverlayController implements Initializable
{

	@FXML
	private Label lblOverlayUserName;

	@FXML
	private Label lblOverlayDifficulty;

	@FXML
	private Label lblOverlayScore;

	@FXML
	private Label lblOverlayDiceOne;

	@FXML
	private Label lblOverlayDiceTwo;

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

    @FXML
    private static AnchorPane gameMenuContainer;
    
	private Dice[] dice = new Dice[]{new Dice(),new Dice()};
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

		loadExternText("da", "DK");
	}

	/**
	 * Simple Method to load the externalized Strings in the desired Language.
	 */
	private void loadExternText(String language, String country)
	{
		loadGameMenu();
		loadBoard();
		LanguageResource.setLocale(language, country);
		setBtnOverlayHint(LanguageResource.getText("btnOverlayHintText"));
		setBtnOverlayMenu(LanguageResource.getText("btnOverlayMenuText"));
		setBtnOverlayRollDice(LanguageResource.getText("btnOverlayRollDiceText"));
	}		
	
	public void rollDice(ActionEvent event)
	{
		System.out.println("dice rolled");
		setDice(dice[0].roll(), dice[1].roll());

		// Disable button until players next turn
		// btnOverlayRollDice.setDisable(true);
	}

	public void setDice(int dice1, int dice2)
	{
		String diceOne = Integer.toString(dice1);
		String diceTwo = Integer.toString(dice2);

		Image image =
						new Image(
									getClass().getResourceAsStream(	"../images/"
																	+ diceOne
																	+ "small.png"));
		lblOverlayDiceOne.setGraphic(new ImageView(image));

		Image image2 =
						new Image(
									getClass().getResourceAsStream(	"../images/"
																	+ diceTwo
																	+ "small.png"));
		lblOverlayDiceTwo.setGraphic(new ImageView(image2));
	}
	
	public static void showMenu(){
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
	
	public void openMenu(ActionEvent event)
	{
		System.out.println("menu triggered");
		showMenu();

	}

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

	public void setBtnOverlayRollDice(String btnOverlayRollDiceText)
	{
		btnOverlayRollDice.setText(btnOverlayRollDiceText);
	}

	public void setBtnOverlayMenu(String btnOverlayMenuText)
	{
		btnOverlayMenu.setText(btnOverlayMenuText);

	}

	public void setBtnOverlayHint(String btnOverlayHintText)
	{
		btnOverlayHint.setText(btnOverlayHintText);
	}

	public Dice[] getDice()
	{
		return Arrays.copyOf(dice, 2);
	}
	
	private void loadBoard()
	{
		try 
		{
			boardContainer.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/gameboard/view/MainStage.fxml")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void loadGameMenu()
	{
		try 
		{
			gameMenuContainer.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/overlay/OverlayGameMenu.fxml")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
