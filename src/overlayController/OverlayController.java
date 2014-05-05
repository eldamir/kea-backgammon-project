package overlayController;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import dice.Dice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class OverlayController implements Initializable {	
	
	
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
	private Button btnOverlayRollDice;
	
	@FXML
	private Button btnOverlayMenu;
	
	@FXML
	private Button btnOverlayHint;	
	
	private Dice[] dice = new Dice[]{new Dice(),new Dice()};
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}		
	
	public void rollDice(ActionEvent event)
	{		
		System.out.println("dice rolled");
		setDice(dice[0].roll(), dice[1].roll());
		
		//Disable button until players next turn
		//btnOverlayRollDice.setDisable(true);		
	}
	
	public void setDice(int dice1, int dice2) 
	{	
		String diceOne = Integer.toString(dice1);
		String diceTwo = Integer.toString(dice2);	  
		
		Image image = new Image(getClass().getResourceAsStream("../images/" + diceOne + "small.png"));	   
		lblOverlayDiceOne.setGraphic(new ImageView(image));
			
		Image image2 = new Image(getClass().getResourceAsStream("../images/" + diceTwo + "small.png"));
		lblOverlayDiceTwo.setGraphic(new ImageView(image2));	
	}
	
	public void openMenu(ActionEvent event)
	{		
		System.out.println("menu triggered");
		
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
	
	

}







	


