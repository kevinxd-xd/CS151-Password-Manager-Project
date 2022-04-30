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
	@FXML
	public void changeAccDetails() {
		try {
			if(inputPassword.getText().equals("") || inputRePassword.getText().equals("")) {
				errLabel.setText("Fields not filled!");
				return;
			}
			if(!inputPassword.getText().equals(inputRePassword.getText())) {
				errLabel.setText("Passwords not matching!");
				return;
			}
			new File("./resources/data/TempUsers.csv").getAbsoluteFile().delete();
			UsersReader ur = new UsersReader();
			UsersWriter uw = new UsersWriter();
			ArrayList<User> userList = ur.getAllUser();
			for(User u : userList) {
				if(!u.getEmail().equals(appInstance.getCurrentUser().getEmail())) {
					uw.writeTemp(uw.toString(u));
				}
			}
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
