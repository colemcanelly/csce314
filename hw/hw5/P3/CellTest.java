
/* CellTest.java skeleton written by Hyunyoung Lee 
   CSCE 314 [Sections 595, 596, 597] Spring 2023  
   Homework 5 Problem 3 Test class 
   class CellTest

   Student Name: 
   UIN:
   Acknowledgements: 
*/

public class CellTest {

    // 15 points for the three methods: int_sum, num_sum, print
    // implement int_sum
    public static int int_sum(Cell<Integer> ints)
    {
        // Check for null list
        if (ints == null) return 0;
        // Start total at 0, iterate through list adding to the sum
        int total = 0;
        for (Integer i : ints) {
            total += i;
        }
        return total;
    }
    
    // implement num_sum
    public static double num_sum(Cell<? extends Number> nums)
    {
        // Check for null list
        if (nums == null) return 0;
        // Start total at 0, iterate through list adding to the sum
        double total = 0;
        for (Number n : nums) {
            total += n.doubleValue();
        }
        return total;
    }

    // implement print
    public static void print(Cell<?> list)
    {
        String out = "[ ";

        // Get raw iterator
        Cell.CellIterator it = list.iterator();
        // Run until there are no elements left
        while(it.hasNext()) {
        // for (Cell<> c : list) {
            // Build an output string
            out += it.next() + ",";
        }
        // Make the output pretty
        out = out.substring(0, out.length() - 1);
        out += " ]";
        // Print the output string
        System.out.println(out);
    }

    // Feel free to "expand" the main method but do not delete whatever provided
    public static void main(String args[]) {
        Cell<Integer> intlist = new Cell<Integer>(1,
                new Cell<Integer>(22,
                        new Cell<Integer>(21,
                                new Cell<Integer>(12,
                                        new Cell<Integer>(24,
                                                new Cell<Integer>(17, null))))));

        Cell<Integer> nullintlist = null;

        System.out.println("===");
        print(intlist);
        System.out.println("sum of intlist is " + int_sum(intlist));
        System.out.println("sum of null list is " + int_sum(nullintlist));
        System.out.println("===");

        Cell<Double> doublelist = new Cell<Double>(1.,
                new Cell<Double>(16.,
                        new Cell<Double>(13.72,
                                new Cell<Double>(5.,
                                        new Cell<Double>(22.,
                                                new Cell<Double>(7.1, null))))));

        System.out.println("===");
        print(doublelist);
        System.out.println("sum ints = " + num_sum(intlist));
        System.out.println("sum doubles = " + num_sum(doublelist));
        System.out.println("===");
    }
}
