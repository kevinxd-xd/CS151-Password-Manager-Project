package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ForgotPWController implements MasterController{

	@FXML
	private Button backBttn;
	@FXML
	public void showLogin() {
		switchScene(backBttn, "view/login.fxml");
	}
	
	public void recoverPass() {
		// Will communicate with DAO to retrieve security question and answer corresponding with the user
	}
	
}
