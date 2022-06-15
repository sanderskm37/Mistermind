// Jacqueline Marion, James Neuburger, Kaia Sanders
// 6.17.2022
// CSE 142 AP CS 6
// Object class of Mistermind
// Creates board object that contains Row[]
public class Board {

   private Row answer;
   private Row[] board;
   
   /**
    * Constructor for answer row and board
    * @param Row answerKey-the secret code for that board
    */
   public Board(Row answerKey) {
      answer = answerKey;
      board = new Row[10];
   }
   
   /**
    * Inputs one row into the board
    * @param Row guess - user guess of 4 symbol
    * @param int spot - # of the guess the user is on corresponding 
    *                   to where it would fall on the board
    */
   public void inputRow(Row guess, int spot) {
      board[spot] = guess;
   }
   
   /**
    * Returns a row based on the spot
    * @param int spot - row of the board wanted to retrieve
    * @return Row from the board
    */
   public Row getRow(int spot) {
      return board[spot];
   }
   
   
}