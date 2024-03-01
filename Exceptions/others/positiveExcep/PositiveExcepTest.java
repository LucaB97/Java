import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PositiveExcepTest {

    public static void readNegativeNumbers(String string) throws FileNotFoundException {
        File myFile = new File(string);
        Scanner myScanner = new Scanner(myFile);
        try {
                while (myScanner.hasNextDouble()) {
                    double num = myScanner.nextDouble();
                    if (num > 0) {
                        throw new PositiveNumberException(num);
                    }
                    System.out.println(num);
                }
        } catch (PositiveNumberException pne) {
            pne.printStackTrace();
        }
        myScanner.close();
        System.out.println("scanner closed");
    }

    public static void main(String[] args) throws FileNotFoundException {
        readNegativeNumbers(args[0]);
    }
}