package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsersReader {

	private File inputFile;
	
	public UsersReader() {
		this.inputFile = new File("./src/model/Users.csv");
	}
	
	
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
	
	public boolean accExists(User userObj) throws IOException {
		ArrayList<User> allUsers = getAllUser();
		return allUsers.contains(userObj);
	}
	
	public static void main(String[] args) {
		try {
			User newaccs = new User();
			newaccs.setUsername("username");
			newaccs.setEmail("w");
			newaccs.setPassword("password");
			newaccs.setQuestion("weaf");
			newaccs.setQuestionAnswer("eawf");
			newaccs.setUserID(5);
			
			UsersReader ur = new UsersReader();
			System.out.println(ur.accExists(newaccs));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
