import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	static String DisplayLine;
	static String wordDisplayed = " ";
	JLabel displayLabel = new JLabel();
	ArrayList<Character> wordsOnScreen = new ArrayList<Character>();

	public static void main(String[] args) throws IOException {
		// LineNumberReader lnr = new LineNumberReader(br);
		String numberString = JOptionPane.showInputDialog("please input a number");
		int number = Integer.parseInt(numberString);
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		Stack<String> wordsReciever = new Stack<String>();
		Hangman h = new Hangman();
		Random r = new Random();
		for (int i = 0; i < number; i++) {
			indexes.add(r.nextInt(2999));
		}
		h.sort(indexes);
		int lineNum = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
			String line = "";
			while (line != null) {
				line = br.readLine();
				for (int i = 0; i < indexes.size(); i++) {
					if (indexes.get(i) == lineNum) {
						words.add(line);
					}
				}
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
		h.putInStack(words, wordsReciever);
		DisplayLine = wordsReciever.pop();
		System.out.println(DisplayLine);
		h.createUI();
	}

	public void sort(ArrayList<Integer> indexes) {
		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i < indexes.size() - 1; i++) {
				if (indexes.get(i) > indexes.get(i + 1)) {
					int temp = indexes.get(i);
					indexes.set(i, indexes.get(i + 1));
					indexes.set(i + 1, temp);
					swap = true;
				}
			}
		}
	}

	public void putInStack(ArrayList<String> words, Stack<String> wordsReciever) {
		for (int i = 0; i < words.size(); i++) {
			wordsReciever.push(words.get(i));
		}
	}

	public void createUI() {
		JOptionPane.showMessageDialog(null, "Guess some words");
		JFrame gameFrame = new JFrame();
		JPanel textHolder = new JPanel();
		gameFrame.setVisible(true);
		gameFrame.setSize(500, 250);
		gameFrame.add(textHolder);
		// wordLabel.setText();
		textHolder.add(displayLabel);
		gameFrame.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < DisplayLine.length(); i++) {
			if (DisplayLine.charAt(i) == e.getKeyChar()) {
				wordDisplayed = wordDisplayed + DisplayLine.charAt(i);
				wordsOnScreen.add(DisplayLine.charAt(i));
			}
		}
		displayLabel.setText(wordDisplayed);
		for (int i = 0; i < wordsOnScreen.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (wordsOnScreen.get(i).equals(wordsOnScreen.get(j))) {
					wordsOnScreen.remove(i);
				}
			}
		}
		for (int i = 0; i < wordsOnScreen.size(); i++) {
			System.out.println(wordsOnScreen.get(i));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
