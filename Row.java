// Jacqueline Marion, James Neuburger, Kaia Sanders
// 6.17.2022
// CSE 142 AP CS 6
// Object class of Mistermind
// Creates row object of char[]

public class Row {
   private char[] code;
   
   /**
   * Constructs a row
   * @param char[] guess - user guess
   */
   public Row(char[] guess) {
      code = guess;
   }
   
   /**
    * Returns char[] of row
    * @return char[]
    */
   public char[] getRow() {
      return code;
   }
   
   /**
    * Equals method to compare object to row
    * @param Object o - object wanted to see if equal to Row
    * @return boolean if object and Row are the same
    */
   public boolean equals(Object o) {
      if (o instanceof Row) {
         Row other = (Row) o;
         boolean doesFirstPegMatch = other.getRow()[0] == code[0];
         boolean doesSecondPegMatch = other.getRow()[1] == code[1];
         boolean doesThirdPegMatch = other.getRow()[2] == code[2];
         boolean doesFourthPegMatch = other.getRow()[3] == code[3];
         
         return doesFirstPegMatch && doesSecondPegMatch &&
                doesThirdPegMatch && doesFourthPegMatch;
      } 
      return false;
      }
}