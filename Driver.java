public class Driver{
  public static void main(String[] args) {
    KnightBoard one = new KnightBoard(1,1);
    KnightBoard two = new KnightBoard(2,2);
    KnightBoard three = new KnightBoard(3,3);
    KnightBoard four = new KnightBoard(4,4);
    KnightBoard five = new KnightBoard(5,5);
    KnightBoard six = new KnightBoard(6,6);
    KnightBoard seven = new KnightBoard(7,7);
    KnightBoard eight = new KnightBoard(8,8);
    KnightBoard nine = new KnightBoard(9,9);
    KnightBoard ten = new KnightBoard(10,10);
    KnightBoard[] q = new KnightBoard[] {
      one, two, three, four, five, six, seven, eight, nine, ten
    };
    for (KnightBoard e : q) {
      System.out.println("Board: \n" + e.toString());
      System.out.println("Solveable? " + e.solve(0,0));
      e.clear();
    //  System.out.println("Solutions: " + e.countSolutions(0,0));
      if (e.solve(0,0)) {
        System.out.println("Example: \n" + e.toString());
      }
      e.clear();
      System.out.println("--------------------------");
    }
  }
}
