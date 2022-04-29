package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;


public class SignUpController implements ControllerInterface{

	@FXML
	private ComboBox<String> securityComboBox;
	@FXML
	public void initialize() {
		securityComboBox.getItems().clear();
		// Needs to communicate with DAO for list of questions, this is a hard code basically
		securityComboBox.getItems().addAll("What is your favorite color?", "What was your first car?", "What was the name of your favorite teacher?");
	}
	
	
	/*
	 * This method will display the login page if the back button is pushed
	 */
	@FXML
	private Button backBttn;
	@FXML
	public void showLogin() {
		switchScene(backBttn, "view/login.fxml");
	}
	
	
	@FXML
	private Button createAccBttn;
	@FXML
	private CheckBox TOSToggle;
	@FXML
	public void createNewAcc() {
		// Checks if the check box is ticked
		if (TOSToggle.isSelected()) {
			switchScene(createAccBttn, "view/login.fxml");
		}
	}
	
}
