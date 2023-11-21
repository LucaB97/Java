import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumberSet extends AbstractSet<Integer> {

    private final MyCustomStorage<Integer> storage = new MyCustomStorage<>();

    @Override
    public Iterator<Integer> iterator() {
        return new EvenNumberIterator();
    }

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

    private static class MyCustomStorage<T> {
        // Implement your custom storage logic
        // For simplicity, let's say it's a list
        private final java.util.List<T> list = new java.util.ArrayList<>();

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
            return nextEven != null;
        }

        @Override
        public Integer next() {
            if (nextEven == null) {
                throw new NoSuchElementException();
            }
            Integer currentEven = nextEven;
            nextEven = findNextEven();
            return currentEven;
        }

        private Integer findNextEven() {
            System.out.println(iterator.hasNext());
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next % 2 == 0) {
                    return next;
                }
            }
            return null;
        }

        /*private Integer nextEven = null;

        @Override
        public boolean hasNext() {
            if (nextEven == null) {
                nextEven = findNextEven();
            }
            return nextEven != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Integer currentEven = nextEven;
            nextEven = null; // Reset nextEven for the next iteration
            return currentEven;
        }

        private Integer findNextEven() {
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next % 2 == 0) {
                    return next;
                }
            }
            return null;
        }*/
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