import java.util.LinkedHashSet;
import java.util.Set;

public class gptExample {
    public static void main(String[] args) {
        // Creating a HashSet
        Set<String> stringSet = new LinkedHashSet<>();

        // Adding elements to the set
        stringSet.add("Apple");
        stringSet.add("Banana");
        stringSet.add("Orange");
        stringSet.add("Apple"); // Duplicate elements are not allowed in a Set

        // Displaying the elements in the set
        System.out.println("Elements in the set: " + stringSet);

        // Checking if a specific element is present
        if (stringSet.contains("Banana")) {
            System.out.println("Banana is present in the set.");
        }

        // Removing an element from the set
        stringSet.remove("Orange");

        // Displaying the updated set
        System.out.println("Updated set: " + stringSet);
    }
}