//Write a Java program that reads a file and 
//throws an exception if the file is empty

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmptyFileTest {
    public static void main(String[] args) {
            try {
                readFile(args[0]);
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            }
    }

    public static void readFile(String fileName) throws FileNotFoundException {
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            if (!myScanner.hasNextLine()) {
                myScanner.close();
                throw new EmptyFileException(fileName);
            }
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                System.out.println(line);
            }    
            myScanner.close();        
        } catch (EmptyFileException efe) {
            System.out.println(efe.getMessage());
            efe.printStackTrace();
        }         
    }
}
