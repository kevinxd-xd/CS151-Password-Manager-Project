package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ForgotPWController implements ControllerInterface{

	@FXML
	private Button backBttn;
	@FXML
	public void showLogin() {
		switchScene(backBttn, "view/login.fxml");
	}
	
	@FXML
	private Button recoverBttn;
	@FXML
	public void recoverPass() {
		// Will communicate with DAO to retrieve security question and answer corresponding with the user
		switchScene(recoverBttn, "view/sec_question.fxml");
	}
	
}
