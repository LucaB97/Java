import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class union_N_intersection {
    
    public static void main(String[] argv) {

        Set<Integer> set1 = new TreeSet<>();
        Set<Integer> set2 = new TreeSet<>();

        for (int i = 0; i < 5; i++) {
            set1.add(4*i);
            set2.add(6*i);
        }

        System.out.println(set1);
        System.out.println(set2);

        // Perform union of the two sets
        Set<Integer> union = new TreeSet<>(set1);
        union.addAll(set2);
        System.out.println("Union of Set 1 and Set 2: " + union);

        // Perform intersection of the two sets
        Set<Integer> intersection = new TreeSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection of Set 1 and Set 2: " + intersection);
        

    }

}
