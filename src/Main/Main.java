package Main;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<String> wordle = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		
		try {
		File wordFile = new File("..\\CompSci2Final\\assets\\words");
		Scanner wF = new Scanner(wordFile);
		while(wF.hasNext()) {
			wordle.add(wF.next());

		}
		
		}catch(FileNotFoundException e)		{
			System.out.println("Problem opening file.");
			System.exit(0);
		}
		
		
		int g = 6;
		String word= getWord(wordle).toUpperCase();
		System.out.println(word);
		String guess = "";
		System.out.println("You have "+g+" guesses remaining");
		
		while (!guess.toUpperCase().equals(word.toUpperCase())&&g>0 ) {

			guess = in.nextLine().toUpperCase();
			if (guess.length()!=5) {
				System.out.println("Must be 5 characters!");
				continue;
			}
			for (int i = 0; i<word.length(); i++) {
				boolean didBreak = false;
				for (int j = 0; j<word.length(); j++) {
					if (guess.toUpperCase().charAt(i)== word.toUpperCase().charAt(i)) {
						System.out.print("Y");
						didBreak = true;
						break;
					}
					if (guess.toUpperCase().charAt(i)==word.toUpperCase().charAt(j)) {
						System.out.print("C");
						didBreak = true;
						break;
					}
				}
				if (!didBreak) {
					System.out.print("X");
				}
			}
			g--;
			System.out.println(" You have "+g+" guesses remaining");
		}
		if (guess.equals(word)) {
			System.out.println("\n Good Job!");
		}else {
			System.out.println("\n You failed, the word was " + word);
		}
		
	}
	
	public static String getWord(ArrayList<String> array) {
		int rnd = new Random().nextInt(array.size());
		return array.get(rnd);
	}
	
}
