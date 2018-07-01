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
		Hangman h = new Hangman();
		Random r = new Random();
		for (int i = 0; i < number; i++) {
			indexes.add(r.nextInt(2999));
		}
		h.sort(indexes);
		int lineNum = 0;
		int currentIndex = 0;
//		System.out.println(c);
//		for (int i = 0; i< indexes.size(); i++) {
//			System.out.println(indexes.get(i));
//		}
		//System.out.println(indexes.get(0));
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
			String line = "";
			while (line != null) {
				for (int i = 0; i < indexes.size(); i++) {
					if (indexes.get(currentIndex) == lineNum) {
						currentIndex++;
						line = br.readLine();
						words.add(line);
					}
				}
				lineNum++;
			}
		//	System.out.println(lineNum);
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
		//System.out.println(words.size());
	}
	public void sort(ArrayList<Integer> indexes) {
		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i <indexes.size() - 1; i++) {
				if (indexes.get(i) > indexes.get(i + 1)) {
					int temp = indexes.get(i);
					indexes.set(i, indexes.get(i+1));
					indexes.set(i + 1, temp);
					swap = true;
				}
			}
		}
	}
}
