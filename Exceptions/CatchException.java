//Basic exception handling

public class CatchException {

    public static void main(String[] args) {

        String s = "Hello";

        try {
            System.out.println(s.charAt(10));
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("An error happened: " + ex.getMessage());
        }

    }

}