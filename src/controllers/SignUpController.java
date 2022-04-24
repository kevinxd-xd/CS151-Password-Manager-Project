package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SignUpController {

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
		try {
			FXMLLoader signUpPage = new FXMLLoader(getClass().getResource("../view/login.fxml"));
			Stage mainStage = (Stage) backBttn.getScene().getWindow();
			mainStage.setScene(new Scene(signUpPage.load()));
			mainStage.setTitle("Login");
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
