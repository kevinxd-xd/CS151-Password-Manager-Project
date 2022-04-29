package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Account;
import model.AccountsWriter;

public class CreateEntryController {
	
	@FXML
	private Button cancelBttn;
	@FXML
	public void closeWindow() {
		cancelBttn.getScene().getWindow().hide();
	}
	
	
	@FXML
	private Button saveBttn;
	@FXML
	private TextField inputWeb;
	@FXML
	private TextField inputUser;
	@FXML
	private PasswordField inputPW;
	@FXML
	private Label errorLbl;
	@FXML
	public void saveToCSV() {
		if (inputUser.getText().equals("") || inputWeb.getText().equals("") || inputPW.getText().equals("")) {
			errorLbl.setTextFill(Color.RED);
			errorLbl.setText("Not all fields are filled!");
			return;
		}
		Account accToAdd = new Account();
		accToAdd.setCreationDate(LocalDate.now());
		accToAdd.setExpirationDate(LocalDate.now().plusDays(90));
		accToAdd.setEmail(inputUser.getText());
		accToAdd.setFavorited(false);
		accToAdd.setUserID(0); // fix later
		accToAdd.setWebsiteName(inputWeb.getText());
		accToAdd.setPassword(inputPW.getText());
		
		AccountsWriter aw = new AccountsWriter();
		try {
			aw.write(aw.toString(accToAdd));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		saveBttn.getScene().getWindow().hide();
	}
}
