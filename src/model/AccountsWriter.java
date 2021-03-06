package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// File writer for accounts
public class AccountsWriter {
	private File inputFile;
	
	public AccountsWriter() {
		this.inputFile = new File("./resources/data/Accounts.csv");
	}
	// converts accountObj csv form
	public String toString(Account accObj) {
		return accObj.getUsername() + "," + accObj.getEmail() + "," + accObj.getPassword() + "," + accObj.getWebsiteName() + "," + accObj.getCreationDate().toString() + "," + accObj.getExpirationDate().toString() + "," + accObj.isFavorited() + "," + accObj.getUserID() + "," + accObj.getAccID() + "\n";
	}
	// writes inputStr to the inputFile
	public void write(String inputStr) throws IOException {
		FileWriter fw = new FileWriter(inputFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(inputStr);
		bw.close();
		fw.close();
	}
	public void writeTemp(String inputStr) throws IOException {
		this.inputFile = new File("./resources/data/TempAccounts.csv");
		FileWriter fw = new FileWriter(inputFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(inputStr);
		bw.close();
		fw.close();
	}
	public File getInputFile() {
		return inputFile;
	}
}
