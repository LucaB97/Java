public class OddNumberException extends Exception {
    public OddNumberException(int number) {
        super("The number (" + number + ") is odd.");
    }
}