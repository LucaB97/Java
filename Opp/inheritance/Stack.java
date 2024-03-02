public interface Stack extends Collection {

    void push(String string);

    String pop();

    default String top() {
        return getValues()[getSize()-1];
    }

}