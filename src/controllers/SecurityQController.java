package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecurityQController implements ControllerInterface{
	
	
	@FXML
	private Button backBttn;
	@FXML
	private Label questionLabel;
	@FXML
	private TextField inputAnswer;
	@FXML
	private Label errLabel;
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
