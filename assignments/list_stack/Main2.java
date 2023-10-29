import myObj.MyList;

class Main2 {

	public static void main(String[] args) {
	
		
		MyList L = new MyList();
		
		System.out.println("\nAdd called.");		
		L.add("hello world");
		
		System.out.println("\ndoes the list contain \"hello world\"? " + L.contains("hello world"));		
		System.out.println("\nthe size of the list is: " + L.getSize());		
		System.out.println("\nthe list is: \n" + L);
		
		System.out.println("\nAdd called.");		
		L.add("hello luca");
		
		System.out.println("\nthe size of the list is: " + L.getSize());		
		System.out.println("\nthe list is: \n" + L);
		
		System.out.println("\nRemove called.");		
		L.remove(1);
		
		System.out.println("\nthe list is: \n" + L);		
		System.out.println("\nthe size of the list is: " + L.getSize());

		System.out.println("\nInsertAt called.");		
		L.insertAt(2, "hello hiwot");
		
		System.out.println("\nRemove called.");		
		L.remove(11);
		
		System.out.println("\nthe list is: \n" + L);
		
		System.out.println("\nInsertAt called.");		
		L.insertAt(5, "hello jack");
		
		System.out.println("\nthe list is: \n" + L);		
		System.out.println("\nis the list empty now? " + L.isEmpty());		
		System.out.println("\nthe size of the list is: " + L.getSize());
		
		System.out.println("\nGet called.");		
		System.out.println(L.get(0));
		System.out.println("\nGet called.");		
		System.out.println(L.get(10));
	
	}

}
