package me.anhvannguyen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * This is program to help parse a CSV file and take out the string representation
 * of the user/player input.
 * @author Anh
 *
 */
public class DataParser {

	UserManager userManager = new UserManager();
	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "rortest.csv";	// The name of the file to parse in
		
		// Sample lines from the text file, used for testing
		String sample1 = "\"Sep 17, 2014 06:42 PM\",1,MatchDetails,,1.0.2,iPhone,Apple iPad 4th Gen (Wi-Fi Only),,\"{ CurrentLevel : 0;  MatchCombo : 3R,3P;  MatchCurrentTurn : 1}\",";
		String sample2 = "\"Sep 17, 2014 06:42 PM\",7,MatchDetails,,1.0.2,iPhone,Apple iPad 4th Gen (Wi-Fi Only),,\"{ CurrentLevel : 0;  MatchCombo : 5B,5X,6X;  MatchCurrentTurn : 1}\",";
		String sample3 = "\"Sep 17, 2014 05:30 PM\",1,MatchDetails,,1.0.2,iPhone,Apple iPad 4th Gen (Wi-Fi Only),,\"{ CurrentLevel : 0;  MatchCombo : 3P;  MatchCurrentTurn : 1}\",";
		
		
		DataParser parser = new DataParser();
		parser.processFile(fileName);
		parser.printResult();
	}
	
	
	/**
	 * Read in the file and parse the data line by line.
	 */
	public void processFile(String fileName) throws FileNotFoundException {
		File textFile = new File(fileName);
		FileReader fileReader = new FileReader(textFile);
		BufferedReader bufferReader = new BufferedReader(fileReader);
		try {
			StringBuilder sb = new StringBuilder();
			String line = bufferReader.readLine();
				
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = bufferReader.readLine();
				if (line != null)
					parseLineData(line.toString());
			}
			//System.out.print(sb.toString());	// Print the contents of file
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Line parser that splits up the data into a more usable format
	 */
	public void parseLineData(String line) {
		// Example of data after we split the string, the data we needed is from the
		// 2nd and 4th line which is the date and the game data.
		// 
		
		/* *** Sample Data ***
		 * (blank line)
		 * Sep 17, 2014 06:42 PM 
		 * ,1,MatchDetails,,1.0.2,iPhone,Apple iPad 4th Gen (Wi-Fi Only),, 
		 * { CurrentLevel : 0; MatchCombo : 3R,3P; MatchCurrentTurn : 1} 
		 * ,
		 */	
		String[] splitString = line.split("\"");

		// Since we didn't have a way to identify individual user, we used the date string.
		// The date generated is when the file is made, so similar date represents the 
		// same user.  At the time, this was in our test phase and there are a limited
		// numbers of user and overlap is unlikely to happen but still possible.
		String user = splitString[1];
		
		// The 4th item in the array has the actual user game data.  Now we removed all
		// the blank space and split the data. We only need the 2nd item that
		// has the "MatchCombo"
		/* *** Sample Data ***
		 * {CurrentLevel:0
		 *	MatchCombo:3R,3P
		 *	MatchCurrentTurn:1}
		 */
		String[] userData = splitString[3].replaceAll("\\s+", "").split(";");
		
		// With the MatchCombo line, we only need to get the item to the right of colon (":").
		/* *** Sample Data ***
		 * MatchCombo:3R,3P
		 */
		String[] comboData = userData[1].split(":");
		String combo = comboData[1];
		
		// Have the user manager add the result to the list
		userManager.add(user, combo);
	}
	
	/**
	 * Print the data
	 */
	public void printResult() {
		userManager.printAllUser();
	}

}

