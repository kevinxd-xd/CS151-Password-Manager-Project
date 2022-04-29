package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import model.UsersReader;

public class LoginController implements MasterController{

	
	/*
	 * This method will communicate with the DAO to authenticate the user
	 */
	@FXML
	private TextField usernameInput;
	@FXML
	private PasswordField passwordInput;
	@FXML
	private Button loginBttn;
	@FXML
	public void authenticate() {
		UsersReader ur = new UsersReader();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * When the sign up hyperlink is clicked, this method will switch to the sign up page
	 */
	@FXML
	private Hyperlink signUpHl;
	@FXML
	public void showSignUp() {
		switchScene(signUpHl, "view/signup.fxml");
	}
	
	/*
	 * When the forgot password hyperlink is clicked, this method will switch to the forgot password page
	 */
	@FXML
	private Hyperlink forgotPWHl;
	@FXML
	public void showForgot() {
		switchScene(forgotPWHl, "view/forgot.fxml");
	}

}
