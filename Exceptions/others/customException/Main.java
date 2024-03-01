public class Main {    

    public static class TextTooLongException extends Exception {
        public TextTooLongException(int size) {
            super("Text length (" + size + ") exceeds display size");
        }
    }    
    
    public static class FixedSizeDisplay {

        private static final int SIZE = 10;
    
        public void display(String text) throws TextTooLongException {
            if (text.length() > SIZE) {
                var newText = text.substring(0, 10);
                System.out.println(newText);
                throw new TextTooLongException(text.length());
            }
            System.out.println(text);
        }
    }

    
    public static void main(String[] args) throws TextTooLongException {
        FixedSizeDisplay fsd = new FixedSizeDisplay();

        fsd.display("hello");
        fsd.display("hello everyone");    
    }
    
    // public static void main(String[] args) {
    //     FixedSizeDisplay fsd = new FixedSizeDisplay();

    //     try {
    //         fsd.display("hello");
    //         fsd.display("hello everyone");
    //     } catch (TextTooLongException ttle) {
    //         System.out.println(ttle.getMessage());
    //     }        
    // }

}
