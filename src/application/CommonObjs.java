package application;

import javafx.scene.layout.HBox;
import model.User;

public class CommonObjs {


	private static CommonObjs appInstance = new CommonObjs();
	
	//Things that need to be shared
	private HBox mainBox;
	private User currentUser;
	
	
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
	
	
}
