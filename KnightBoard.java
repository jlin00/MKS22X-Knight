public class KnightBoard{

  //fields
  private int[][] board; //keeps track of squares visited
  private int[][] positions; //keeps track of potential positions for optimization
  private int[][] diff = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{-1,-2},{1,-2},{-1,2}}; //8 possible moves for a knight

  //constructor
  public KnightBoard(int startingRows, int startingCols){
    if (startingRows <= 0 || startingCols <= 0) throw new IllegalArgumentException();
    board = new int[startingRows][startingCols];
    positions = new int[startingRows][startingCols];
    initialize();
  }

  //turns board into string
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

  //checks to see if board is empty
  private boolean isEmpty(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] != 0) return false;
      }
    }
    return true;
  }

  //clears board
  public void clear(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        board[r][c] = 0;
      }
    }
  }

  //checks to see if row and col are out of bounds
  private boolean outOfBounds(int row, int col){
    return row < 0 || row >= board.length || col < 0 || col >= board[0].length;
  }

  //initializes the position board with possible number of moves for each square
  private void initialize(){
    for (int row = 0; row < positions.length; row++){
      for (int col = 0; col < positions[row].length; col++){
        for (int i = 0; i < diff.length; i++){
          if (!outOfBounds(row + diff[i][0], col + diff[i][1])) positions[row][col]++; //if move is possible, add to positions
        }
      }
    }
  }

  //given row and col, order possible moves based on number of outgoing moves
  private int[][] sortPos(int row, int col){

    int[][] output = new int[positions[row][col]][2];
    int index = 0; //keeps track of index

    for (int i = 0; i < diff.length; i++){ //add possible moves to output
      int newRow = row + diff[i][0];
      int newCol = col + diff[i][1];

      if (!outOfBounds(newRow,newCol) && board[newRow][newCol] == 0){ //if valid move
        output[index] = diff[i]; //add move
        index++;
      }
    }

    //sort possible moves in output using insertion sort
    for (int i = 1; i < output.length; i++){
      int[] old_val = output[i]; //sort by number of outgoing moves
      int j;
      for (j = i; j > 0 && positions[row + old_val[0]][col + old_val[1]] < positions[row + output[j - 1][0]][col + output[j - 1][1]]; j--){
        output[j] = output[j-1]; //shifts value down
      }
      output[j] = old_val; //inserts value
    }

    return output; //returns sorted list of moves
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public boolean solve(int startingRow, int startingCol){
    if (!isEmpty())
      throw new IllegalStateException(); //exception

    if (outOfBounds(startingRow,startingCol))
      throw new IllegalArgumentException();

    return solveO(startingRow,startingCol,1); //call optimized helper
    //return solveH(startingRow,startingCol,1); //call helper
  }

  //helper method for solve
  private boolean solveH(int row, int col, int level){
    if (level > (board.length * board[0].length)) return true; //if filled up board, return true

    if (outOfBounds(row,col)) return false; //if any moves go out of bounds return false
    if (board[row][col] != 0) return false; //if knight has already been here, return false

    for (int i = 0; i < diff.length; i++){ //loop through possible moves
        board[row][col] = level;
        if (solveH(row + diff[i][0], col + diff[i][1],level+1)) return true; //try moving knight
        board[row][col] = 0; //try different position instead
    }

    return false;
  }

  //optimized helper method for solve
  private boolean solveO(int row, int col, int level){
    if (level == (board.length * board[0].length) && board[row][col] == 0){
      board[row][col] = level; //fixes 1 by 1 board error
      return true; //if filled up board, return true
    }

    if (outOfBounds(row,col)) return false; //if out of bounds
    if (board[row][col] != 0) return false; //if knight has already traveled here

    int[][] optMoves = sortPos(row,col); //optimized set of moves

    for (int i = 0; i < optMoves.length; i++){ //update positions board
      positions[row + optMoves[i][0]][col + optMoves[i][1]]--;
    }

    for (int i = 0; i < optMoves.length; i++){ //loop through possible moves
        board[row][col] = level;
        if (solveO(row + optMoves[i][0], col + optMoves[i][1],level+1)) return true; //try moving knight
        board[row][col] = 0; //try different position instead
    }

    for (int i = 0; i < optMoves.length; i++){ //undo positions board
      positions[row + optMoves[i][0]][col + optMoves[i][1]]++;
    }

    return false;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol){
    if (!isEmpty())
      throw new IllegalStateException(); //exception

    if (outOfBounds(startingRow,startingCol))
      throw new IllegalArgumentException(); //exception

    return countH(startingRow,startingCol,1); //helper method
  }

  //helper method for count
  private int countH(int row, int col, int level){

    if (outOfBounds(row,col)) return 0; //if any moves go out of bounds return false
    if (board[row][col] != 0) return 0; //if knight has already traveled here

    if (level == (board.length * board[0].length)) return 1; //if finished level

    int count = 0;
    for (int i = 0; i < diff.length; i++){ //loops through moves
      board[row][col] = level; //adds knight
      count += countH(row + diff[i][0], col + diff[i][1], level+1); //adds to count
      board[row][col] = 0; //removes knight
    }

    return count;
  }

}
