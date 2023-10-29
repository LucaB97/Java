import myObj.MyList;

class Main_list {

	public static void main(String[] args) {
	
		
		MyList L = new MyList();
		
		System.out.println("\nAdd called: adding \"hello world\"");		
		L.add("hello world");
		
		System.out.println("\nContain called: does the list contain \"hello world\"? " + L.contains("hello world"));		
		System.out.println("\ngetSize called: " + L.getSize());		
		System.out.println("\nThe list is: \n" + L);
		
		System.out.println("Add called: adding \"hello luca\"");		
		L.add("hello luca");
		
		System.out.println("\ngetSize called: " + L.getSize());		
		System.out.println("\nThe list is: \n" + L);
		
		System.out.println("Remove called: element 1");		
		L.remove(1);
		
		System.out.println("\nthe list is: \n" + L);		
		System.out.println("getSize called: " + L.getSize());

		System.out.println("\nInsertAt called: insert \"hello hiwot\" in position 1");		
		L.insertAt(1, "hello hiwot");
		System.out.println("\nThe list is: \n" + L);

		System.out.println("InsertAt called: insert \"hello jack\" in position 50");		
		L.insertAt(50, "hello jack");
		System.out.println("\nThe list is: \n" + L);

		System.out.println("Remove called: element 11");		
		L.remove(11);		
		System.out.println("\nThe list is: \n" + L);

		System.out.println("InsertAt called: insert \"hello luca\" in position -1");		
		L.insertAt(-1, "hello luca");
		System.out.println("\nThe list is: \n" + L);

		System.out.println("InsertAt called: insert \"hello luca\" in position 1");		
		L.insertAt(1, "hello luca");
		System.out.println("\nThe list is: \n" + L);

		System.out.println("IndexOf called: look for string \"hello luca\"");		
		System.out.println(L.indexOf("hello luca"));

		System.out.println("\nInsertAt called: insert \"hello everyone\" in position 3");		
		L.insertAt(3, "hello everyone");		
		System.out.println("\nThe list is: \n" + L);		
		System.out.println("isEmpty called: " + L.isEmpty());		
		System.out.println("\ngetSize called: " + L.getSize());
		
		System.out.println("\nGet called: element 0");		
		System.out.println(L.get(0));
		System.out.println("\nGet called: element 10");		
		System.out.println(L.get(10));

		System.out.println("Remove called: element 0");		
		L.remove(0);
		L.remove(0);
		L.remove(0);
		L.remove(0);
		System.out.println("\nisEmpty called: " + L.isEmpty());
		System.out.println("\nThe list is: \n" + L);

	}

}
