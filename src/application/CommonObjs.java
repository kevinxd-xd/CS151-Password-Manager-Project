package application;

import java.util.ArrayList;
import javafx.scene.layout.HBox;
import model.Account;
import model.User;

//This is a Singleton instance that will share objects among all the controllers
public class CommonObjs {

	private static CommonObjs appInstance = new CommonObjs();
	
	//Things that need to be shared
	private HBox mainBox;
	private User currentUser;
	private ArrayList<Account> accountList;
	
	
	private CommonObjs() {}
	
	public static CommonObjs getInstance() {
		return appInstance;
	}
	
	public HBox getMainBox() {
		return mainBox;
	}

	public void setMainBox(HBox mainBox) {
		this.mainBox = mainBox;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}
	
	
}
