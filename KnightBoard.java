public class KnightBoard{
  private int[][] board; //keeps track of squares visited
  private int[][] positions; //keeps track of potential positions for optimization

  public KnightBoard(int startingRows, int startingCols){
    if (startingRows <= 0 || startingCols <= 0) throw new IllegalArgumentException();
    board = new int[startingRows][startingCols];
    positions = new int[startingRows][startingCols];
  }

  public String toString(){
    String output = "";
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] < 10) output += " "; //space in front of single digit numbers
        if (board[r][c] == 0) output += "_ ";
        else output += board[r][c] + " "; //add number to output
        if (c == board[r].length - 1) output += "\n"; //new line
      }
    }
    return output;
  }

  public boolean isEmpty(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] != 0) return false;
      }
    }
    return true;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public boolean solve(int startingRow, int startingCol){
    if (!isEmpty()) throw new IllegalStateException(); //exception
    if (startingRow < 0 || startingCol < 0) throw new IllegalArgumentException();
    if (startingRow >= board.length || startingCol >= board[startingRow].length) throw new IllegalArgumentException();
    return solveH(startingRow,startingCol,1); //call helper
  }

  //helper method for solve
  private boolean solveH(int row, int col, int level){
    if (level > (board.length * board[0].length)) return true; //if filled up board, return true

    if (row < 0 || col < 0) return false; //if any moves go out of bounds return false
    if (row >= board.length || col >= board[row].length) return false; //if any moves go out of bounds return false

    if (board[row][col] != 0) return false; //if knight has already been to that position

    int[][] diff = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}}; //8 possible moves for a knight
    for (int i = 0; i < diff.length; i++){ //loop through possible moves
      board[row][col] = level;
      if (solveH(row + diff[i][0], col + diff[i][1],level+1)) return true; //try moving knight
      board[row][col] = 0;
    }
    return false;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol){
    if (!isEmpty()) throw new IllegalStateException(); //exception
    if (startingRow < 0 || startingCol < 0) throw new IllegalArgumentException();
    if (startingRow >= board.length || startingCol >= board[startingRow].length) throw new IllegalArgumentException();
    return 0;
  }



}
