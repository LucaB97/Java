public class MyStack extends MyCollection implements Stack {

    @Override
    public void push(String value) {
        super.add(value);
    }

    @Override
    public String pop() {
        String value = top();
        remove(getSize()-1);
        return value;
    }

}