/*
 * Kayla Washington-Tapia
 * CSCI212 12W
 * Prof. O. Steinburg
 * 01.17.2021
 */
import java.util.Scanner;

public class Handman_Play {

	public static void main(String[] args) {
		
		//game is created and variables are instantiated
		Hangman_Game game = new Hangman_Game();
		Scanner scan = new Scanner(System.in);
		String letter;
		
		System.out.println("Welcome to Hangman. A random word will be chosen and please guess the letters.");
		System.out.println();
		
		//loop for the duration of the game
		while(!game.checkWin() && game.getLosses()<6) {
			//game is displayed after each turn and updated
			game.displayGame();
			System.out.print("Please enter a letter: ");
			letter = scan.nextLine();
			
			//when letter guess is wrong/right
			if(!game.guessLetter(letter.charAt(0))) {
				System.out.println("Sorry that's the wrong letter. You have "+(6-game.getLosses()) + " guesses left.");
			}
			else {
				System.out.println("That's the right letter, keep guessing!");
			}
			
			//if game is won
			if(game.checkWin()) {
				game.displayGame();
				System.out.println("Congrats you won! The word was "+ game.getWord());
			}
			//if game is finished due to no more turns
			if(game.getLosses() == 6) {
				game.displayGame();
				System.out.println("Sorry you are out of guesses, the word was "+ game.getWord());
			}
			
		}
	}

}
