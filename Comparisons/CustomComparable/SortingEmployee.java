//Exercise 3: Sorting Employees
//Write a class Employee with attributes name (String), salary (double), and hireDate (Date). 
//Implement a custom Comparator<Employee> to sort employees based on their salaries. 
//Create a list of employees and sort them using Collections.sort() with the custom comparator.

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;


public class SortingEmployee {


    public static class Employee implements Comparable<Employee> {

        private String name;
        private double salary;
        public LocalDate hireDate;
    
        Employee(String n, double s, LocalDate d) {
            name = n;
            salary = s;
            hireDate = d;
        }
        String getName() {return name;}
        double getSalary() {return salary;}
        LocalDate getHireDate() {return hireDate;}
    
        @Override
        public String toString() {
            String printed = "\nEmployee:\n\tName: " + getName() + "\n\tSalary: " + getSalary() + "\n\tHire date: " + getHireDate();
            return printed;
        }
    
        @Override
        public int compareTo(Employee otherEmployee) {
            return Double.compare(this.salary, otherEmployee.salary);
        }
    }
    
    public static void main(String[] argv) {

        List<Employee> myEmployees = new ArrayList<>();
        myEmployees.add(new Employee("Bianchi" , 85000 , LocalDate.of(1997,12,12)));
        myEmployees.add(new Employee("Rossi" , 44500 , LocalDate.of(2022,9,11)));
        myEmployees.add(new Employee("Neri" , 36000.9 , LocalDate.of(2023,9,25)));
        myEmployees.add(new Employee("Verdi" , 185000 , LocalDate.of(1981,3,9)));

        System.out.println(myEmployees);
        Collections.sort(myEmployees);
        System.out.println("\nAfter sorting based on the salary: \n" + myEmployees);


        List<Employee> myEmployees2 = new ArrayList<>();
        myEmployees2.add(new Employee("Bianchi" , 85000 , LocalDate.of(1997,12,12)));
        myEmployees2.add(new Employee("Rossi" , 44500 , LocalDate.of(2022,9,11)));
        myEmployees2.add(new Employee("Neri" , 36000.9 , LocalDate.of(2023,9,25)));
        myEmployees2.add(new Employee("Verdi" , 185000 , LocalDate.of(1981,3,9)));

        Comparator<Employee> dateSort = Comparator.comparing(Employee::getHireDate);
        System.out.println("---------------------------------------\n" + myEmployees2);
        Collections.sort(myEmployees2, dateSort);
        System.out.println("\nAfter sorting based on the hiring date: \n" + myEmployees2);

    }

}
