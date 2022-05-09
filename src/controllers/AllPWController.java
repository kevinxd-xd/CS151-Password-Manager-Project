package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import application.CommonObjs;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Account;
import model.AccountsReader;
import model.AccountsWriter;

//Controls the logic behind the All Passwords table
public class AllPWController {
	
	private CommonObjs appInstance = CommonObjs.getInstance();
	
	@FXML
	private Button addBttn;
	public void openAddAccStage() {
		try {
			Pane pwPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("view/createentry.fxml"));
			Stage accStage = new Stage();
			Scene addScene = new Scene(pwPane, 330, 400);
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
	private TableColumn<Account, String> credEmail;
	@FXML
	private TableView<Account> passwordTable;
	@FXML
	private Label statusLbl;
	//initializes the table when user clicks on the All Passwords Tab
	@FXML
	public void initialize() {
		appInstance.setPasswordTable(passwordTable);
		appInstance.setStatusLbl(statusLbl);
		appInstance.setAccToEdit(null);
		updateAccList();
		ObservableList<Account> list = FXCollections.observableArrayList(appInstance.getAccountList());
		credEmail.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));
		credWeb.setCellValueFactory(new PropertyValueFactory<Account, String>("websiteName"));
		credUser.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
		credCreation.setCellValueFactory(new PropertyValueFactory<Account, String>("creationDate"));
		credExpire.setCellValueFactory(new PropertyValueFactory<Account, String>("expirationDate"));
		credPW.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
		passwordTable.setItems(list);
	}
	
	
	@FXML
	private Button copyBttn;
	//Copies password to clipboard of the selected row in the table
	@FXML
	public void copyToClip() {
		if (passwordTable.getSelectionModel().getSelectedItem() == null) {
			statusLbl.setText("Status: Please select an entry to copy a password from");
			return;
		}
		PassUtil pwUtil = new PassUtil();
		String pwSelected = pwUtil.decrypt(passwordTable.getSelectionModel().getSelectedItem().getPassword())  ;
		final Clipboard clip = Clipboard.getSystemClipboard();
		final ClipboardContent pwContent = new ClipboardContent();
		pwContent.putString(pwSelected);
		clip.setContent(pwContent);
		statusLbl.setText("Status: Copied to clipboard!");
	}
	
	@FXML
	private TextField searchBar;
	@FXML
	public void filterList() {
        // Search algorithm pulling each selection model and then checking contents with
        // contains
        AccountsReader ar = new AccountsReader();
        
        String searchEntry = searchBar.getText();
        ArrayList<Account> filteredAccs = new ArrayList<>();
        try {
            ArrayList<Account> accs = ar.getAllAccounts(appInstance.getCurrentUser());
            
            for (Account a : accs) {
            	//searches database for search entry
                if (a.getWebsiteName().contains(searchEntry) || a.getUsername().contains(searchEntry)) {
                        filteredAccs.add(a);           
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ObservableList<Account> filteredList = FXCollections.observableArrayList(filteredAccs);
        //Sets the visible password list to the ones containing the searched term
        passwordTable.setItems(filteredList);
	}
	
	
	@FXML
	private Button deleteBttn;
	//Deletes account depending on selected row in the table
	@FXML
	public void delete()  {
		if (passwordTable.getSelectionModel().getSelectedItem() == null) {
			statusLbl.setText("Status: Please select an entry to delete");
			return;
		}
		
		try {
			Account acc = passwordTable.getSelectionModel().getSelectedItem();
			//deletes TempAccounts.csv to refresh contents
			new File("./resources/data/TempAccounts.csv").getAbsoluteFile().delete();
			AccountsWriter aw = new AccountsWriter();
			AccountsReader ar = new AccountsReader();
			//writes to TempAccounts.csv or makes new Accounts file if only one account remains
			ArrayList<Account> allAccounts = ar.getAllAccounts();
			if (allAccounts.size() == 1) {
				String abs = new File("./resources/data/Accounts.csv").getAbsolutePath();
				new File(abs).delete();
				new File(abs).createNewFile();
			}
			else {
				for(Account a : allAccounts) {
					if(!a.equals(acc)) {
						aw.writeTemp(aw.toString(a));
					}
				}
				//writes in new password for user
				String abs = new File("./resources/data/Accounts.csv").getAbsolutePath();
				new File(abs).delete();
				aw.getInputFile().renameTo(new File(abs));
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		refreshTable();
		statusLbl.setText("Status: Successfully deleted!");
	}
	
	@FXML
	private Button editBttn;
	@FXML
	public void editEntry() {
		try {
			if (passwordTable.getSelectionModel().getSelectedItem() == null) {
				statusLbl.setText("Status: Please select an entry to edit");
				return;
			}
			appInstance.setAccToEdit(passwordTable.getSelectionModel().getSelectedItem());
			Pane pwPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("view/createentry.fxml"));
			Stage accStage = new Stage();
			Scene addScene = new Scene(pwPane, 330, 400);
			accStage.setScene(addScene);
			accStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Method to grab the latest csv file for accounts
	 */
	private void updateAccList() {
		AccountsReader ar = new AccountsReader();
		try {
			appInstance.setAccountList(ar.getAllAccounts(appInstance.getCurrentUser()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Refreshes the table view of passwords
	 */
	private void refreshTable() {
		updateAccList();
		ObservableList<Account> list = FXCollections.observableArrayList(appInstance.getAccountList());
		passwordTable.setItems(list);
		statusLbl.setText("Status: List Refreshed!");
	}
	
}
