package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChangePWController implements ControllerInterface{

	@FXML
	private Button changePWBttn;
	@FXML
	public void changeAccDetails() {
		switchScene(changePWBttn, "view/login.fxml");
	}
}
