package controllers;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecurityQController implements ControllerInterface{

	private CommonObjs appInstance = CommonObjs.getInstance();
	
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
		if(appInstance.getCurrentUser().getQuestionAnswer().equals(inputAnswer.getText()))
			switchScene(checkAns,"view/changepw.fxml");
		else {
			errLabel.setText("Wrong Answer!");
			return;
		}
	}
	@FXML
	public void initialize() {
		questionLabel.setText(appInstance.getCurrentUser().getQuestion());
		//load question from database
	}
}
