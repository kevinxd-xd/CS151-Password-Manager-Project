package controllers;

import java.io.IOException;
import java.util.ArrayList;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;
import model.UsersReader;


public class ForgotPWController implements ControllerInterface{
	
	private CommonObjs appInstance = CommonObjs.getInstance();

	@FXML
	private Button backBttn;
	@FXML
	private TextField inputEmail;
	@FXML
	public void showLogin() {
		switchScene(backBttn, "view/login.fxml");
	}
	@FXML
	private Button recoverBttn;
	@FXML
	public void recoverPass() {
		// Will communicate with DAO to retrieve security question and answer corresponding with the user
		UsersReader ur = new UsersReader();
		try {
			ArrayList<User> userList = ur.getAllUser();
			User u = getUser(userList);
			if(u == null) {
				return;
			}
			appInstance.setCurrentUser(u);
		} catch (IOException e) {
			e.printStackTrace();
		}


		switchScene(recoverBttn, "view/sec_question.fxml");
	}
	public User getUser(ArrayList<User> userList) {

		for(User u : userList) {
			if(u.getEmail().equals(inputEmail.getText())) {
				return u;
			}
		}
		return null;
	}

}
