//Write a Java program that reads a file and 
//throws an exception if the file is empty

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EmptyFileTest {

    public static class NoInputProvidedException extends Exception {
        NoInputProvidedException() {
            super("Function usage: java EmptyFileTest \"file_name\"");
        }
    }
    
    public static class EmptyFileException extends Exception {
        EmptyFileException(String fileName) {
            super("The specified file (" + fileName + ") is empty");
        }
    }

    public static void readFile(String fileName) throws FileNotFoundException, EmptyFileException {
        
        Scanner myScanner = null;
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
    }

    public static void main(String[] args) throws NoInputProvidedException, FileNotFoundException, EmptyFileException {
        if (args.length == 0) {
            throw new NoInputProvidedException();
        }
        readFile(args[0]);
    }
}








    // public static void readFile(String fileName) throws EmptyFileException {
        
    //     Scanner myScanner = null;
    //     try {
    //         File myFile = new File(fileName);
    //         myScanner = new Scanner(myFile);
    //         if (!myScanner.hasNextLine()) {
    //             throw new EmptyFileException(fileName);
    //         }
    //         while (myScanner.hasNextLine()) {
    //             String line = myScanner.nextLine();
    //             System.out.println(line);
    //         }    
    //     } catch (FileNotFoundException fnfe) {
    //         fnfe.printStackTrace();
    //     } finally {
    //         if (myScanner != null) {
    //             myScanner.close();
    //         }
    //     }       
    // }
