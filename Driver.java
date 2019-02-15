public class Driver{
  public static void main(String[] args) {
    KnightBoard k1 = new KnightBoard(5,6);
    KnightBoard k2 = new KnightBoard(4,3);
    KnightBoard k3 = new KnightBoard(2,2);
    KnightBoard k4 = new KnightBoard(3,9);
    KnightBoard k5 = new KnightBoard(1,3);
    KnightBoard k6 = new KnightBoard(1,1);
    KnightBoard k8 = new KnightBoard(2,5);
    KnightBoard k9 = new KnightBoard(3,8);

    System.out.println(k1);
    System.out.println(k2);
    System.out.println(k3);
    System.out.println(k4);
    System.out.println(k5);
    System.out.println(k6);
    System.out.println(k7);
    System.out.println(k8);
    System.out.println(k9);

    System.out.println(k1.solve(0,0));
    System.out.println(k2.solve(0,0));
    System.out.println(k3.solve(0,0));
    System.out.println(k4.solve(0,0));
    System.out.println(k5.solve(0,0));
    System.out.println(k6.solve(0,0));
    System.out.println(k7.solve(0,0));
    System.out.println(k8.solve(0,0));
    System.out.println(k9.solve(0,0));

    System.out.println(k1);
    System.out.println(k2);
    System.out.println(k3);
    System.out.println(k4);
    System.out.println(k5);
    System.out.println(k6);
    System.out.println(k7);
    System.out.println(k8);
    System.out.println(k9);

    System.out.println(k1.countSolutions(0,0));
  }
}
