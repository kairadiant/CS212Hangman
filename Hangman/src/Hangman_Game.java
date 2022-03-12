/*
 * Kayla Washington-Tapia
 * CSCI212 12W
 * Prof. O. Steinburg
 * 01.17.2021
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Hangman_Game {
	//all instance variables
	private List<Character> puzzle = new ArrayList<>();
	private List<String> words;
	private char[] letters;
	private String word;
	private int lettersWrong;
	
	public Hangman_Game(){ //constructor
		
		letters = "abcdefghijklmnopqrstuvwxyz".toCharArray(); //array of letters of the alphabet is created
		lettersWrong = 0; //variable to keep track of wrong guesses
		
		try { //words are imported from file and stores in an array list
			words = Files.readAllLines(Paths.get("C:\\Users\\Kayla\\Downloads\\wordfile.txt"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		chooseWord();
		//array of underscore/word display is made
		for(int i = 0; i < word.length(); i++) {
			puzzle.add('_');
		}
		
	}
	
	//accessor methods for variables are created
	public String getWord() {
		return word;
	}
	
	public int getLosses() {
		return lettersWrong;
	}
	
	
	//hang-man, underscores, and alphabet is printed out
	public void displayGame() {
		wordbankPrint();
		displayHangman();
		underScores();
		
	}
	
	//word is chosen randomly from the word ArrayList
	public String chooseWord() {
		word= words.get((int) (Math.random()*(words.size()-1)+1));
		
		return word;
	}
	
	//underscores printed
	public void underScores() {
		for(char c:puzzle) {
			System.out.print(c);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	//hangman display is mutated based on how many wrong guess there are
	public void displayHangman() {
		//prints out the top of the hanging board
		System.out.println("  ____");
		System.out.println(" |    |");
		
		//prints only the head
		if(lettersWrong >= 1) {
			System.out.println(" |   (_)"); 
		}
		
		//prints the body
		if(lettersWrong == 2) {
			System.out.println(" |    |"); 
			System.out.println(" |    |");
		}
		//prints the body and the arm
		else if(lettersWrong ==3) {
			System.out.println(" |   \\|"); 
			System.out.println(" |    |");
		}
		//prints out the body and both arms
		else if(lettersWrong >= 4) {
			System.out.println(" |   \\|/"); 
			System.out.println(" |    |");
		}
		//otherwise just the other portion is printed
		else {
			System.out.println(" |");
		}
		
		//the legs are printed
		if(lettersWrong == 5) {
			System.out.println(" |   /");
		}
		else if(lettersWrong == 6) {
			System.out.println(" |   / \\");
		}
		//otherwise the board is printed
		else {
			System.out.println(" |");
		}
		//the rest of the board is printed
		System.out.println(" |");
		System.out.println("_|___");
		
	}
	
	//if there are no more letters to guess, returns true
	public boolean checkWin() {
		for(int i=0;i<puzzle.size();i++) {
			if(puzzle.get(i) == '_') {
				return false;
			}
		}
		return true;
	}
	
	//checks if the guess is valid
	public boolean guessLetter(char letter) {
		boolean c = false;
		
		//updates letters left to guess
		for(int i=0;i < 26;i++) {
			if(letters[i] == Character.toLowerCase(letter)) {
				letters[i] = '-';
			}
		}
		//checks if guessed letter is in the word
		for(int j=0;j < word.length();j++) {
			if(Character.toLowerCase(word.charAt(j)) == Character.toLowerCase(letter)) {
				puzzle.set(j, word.charAt(j));
				c = true;
				
			}
		}
		//if its not in the word, letters wrong count is added
		if(c == false) {
			lettersWrong++;
		}
		return c;
	}
	
	//all words that haven't been guessed already are displayed
	public void wordbankPrint() {
		System.out.print("Letters available for use: ");
		for(char c:letters) {
			if(c !='-') {
				System.out.print(c);
				System.out.print(" ");
			}
		}
		System.out.println();
	}
	
	
	
	

}
