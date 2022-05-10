package controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import application.CommonObjs;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Account;
import model.AccountsReader;
import model.AccountsWriter;
//controls the creating a new account entry in the All Passwords tab
public class CreateEntryController {
	
	private CommonObjs appInstance = CommonObjs.getInstance();
	private boolean inEditMode;
	private Account editableAcc;
	private Account accToAdd;
	
	
	@FXML
	public void initialize() {
		inEditMode = false;
		Account accountEdit = appInstance.getAccToEdit();
		if (accountEdit != null) {
			inputWeb.setText(accountEdit.getWebsiteName());
			inputUser.setText(accountEdit.getUsername());
			PassUtil pwUtil = new PassUtil();
			inputPW.setText(pwUtil.decrypt(accountEdit.getPassword()));
			inputEmail.setText(accountEdit.getEmail());
			inputExpire.setText(String.valueOf(ChronoUnit.DAYS.between(LocalDate.now(), accountEdit.getExpirationDate())));
			inEditMode = true;
			editableAcc = accountEdit;
			appInstance.setAccToEdit(null);
		}
		else {
			editableAcc = null;
		}
	}
	
	@FXML
	private Button cancelBttn;
	@FXML
	public void closeWindow() {
		appInstance.setAccToEdit(null);
		cancelBttn.getScene().getWindow().hide();
	}
	
	
	@FXML
	private Button saveBttn;
	@FXML
	private TextField inputWeb;
	@FXML
	private TextField inputUser;
	@FXML
	private TextField inputPW;
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
	/*
	 * Saves entry to the csv file, will either edit or add new entry depending on the conditons
	 */
	@FXML
	public void saveToCSV() {
		if (validateFields()) {
			PassUtil pwUtil = new PassUtil();
			this.accToAdd = new Account();
			accToAdd.setCreationDate(LocalDate.now());
			accToAdd.setExpirationDate(LocalDate.now().plusDays(Integer.parseInt(inputExpire.getText())));
			accToAdd.setEmail(inputEmail.getText());
			accToAdd.setUsername(inputUser.getText());
			accToAdd.setFavorited(false);
			accToAdd.setUserID(appInstance.getCurrentUser().getUserID());
			accToAdd.setWebsiteName(inputWeb.getText());
			accToAdd.setPassword(pwUtil.encrypt(inputPW.getText()));
			accToAdd.setAccID(genID());
		}
		
		if (!this.accToAdd.equals(this.editableAcc)) {
			AccountsWriter aw = new AccountsWriter();
			try {
				aw.write(aw.toString(accToAdd));
				appInstance.getStatusLbl().setText("Status: Successfully added new entry!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		if (inEditMode && !this.accToAdd.equals(this.editableAcc)) {
			try {
				Account acc = editableAcc;
				//deletes TempAccounts.csv to refresh contents
				new File("./resources/data/TempAccounts.csv").getAbsoluteFile().delete();
				AccountsWriter aw = new AccountsWriter();
				AccountsReader ar = new AccountsReader();
				ArrayList<Account> allAccounts = ar.getAllAccounts();
				for(Account a : allAccounts) {
					if(!a.equals(acc)) {
						aw.writeTemp(aw.toString(a));
					}
				}
				//writes in new password for user
				String abs = new File("./resources/data/Accounts.csv").getAbsolutePath();
				new File(abs).delete();
				aw.getInputFile().renameTo(new File(abs));
				appInstance.getStatusLbl().setText("Status: Successfully edited entry!");
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Refresh the list after an edit or new entry
		try {
			AccountsReader ar = new AccountsReader();
			ObservableList<Account> list = FXCollections.observableArrayList(ar.getAllAccounts(appInstance.getCurrentUser()));
			appInstance.getPasswordTable().setItems(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		saveBttn.getScene().getWindow().hide();
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
	
	
	//93 + 33;
	// Generates a random string as a ID for each entry
	private String genID() {
		//Password generator algorithm
       
        StringBuilder randID = new StringBuilder();
        int baseLength = 15;
        
        // Generate base string with regular lowercase letters and numbers
        for (int i = 0; i < baseLength; i++) {
        	char randomChar = (char)(ThreadLocalRandom.current().nextInt(33) + 93);
        	randID.append(randomChar);
        }

        
        return randID.toString();
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
            	int insertAt = ThreadLocalRandom.current().nextInt(currentLenPass - 1);
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
		try {
			Integer.parseInt(inputExpire.getText());
			errorLbl.setText("");
		}
		catch(Exception e) {
			errorLbl.setTextFill(Color.RED);
			errorLbl.setText("Expiration date must be a number!");
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
