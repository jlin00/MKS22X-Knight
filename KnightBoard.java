public class KnightBoard{
  private int[][] board;
  private int[][] moves;

  public KnightBoard(int startingRows, int startingCols){
    board = new int[startingRows][startingCols];
    moves = new int[startingRows][startingCols];
  }

  public String toString(){
    String output = "";
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] < 10) output += " "; //space in front of single digit numbers
        output += "" + board[r][c]; //add number to output
        if (c == board[r].length - 1) output += "\n"; //new line 
      }
    }
    return output;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public boolean solve(int startingRow, int startingCol){
    return true;
  }

  //helper method for solve
  private boolean solveH(int row ,int col, int level){
    return true;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol){
    return 0;
  }



}
