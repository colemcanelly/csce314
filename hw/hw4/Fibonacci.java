// PROBLEM 2
class SubsetOutputFib {
    static final int MAX_INDEX = 9;

    /**
     * Print out the first few Fibonacci numbers,
     * marking evens with a '*'
     */
    public static void main(String[] args) {
        // Convert the command line args to integers
        int be = Integer.parseInt(args[0]);
        int en = Integer.parseInt(args[1]);

        // Error checking the command line arguments
        {
            if (be < 0) {
                System.out.println(
                        "Arguments must be positive integers. Got 'be' = " + be + " Taking 'be' = " + (-1 * be));
                be = -1 * be;
            }
            if (en < 1) {
                System.out.println(
                        "Arguments must be positive integers. Got 'en' = " + en + " Taking 'en' = " + (-1 * en));
                en = -1 * en;
            }
            if (!(be <= en)) {
                System.out.println("Arguments out of order, swapping to satisfy be ≤ en");
                int x = be;
                be = en;
                en = x;

            }
        }

        // Code copied from textbook example problem
        int lo = 1;
        int hi = 1;
        String mark;
        // Check if 1 is in the subset of values we want to print
        if (be <= 1 && 1 <= en)
            System.out.println("1: " + lo);
        for (int i = 2; i <= MAX_INDEX; i++) {
            /*
             * Check if the current fibonacci value is
             * in the subset of values we want to print
             */
            if (be <= i && i <= en) {
                if (hi % 2 == 0)
                    mark = " *";
                else
                    mark = "";
                System.out.println(i + ": " + hi + mark);
            }
            hi = lo + hi;
            lo = hi - lo;
        }
    }
} // end PROBLEM 2


// PROBLEM 3
// My own custom tuple class to hold an int and a bool for this problem
class Tuple {
    public int value;
    public boolean even;
    public Tuple()
    {
        value = 0;
        even = false;
    }
    public Tuple (int v, boolean e)
    {
        value = v;
        even = e;
    }
    public int getValue() { return value; }
    public boolean getIsEven() { return even; }

    @Override
    public String toString() { return getValue() + (getIsEven() ? " is even" : " id odd");
    }
}

class ImprovedFibonacci {
    static final int MAX_INDEX = 9;
    /**
     * Modify the ImprovedFibonacci application to store its sequence in an array. Do this by
     * creating a new class to hold both the value and a boolean value that says whether the value is even, and
     * then having an array of object references to objects of that class
     */

    private static Tuple[] fibonacciArray()
    {
        Tuple[] tuples = new Tuple[MAX_INDEX + 1];
        int lo = 0;
        int hi = 1;
        
        // Initialize Fib_0 and Fib_1
        tuples[0] = new Tuple(lo, false);
        tuples[1] = new Tuple(hi, false);
    
        // Assemble remainder of Fibonacci sequence
        for (int i = 1; i <= MAX_INDEX; i++) {
            tuples[i] = new Tuple(hi, hi % 2 == 0);
            hi = lo + hi;
            lo = hi - lo;
        }
        return tuples;
    }


    public static void main(String[] args) {
        Tuple[] tuples = fibonacciArray();
        // Print out resultant array
        for (Tuple t : tuples) {
            System.out.println(t);
        }
    }
} // end PROBLEM 3