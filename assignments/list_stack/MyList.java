package myObj;

public class MyList implements List {
	
	
	private String[] elements;	
	
	
	public boolean isEmpty() {return elements == null;}	
	
	
	public int getSize() {
		if (this.isEmpty()) {return 0;}
		return elements.length;
	}	
	
	
	public boolean contains(String string) {	
		if (this.isEmpty()) {return false;}		
		for (int i = 0 ; i < this.getSize() ; i++) {
			if (elements[i].equals(string)) {
				return true;
			}
		}		
		return false;	
	}	
	
	
	public String[] getValues() {	
		if (!this.isEmpty()) {
			return new String[0];
		}		
		return elements;		
	}
	
	
	public void add(String string) {
		if (this.isEmpty()) {
			elements = new String[1];
			elements[0] = string;
			return;
		} else {
			String[] temp = new String[this.getSize()+1];
			for (int i = 0 ; i < this.getSize() ; i++) {
				temp[i] = elements[i];
			} 
			temp[this.getSize()] = string;
			elements = temp;
			temp = null;
		}
	}
	
	
	public String get(int index) {
		if (this.isEmpty() || this.getSize() <= index || index < 0) {
			System.out.println("\nIndex out of range.");
			return "";
		}
		else {return elements[index];}
	}	
	
	
	/*public void insertAt(int index, String string) {
		//you can insert either in place of an existing element, or at the end	
		if (this.getSize()+1 <= index || index < 0) {
			System.out.println("\nInsertion not admitted.");
			return;
		}
		if (!this.isEmpty()) {
			elements[index]=string;
		} else {
			elements = new String[1];
			elements[0] = string;
		}
	}*/
	
	
	public void insertAt(int index, String string) {
		if (index < 0) {
			System.out.println("\nInsertion not admitted.");
			return;
		}
		if (this.isEmpty()) {
			elements = new String[1];
			elements[0] = string;
			return;
		} 
		if (index < this.getSize()) {
			elements[index] = string;
			return;
		}
		String[] temp = new String[index+1];
		for (int i = 0; i < index ; i++) {
			temp[i] = (i < this.getSize() ? elements[i] : "");
		}
		temp[index] = string;
		elements = temp;
		temp = null;
	}
	
	
	public void remove(int index) {
		if (this.isEmpty() || this.getSize() <= index || index < 0) {return;}
		elements[index] = "";
	}
	
	
	public int indexOf(String string) {
		//if (this.isEmpty()) {return -1;}
		for (int i = 0 ; i < this.getSize() ; i++) {
			if (elements[i].equals(string)) {return i;};
		}
		return -1;	
	}
		
	
	
	
	@Override
	public String toString() {	
		String printed = "";		
		for (int i = 0 ; i < this.getSize() ; i++) {
			printed += "L[" + i + "] = " + elements[i] + "\n";
		}		
		return printed;	
	}

}
