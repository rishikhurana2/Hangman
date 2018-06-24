import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Hangman {
	public static void main(String[] args) throws IOException {
	//	LineNumberReader lnr = new LineNumberReader(br);
		String numberString = JOptionPane.showInputDialog("please input a number");
		int number = Integer.parseInt(numberString);
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < number; i++) {
			indexes.add(r.nextInt(3000));
		}
		int lineNum = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
			String TheLine;
			for (int i: indexes) {
				if (i == lineNum) {
					for (int j = 0; j < i; j++) {
						br.readLine();
					}
					TheLine = br.readLine();
					words.add(TheLine);
				}
				//System.out.println(br.readLine());
				lineNum++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}
		System.out.println(words.size());
	}
}
