package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecurityQController implements ControllerInterface{
	
	
	@FXML
	private Button backBttn;
	@FXML
	public void showForgot() {
		switchScene(backBttn, "view/forgot.fxml");
	}
	
	
	@FXML
	private Button checkAns;
	@FXML
	public void showChangePW() {
		switchScene(checkAns,"view/changepw.fxml");
	}
	
	
	@FXML
	public void initialize() {
		//load question from database
	}
}
