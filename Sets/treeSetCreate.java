import java.util.SortedSet;
import java.util.TreeSet;

public class treeSetCreate {
    
    public static void main(String[] argv) {

        SortedSet<Integer> mySet = new TreeSet<>();
        mySet.add(12);
        mySet.add(1);
        mySet.add(2);
        mySet.add(7);
        mySet.add(102);

        mySet.remove(12);
        System.out.println(mySet);

        System.out.println(mySet.last());
        //System.out.println(mySet.headSet(5));
        System.out.println(mySet.tailSet(4));

    }

}
