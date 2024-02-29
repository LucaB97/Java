//Create a generic method named printArray that prints the elements of an array. 
//Test the method with arrays of different types.

import java.util.ArrayList;
import java.util.Collections;

public class GenericMethodExercise {
  
    
    static void printArray(ArrayList<?> myList) {
        
        if (myList.size() == 0) {return;}
        
        var arrType = myList.get(0).getClass().toString();
        arrType = arrType.replaceAll("class java.lang.", "");
        System.out.println("\nPrinting " + arrType + " array:");
        
        for (var cnt : myList) {
            System.out.println(cnt);
        }

    }


    
    public static void main(String[] argv) {

        var intArray = new ArrayList<Integer>();
        Collections.addAll(intArray, 1,2,3,4);
        printArray(intArray);

        var strArray = new ArrayList<String>();
        Collections.addAll(strArray, "hello", "world", "!");        
        printArray(strArray);

    }
}
