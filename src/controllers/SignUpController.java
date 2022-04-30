package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import model.UsersReader;
import model.UsersWriter;


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
	private TextField inputUser;
	@FXML
	private TextField inputEmail;
	@FXML
	private PasswordField inputPassword;
	@FXML
	private PasswordField inputRePassword;
	@FXML
	private TextField inputSecurityAnswer;
	@FXML
	private Label errLabel;
	@FXML
	private Button createAccBttn;
	@FXML
	private CheckBox TOSToggle;
	@FXML
	public void createNewAcc() {
		if(inputUser.getText().equals("") || inputEmail.getText().equals("") || inputPassword.getText().equals("") || inputRePassword.getText().equals("") || inputSecurityAnswer.getText().equals("") || securityComboBox.getSelectionModel().getSelectedItem() == null) {
			errLabel.setText("Fields not filled!");
			return;
		}
		if(!inputPassword.getText().equals(inputRePassword.getText())) {
			errLabel.setText("Passwords not matching!");
			return;
		}
		// Checks if the check box is ticked && if all box filled && passwords in both pw fields match
		if (!TOSToggle.isSelected()) {
			errLabel.setText("Terms of Service needs to be checked!");
			return;
		}
		UsersReader ur = new UsersReader();
		ArrayList<User> userList;
		try {
			userList = ur.getAllUser();
			for(User u : userList) {
				if(u.getEmail().equals(inputEmail.getText())) {
					errLabel.setText("Email already exists!");
					return;
				}
				if(u.getUsername().equals(inputUser.getText())) {
					errLabel.setText("Username already exists!");
					return;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		switchScene(createAccBttn, "view/login.fxml");
		User newUser = new User();
		newUser.setUsername(inputUser.getText());
		newUser.setEmail(inputEmail.getText());
		newUser.setPassword(inputPassword.getText());
		newUser.setQuestion(securityComboBox.getSelectionModel().getSelectedItem());
		newUser.setQuestionAnswer(inputSecurityAnswer.getText());
		newUser.setUserID(newUser.hashCode());
		UsersWriter uw = new UsersWriter();
		try {
			uw.write(uw.toString(newUser));
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
}
