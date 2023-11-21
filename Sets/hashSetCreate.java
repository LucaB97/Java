import java.util.HashSet;
import java.util.Set;

public class hashSetCreate {

    public static void main(String[] argv) {

        /*Set mySet = new Set<E>() {
            
        };*/

        Set<Integer> mySet = new HashSet<>(3);
        mySet.add(1);
        mySet.add(1);
        mySet.add(2);
        mySet.add(1);
        mySet.remove(1);
        System.out.println(mySet);
    }


}