// Jacqueline Marion, James Neuburger, Kaia Sanders
// 6.17.2022
// CSE 142 AP CS 6
// Logic gamed based off of the board game Mastermind 
// User must guess computer generated sequence of symbols

import java.util.*;
public class Mistermind {
   
   /**
    * Main method of game class. 
    * Structure of how to play multiple games and keep track of stats
    */
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      
      introCode();
      int guessNumFinal = game();
      
      String play = playAgain(input);
      int totalGames = 1;
      int bestGame = guessNumFinal;
      
      while (play.equals("yes")) {
         int numGuesses = game();
         play = playAgain(input);
         //stats per game
         
         totalGames ++;
         bestGame = Math.min(numGuesses, bestGame);
         guessNumFinal += numGuesses;
      }
      
      //closing code
      stats(totalGames, guessNumFinal, bestGame);
      
   }
   
   /** 
    * Prints intro code to console
    */
   public static void introCode() {
      System.out.println("_______  Welcome to the game of MisterMind!");
      System.out.println("|     |  Here is how to play:");
      System.out.println("|     |");
      System.out.println("|     |  The computer will come up with a secret code of");
      System.out.println("-------  the following symbols:");
      System.out.println("(ʘ͡ʖ ʘ)    ~  @  #  %  $  &  *");
      System.out.println("   O");
      System.out.println("   ||     Your job is to guess the code ");
      System.out.println("   ||                                  ...and there can be repeats");
      System.out.println("o--||--o");
      System.out.println("  //\\\\    If you guess a symbol that is in the code "+ 
                         "but in the wrong spot you will recive a '?'");
      System.out.println(" //  \\\\   If you guess the correct symbol in the correct spot" + 
                         " you will recive a '!'");
      System.out.println("//    \\\\  If the symbol is incorrect," + 
                                       " and not included in the code ' ' will show");
      System.out.println("O      O  ");
      System.out.println("You have 10 guesses per game to find the mystery code"); 
      System.out.println("the sooner you guess, the answer, " + 
                         "the more points you will get.");
      System.out.println("Have fun guessing and good luck!");
      System.out.println();
   }
   
   /**
   * Prompts user to play again
   * @param Scanner input - to allow for user input
   * @return user response, can be yes or no
   */
   public static String playAgain(Scanner input) {
      System.out.println();
      System.out.print("Do you want to play again? (yes / no): ");
      return input.next();
   }
   
   /**
    * Plays one game.
    * And builds a board and answer row
    * @return number of guesses taken 
    */
   public static int game() {
      Row key = new Row(randomKey()); // new random sequence 
      Board game = new Board(key); // new board every game that includes random key
      
      int guessNum = 0;
      while (guessNum <= 9) { // max of 10 guesses
         Row guess = new Row(guess()); // guess user makes, calls guess method
         game.inputRow(guess, guessNum); //inputs each guess into the board
         guessNum++;
         if (!key.equals(guess)) { // if key does NOT equal guess, user gets feedback
            System.out.println(feedback(key, guess));
         } else {
            System.out.println("Great Job!");
            if (guessNum == 1) {
               System.out.println("You guessed the code in 1 guess!");
            } else {
               System.out.println("You guessed the code in " + guessNum +" guesses!");
            }
            return guessNum;
         }
         
      }
      System.out.println("You did not guess the code correct :(");
      return 11;
   }
   
   /**
    * Creates random sequence of symbols that is the answer code
    * @return Array of char
    */
   public static char[] randomKey() {
      Random rand = new Random();
      char[] key = new char[4];
      for (int i = 0; i < 4; i++) {
         int num = rand.nextInt(7);
         if (num == 0) {
            key[i] = '@';
         } else if (num == 1) {
            key[i] = '#';
         } else if (num == 2) {
            key[i] = '$';
         } else if (num == 3) {
            key[i] = '%';
         } else if (num == 4) {
            key[i] = '*';
         } else if (num == 5) {
            key[i] = '&';
         } else {
            key[i] = '~';
         }
      }
      return key;
   }
   
   /**
    * Asks user to make and guess of the code
    * @return user guess in the form of char[]
    */
   public static char[] guess() {
      Scanner input = new Scanner(System.in);
      System.out.println("Make a guess of 4 symbols:");
      String guess = input.next();
      char[] code = new char[4];
      for (int i = 0; i < 4; i++) {
         code[i] = guess.charAt(i);
      }
      return code;      
   }   
   
   /**
    * Compares user guess to answer code
    * @param Row key - object of secret code
    * @param Row guess - object of user guess
    * @return String of feedback
    */
   public static String feedback(Row key, Row guess) {
      String clues = "";
      char[] key1 = key.getRow();
      char[] guess1 = guess.getRow();
      for(int i = 0; i < 4; i++) {
         if (guess1[i] == key1[i]) {
            clues += "!";
         } else {
            clues += checkQmark(key1, guess1[i]);
         }
      }
      return clues;
   } 
   
   /**
    * Compares symbol of guess to see if it is contained in the key
    * @param char[] key - the secret key
    * @param char guessPeg - one "peg"(one symbol) of the user's guess
    * @return char - ' ' if that symbol is not included in the key 
                     '?' if symbol is included in the key
    */
   public static char checkQmark(char[] key, char guessPeg) {
      for (char pegKey : key) {
         if (pegKey == guessPeg) {
            return '?';
         }
      }
      return ' ';
   }
   
   /**
    * Prints final statistics to the console
    * @param int totalGames- of amount of games user has played
    * @param int totalGuesses- of total guesses from all games user has played
    * @param int bestGame - of smallest amount of guesses user had in one game
    */
   public static void stats(int totalGames, int totalGuesses, int bestGame) {
      double guessesPerGame = (double)totalGuesses / totalGames * 10;
      guessesPerGame = Math.round(guessesPerGame);
      guessesPerGame = guessesPerGame / 10;
      System.out.println();
      System.out.println("Overall results:");
      System.out.println("Total games       = " + totalGames);
      System.out.println("Total guesses     = " + totalGuesses);
      System.out.println("Avg. guesses/game = " + guessesPerGame);
      System.out.println("Best game         = " + bestGame + " guesses");
      System.out.println();
      System.out.println("Great job!! Have an awesome day");
   }
}