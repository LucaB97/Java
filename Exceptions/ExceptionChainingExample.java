public class ExceptionChainingExample {
    public static void main(String[] args) {
        try {
            divide(10, 0);
        } catch (Exception e) {
            System.err.println("Caught an exception: " + e.getMessage());

            // Extract and print the original cause of the exception
            Throwable originalCause = e.getCause();
            if (originalCause != null) {
                System.err.println("Original cause: " + originalCause.getMessage());
            }
        }
    }

    public static void divide(int numerator, int denominator) {
        try {
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            // Create a new exception and chain it to the original exception
            RuntimeException customException = new RuntimeException("Custom Exception");
            customException.initCause(e);

            // Throw the new exception with the original exception as its cause
            throw customException;
        }
    }
}