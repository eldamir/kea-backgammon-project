package controller;

import model.internationalization.LanguageResource;

import java.net.URL;
import java.util.ResourceBundle;

import controller.OverlayController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * 
 * Controller class for the graphical game menu component
 *
 */
public class OverlayGameMenuController implements Initializable{
	@FXML
	private Button btnGameMenuNewGame;
	@FXML
	private Button btnGameMenuLoad;
	@FXML
	private Button btnGameMenuSave;
	@FXML
	private Button btnGameMenuLogOut;
	@FXML
	private Button btnGameMenuQuit;
	@FXML
	private Button btnGameMenuResume;
	@FXML
	private Label btnGameMenuNewGameLabel;
	@FXML
	private Label btnGameMenuLoadLabel;
	@FXML
	private Label btnGameMenuSaveLabel;
	@FXML
	private Label btnGameMenuLogOutLabel;
	@FXML
	private Label btnGameMenuQuitLabel;
	@FXML
	private Label btnGameMenuResumeLabel;
	
	private BoardController boardController;
	private OverlayController overlayController;
	
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
		LanguageResource.setLocale(language, country);
		setBtnLblGameMenuNewGame(LanguageResource.getText("btnGameMenuNewGame"));
		setBtnLblGameMenuLoad(LanguageResource.getText("btnGameMenuLoad"));
		setBtnLblGameMenuSave(LanguageResource.getText("btnGameMenuSave"));
		setBtnLblGameMenuLogOut(LanguageResource.getText("btnGameMenuLogOut"));
		setBtnLblGameMenuQuit(LanguageResource.getText("btnGameMenuQuit"));
		setBtnLblGameMenuReturn(LanguageResource.getText("btnGameMenuResume"));
	}
	
	/**
	 * Triggered when "New game" button is pressed.
	 * Loads the game board with the default set up. 
	 * @param event
	 */
	public void newGamePressed(ActionEvent event)
	{
		System.out.println("New game triggered");
		boardController.startBoard();
		overlayController.showMenu();
	}
	
	/**
	 * Triggered when the "Load" button is pressed.
	 * No application logic is yet implemented.
	 * 
	 * @param event
	 */
	public void loadPressed(ActionEvent event)
	{
		System.out.println("Load triggered");
		overlayController.showMenu();
	}
	
	/**
	 * Triggered when the "Save" button is pressed.
	 * No application logic is yet implemented.
	 * 
	 * @param event
	 */
	public void savePressed(ActionEvent event)
	{
		System.out.println("Save triggered");
		overlayController.showMenu();
	}
	
	/**
	 * Triggered when the "Log out" button is pressed.
	 * No application logic is yet implemented.
	 * @param event
	 */
	public void logOutPressed(ActionEvent event)
	{
		System.out.println("Log out triggered");
		overlayController.showMenu();
	}
	
	/**
	 * Triggered when the "Quit" button is pressed.
	 * Exists the running application
	 * 
	 * @param event
	 */
	public void quitPressed(ActionEvent event)
	{
		System.out.println("Quit triggered");
		System.exit(0);
	}
	
	/**
	 * Triggered when the "Resume" button is pressed.
	 * Closes the game menu component and returns to the game
	 * 
	 * @param event
	 */
	public void resumePressed(ActionEvent event)
	{
		System.out.println("Resume triggered");
		overlayController.showMenu();
	}
	
	public void setBtnLblGameMenuNewGame(String st){
		btnGameMenuNewGameLabel.setText(st);
	}
	
	public void setBtnLblGameMenuLoad(String st){
		btnGameMenuLoadLabel.setText(st);
	}
	
	public void setBtnLblGameMenuSave(String st){
		btnGameMenuSaveLabel.setText(st);
	}
	
	public void setBtnLblGameMenuLogOut(String st){
		btnGameMenuLogOutLabel.setText(st);
	}
	
	public void setBtnLblGameMenuQuit(String st){
		btnGameMenuQuitLabel.setText(st);
	}
	
	public void setBtnLblGameMenuReturn(String st){
		btnGameMenuResumeLabel.setText(st);
	}

	public void setOverlayController(OverlayController overlayController)
	{
		this.overlayController = overlayController;
	}

	public void setBoardController(BoardController boardController)
	{
		this.boardController = boardController;
	}
}
