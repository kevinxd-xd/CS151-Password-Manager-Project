package controllers;

import java.util.ArrayList;

import application.CommonObjs;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import model.UsersReader;
//Controls login logic
public class LoginController implements ControllerInterface {

	private CommonObjs appInstance = CommonObjs.getInstance();


	// Gets all the users stored in database and searches for any matching users and checks the password
	@FXML
	private TextField usernameInput;
	@FXML
	private PasswordField passwordInput;
	@FXML
	private Button loginBttn;
	@FXML
	public void authenticate() {
		UsersReader ur = new UsersReader();
		String username = usernameInput.getText();
		String password = passwordInput.getText();

		try {
			ArrayList<User> acc = ur.getAllUser();
			for (User a : acc) {//Verifies entered login info matches stored credentials
				if (a.getUsername().equals(username)) {
					PassUtil pwUtil = new PassUtil();
					if (pwUtil.decrypt(a.getPassword()).equals(password)) {
						appInstance.setCurrentUser(a);
						switchScene(loginBttn, "view/main.fxml");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// When the sign up hyperlink is clicked, this method will switch to the sign up page
	@FXML
	private Hyperlink signUpHl;
	@FXML
	public void showSignUp() {
		switchScene(signUpHl, "view/signup.fxml");
	}

	// When the forgot password hyperlink is clicked, this method will switch to the forgot password page
	@FXML
	private Hyperlink forgotPWHl;
	@FXML
	public void showForgot() {
		switchScene(forgotPWHl, "view/forgot.fxml");
	}

}
