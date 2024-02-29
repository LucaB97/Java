//Create a generic method named findMax that finds and returns the maximum element from an array. 
//Use a type parameter with bounds to ensure that the elements in the array are comparable

import java.util.ArrayList;
import java.util.Collections;

public class Maximum {
    
    
    public static <T extends Comparable<T>> T findMax(ArrayList<T> myList) {
        
        if (myList == null || myList.isEmpty()) {return null;}

        T max = myList.get(0);

        for (var cnt : myList) {
            if (cnt.compareTo(max) > 0) {
                max = cnt;
            }
        }
        return max;
    }
    


    public static void main(String[] argv) {

        System.out.println("Try with Integers:");
        var myArray = new ArrayList<Integer>();
        System.out.println(findMax(myArray));
        Collections.addAll(myArray, 1,5,3,2,4,10,7,6,8);
        System.out.println(findMax(myArray));
        myArray = null;
        System.out.println(findMax(myArray));


        System.out.println("\nTry with Doubles:");
        var myArray2 = new ArrayList<Double>();
        Collections.addAll(myArray2, 1.5,3.2,4.,10.7,6.8);
        System.out.println(findMax(myArray2) + "\n");

    }
}
