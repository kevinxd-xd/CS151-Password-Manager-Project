package controllers;

import java.io.IOException;

import application.CommonObjs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Account;
import model.AccountsReader;

//Controls the logic behind the All Passwords table
public class AllPWController {
	
	private CommonObjs appInstance = CommonObjs.getInstance();
	
	@FXML
	private Button addBttn;
	public void openAddAccStage() {
		try {
			Pane pwPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("view/createentry.fxml"));
			Stage accStage = new Stage();
			Scene addScene = new Scene(pwPane, 330, 350);
			accStage.setScene(addScene);
			accStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private TableColumn<Account, String> credWeb;
	@FXML
	private TableColumn<Account, String> credUser;
	@FXML
	private TableColumn<Account, String> credCreation;
	@FXML
	private TableColumn<Account, String> credExpire;
	@FXML
	private TableColumn<Account, String> credPW;
	@FXML
	private TableView<Account> passwordTable;
	//initializes the table when user clicks on the All Passwords Tab
	@FXML
	public void initialize() {
		updateAccList();
		ObservableList<Account> list = FXCollections.observableArrayList(appInstance.getAccountList());
		credWeb.setCellValueFactory(new PropertyValueFactory<Account, String>("websiteName"));
		credUser.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));
		credCreation.setCellValueFactory(new PropertyValueFactory<Account, String>("creationDate"));
		credExpire.setCellValueFactory(new PropertyValueFactory<Account, String>("expirationDate"));
		credPW.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
		passwordTable.setItems(list);
		
	}
	
	@FXML
	private Button refreshBttn;
	//Refreshes all passwords in the table
	@FXML
	public void refreshTable() {
		updateAccList();
		ObservableList<Account> list = FXCollections.observableArrayList(appInstance.getAccountList());
		passwordTable.setItems(list);
	}
	
	
	@FXML
	private Button copyBttn;
	//Copies password to clipboard of the selected row in the table
	@FXML
	public void copyToClip() {
		if (passwordTable.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		String pwSelected = passwordTable.getSelectionModel().getSelectedItem().getPassword();
		final Clipboard clip = Clipboard.getSystemClipboard();
		final ClipboardContent pwContent = new ClipboardContent();
		pwContent.putString(pwSelected);
		clip.setContent(pwContent);
		
	}
	
	/*
	 * Method to grab the latest csv file for accounts
	 */
	public void updateAccList() {
		AccountsReader ar = new AccountsReader();
		try {
			appInstance.setAccountList(ar.getAllAccounts(appInstance.getCurrentUser()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
