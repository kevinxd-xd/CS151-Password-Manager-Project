package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Interface that controllers implement
public interface ControllerInterface {
	/*
	 * Switches the scene that is on the stage using a node and the path of the fxml
	 */

	default public void switchScene(Node element, String path) {
		try {
			FXMLLoader page = new FXMLLoader(getClass().getClassLoader().getResource(path));
			Stage mainStage = (Stage) element.getScene().getWindow();
			mainStage.setScene(new Scene(page.load()));
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	default public void setTitle(Node element, String name) {
		Stage mainStage = (Stage) element.getScene().getWindow();
		mainStage.setTitle(name);
	}
	
}
