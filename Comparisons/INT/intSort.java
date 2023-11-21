import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class intSort {

    static class DescendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer num1, Integer num2) {
            // Reverse the comparison to achieve descending order
            return Integer.compare(num2, num1);
        }
    }
    
    public static void main(String[] argv) {

        List<Integer> myInt = new ArrayList<>();
        myInt.add(34);
        myInt.add(23);
        myInt.add(99);
        myInt.add(107);
        myInt.add(17);
        System.out.println(myInt);
        Collections.sort(myInt);
        System.out.println("Using default sorting:\n" + myInt);

        List<Integer> myInt2 = new ArrayList<>();
        myInt2.add(34);
        myInt2.add(23);
        myInt2.add(99);
        myInt2.add(107);
        myInt2.add(17);
        System.out.println("\n\n" + myInt2);
        Comparator<Integer> valueComp = Comparator.comparing(Integer::intValue);
        Collections.sort(myInt2, valueComp);
        System.out.println("Using in-line defined Comparator for sorting:\n" + myInt2);

        List<Integer> myInt3 = new ArrayList<>();
        myInt3.add(34);
        myInt3.add(23);
        myInt3.add(99);
        myInt3.add(107);
        myInt3.add(17);
        System.out.println("\n\n" + myInt3);
        Comparator<Integer> negvalueComp = Comparator.comparing(Integer::intValue).reversed();
        Collections.sort(myInt3, negvalueComp);
        System.out.println("Using in-line defined Comparator for inverse sorting:\n" + myInt3);

        List<Integer> myInt4 = new ArrayList<>();
        myInt4.add(34);
        myInt4.add(23);
        myInt4.add(99);
        myInt4.add(107);
        myInt4.add(17);
        System.out.println("\n\n" + myInt4);
        Collections.sort(myInt4, new DescendingComparator());
        System.out.println("Using class defined Comparator for inverse sorting:\n" + myInt4);
        
    }  
    

}
