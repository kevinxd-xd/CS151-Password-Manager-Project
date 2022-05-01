package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import model.UsersReader;
import model.UsersWriter;

//Controls the logic behind Change Password
public class ChangePWController implements ControllerInterface{
	
	private CommonObjs appInstance = CommonObjs.getInstance();
	
	@FXML
	private Button changePWBttn;
	@FXML
	private Label errLabel;
	@FXML
	private TextField inputPassword;
	@FXML
	private TextField inputRePassword;
	
	//changes the passwords of the users
	@FXML
	public void changeAccDetails() {
		try {
			//case if fields not filled
			if(inputPassword.getText().equals("") || inputRePassword.getText().equals("")) {
				errLabel.setText("Fields not filled!");
				return;
			}
			//case if passwords not matching
			if(!inputPassword.getText().equals(inputRePassword.getText())) {
				errLabel.setText("Passwords not matching!");
				return;
			}
			//deletes TempUsers.csv to refresh contents
			new File("./resources/data/TempUsers.csv").getAbsoluteFile().delete();
			UsersReader ur = new UsersReader();
			UsersWriter uw = new UsersWriter();
			//writes to TempUsers.csv
			ArrayList<User> userList = ur.getAllUser();
			for(User u : userList) {
				if(!u.getEmail().equals(appInstance.getCurrentUser().getEmail())) {
					uw.writeTemp(uw.toString(u));
				}
			}
			//writes in new password for user
			User u = appInstance.getCurrentUser();
			u.setPassword(inputPassword.getText());
			uw.writeTemp(uw.toString(u));
			String abs = new File("./resources/data/Users.csv").getAbsolutePath();
			new File(abs).delete();
			uw.getInputFile().renameTo(new File(abs));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		switchScene(changePWBttn, "view/login.fxml");
	}
}
