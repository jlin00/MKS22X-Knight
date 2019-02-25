public class Driver{
  public static void main(String[] args) {
    KnightBoard k2 = new KnightBoard(1,1);
    KnightBoard k3 = new KnightBoard(2,2);
    KnightBoard k4 = new KnightBoard(3,3);
    KnightBoard k5 = new KnightBoard(4,4);
    KnightBoard k6 = new KnightBoard(5,5);
    KnightBoard k7 = new KnightBoard(6,6);
    KnightBoard k8 = new KnightBoard(7,7);
    KnightBoard k9 = new KnightBoard(8,8);

    System.out.println(k2);
    System.out.println(k3);
    System.out.println(k4);
    System.out.println(k5);
    System.out.println(k6);
    System.out.println(k7);
    System.out.println(k8);
    System.out.println(k9);

    System.out.println(k2.solve(0,0));
    System.out.println(k3.solve(0,0));
    System.out.println(k4.solve(0,0));
    System.out.println(k5.solve(0,0));
    System.out.println(k6.solve(0,0));
    System.out.println(k7.solve(0,0));
    System.out.println(k8.solve(0,0));
    //System.out.println(k9.solve(0,0));

    System.out.println(k2);
    System.out.println(k3);
    System.out.println(k4);
    System.out.println(k5);
    System.out.println(k6);
    System.out.println(k7);
    System.out.println(k8);
    System.out.println(k9);

    k2.clear();
    k3.clear();
    k4.clear();
    k5.clear();
    k6.clear();
    k7.clear();
    k8.clear();
    k9.clear();

    System.out.println(k2.countSolutions(0,0));
    System.out.println(k3.countSolutions(0,0));
    System.out.println(k4.countSolutions(0,0));
    System.out.println(k5.countSolutions(0,0));
    System.out.println(k6.countSolutions(0,0));
    //System.out.println(k7.countSolutions(0,0));
    //System.out.println(k8.countSolutions(0,0));
    //System.out.println(k9.countSolutions(0,0));

    k2.initialize();
    k3.initialize();
    k4.initialize();
    k5.initialize();
    k6.initialize();
    k7.initialize();
    k8.initialize();
    k9.initialize();

    System.out.println(k2.toStringPos());
    System.out.println(k3.toStringPos());
    System.out.println(k4.toStringPos());
    System.out.println(k5.toStringPos());
    System.out.println(k6.toStringPos());
    System.out.println(k7.toStringPos());
    System.out.println(k8.toStringPos());
    System.out.println(k9.toStringPos());

  }
}
