package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
//File reader for accounts
public class AccountsReader {
	
	private File inputFile;
	
	public AccountsReader() {
		this.inputFile = new File("./resources/data/Accounts.csv");
	}
	//adds entered account details to the list of saved accounts
	public Account toAccObj(String[] input) {
		Account createAccount = new Account();
		createAccount.setUsername(input[0]);
		createAccount.setEmail(input[1]);
		createAccount.setPassword(input[2]);
		createAccount.setWebsiteName(input[3]);
		createAccount.setCreationDate(LocalDate.parse(input[4]));
		createAccount.setExpirationDate(LocalDate.parse(input[5]));
		createAccount.setFavorited(Boolean.parseBoolean(input[6]));
		createAccount.setUserID(Integer.parseInt(input[7]));
		
		
		return createAccount;
	}
	//returns ArrayList<Account> of all accounts in inputFile
	public ArrayList<Account> getAllAccounts(User inputUser) throws IOException {
		FileReader csvFileReader = new FileReader(this.inputFile);
		BufferedReader csvLineReader = new BufferedReader(csvFileReader);
		
		ArrayList<Account> accs = new ArrayList<Account>();
		String line;
		while ((line = csvLineReader.readLine()) != null) {
			if (Integer.parseInt(line.split(",")[7]) == inputUser.getUserID()) {
				accs.add(toAccObj(line.split(",")));
			}
		}
		csvLineReader.close();
		csvFileReader.close();
		return accs;
	}
}
