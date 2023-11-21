//Write a Java program to create a method that reads a file 
//and throws an exception if the file is not found

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileNotFoundTest {
    public static void main(String[] args) {
        try {
            readFile(args[0]);
        } catch (FileNotFoundException fnfe) {
            //System.out.println(fnfe.getMessage());
            fnfe.printStackTrace();
        }
    }

    public static void readFile(String fileName) throws FileNotFoundException {
        File myFile = new File(fileName);
        Scanner myScanner = new Scanner(myFile);
        while (myScanner.hasNextLine()) {
            String line = myScanner.nextLine();
            System.out.println(line);
        }
        myScanner.close();
    }
}