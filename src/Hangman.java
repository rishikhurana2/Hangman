import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Hangman {
	public static void main(String[] args) {
		String numberString = JOptionPane.showInputDialog("please input a number");
		int number = Integer.parseInt(numberString);
		try {
			BufferedReader br = new BufferedReader(new FileReader("/Hangman/src/dictionary.txt"));
			String line = br.readLine();
			while(line != null) {
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
