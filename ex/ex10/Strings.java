/** Example: 
   1) Strings are immutable
   2) String.equals() vs. ==
   3) String.hashCode()

   written by Hyunyoung Lee for CSCE 314 Students
*/

public class Strings { 
  public static void main(String args[]) {
    String s1 = new String("Howdy");
    String s2 = new String("Howdy");
    String s3 = s1;
    String s4 = s2;
    String s5 = "Howdy";
    String s6 = "Howdy";
    System.out.println("s1.hashCode() = " + s1.hashCode()); 
    System.out.println("s2.hashCode() = " + s2.hashCode()); 
    System.out.println("s3.hashCode() = " + s3.hashCode()); 
    System.out.println("s5.hashCode() = " + s5.hashCode()); 
    System.out.println("s6.hashCode() = " + s6.hashCode()); 
    System.out.println("s5 == s6 ?" + (s5==s6));
    System.out.println("s1 == s2 ?" + (s1==s2));
    System.out.println("s1 == s3 ?" + (s1==s3));
    System.out.println("s1 == s5 ?" + (s1==s5));
    System.out.println("s1.equals(s2) ?" + (s1.equals(s2)));
    System.out.println("s1.equals(s3) ?" + (s1.equals(s3)));
    System.out.println("s1.equals(s5) ?" + (s1.equals(s5)));
    System.out.println("\n** After s3 += \" y'all!\" ...");
    s3 += " y'all!";
    System.out.println("s1 = " + s1);
    System.out.println("s3 = " + s3);
    System.out.println("s1.hashCode() = " + s1.hashCode()); 
    System.out.println("s3.hashCode() = " + s3.hashCode()); 
    System.out.println("s1 == s3 ?" + (s1==s3));
    System.out.println("s1.equals(s3) ?" + (s1.equals(s3)));
  }
} 

