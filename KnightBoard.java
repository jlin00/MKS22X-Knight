public class KnightBoard{
  private int[][] board; //keeps track of squares visited
  private int[][] positions; //keeps track of potential positions for optimization
  private int[][] diff = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{-1,-2},{1,-2},{-1,2}}; //8 possible moves for a knight

  public KnightBoard(int startingRows, int startingCols){
    if (startingRows <= 0 || startingCols <= 0) throw new IllegalArgumentException();
    board = new int[startingRows][startingCols];
    positions = new int[startingRows][startingCols];
    initialize();
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

  //prints out positions, for testing purposes
  public String toStringPos(){
    String output = "";
    for (int r = 0; r < positions.length; r++){
      for (int c = 0; c < positions[r].length; c++){
        if (positions[r][c] < 10) output += " "; //space in front of single digit numbers
        if (positions[r][c] == 0) output += "_ ";
        else output += positions[r][c] + " "; //add number to output
        if (c == positions[r].length - 1) output += "\n"; //new line
      }
    }
    return output;
  }

  public boolean isEmpty(){ //checks to see if board is empty
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] != 0) return false;
      }
    }
    return true;
  }

  public void clear(){ //clears board
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        board[r][c] = 0;
      }
    }
  }

  private boolean outOfBounds(int row, int col){
    return row < 0 || row >= board.length || col < 0 || col >= board[0].length;
  }

  //initializes the position board with possible number of moves for each square
  public void initialize(){
    for (int row = 0; row < positions.length; row++){
      for (int col = 0; col < positions[row].length; col++){
        for (int i = 0; i < diff.length; i++){
          if (!outOfBounds(row + diff[i][0], col + diff[i][1])) positions[row][col]++; //if move is possible, add to positions
        }
      }
    }
  }

  private int[][] sortPos(int row, int col){
    //add possible moves to output
    int[][] output = new int[positions[row][col]][2]; //list of possible moves
    //System.out.println(output[0].length);
    int index = 0; //keeps track of list index
    for (int i = 0; i < diff.length; i++){
      if (!(row + diff[i][0] >= positions.length ||
      col + diff[i][1] >= positions[row].length ||
      row + diff[i][0] < 0 ||
      col + diff[i][1] < 0)){ //if valid move
        if (board[row + diff[i][0]][col + diff[i][1]] == 0){
          output[index] = diff[i];
          index++;
        }
      }
    }

    //sort possible moves in output using insertion sort
    for (int i = 1; i < output.length; i++){
      int[] old_val = output[i]; //sort by number of outgoing moves
      int j;
      for (j = i; j > 0 && positions[row + old_val[0]][col + old_val[1]] < positions[row + output[j - 1][0]][col + output[j - 1][1]]; j--){
        output[j] = output[j-1];
      }
      output[j] = old_val;
    }

    return output; //returns sorted list of moves
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public boolean solve(int startingRow, int startingCol){
    if (!isEmpty()) throw new IllegalStateException(); //exception
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[startingRow].length){
      throw new IllegalArgumentException();
    }
    //return solveH(startingRow,startingCol,1); //call helper
    return solveO(startingRow,startingCol,1); //call optimized helper
  }

  //helper method for solve
  private boolean solveH(int row, int col, int level){
    if (level > (board.length * board[0].length)) return true; //if filled up board, return true

    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return false; //if any moves go out of bounds return false
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
    if (level > (board.length * board[0].length)) return true; //if filled up board, return true;

    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return false; //if any moves go out of bounds return false
    if (board[row][col] != 0) return false; //if knight has already been here, return false

    int[][] tryMoves = sortPos(row, col); //creates list of moves to tryMoves
    for (int i = 0; i < tryMoves.length; i++){ //loop through possible moves
      board[row][col] = level;
      //System.out.println(toStringPos()); //debugging purposes
      if (solveO(row + tryMoves[i][0], col + tryMoves[i][1],level+1))
        return true; //try  moving knight

      board[row][col] = 0; //try different position instead
    }
    return false;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol){
    if (!isEmpty()) throw new IllegalStateException(); //exception

    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[startingRow].length){
      throw new IllegalArgumentException(); //exception
    }
    return countH(startingRow,startingCol,1); //helper method
  }

  //helper method for count
  private int countH(int row, int col, int level){
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return 0; //if any moves go out of bounds return false
    if (board[row][col] != 0) return 0;
    if (level == (board.length * board[0].length)) return 1;
    int count = 0;
    for (int i = 0; i < diff.length; i++){ //loops through moves
      board[row][col] = level;
      count += countH(row + diff[i][0], col + diff[i][1], level+1); //adds to count
      board[row][col] = 0; //removes knight
    }
    return count;
  }

  //optimized helper method for count
  private int countO(int row, int col, int level){
    return 0;
  }

}
