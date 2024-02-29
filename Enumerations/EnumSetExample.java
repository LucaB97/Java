import java.util.EnumSet;
import java.util.Objects;

// Define an enumeration named Day
enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

public class EnumSetExample {
    public static void main(String[] args) {
        // Creating an EnumSet using all elements of the Day enumeration
        EnumSet<Day> weekendDays = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        System.out.println("Weekend days: " + weekendDays);

        // Creating an EnumSet using a range of elements
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("Weekdays: " + weekdays);

        // Creating an empty EnumSet and adding elements later
        EnumSet<Day> workingDays = EnumSet.noneOf(Day.class);
        workingDays.add(Day.MONDAY);
        workingDays.add(Day.TUESDAY);
        workingDays.add(Day.WEDNESDAY);
        workingDays.add(Day.THURSDAY);
        workingDays.add(Day.FRIDAY);
        System.out.println("Working days: " + workingDays);
        // Creating a complement of an existing EnumSet
        EnumSet<Day> nonWorkingDays = EnumSet.complementOf(workingDays);
        System.out.println("Non-working days: " + nonWorkingDays);

        System.out.println("\nweekdays = workingDays ? " + Objects.equals(weekdays, workingDays));
        System.out.println("weekendDays = nonWorkingDays ? " + weekendDays.equals(nonWorkingDays));
    }
}