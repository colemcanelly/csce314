
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5
   Student Name:
   UIN:
   Acknowledgements:
*/

class Square extends Shape {
    private double side; // side is the side length

    // constructor that accepts a Point (for position) and a double
    // (for the side length).
    public Square(Point p0, double side)
    {
        super(p0);
        this.side = side;
        this.area = side * side;
    }

    // implement equals(), hashCode(), area(), and toString()
    @Override
    public boolean equals(Object o)
    {
        return o instanceof Square && super.equals(o);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;                           // Random prime number
        hash = 31 * hash + (int) this.area;     // Use 31 as a prime number and existing hash to generate unique hashcode
        hash = 31 * hash + (position == null ? 0 : position.hashCode());
        hash = 31 * hash + (int) this.side;
        return hash;
    }

    @Override
    public double area()
    {
        return this.area;
    }

    @Override
    public String toString()
    {
        return "Square: " + position.toString() + "\t" + String.valueOf(area) + " units^2";
    }
} // end of class Square
