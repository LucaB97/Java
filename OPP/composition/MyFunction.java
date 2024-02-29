public class MyFunction {

    public static void main(String[] args) {

        MyList test1 = new MyList();

        test1.add("A");
        test1.add("B");
        test1.add("C");
        String[] vv = test1.getValues();
        for (String ss : vv) {
            System.out.println(ss);
        }
        System.out.println("\n\n");
        test1.insert(0,"D");
        vv = test1.getValues();
        for (String ss : vv) {
            System.out.println(ss);
        }
        System.out.println("\n\n");
        test1.remove(2);
        vv = test1.getValues();
        for (String ss : vv) {
            System.out.println(ss);
        }
        System.out.println("\n\n");
        System.out.println(test1.indexOf("B"));
        System.out.println(test1.indexOf("C"));
    }
}