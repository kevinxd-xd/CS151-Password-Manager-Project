package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * The main class will work to launch the application by initializing the master controller
 */
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		/*
		 * Initializes the first scene to the login page
		 */
		try {
			HBox root = (HBox)FXMLLoader.load(getClass().getResource("../view/login.fxml"));
			Scene scene = new Scene(root,1000,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}