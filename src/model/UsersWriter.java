package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//File writer for users
public class UsersWriter {
	private File inputFile;
	
	public UsersWriter() {
		this.inputFile = new File("./resources/data/Users.csv");
	}
	//converts userObj csv form
	public String toString(User userObj) {
		return userObj.getUsername() + "," + userObj.getEmail() + "," + userObj.getPassword() + "," + userObj.getQuestion() + "," + userObj.getQuestionAnswer() + "," + userObj.getUserID() + "\n";
	}
	//Writes inputStr to the inputFile
	public void write(String inputStr) throws IOException {
		FileWriter fw = new FileWriter(inputFile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(inputStr);
		bw.close();
		fw.close();
	}
	//Writes inputStr to the TempUsers.csv file
	public void writeTemp(String inputStr) throws IOException {
		this.inputFile = new File("./resources/data/TempUsers.csv");
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
