public class Driver{
  public static void main(String[] args) {

    int n = 55;
    KnightBoard k;
    for (int i = 1; i <= n; i++){
      k = new KnightBoard(i,i);
      System.out.println("Board: " + i + " by " + i + "\n" + k.toString());
      if (k.solve(0,0)){
        System.out.println("Solution: \n" + k.toString());
      }
      else{
        System.out.println("No solution");
      }
      k.clear();
      System.out.println("----------------------------");
    }

    //Mr. K's test cases
    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);

    System.out.println();

    KnightBoard k2 = new KnightBoard(5,6);
    System.out.println("Board:\n" + k2.toString());
    if (k2.solve(2,2)){
      System.out.println("Solution: \n" + k2.toString());
    }
    else{
      System.out.println("No solution");
    }
    k2.clear();
    System.out.println("\nEND OF TESTING");

  }

  //testcase must be a valid index of your input/output array
  public static void runTest(int i){

    KnightBoard b;
    int[] m =   {4,5,5,5,5};
    int[] n =   {4,5,4,5,5};
    int[] startx = {0,0,0,1,2};
    int[] starty = {0,0,0,1,2};
    int[] answers = {0,304,32,56,64};
    if(i >= 0 ){
      try{
        int correct = answers[i];
        b = new KnightBoard(m[i%m.length],n[i%m.length]);

        int ans  = b.countSolutions(startx[i],starty[i]);

        if(correct==ans){
          System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
        }else{
          System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
        }
      }catch(Exception e){
        System.out.println("FAIL Exception case: "+i);

      }
    }
  }

}
