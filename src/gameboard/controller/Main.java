package gameboard.controller;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	
	
	@Override	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader stageLoader = new FXMLLoader(Main.class.getResource("/gameboard/view/MainStage.fxml"));
			AnchorPane mainAnchor = (AnchorPane) stageLoader.load();
			Scene mainScene = new Scene(mainAnchor);
			primaryStage.setScene(mainScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
