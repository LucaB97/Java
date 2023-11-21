public class EmptyFileException extends Exception {
    EmptyFileException(String fileName) {
        super("The specified file (" + fileName + ") is empty");
    }
}