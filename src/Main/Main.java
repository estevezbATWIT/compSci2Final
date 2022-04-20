package Main;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Main {
	
public static void main(String[] args) {
		
		ArrayList<String> wordle = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		Scanner wF,ver;
		
		try {
			File wordFile = new File("..\\CompSci2Final\\assets\\words");
			wF = new Scanner(wordFile);
			while(wF.hasNext()) {
				wordle.add(wF.next());
			}	
		
			int g = 6;
			String word= getWord(wordle).toUpperCase();
			System.out.println(word);
			String guess = "";
			String verify = "";
			System.out.println("You have "+g+" guesses remaining");
		
			while (!guess.toUpperCase().equals(word.toUpperCase())&&g>0 ) {
				guess = in.nextLine().toLowerCase();
				ver = new Scanner(wordFile);
				if(verify.contains(guess)) {
					
				}
			
				if (guess.length()!=5) {
					System.out.println("Must be 5 characters!");
					continue;
				}
				
				while(ver.hasNext()) {
					if(ver.next().equals(guess)) {
						verify = guess;
						continue;
					}
				}
				
				if(!verify.equals(guess)) {
					System.out.println("Not a valid word!");
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
			
			if (guess.toUpperCase().equals(word)) {
				System.out.println("\n Good Job!");
			}else {
				System.out.println("\n You failed, the word was " + word);		
			}}
	
		catch(FileNotFoundException e)		{
			System.out.println("Problem opening file.");
			System.exit(0);
		}
	}
	
	public static String getWord(ArrayList<String> array) {
		int rnd = new Random().nextInt(array.size());
		return array.get(rnd);
	}
	
}
