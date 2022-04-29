package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AccountsWriter {
	private File inputFile;
	
	public AccountsWriter() {
		this.inputFile = new File("./src/model/Accounts.csv");
	}
	
	public String toString(Account accObj) {
		return accObj.getEmail() + "," + accObj.getPassword() + "," + accObj.getWebsiteName() + "," + accObj.getCreationDate().toString() + "," + accObj.getExpirationDate().toString() + "," + accObj.isFavorited() + "," + accObj.getUserID() + "\n";
	}
	
	public void write(String inputStr) throws IOException {
		FileWriter fw = new FileWriter(inputFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(inputStr);
		bw.close();
		fw.close();
	}
}
