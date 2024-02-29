public class MyStack implements Stack {

    private final MyCollection collection = new MyCollection();

    @Override
    public String[] getValues() {
        return collection.getValues();
    }
    
    @Override
    public void push(String value) {
        collection.add(value);
    }

    @Override
    public String pop() {
        String value = top();
        collection.remove(collection.getSize()-1);
        return value;
    }

}