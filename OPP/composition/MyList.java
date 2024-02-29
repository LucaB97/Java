public class MyList implements List {

    private final MyCollection collection = new MyCollection();

    @Override
    public String[] getValues() {
        return collection.getValues();
    }

    @Override
    public void add(String value) {
        collection.add(value);
    }

    @Override
    public void insert(int index, String value) {
        collection.insert(index, value);
    }

    @Override
    public void remove(int index) {
        collection.remove(index);
    }
}