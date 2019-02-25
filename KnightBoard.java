public class KnightBoard{
  private int[][] board; //keeps track of squares visited
  private int[][] positions; //keeps track of potential positions for optimization
  private int[][] diff = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}}; //8 possible moves for a knight

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

  public boolean isEmpty(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] != 0) return false;
      }
    }
    return true;
  }

  public void clear(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        board[r][c] = 0;
      }
    }
  }

  public boolean move(int row, int col, int level){
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return false; //if any moves go out of bounds return false
    if (board[row][col] != 0) return false; //if knight has already been here, return false
    board[row][col] = level; //else move knight here
    return true;
  }

  //initializes the position board with possible number of moves for each square
  public void initialize(){
    for (int x = 0; x < positions.length; x++){
      for (int y = 0; y < positions[x].length; y++){
        for (int i = 0; i < diff.length; i++){
          if (!(x + diff[i][0] >= positions.length || y + diff[i][1] >= positions[x].length || x + diff[i][0] < 0 || y + diff[i][1] < 0)) positions[x][y]++;
          //if move is possible, add to positions
        }
      }
    }
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
    return solveH(startingRow,startingCol,1); //call helper
  }

  //helper method for solve
  private boolean solveH(int row, int col, int level){
    if (level > (board.length * board[0].length)) return true; //if filled up board, return true
    for (int i = 0; i < diff.length; i++){ //loop through possible moves
      if (move(row,col,level)){
        if (solveH(row + diff[i][0], col + diff[i][1],level+1)) return true; //try moving knight
        board[row][col] = 0; //try different position instead
      }
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

  public int countH(int row, int col, int level){
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



}
