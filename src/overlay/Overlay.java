package overlay;


import overlayController.OverlayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Overlay extends Application
{
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			//Parent root = FXMLLoader.load(getClass().getResource("/overlay/OverlayFXml.fxml"));
			Parent overlay = FXMLLoader.load(getClass().getResource("/overlay/OverlayFXml.fxml"));
			//Scene scene = new Scene(root);
			Scene scene = new Scene(overlay);
			scene.getStylesheets().add(getClass().getResource("/overlay/overlay.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
//		OverlayController controller = new OverlayController();
//		controller.
	}

}







	
	


