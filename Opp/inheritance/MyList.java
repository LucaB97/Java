public class MyList extends MyCollection implements List {

    @Override
    public void add(String value) {
        super.add(value);
    }

    @Override
    public void insert(int index, String value) {
        super.insert(index, value);
    }

    @Override
    public void remove(int index) {
        super.remove(index);
    }
}