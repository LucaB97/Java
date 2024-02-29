//An enumeration declaration
// 1. is a list of named constants 
// 2. that define a new data type 
// 3. and its legal values.
//Each enumeration constant is a public static final member of the Degree class

//Enumerations can have fields, constructors and methods

//Once it is declared, an enumeration cannot be changed at runtime

//You don’t instantiate an enumeration, but you can reference its members

//Enumerations automatically get two static methods:
// one to enumerate the constants: public static enum-type [ ] values( )
// and one to get a constant from its name: public static enum-type valueOf(String str )
//Each enumeration constant has an ordinal value: final int ordinal( )

import java.util.EnumSet;

enum Degree {
	HIGH_SCHOOL , BACHELOR , MASTER , PHD
}



public class EnumExample {

	
	public static Degree getDegree(String userInput) {
		
		try {
			String normalizedUserInput = userInput.replace(" ", "_");
			normalizedUserInput = normalizedUserInput.toUpperCase();
			Degree d = Degree.valueOf(normalizedUserInput);
			System.out.println("User input accepted.");
			// switch(d) {
			// 	case HIGH_SCHOOL -> System.out.println("High School");
			// 	case BACHELOR -> System.out.println("Bachelor");
			// 	case MASTER -> System.out.println("Master");
			// 	case PHD -> System.out.println("PhD");
			// }
			return d;
		} catch (IllegalArgumentException e) {
        	System.out.println("User input: \"" + userInput + "\" invalid.");
			return null;
    	}
		
	}

	
	public static void main(String[] args) {
	
		/*
		Degree d1 = Degree.PHD;
		Degree d2 = Degree.valueOf("PHD");
		
		if (d1 == d2) {
			System.out.println("this looks ok!");
		} 
		*/
		
		if (args.length != 1) {
			System.out.println("Usage:\tjava EnumExample \"degree\"\n");
			System.out.println("Degrees:");
			for (Degree dd : Degree.values()) {
				System.out.println(dd.ordinal() + " " + dd);
			}	
			return;
		}

		try {
			Degree d = getDegree(args[0]);
		} catch (ArrayIndexOutOfBoundsException exc) {
			System.out.println("\nNo input provided.\n");
		}		

		/*
		EnumSet<Degree> mySet = EnumSet.range(Degree.MASTER, Degree.PHD);
		for (Degree w : mySet) {
			System.out.println(w);
		}
	 	*/
	
	}

}
