
// Written by Hyunyoung Lee for CSCE314 Exercise 9 Problem 2

import static java.lang.System.out;

class A { // by default, extends ___Object___ [P2.1]
  protected int x = 0xF0; // 0xF0 = ___240___ in decimal [P2.2] 
  protected int z;
  public A() {
    z = fun( x );
    out.printf("A:%x ", z);
  }
  public int fun(int i) { return (i + 1); }
  public static int staticFun(int i) { return (i + 3); }
}

class B extends A {
  protected int y = 0x11;
  public B() {
    z = fun( z );
    out.printf("B:%x ", z);
  }
  @Override
  public int fun(int i) { return (i + 2); }
  public static int staticFun(int i) { return (i + 4); }

  public static void main(String args[]) {
    int i = 0;
    out.println("[P2.3] Creating B object");
    A a = new B(); // a's type is A but a refers B type object (upcasting)
    out.println(((B)a).y);


    out.println("\n\n[P2.4] a's dynamic/static binding");
    out.print( "a.fun(i):" + a.fun( i ) + " ");
    out.println( "a.staticFun(i):" + a.staticFun( i ) );

    out.println("\n[P2.5] Creating another B object");
    B b = new B(); // b's type is B and b refers B type object
    out.println( b.x );

    out.println("\n\n[P2.6] b's dynamic/static binding");
    out.print( b.fun( i ) + " ");
    out.println( b.staticFun( i ) );
  } 
}

