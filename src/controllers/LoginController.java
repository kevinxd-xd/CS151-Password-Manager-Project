package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button loginBttn;
	@FXML
	private TextField usernameInput;
	@FXML
	private PasswordField passwordInput;
	@FXML
	private Hyperlink signUpHl;
	
	/*
	 * This method will communicate with the DAO to authenticate the user
	 */
	@FXML
	public void authenticate() {
		// Passes parameters to the DAO and will authenticate
	}
	
	/*
	 * When the sign up hyperlink is clicked, this method will switch to the sign up page
	 */
	@FXML
	public void showSignUp() {
		try {
			FXMLLoader signUpPage = new FXMLLoader(getClass().getResource("../view/signup.fxml"));
			Stage mainStage = (Stage) signUpHl.getScene().getWindow();
			mainStage.setScene(new Scene(signUpPage.load()));
			mainStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
