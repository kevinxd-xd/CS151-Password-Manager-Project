package controllers;

import java.io.IOException;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

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
		
		
		// Check for expired passwords
		
		
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
