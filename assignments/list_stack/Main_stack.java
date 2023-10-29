import myObj.MyStack;

class Main_stack {

	public static void main(String[] args) {
	
		MyStack s1 = new MyStack();
		
		s1.push("hello world");
		
		System.out.println("\nPush performed.");
		
		System.out.println("\ndoes the stack contain \"hello world\"? " + s1.contains("hello world"));
		
		System.out.println("\nthe top of the stack is: " + s1.top());
		
		System.out.println("\nthe size of the stack is: " + s1.getSize());
		
		System.out.println("\nthe stack is: \n" + s1);
		
		s1.push("hello luca");
		
		System.out.println("\nPush performed.");
		
		System.out.println("\nthe top of the stack is: " + s1.top());
		
		System.out.println("\nthe size of the stack is: " + s1.getSize());
		
		System.out.println("\nthe stack is: \n" + s1);
		
		s1.pop();
		
		System.out.println("\nPop performed.");
		
		System.out.println("\nAfter pop, the top of the stack is: " + s1.top());
		
		System.out.println("\nthe size of the stack is: " + s1.getSize());
		
		s1.pop();
		
		System.out.println("\nPop performed.");
		
		System.out.println("\nis the stack empty now? " + s1.isEmpty());
		
		System.out.println("\nAfter pop, the top of the stack is: " + s1.top());
		
		System.out.println("\nthe size of the stack is: " + s1.getSize());
	
	}

}
