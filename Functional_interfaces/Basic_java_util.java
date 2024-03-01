import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;


public class Basic_java_util {
    
    public static void main(String[] args) {
        
        //Function
        System.out.println("Function usage example");
        Function<Integer, String> p = x -> ":" + x + ":";
        System.out.println(p.apply(3));
        Function<Integer, Integer> f1 = x -> x * 2;
        Function<String, String> f2 = x -> x + x;
        System.out.println(p.compose(f1).andThen(f2).apply(3));
        
        //Consumer
        System.out.println("\nConsumer usage example");
        Consumer<String> c = x -> System.out.println(x);
        Consumer<String> c2 = c.andThen(y -> System.err.println(y));
        c2.accept("Software Development Methods");

        //Supplier
        System.out.println("\nSupplier usage example");
        Supplier<Long> s = () -> System.currentTimeMillis();
        System.out.println(s.get());
        Supplier<String> m = () -> Arrays.toString(args);
        System.out.println(m.get());

        //Predicate
        System.out.println("\nPredicate usage example");
        Predicate<Integer> greaterThanZero = x -> x > 0;
        Predicate<Integer> smallerThanFive = x -> x < 5;
        Predicate<Integer> betweenZeroAndFive = greaterThanZero.and(smallerThanFive);
        Predicate<Integer> notBetweenZeroAndFive = betweenZeroAndFive.negate();
        System.out.println(notBetweenZeroAndFive.test(6));

    }
}