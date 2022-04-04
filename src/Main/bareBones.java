package Main;

import java.util.Random;
import java.util.Scanner;

public class bareBones {
	
	public static void main(String[] args) {
		
		String[] words = {"Main","Moths", "Loath", "Razer", "Youth", "Three"};
		
		
		String word = getWord(words);
		Scanner input = new Scanner(System.in);
		
		int g = 6;
		
		String guess = "";
		System.out.println("You have "+g+" guesses remaining");
		
		while (!guess.equals(word)&&g>0 ) {

			guess = input.nextLine();
			if (guess.length()!=5) {
				System.out.println("Must be 5 characters!");
				continue;
			}
			for (int i = 0; i<word.length(); i++) {
				boolean didBreak = false;
				for (int j = 0; j<word.length(); j++) {
					if (guess.charAt(i)== word.charAt(i)) {
						System.out.print("Y");
						didBreak = true;
						break;
					}
					if (guess.charAt(i)==word.charAt(j)) {
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
	
	public static String getWord(String[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}
	
}
