import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	public static void main(String[] args) throws IOException {
	//	LineNumberReader lnr = new LineNumberReader(br);
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
		h.createUI();
		String DisplayLine = wordsReciever.pop();
		System.out.println(DisplayLine);
		System.out.println(DisplayLine.substring(0, 1));
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
	public void putInStack(ArrayList<String> words, Stack<String> wordsReciever) {
		for (int i = 0; i< words.size(); i++) {
			wordsReciever.push(words.get(i));
		}
	}
	public void createUI() {
		JFrame gameFrame = new JFrame();
		JPanel gamePanel = new JPanel();
		JLabel gameLabel = new JLabel();
		gameFrame.setVisible(true);
		gameFrame.setSize(1000, 500);
		gameFrame.add(gamePanel);
		gameLabel.setText("Guess a letter");
		gamePanel.add(gameLabel);
		gamePanel.addKeyListener(this);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
