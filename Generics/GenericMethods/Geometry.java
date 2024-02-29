//Create a hierarchy of generic classes representing different shapes. 
//Include classes for a Circle, Rectangle, and Triangle. 
//Each shape class should have methods to calculate area and perimeter.

import java.lang.Math;
import java.lang.NullPointerException;

public class Geometry {
    
    public static class Triangle <T extends Number> implements Poligon<T> {

        private T sideA, sideB, sideC;

        Triangle(T a, T b, T c) {
            if (Double.valueOf(a.doubleValue()).compareTo(0.) <= 0 || 
            Double.valueOf(b.doubleValue()).compareTo(0.) <= 0 || 
            Double.valueOf(c.doubleValue()).compareTo(0.) <= 0) {
                System.out.println("Value not admissable");
                return;
            }
            this.sideA = a;
            this.sideB = b;
            this.sideC = c;
        }

        public Double perimeter() {
            try {
                Double res = sideA.doubleValue() + sideB.doubleValue() + sideC.doubleValue();
                return res;
            } catch(NullPointerException npe) {
                //System.out.println(npe.getMessage());
                return null;
            }             
        }    

        public Double area() {
            try {
                Double semiP = this.perimeter() / 2;
                return Math.sqrt(semiP*(semiP-sideA.doubleValue())*(semiP-sideB.doubleValue())*(semiP-sideC.doubleValue()));
            } catch(NullPointerException npe) {
                //System.out.println(npe.getMessage());
                return null;
            }             
        }
    }


    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Usage: java Geometry <a> <b> <c>\n");
            return;
        }

        //var myTr = new Triangle<Double>(7., 3., 5.);
        var myTr = new Triangle<Double>(Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]));

        System.out.println("perimeter: " + myTr.perimeter());

        System.out.println("area: " + myTr.area());
    }

}
