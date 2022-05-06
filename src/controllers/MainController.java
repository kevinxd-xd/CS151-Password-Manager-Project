package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Account;
import model.AccountsReader;

//Controls the Home scene and the All Passwords Tab
public class MainController implements ControllerInterface{
	
	private CommonObjs appInstance = CommonObjs.getInstance();
	
	/*
	 * Method to change pane showing in main
	 */
	private void changePane(String fxmlPath) {
		try {
			AnchorPane newPane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource(fxmlPath));
			
			HBox mainBox = appInstance.getMainBox();
			
			if (mainBox.getChildren().size() > 1) {
				mainBox.getChildren().remove(1);
			}
			mainBox.getChildren().add(newPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private HBox appMainBox;
	@FXML
	public void initialize() {
		appInstance.setMainBox(appMainBox);
		
		AccountsReader ar = new AccountsReader();
        try {
            ArrayList<Account> pass = ar.getAllAccounts(appInstance.getCurrentUser());

            //Iterates through saved passwords to check if any are expired
            for (Account a : pass) {
                if (a.getExpirationDate().isBefore(LocalDate.now()) || a.getExpirationDate().equals(LocalDate.now())) {
                    Dialog<String> test = new Dialog<>();
                    DialogPane alertPane = (DialogPane) FXMLLoader
                            .load(getClass().getClassLoader().getResource("view/alert.fxml"));
                    test.setDialogPane(alertPane);
                    test.show();
                    //displays warning message if a password is expired
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		try {
			AnchorPane pwPane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/homepage.fxml"));
			HBox mainBox = appInstance.getMainBox();
			mainBox.getChildren().add(pwPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button homeBttn;
	@FXML
	public void showHome() {
		changePane("view/homepage.fxml");
	}
	
	@FXML
	private Button allPWBttn;
	@FXML
	public void showAllPW() {
		changePane("view/allpwpage.fxml");
	}
	
	@FXML
	private Button logoutBttn;
	@FXML
	public void logout() {
		switchScene(logoutBttn, "view/login.fxml");
	}

}
