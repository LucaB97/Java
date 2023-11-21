//Write a Java program to create a method that takes an integer as a 
//parameter and throws an exception if the number is odd

public class OddExceptionTest {
    public static void check_number(int number) throws OddNumberException {
        if (number % 2 != 0) {
            throw new OddNumberException(number);
        }
    }
    public static void main(String[] args) throws OddNumberException {
        int t = 13;
        check_number(t);
    }
}