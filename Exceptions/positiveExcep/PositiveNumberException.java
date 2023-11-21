public class PositiveNumberException extends Exception {
    PositiveNumberException(double number) {
        super("The number (" + number + ") is positive.");
    }
}