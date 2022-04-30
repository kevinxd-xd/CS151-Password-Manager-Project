package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AccountsReader {
	
	private File inputFile;
	
	public AccountsReader() {
		this.inputFile = new File("./src/model/Accounts.csv");
	}
	
	public Account toAccObj(String[] input) {
		Account createAccount = new Account();
		createAccount.setEmail(input[0]);
		createAccount.setPassword(input[1]);
		createAccount.setWebsiteName(input[2]);
		createAccount.setCreationDate(LocalDate.parse(input[3]));
		createAccount.setExpirationDate(LocalDate.parse(input[4]));
		createAccount.setFavorited(Boolean.parseBoolean(input[5]));
		createAccount.setUserID(Integer.parseInt(input[6]));
		
		
		return createAccount;
	}
	
	public ArrayList<Account> getAllAccounts(User inputUser) throws IOException {
		FileReader csvFileReader = new FileReader(this.inputFile);
		BufferedReader csvLineReader = new BufferedReader(csvFileReader);
		
		ArrayList<Account> accs = new ArrayList<Account>();
		String line;
		while ((line = csvLineReader.readLine()) != null) {
			if (Integer.parseInt(line.split(",")[6]) == inputUser.getUserID()) {
				accs.add(toAccObj(line.split(",")));
			}
		}
		csvLineReader.close();
		csvFileReader.close();
		return accs;
	}
}
