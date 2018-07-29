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
	static Hangman h = new Hangman();
	static String underScores = " ";
	static ArrayList<Character> underScoresArray = new ArrayList<Character>();
	static Stack<String> wordsReciever = new Stack<String>();
	static JLabel label = new JLabel();
	JLabel livesCounter = new JLabel();
	JFrame gameFrame = new JFrame();
	static String set = "";
	static String newSet = "";
	int lives = 10;

	public static void main(String[] args) throws IOException {
		// LineNumberReader lnr = new LineNumberReader(br);
		String numberString = JOptionPane.showInputDialog("please input a number");
		int number = Integer.parseInt(numberString);
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> indexes = new ArrayList<Integer>();
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
		for (int i = 0; i < DisplayLine.length(); i++) {
			set += "_" + " ";
		}
		label.setText(set);
		int length = DisplayLine.length();
		for (int i = 0; i < length; i++) {
			underScoresArray.add('_');
		}
		for (int i = 0; i < underScoresArray.size(); i++) {
			underScores += underScoresArray.get(i) + " ";
		}
		h.createUI();
		underScores = "";
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
		JPanel textHolder = new JPanel();
		gameFrame.addKeyListener(this);
		gameFrame.setVisible(true);
		gameFrame.setSize(500, 250);
		gameFrame.add(textHolder);
		textHolder.add(label);
		textHolder.add(livesCounter);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < DisplayLine.length(); i++) {
			if (e.getKeyChar() == DisplayLine.charAt(i)) {
				underScoresArray.set(i, DisplayLine.charAt(i));
			}
		}
		for (int i = 0; i < underScoresArray.size(); i++) {
			underScores += underScoresArray.get(i) + " ";
		}
		String temp = e.getKeyChar() + "";
		if (!DisplayLine.contains(temp)) {
			lives--;
		}
		label.setText(underScores);
		String temp2 = lives + "";
		livesCounter.setText(temp2);
		if (!underScoresArray.contains('_')) {
			JOptionPane.showMessageDialog(null, "Game Over//Good job");
			wordsReciever.remove(DisplayLine);
			DisplayLine = wordsReciever.pop();
			System.out.println(DisplayLine);
			underScoresArray.clear();
			newSet = "";
			for (int i = 0; i < DisplayLine.length(); i++) {
				newSet += "_" + " ";
			}
			label.setText(newSet);
			for (int i = 0; i < DisplayLine.length(); i++) {
				underScoresArray.add('_');
			}
			for (int i = 0; i < underScoresArray.size(); i++) {
				underScores += underScoresArray.get(i) + " ";
			}
		}
		if (lives <= 0) {
			JOptionPane.showMessageDialog(null, "Game Over//You ran out of lives");
			gameFrame.setVisible(false);
		}
		underScores = "";
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
