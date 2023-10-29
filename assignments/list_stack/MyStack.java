package myObj;

public class MyStack implements Stack {
	
	
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
	
	
	public void push(String string) {
		if (this.isEmpty()) {
			elements = new String[1];
			elements[0] = string;
			return;
		}		
		String[] temp = new String[this.getSize()+1];
		temp[0] = string;
		for (int i = 0; i < this.getSize() ; i++) {
			temp[i+1] = elements[i];
		}
		elements = temp;		
	}	
	
	
	public String pop() {		
		if (this.isEmpty()) {
			return "";
		} else if (this.getSize() == 1) {
			String popped = elements[0];
			elements = null;
			return popped;
		} else {		
			String popped = elements[0];
			String[] temp = new String[this.getSize()-1];
			for (int i = 1; i < this.getSize() ; i++) {
				temp[i-1] = elements[i];
			}
			elements = temp;
			return popped;
		}
	}	
	
	
	public String top() {	
		if (this.isEmpty()) {return "";}
		return elements[0];	
	}
	
	//@Override
	public String toString() {	
		String printed = "";		
		for (int i = 0 ; i < this.getSize() ; i++) {
			printed += elements[i] + "\n";
		}		
		return printed;	
	}

}
