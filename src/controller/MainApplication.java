package controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainApplication extends Application
{
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Parent overlay = FXMLLoader.load(getClass().getResource("/view/overlay/OverlayFXml.fxml"));
			Scene scene = new Scene(overlay);
			scene.getStylesheets().add(getClass().getResource("/view/overlay/overlay.css").toExternalForm());
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