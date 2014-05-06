package overlayGameMenuController;

import overlayController.OverlayController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OverlayGameMenuController {
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
    private Button btnGameMenuReturn;
	
	public void newGamePressed(ActionEvent event)
	{
		System.out.println("New game triggered");
	}
	
	public void loadPressed(ActionEvent event)
	{
		System.out.println("Load triggered");
	}
	
	public void savePressed(ActionEvent event)
	{
		System.out.println("Save triggered");
	}
	
	public void logOutPressed(ActionEvent event)
	{
		System.out.println("Log out triggered");
	}
	
	public void quitPressed(ActionEvent event)
	{
		System.out.println("Quit triggered");
		System.exit(0);
	}
	
	public void resumePressed(ActionEvent event)
	{
		System.out.println("Resume triggered");
		OverlayController.showMenu();
	}
}
