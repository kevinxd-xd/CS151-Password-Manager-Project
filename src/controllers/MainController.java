package controllers;

import java.io.IOException;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainController implements ControllerInterface{
	
	private CommonObjs appInstance = CommonObjs.getInstance();

	@FXML
	private HBox appMainBox;
	@FXML
	public void initialize() {
		appInstance.setMainBox(appMainBox);
		
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
		try {
			AnchorPane pwPane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/homepage.fxml"));
			
			HBox mainBox = appInstance.getMainBox();
			
			if (mainBox.getChildren().size() > 1) {
				mainBox.getChildren().remove(1);
			}
			
			mainBox.getChildren().add(pwPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button allPWBttn;
	@FXML
	public void showAllPW() {
		try {
			AnchorPane pwPane = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("view/allpwpage.fxml"));
			
			HBox mainBox = appInstance.getMainBox();
			
			if (mainBox.getChildren().size() > 1) {
				mainBox.getChildren().remove(1);
			}
			
			mainBox.getChildren().add(pwPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
