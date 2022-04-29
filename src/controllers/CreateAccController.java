package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateAccController {
	
	@FXML
	private Button cancelBttn;
	@FXML
	public void closeWindow() {
		cancelBttn.getScene().getWindow().hide();
	}
	
	@FXML
	private Button saveBttn;
	public void saveToCSV() {
		saveBttn.getScene().getWindow().hide();
	}
}
