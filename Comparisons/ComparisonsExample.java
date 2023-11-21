//Basic use of Comparable and Comparator

import java.util.Comparator;

public class ComparisonsExample {
    
    public static void main(String[] argv) {
	/*
        Comparable<Integer> comp = 6;
        System.out.println("Use of Comparable.\n");
        System.out.println("\n6 > 4? " + comp.compareTo(4));
        System.out.println("\n6 > 6? " + comp.compareTo(6));
        System.out.println("\n6 > 8? " + comp.compareTo(8));
	*/
        Integer aa = 6;
        System.out.println("Use of Comparable.");
        System.out.println("\n6 > 4? " + aa.compareTo(4));
        System.out.println("\n6 > 6? " + aa.compareTo(6));
        System.out.println("\n6 > 8? " + aa.compareTo(8));

        Comparator<Integer> comparator = (num1, num2) -> Integer.compare(num1, num2);
        //Comparator<Integer> comparator = Comparator.comparing(Integer::intValue);
        System.out.println("\n\nUse of Comparator.");
        System.out.println("\n6 > 4? " + comparator.compare(6, 4));
        System.out.println("\n6 > 6? " + comparator.compare(6, 6));
        System.out.println("\n6 > 8? " + comparator.compare(6, 8));
        System.out.println("\n");
    }

}
