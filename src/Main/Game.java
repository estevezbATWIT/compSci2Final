package Main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Game implements ActionListener {

	private JFrame gameFrame;
	private WordPanel[] wordPanelArray = new WordPanel[6];
	private UserPanel userPanel;
	private String wordleString;
	private int count = 0;

	public Game() {
		gameFrame = new JFrame("Wordle Game");
		gameFrame.setSize(300, 300);
		gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gameFrame.setLayout(new GridLayout(7, 1));
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null);

		for (int i = 0; i < 6; i++) {
			wordPanelArray[i] = new WordPanel();
			gameFrame.add(wordPanelArray[i]);
		}
		userPanel = new UserPanel();
		userPanel.getOkButton().addActionListener(this);
		gameFrame.add(userPanel);
		gameFrame.revalidate();

		wordleString = getWordleString();
		System.out.println("Word for the day : " + wordleString);
	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userWord = this.userPanel.getUserInput().getText();

		if (userWord.length() > 4) {
			if (isWordleWordEqualTo(userWord.trim().toUpperCase())) {
				clearAllPanels();
				JOptionPane.showMessageDialog(null, "You Win!!!", "Congrats", JOptionPane.INFORMATION_MESSAGE);
				gameFrame.dispose();
				return;
			}
		}
		if (count > 5) {
			JOptionPane.showMessageDialog(null, "You Lost.Better luck next time.", "Oops", JOptionPane.INFORMATION_MESSAGE);
			gameFrame.dispose();
			return;
		}
		count++;
	}

	private void clearAllPanels() {
		for (int i = 0; i <= count; i++) {
			wordPanelArray[i].clearWordPanel();
		}
	}

	private boolean isWordleWordEqualTo(String userWord) {
		List<String> wordleWordsList = Arrays.asList(wordleString.split(""));
		String[] userWordsArray = userWord.split("");
		List<Boolean> wordMatchesList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			if (wordleWordsList.contains(userWordsArray[i])) {
				if (wordleWordsList.get(i).equals(userWordsArray[i])) {
					getActivePanel().setPanelText(userWordsArray[i], i, Color.GREEN);
					wordMatchesList.add(true);
				} else {
					getActivePanel().setPanelText(userWordsArray[i], i, Color.YELLOW);
					wordMatchesList.add(false);
				}
			} else {
				getActivePanel().setPanelText(userWordsArray[i], i, Color.GRAY);
				wordMatchesList.add(false);
			}
		}
		return !wordMatchesList.contains(false);
	}

	public WordPanel getActivePanel() {
		return this.wordPanelArray[count];
	}

	public String getWordleString() {
		Path path = Paths.get("..\\\\CompSci2Final\\\\assets\\\\words");
		List<String> wordList = new ArrayList<>();
		try {
			wordList = Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random random = new Random();
		int position = random.nextInt(wordList.size());
		return wordList.get(position).trim().toUpperCase();
	}

}

