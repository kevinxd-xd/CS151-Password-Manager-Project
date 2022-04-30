package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UsersWriter {
	private File inputFile;
	
	public UsersWriter() {
		this.inputFile = new File("./resources/data/Users.csv");
	}
	
	public String toString(User userObj) {
		return userObj.getUsername() + "," + userObj.getEmail() + "," + userObj.getPassword() + "," + userObj.getQuestion() + "," + userObj.getQuestionAnswer() + "," + userObj.getUserID() + "\n";
	}
	
	public void write(String inputStr) throws IOException {
		FileWriter fw = new FileWriter(inputFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(inputStr);
		bw.close();
		fw.close();
	}
}
