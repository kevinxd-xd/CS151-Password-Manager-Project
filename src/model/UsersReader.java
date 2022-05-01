package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//File reader for users
public class UsersReader {

	private File inputFile;
	
	public UsersReader() {
		this.inputFile = new File("./resources/data/Users.csv");
	}
	
	//retrieves user entered username, email, and security details 
	public User toUserObj(String[] input) {
		User createUser = new User();
		createUser.setUsername(input[0]);
		createUser.setEmail(input[1]);
		createUser.setPassword(input[2]);
		createUser.setQuestion(input[3]);
		createUser.setQuestionAnswer(input[4]);
		createUser.setUserID(Integer.parseInt(input[5]));
		
		return createUser;
	}
	//returns ArrayList<User> of all users in inputFile
	public ArrayList<User> getAllUser() throws IOException {
		FileReader csvFileReader = new FileReader(this.inputFile);
		BufferedReader csvLineReader = new BufferedReader(csvFileReader);
		
		ArrayList<User> accs = new ArrayList<User>();
		String line;
		while ((line = csvLineReader.readLine()) != null) {
			accs.add(toUserObj(line.split(",")));
		}
		
		csvLineReader.close();
		csvFileReader.close();
		return accs;
	}
}
