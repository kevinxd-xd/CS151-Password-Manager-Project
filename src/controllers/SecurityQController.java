package controllers;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//Controls the Security Question logic
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
	// Switches to the forgot password screen
	@FXML
	public void showForgot() {
		switchScene(backBttn, "view/forgot.fxml");
	}
	@FXML
	private Button checkAns;
	@FXML
	// verifies the questionAnswer for the user
	public void showChangePW() {
		if(appInstance.getCurrentUser().getQuestionAnswer().equals(inputAnswer.getText()))
			switchScene(checkAns,"view/changepw.fxml");
		else {
			//case if questionAnswer does not match
			errLabel.setText("Wrong Answer!");
			return;
		}
	}
	// loads question when this becomes the scene
	@FXML
	public void initialize() {
		questionLabel.setText(appInstance.getCurrentUser().getQuestion());
		// load question from database
	}
}
