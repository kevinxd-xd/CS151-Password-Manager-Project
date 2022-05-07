package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import application.CommonObjs;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Account;
import model.AccountsReader;
import model.AccountsWriter;
//controls the creating a new account entry in the All Passwords tab
public class CreateEntryController {
	
	private CommonObjs appInstance = CommonObjs.getInstance();
	
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
	private TextField inputEmail;
	@FXML
	private TextField inputExpire;
	@FXML
	private TextField inputSpec;
	@FXML
	private TextField inputCap;
	@FXML
	private TextField inputMin;
	@FXML
	private TextField inputMax;
	@FXML
	private Label errorLbl;
	@FXML
	public void saveToCSV() {
		if (validateFields()) {
			PassUtil pwUtil = new PassUtil();
			Account accToAdd = new Account();
			accToAdd.setCreationDate(LocalDate.now());
			accToAdd.setExpirationDate(LocalDate.now().plusDays(Integer.parseInt(inputExpire.getText())));
			accToAdd.setEmail(inputEmail.getText());
			accToAdd.setUsername(inputUser.getText());
			accToAdd.setFavorited(false);
			accToAdd.setUserID(appInstance.getCurrentUser().getUserID());
			accToAdd.setWebsiteName(inputWeb.getText());
			accToAdd.setPassword(pwUtil.encrypt(inputPW.getText()));
			
			AccountsWriter aw = new AccountsWriter();
			try {
				aw.write(aw.toString(accToAdd));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			saveBttn.getScene().getWindow().hide();
			
			try {
				AccountsReader ar = new AccountsReader();
				ObservableList<Account> list = FXCollections.observableArrayList(ar.getAllAccounts(appInstance.getCurrentUser()));
				appInstance.getPasswordTable().setItems(list);
				appInstance.getStatusLbl().setText("Status: Successfully added new entry!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// Validates the fields, generates the password, and fills in the textbox
	@FXML
	private Button genPassBttn;
	@FXML
	public void setRandPass() {
		if (validateGenPWFields()) {
			inputPW.setText(genPass(Integer.parseInt(inputMin.getText()), Integer.parseInt(inputMax.getText()), Integer.parseInt(inputSpec.getText()), Integer.parseInt(inputCap.getText())));
		}
	}
	
	
	// Generates a random string of characters
	private String genPass(int minLen, int maxLen, int specChars, int capChars) {
		//Password generator algorithm
        char[] specialChars = {33, 34, 35, 36, 37, 38, 39, 40, 40, 41, 42 , 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96, 123, 124, 125, 126};
        char[] regChars = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117,118, 119, 120, 121, 122};
       
        StringBuilder randPass = new StringBuilder();
        int baseLength;
        if (minLen == maxLen) {
        	baseLength = maxLen - specChars - capChars;
        }
        else {
        	baseLength = ThreadLocalRandom.current().nextInt(maxLen-minLen) + minLen - specChars - capChars;
        }
        
        // Generate base string with regular lowercase letters and numbers
        for (int i = 0; i < baseLength; i++) {
        	int randomNum = ThreadLocalRandom.current().nextInt(regChars.length - 1);
        	randPass.append(regChars[randomNum]);
        }
        
        //Insert random capital letter into random position
        for (int i = 0; i < capChars; i++) {
        	int currentLenPass = randPass.toString().length();
        	char randomCaptial = (char)(ThreadLocalRandom.current().nextInt(25) + 65);
        	if (currentLenPass < 2) {
        		randPass.append(randomCaptial);
        	}
        	else {
            	int insertAt = ThreadLocalRandom.current().nextInt(currentLenPass- 1);
            	randPass.insert(insertAt, randomCaptial);
        	}
        }
        
        //Insert random special character into random position
        for (int i = 0; i < specChars; i++) {
        	int currentLenPass = randPass.toString().length() - 1;
        	int randIndex = ThreadLocalRandom.current().nextInt(specialChars.length);
        	char randomSpecial = specialChars[randIndex];
        	if (currentLenPass < 2) {
        		randPass.append(randomSpecial);
        	}
        	else {
            	int insertAt = ThreadLocalRandom.current().nextInt(currentLenPass- 1);
            	randPass.insert(insertAt , randomSpecial);
        	}
        }
        
        return randPass.toString();
	}
	
	// Validates the non-pw related fields
	private boolean validateFields() {
		if (inputUser.getText().equals("") || inputWeb.getText().equals("") || inputPW.getText().equals("") || inputEmail.getText().equals("") || inputExpire.getText().equals("")) {
			errorLbl.setTextFill(Color.RED);
			errorLbl.setText("Not all fields are filled!");
			return false;
		}
		errorLbl.setText("");
		return true;
	}
	
	// Validates the generate password fields
	private boolean validateGenPWFields() {
		if (inputMin.getText().equals("") || inputMax.getText().equals("") || inputSpec.getText().equals("") || inputCap.getText().equals("")) {
			errorLbl.setTextFill(Color.RED);
			errorLbl.setText("Not all fields are filled!");
			return false;
		}
		try {
			Integer.parseInt(inputMin.getText());
			Integer.parseInt(inputMax.getText());
			Integer.parseInt(inputSpec.getText());
			Integer.parseInt(inputCap.getText());
			errorLbl.setText("");
		}
		catch(Exception e) {
			errorLbl.setTextFill(Color.RED);
			errorLbl.setText("Some fields are not filled correctly!");
			return false;
		}
		if ((Integer.parseInt(inputMax.getText()) < Integer.parseInt(inputSpec.getText()) + Integer.parseInt(inputCap.getText())) || (Integer.parseInt(inputMin.getText()) > Integer.parseInt(inputMax.getText()))) {
			errorLbl.setTextFill(Color.RED);
			errorLbl.setText("Some fields are not filled correctly!");
			return false;
		}
		
		return true;
	}
}
