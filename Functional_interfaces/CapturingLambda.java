public class CapturingLambda {
    
    private double a = 3.14;
    
    public void print_a() {System.out.println(a);}
    
    public CapturingLambda() {
        double b = 0.1;
        Runnable lambda = () -> System.out.println(a + b);
        lambda.run();
        a = 6;
        lambda.run();
    }
    
    public static void main(String[] args) {
        CapturingLambda capturingLambda = new CapturingLambda();
    }
}