import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Method_ref {
    

    //Example 1

    // interface IntFunction<T> {
    //     int apply(T t);
    // }
    // public static void main(String[] args) {
    //     IntFunction<String> f1 = x -> x.length();
    //     IntFunction<String> f2 = String::length;
    //     System.out.println(f1.apply("Software Development Method"));
    //     System.out.println(f2.apply("Java is great!"));
    // }



    //Example 2

    interface RandomGenerator {
        int get(int scale);
    }
    
    public static void main(String[] args) {
        Random random = new Random();
        RandomGenerator g1 = s -> random.nextInt(s);
        RandomGenerator g2 = random::nextInt;
        System.out.println(g1.get(10));
        System.out.println(g2.get(10));
    }



    //Example 3

    // interface ListSupplier {
    //     List get();
    // }

    // interface ListSupplier2 {
    //     List get(int capacity);
    // }
    
    // public static void main(String[] args) {
    //     ListSupplier s1 = () -> new ArrayList();
    //     ListSupplier s2 = ArrayList::new;
    //     System.out.println(s1.get());
    //     System.out.println(s2.get());
 
    //     ListSupplier2 s3 = c -> new ArrayList(c);
    //     ListSupplier2 s4 = ArrayList::new;
    //     System.out.println(s3.get(25));
    //     System.out.println(s4.get(25));
    // }
    
}


