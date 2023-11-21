import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class EvenNumberSetClass extends AbstractSet<Integer> {

    private final MyCustomStorage<Integer> storage = new MyCustomStorage<>();

    @Override
    public boolean add(Integer newValue) {
        if (newValue % 2 == 0) {
            storage.add(newValue);
            return true;
        }
        return false;
    }

    @Override
    public int size() {return storage.size(); }

    @Override
    public Iterator<Integer> iterator() {return new EvenNumberSetIterator(); }


    private static class MyCustomStorage<T> {
        // Implement your custom storage logic
        // For simplicity, let's say it's a list
        private final java.util.List<T> list = new java.util.ArrayList<>();
        public boolean add(T element) {return list.add(element); }
        public Iterator<T> iterator() {return list.iterator(); }
        public int size() {return list.size(); }
    }


    public class EvenNumberSetIterator implements Iterator<Integer> {
        
        private final Iterator<Integer> iterator = storage.iterator();
        private Integer nextValue = iterator.next();
        private Integer currValue;

        public boolean hasNext() {
            return (nextValue != null);
        }

        public Integer next() {
            
            if (nextValue == null) {
                throw new NoSuchElementException();
            }
            currValue = nextValue;
            if (iterator.hasNext()) {                
                nextValue = iterator.next();                
            } else {
                nextValue = null;
            }
            return currValue;
        }
    }


    public static void main(String[] argv) {

        EvenNumberSetClass mySet = new EvenNumberSetClass();
        /*
        mySet.add(2);
        mySet.add(8);
        mySet.add(1);
        mySet.add(3);
        mySet.add(12);*/

        for (Integer ii : mySet) {
            System.out.println(ii);
        }
    }
    
}
