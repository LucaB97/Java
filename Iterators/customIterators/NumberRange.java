import java.util.Iterator;

public class NumberRange implements Iterable<Integer> {

    private final int start;
    private final int end;

    public NumberRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new NumberRangeIterator();
    }

    private class NumberRangeIterator implements Iterator<Integer> {

        private int current = start;

        @Override
        public boolean hasNext() {
            return current <= end;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the range");
            }
            return current++;
        } 
    }

    public static void main(String[] args) {
        // Using the custom iterator to iterate through a range of numbers
        NumberRange numberRange = new NumberRange(1, 5);
        for (int number : numberRange) {
            System.out.println(number);
        }
    }
}