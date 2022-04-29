package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class AllPWController {

	
	@FXML
	private Button addBttn;
	public void openAddAccStage() {
		try {
			Pane pwPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("view/createentry.fxml"));
			Stage accStage = new Stage();
			Scene addScene = new Scene(pwPane, 330, 350);
			accStage.setScene(addScene);
			accStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
