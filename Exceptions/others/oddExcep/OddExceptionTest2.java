//Write a Java program to create a method that takes an integer as a 
//parameter and throws an exception if the number is odd

public class OddExceptionTest2 {
    public static void check_number(int number) {
        try {
            if (number % 2 != 0) {
                throw new OddNumberException(number);
            }
        } catch (OddNumberException one) {
            System.out.println(one.getMessage());
        }
        
    }
    public static void main(String[] args) {
        int t = 3;
        check_number(t);
    }
}