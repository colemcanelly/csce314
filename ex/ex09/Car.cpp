// Written by Hyunyoung Lee for CSCE 314 Students
// This example is for
//  (1) a simple class and subclass relation,
//  (2) upcasting,
//  (3) an effect of invoking subclass constructor,
//  (4) dynamic binding vs. static binding ** compare with Java
//  (5) a variable scoping rule -- block scope

#include <iostream>
using namespace std;

class Car {
public:
  Car() {
    cout << "A car is constructed!" << endl; 
  }
  void run() {
    cout << "A car is running!" << endl; 
  }
};

class MyCar: public Car {
public:
  MyCar() {
    cout << "Here, my car is constructed!" << endl; 
  }
  void run() {
    cout << "That is my car running!" << endl;
  }
};
		
int main()
{
  Car a;
  cout << endl;

  cout << "** constructing MyCar ..." << endl;
  //Car b = MyCar();
  MyCar b;
  cout << endl;
  Car * ptr_b = &b; //upcasting

  a.run();
  b.run();
  ptr_b->run();
  cout << endl;

  int i = 10;
  cout << "i = " << i << endl;

  for (int i = 0; i < 5; i++) {
    cout << "for i = " << i << endl;
  }
}

