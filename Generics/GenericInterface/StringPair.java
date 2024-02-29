//Create a generic interface named Pair that defines a method to get the first and 
// second elements of a pair. 
//Implement the interface for a class representing a pair of elements

    
public class StringPair implements Pair<String,String> {

    private String first;
    private String second;

    StringPair(String s1, String s2) {
        this.first = s1;
        this.second = s2;
    }
    
    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }


    public static void main(String[] argv) {

        StringPair myPair = new StringPair("hello", "world");

        System.out.println("getFirst: " + myPair.getFirst());
        System.out.println("getSecond: " + myPair.getSecond());
    }

}


    


