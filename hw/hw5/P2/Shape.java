
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5 
   Student Name:
   UIN:
   Acknowledgements:
*/

abstract class Shape implements Comparable<Shape> {
    public Point position;
    public double area;

    // constructor that sets position as the Point passed as an argument
    // signature: Shape (Point)
    // implement the constructor
    public Shape(Point _p)
    {
        this.position = _p;
    }

    // implement equals()
    @Override
    public boolean equals(Object o)
    {
        // Check if the object is null
        if (o == null) return false;
        // Check if the objects are shallow copies
        if (this == o) return true;
        // Check if the object is an instance of a shape, if so cast it and compare values
        if (o instanceof Shape) {
            Shape s = (Shape) o;
            return this.position.equals(s.position) && (this.area == s.area);
        }
        return false;
    }

    // area() should be abstract
    public abstract double area();

    // implement compareTo()
    @Override
    public int compareTo(Shape _s)
    {
        // Compare shapes based off of area
        return ((Double) this.area).compareTo(_s.area);
    }
} // end of class Shape
