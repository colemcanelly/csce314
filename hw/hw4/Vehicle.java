import java.util.ArrayList;
import java.util.Collections;

public class Vehicle {
    /**\
     * PROBLEM 4
     * Section 2.1 Exercise 2.1
     *      Write a simple Vehicle class that has fields for (at least) current speed,
     *      current direction in degrees, and owner name.
     * Section 2.2 Exercise 2.3
     *      Add a static field to your Vehicle class to hold the next vehicle identification number,
     *      and a non-static field to the Vehicle class to hold each car's ID number.
     * Section 2.6 Exercise 2.13
     *      Make the fields in your Vehicle class private, and add accessor methods for the fields.
     *      Which fields should have methods to change them, and which should not?
     * 
     * PROBLEM 5
     * Section 2.5, Exercise 2.7
     *      Add two constructors to Vehicle: a no-arg constructor and one that takes an initial owner's
     *      name. Modify the main program so that it generates the same output it did before.
     * Section 2.6, Exercise 2.9
     *      Add a static method to Vehicle that returns the highest identification number used thus far.
     * 
     * PROBLEM 6
     * Section 2.6, Exercise 2.10
     *      Add a toString method to Vehicle.
     * 
     * PROBLEM 7
     * Section 2.6, Exercise 2.15
     *      Add a changeSpeed method that changes the current speed of the vehicle to a passed-in
     *      value and add a stop method that sets the speed to zero.
     * 
     * PROBLEM 8
     *  Section 2.8, Exercise 2.17
     *      Add two turn methods to Vehicle: one that takes a number of degrees to turn and one that
     *      takes either of the constants Vehicle.TURN_LEFT or Vehicle.TURN_RIGHT.
     */

    // public fields
    public static final int TURN_LEFT = -90;
    public static final int TURN_RIGHT = 90;
    // private fields
    private int speed;
    private int direction; // degrees; (0 = North, 90 = East, 180 = South, 270 = West)
    private String owner_name;
    private static int next_vin;  // autoincrementing upon construction
    private int vin;

    // constructors
    public Vehicle()
    {
        this.vin = next_vin;
        next_vin++;
    }
    public Vehicle(String owner)
    {
        this.owner_name = owner;
        this.vin = next_vin;
        next_vin++;
    }
    // static methods
    public static int highestVin () { return next_vin - 1; }

    // public getters
    public int getVin () { return vin; }
    public int getSpeed () { return speed; }
    public int getDirection () { return direction; }
    public String getOwner () { return owner_name; }
    
    // public setters
    // Don't need ability to change VIN, it is a one-time event, when car is made
    public void setSpeed (int speed) { this.speed = speed; }
    public void setDirection (int direction) { this.direction = direction; }
    public void setOwner (String owner_name) { this.owner_name = owner_name; }

    // general public methods

    // PROBLEM 6
    @Override
    public String toString()
    {
        return getVin() + ": " + getOwner() + " is driving " + getSpeed() + "mph @ " + getDirection() + " degrees";
    }
    
    // PROBLEM 7
    public void changeSpeed (int speed) { this.speed = speed; }
    public void stop () { this.speed = 0; }
    
    // PROBLEM 8
    // Don't need to overload becuase the constants are of type int.
    public void turn (int degs) {
        /* This keeps the direction in between 0 and 359 degrees,
        preventing possible integer overflow and otherwise extremely negative directions */ 
        this.direction = (this.direction + degs + 360) % 360;
    }
}

/**
 * Starting with the Vehicle class from the exercises in Chapter 2, create an extended class
 * called PassengerVehicle to add a capability for counting the number of seats available in the car and the
 * number currently occupied. Provide a new main method in PassengerVehicle to create a few of these
 * objects and print them out
 */
class PassengerVehicle extends Vehicle implements Comparable<PassengerVehicle>{
    private int seats;
    private int occupied;

    public PassengerVehicle() { super(); }
    public PassengerVehicle(String name) { super(name); }
    public PassengerVehicle(String name, int total_seats)
    {
        super(name);
        this.seats = total_seats;
    }

    public int getSeats () { return seats; }
    public int getOccupied () { return occupied; }

    public void setOccupied (int occupied) { this.occupied = occupied; }

    @Override
    public int compareTo(PassengerVehicle o) {
        return this.getSeats() - o.getSeats();
    }

    @Override
    public String toString() {
        return super.toString() + " with possibly " + getSeats() + " other people";
    }

    public static void main(String[] args) {
        ArrayList<PassengerVehicle> pvs = new ArrayList<PassengerVehicle>();
        pvs.add( new PassengerVehicle("Cole", 5) );
        pvs.add( new PassengerVehicle("Evan", 4) );
        pvs.add( new PassengerVehicle("Tate", 3) );
        pvs.add( new PassengerVehicle("Connor", 7) );
        pvs.add( new PassengerVehicle("Daniel", 2) );

        Collections.sort(pvs);

        for (PassengerVehicle pv : pvs) {
            System.out.println(pv);
        }
    }
}


class VehicleTest {
    static final int NUM_VEHICLES = 10;
    /*
    * In the main method, you will create ten vehicles using the constructors
    * you added to the Vehicle class, first five of which using the no-arg constructor
    * (setting the values using the setter methods that you implemented in Exercise 2.13)
    * and the latter five vehicles using the one-arg constructor.
    */
    public static void main(String[] args) {
        // create Vehicle instances

        // Default values for each car in an initial array, so I can use a loop
        int[] speeds = { 75,0,20,80,31,45,57,30,41,10 };
        int[] dirs = { 0,70,160,290,3,99,180,240,45,69 };
        String[] names = { "Charlie","Noah","Amelia","Harry","Olivia","George","Isla","Leo","Ava","Theo" }; // Stereotypical british names lol
        Vehicle[] vs = new Vehicle[NUM_VEHICLES];
        
        // First 5 using the no-arg constructor
        int i = 0;
        for (;i < 5; i++) {
            vs[i] = new Vehicle();
            vs[i].setOwner(names[i]);
            vs[i].setDirection(dirs[i]);
            vs[i].setSpeed(speeds[i]);
        }
        // Latter 5 using the one-arg constructor
        for (;i < 10; i++) {
            vs[i] = new Vehicle(names[i]);
            vs[i].setDirection(dirs[i]);
            vs[i].setSpeed(speeds[i]);
        }
        
        // test the functionalities you implemented
        int num_tests = 0;
        int passed = 0;
        
        // PROBLEM 6
        for (int j = 0; j < 10; j++)
        {
            String output = j + ": " + names[j] + " is driving " + speeds[j] + "mph @ " + dirs[j] + " degrees";
            num_tests++;
            if (output.equals(vs[j].toString())) passed++;
        }

        // PROBLEM 7
        vs[3].stop();
        num_tests++;
        if (vs[3].getSpeed() == 0) passed++;

        int new_speed = 100;
        vs[9].changeSpeed(new_speed);
        num_tests++;
        if (vs[9].getSpeed() == new_speed) passed++;
        
        // PROBLEM 8
        int degrees = 27;
        vs[6].turn(degrees);
        num_tests++;
        if (vs[6].getDirection() == dirs[6] + 27) passed++;
        
        vs[0].turn(Vehicle.TURN_LEFT);
        num_tests++;
        if (vs[0].getDirection() == 270) passed++;


        // Output results
        System.out.println("Passed " + passed + "/" + num_tests + " tests");

    }
}
