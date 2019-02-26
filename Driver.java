public class Driver{
  public static void main(String[] args) {

    int n = 10;
    KnightBoard k;
    for (int i = 1; i < n; i++){
      k = new KnightBoard(i,i);
      System.out.println("Board: \n" + k.toString());
      System.out.println("Solveable? " + k.solve(0,0));
      k.clear();
      k.clearPos();
      k.initialize();
      if (k.solve(0,0)){
        System.out.println("Example: \n" + k.toString());
      }
      k.clear();
      System.out.println("----------------------------");
    }

    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);
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
