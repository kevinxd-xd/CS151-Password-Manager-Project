package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ForgotPWController implements MasterController{

	@FXML
	private Button backBttn;
	@FXML
	public void showLogin() {
		switchScene(backBttn, "../view/login.fxml");
	}
}
