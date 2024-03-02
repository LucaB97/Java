// "Record" is a new feature introduced in Java 14 that provides a concise way of declaring classes that are essentially data carriers.
// They reduce boilerplate code by automatically generating constructor, accessor methods, equals(), hashCode(), and toString() methods 
// based on the components declared in the record.

// • We access the fields of the record using accessor methods generated automatically for each component. 
// • Records are immutable, so once created, their state cannot be modified. Trying to modify the state will result in a compilation error.
// • Records provide a convenient toString() method that prints out the contents of the record in a readable format.

public class RecordExample {
    public static void main(String[] args) {
        // Creating a new instance of the Person record
        Person person = new Person("John", 30);

        // Accessing fields of the record
        System.out.println("Name: " + person.name());
        System.out.println("Age: " + person.age());

        // Records are immutable, so you can't modify their state after creation
        // person.age(31); // This will result in a compilation error

        // Records provide a convenient toString() method
        System.out.println("Person details: " + person);
    }

    // Declaring a record named "Person" with two components: name and age
    record Person(String name, int age) {}
}