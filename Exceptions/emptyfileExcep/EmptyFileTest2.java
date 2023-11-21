//Write a Java program that reads a file and 
//throws an exception if the file is empty

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmptyFileTest2 {
    public static void main(String[] args) {
        readFile(args[0]);
    }

    public static void readFile(String fileName) {
        Scanner myScanner = null;
        try {
            File myFile = new File(fileName);
            myScanner = new Scanner(myFile);
            if (!myScanner.hasNextLine()) {
                myScanner.close();
                throw new EmptyFileException(fileName);
            }
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                System.out.println(line);
            }    
            myScanner.close();        
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (EmptyFileException efe) {
            System.out.println(efe.getMessage());
            efe.printStackTrace();
        } finally {
            if (myScanner != null) {
                myScanner.close();
            }
        }       
    }
}
