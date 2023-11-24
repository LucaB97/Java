import java.util.Set;
import java.util.AbstractSet;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class CustomSetExample {
    
    public static class EvenNumberSet extends AbstractSet<Integer> {

        private final MyCustomStorage<Integer> storage = new MyCustomStorage<>();

        @Override
        public int size() {
            return storage.size();
        }
        
        @Override
        public boolean add(Integer integer) {
            if (integer % 2 == 0) {
                storage.add(integer);
                return true;
            }
            return false; // Reject odd numbers
        }

        @Override
        public Iterator<Integer> iterator() {
            return new EvenNumberIterator();
        }        

    

        private static class MyCustomStorage<T> {
            // Implement your custom storage logic
            // For simplicity, let's say it's a list
            private final List<T> list = new ArrayList<>();

            public boolean add(T element) {
                return list.add(element);
            }

            public Iterator<T> iterator() {
                return list.iterator();
            }

            public int size() {
                return list.size();
            }
        }

        private class EvenNumberIterator implements Iterator<Integer> {
            
            private final Iterator<Integer> iterator = storage.iterator();
            private Integer nextEven = findNextEven();

            @Override
            public boolean hasNext() {
                //System.out.println("hasNext call:\tnextEven is: " + nextEven);
                return nextEven != null;
            }

            @Override
            public Integer next() {
                //System.out.println("nextEven is: " + nextEven);
                if (nextEven == null) {
                    throw new NoSuchElementException();
                }
                Integer currentEven = nextEven;
                nextEven = findNextEven();
                return currentEven;
            }

            private Integer findNextEven() {
                while (iterator.hasNext()) {
                    //System.out.println("\nCalling *next* function\n");
                    Integer next = iterator.next();
                    if (next % 2 == 0) {
                        return next;
                    }
                }
                return null;
            }
        }
    }

    public static void main(String[] args) {
        // Creating and testing the custom EvenNumberSet
        EvenNumberSet evenNumberSet = new EvenNumberSet();
        evenNumberSet.add(2);
        evenNumberSet.add(5); // This should be rejected
        evenNumberSet.add(4);
        evenNumberSet.add(7); // This should be rejected
        evenNumberSet.add(6);

        // Displaying the elements in the custom set
        System.out.println("Even Numbers: " + evenNumberSet);
    }

}
