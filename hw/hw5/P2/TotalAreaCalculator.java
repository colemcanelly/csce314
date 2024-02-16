
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5 
   Student Name:
   UIN:
   Acknowledgements:
*/

class TotalAreaCalculator {
    public static double calculate(Shape[] shapes) {
        // summing up the areas
        double total = 0.0;
        for (Shape s : shapes)  // for each shape in the shapes array,
        {
            total += s.area();  // invoke the object's area() method,
        }
        return total;           // and finally returns the total area
    }
}
