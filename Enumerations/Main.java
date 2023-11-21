class Main {

	public static void main(String[] args) {
	
		Degree d1 = Degree.PHD;
		Degree d2 = Degree.valueOf("PHD");
		
		if (d1 == d2) {
			System.out.println("this looks ok!");
		}
		
		for (Degree dd : Degree.values()) {
			System.out.println(dd.ordinal() + " " + dd);
		}
		
		
		Degree d = getDegree(args[0]);
		
		switch(d) {
			case HIGH_SCHOOL -> System.out.println("High School");
			case BACHELOR -> System.out.println("Bachelor");
			case MASTER -> System.out.println("Master");
			case PHD -> System.out.println("PhD");
		}
	
	}

}
