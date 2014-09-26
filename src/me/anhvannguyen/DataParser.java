package me.anhvannguyen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
		Pattern pattern = Pattern.compile("MatchCombo : ");
		Matcher matcher = pattern.matcher(line);
		
		String endString = "";
		boolean found = false;
		
		while(matcher.find()) {
			// Get the string after our matching pattern
			endString = line.substring(matcher.end());
			found = true;
		}
		
		// data is not the correct we need, no need to parse
		if (!found) {
			return;
		}
		
		/* *** Sample Data ***
		 * (blank line)
		 * Sep 17, 2014 06:42 PM 
		 * ,1,MatchDetails,,1.0.2,iPhone,Apple iPad 4th Gen (Wi-Fi Only),, 
		 * { CurrentLevel : 0; MatchCombo : 3R,3P; MatchCurrentTurn : 1} 
		 * ,
		 */
		// Take the original line and split all items between " (quote)
		String[] splitString = line.split("\"");

		// Since we didn't have a way to identify individual user, we used the date string.
		// The date generated is when the file is made, so similar date represents the 
		// same user.  At the time, this was in our test phase and there are a limited
		// numbers of user and overlap is unlikely to happen but still possible.
		String user = splitString[1];
		
		
		
		String[] comboData = endString.split(";");
		String combo = comboData[0];
		
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

