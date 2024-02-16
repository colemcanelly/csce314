
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5
   Student Name:
   UIN:
   Acknowledgements:
*/

public final class Point {
    public double x;
    public double y;

    // constructor that sets the values of x and y
    public Point(double _x, double _y)
    {
        this.x = _x;
        this.y = _y;
    }

    // implement equals, hashCode(), toString()
    @Override
    public boolean equals(Object s)
    {
        // Check if the object is null
        if (s == null) return false;
        // Check if the objects are shallow copies
        if (this == s) return true;
        // Check if the object is an instance of a point, if so cast it and compare values
        if (s instanceof Point) {
            Point p = (Point) s;
            return (this.x == p.x) && (this.y == p.y);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;                       // Random prime number
        hash = 31 * hash + (int) this.x;    // Use 31 as a prime number and existing hash to generate unique hashcode
        hash = 31 * hash + (int) this.y;    // Use 31 as a prime number and existing hash to generate unique hashcode
        return hash;
    }

    @Override
    public String toString()
    {
        // Output point in cartesian format
        return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
    }
} // end of class Point
