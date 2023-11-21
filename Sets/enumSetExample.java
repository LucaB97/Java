import java.util.EnumSet;

enum Days {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class enumSetExample {
    public static void main(String[] args) {
        // Creating an EnumSet
        EnumSet<Days> weekDays = EnumSet.of(Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY);

        // Displaying the elements in the EnumSet
        System.out.println("Weekdays: " + weekDays);

        // Other operations specific to EnumSet can be performed
    }
}
