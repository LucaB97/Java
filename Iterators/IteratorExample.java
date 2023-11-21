// Iterators in Java are a way to traverse through elements in a collection without 
//  exposing the underlying structure of the collection. They provide a standard 
//  interface for iterating over different types of collections, including lists, 
//  sets, and maps. The Iterator interface is part of the Java Collections Framework.

// Here's a brief overview of the Iterator interface and its key methods:
//  • boolean hasNext(): Returns true if there are more elements in the collection, 
//    and false otherwise.
//  • E next(): Returns the next element in the iteration. If there are no more 
//    elements, a NoSuchElementException is thrown.
//  • void remove(): Removes the last element returned by next from the underlying 
//    collection. This method is optional, and not all iterators support removal. 
//    Attempting to remove an element without support will result in an UnsupportedOperationException.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        // Using Iterator to iterate and remove elements
        //for (Iterator<Integer> iterator = numbers.iterator() ; iterator.hasNext() ; )
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            int number = iterator.next();
            if (number == 2) {
                iterator.remove(); // Removes the element 2 from the list
            }
        }

        // Displaying the updated list
        System.out.println("Updated List: " + numbers);
    }
}