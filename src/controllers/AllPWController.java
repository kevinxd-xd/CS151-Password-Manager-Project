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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Account;
import model.AccountsReader;


public class AllPWController {
	
//	private CommonObjs appInstance = CommonObjs.getInstance();
//	private ObservableList<Account> list = FXCollections.observableArrayList(appInstance.getAccountList());
	
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
	private TableView<Account> passwordTable;
	@FXML
	public void initialize() {
//		credWeb.setCellValueFactory(new PropertyValueFactory<Account, String>("web"));
//		credUser.setCellValueFactory(new PropertyValueFactory<Account, String>("user"));
//		credCreation.setCellValueFactory(new PropertyValueFactory<Account, String>("creation"));
//		credExpire.setCellValueFactory(new PropertyValueFactory<Account, String>("expiration"));
//		
//		passwordTable.setItems(list);
		
	}
}
