import java.util.HashMap;
import java.util.Objects;

public class FileTest {

    public static class File {
 
        private String name;
        
        public File(String name) {
            this.name = name;    
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            File file = (File) o;
            return Objects.equals(name, file.name);
        }
        
        @Override
        public int hashCode() {
            return name.hashCode();
            //return Objects.hash(name);
        }

        public void rename(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }

    }
    

    public static void main(String[] argv) {

        var fileSizes = new HashMap<File, Long>();
        fileSizes.put(new File("C:\\Users\\pvercesi\\Desktop\\lesson8.pdf"), 342340L);
        //fileSizes.put(new File("C:\\Users\\pvercesi\\Desktop\\lesson8a.pdf"), 492340L);
        fileSizes.put(new File("C:\\Users\\pvercesi\\Desktop\\lesson9.pdf"), 512956L);

        System.out.println("\nElements in the HashMap (HashCode, Value):");
        for (File file : fileSizes.keySet()) {
            System.out.println("(" + file.hashCode() + ", " + fileSizes.get(file) + ")");
        }

        //check that the overridden *equals* function works to retrieve a key
        File fileToRetrieve = new File("C:\\Users\\pvercesi\\Desktop\\lesson8.pdf");        
        System.out.println("\nHash code of the desired key: " + fileToRetrieve.hashCode());
        //the get method uses the equals method to compare keys
        System.out.println("Value of the element matching the key: " + fileSizes.get(fileToRetrieve));

        //exploit the *equals* function to find a file and change its name
        String fromName = "C:\\Users\\pvercesi\\Desktop\\lesson8.pdf";
        String toName = "C:\\Users\\pvercesi\\Desktop\\lesson8a.pdf";

        for (File file : fileSizes.keySet()) {
            if (file.getName().equals(fromName)) {
                file.rename(toName);
                System.out.println("\nA match has been found. The key value has been updated.\n");
            }
        }        

        //try to retrieve the file after renaming 
        File fileToRetrieve_2 = new File("C:\\Users\\pvercesi\\Desktop\\lesson8a.pdf");        
        System.out.println("\nHash code of the updated key: " + fileToRetrieve_2.hashCode());        
        System.out.println("Value of the element matching the key: " + fileSizes.get(fileToRetrieve_2));
        
        System.out.println("\nElements in the HashMap (HashCode, Value), key matched?");
        for (File file : fileSizes.keySet()) {
            System.out.println("(" + file.hashCode() + ", " + fileSizes.get(file) + "), " + file.getName().equals(fileToRetrieve_2.getName()));
        }
              
        System.out.println("\nWhen you try to retrieve the File with the new name, the HashMap calculates the \r\n" + //
                "hash code based on the new name, but the entry is stored in the location determined\r\n" + //
                "by the original hash code, which was based on the old name.\r\n" + //
                "This mismatch (between the hash code used to store the object and the hash code \r\n" + //
                "computed when you try to retrieve it) makes HashMap unable to find the desired element.\n");
        //When you try to retrieve the File with the new name, the HashMap calculates the 
        // hash code based on the new name, but the entry is stored in the location determined
        // by the original hash code, which was based on the old name.
        // This mismatch (between the hash code used to store the object and the hash code 
        //computed when you try to retrieve it) makes HashMap unable to find the desired element.
    }
    
}