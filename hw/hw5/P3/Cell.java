
/* Cell.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 595, 596, 597] Spring 2023, Assignment 5 

   Student Name:
   UIN:
   Acknowledgements:
*/

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

// class Cell: 15 points
// give correct class header - given in the problem statement
public final class Cell<E> implements Iterable<E>
{
    // private fields
    private E elem; // stores a value of type E
    private Cell<E> next; // link to the next Cell

    // (5 points) constructor
    public Cell(E elem, Cell<E> next) {
        this.elem = elem;
        this.next = next;
    }

    // (5 points) iterator() returns a CellIterator object for this object
    @Override
    public CellIterator<E> iterator() { return new CellIterator<E>(this); }

    // (5 points) getter and setter methods for the private fields
    public E getVal() { return this.elem; }

    public void setVal(E v) { this.elem = v; }

    public Cell<E> getNext() { return this.next; }

    public void setNext(Cell<E> node) { this.next = node; }

    // CellIterator: 20 points
    // Having CellIterator as an inner class of Cell makes sense...
    // (2 points) correct class header - given in the problem statement
    class CellIterator<E> implements Iterator<E>
    {
        private Cell<E> curr; // given

        // (3 points) constructor
        public CellIterator(Cell<E> n)
        {
            this.curr = n;
        }

        // (5+10=15 points) two methods to implement the Iterator interface
        // (5 points) hasNext()
        @Override
        public boolean hasNext() { return this.curr != null; }

        // (10 points) next()
        @Override
        public E next()
        {
            // Throw an exception if there are no more elements
            if (!hasNext()) throw new NoSuchElementException();
            // Get the current data to return
            E data = this.curr.getVal();
            // Mover the cursor to the next element
            this.curr = this.curr.next;
            // Return the previous node's data
            return data;
        }

    } // end of CellIterator
} // end of Cell
