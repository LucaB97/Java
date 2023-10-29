class Calculator {

	public static void main(String[] args) {
	
		if(check_expression(args)) {
		System.out.println(computation(args));
		}
	
	}
	
	
	
	private static double computation(String[] args) {
		if (args.length == 1) {
			return Double.parseDouble(args[0]);
		}		
		double result = 0;
		
		for (int i = 1 ; i < args.length ; i += 2) {			
			if (args[i].equals("-")) {
				args[i+1] = String.valueOf(Double.parseDouble(args[i+1])*(-1));
			}		
		}
		
		for (int i = 1 ; i < args.length ; i += 2) {			
			switch(args[i]) {
				case "*": 
					result = (i==1 || !hasPriority(args[i-2]) ? result+Double.parseDouble(args[i-1])*Double.parseDouble(args[i+1]) : result*Double.parseDouble(args[i+1]));
					args[i+1] = "0";
					args[i-1] = "0";
					break;
				case "/": 
					result = (i==1 || !hasPriority(args[i-2]) ? result+Double.parseDouble(args[i-1])/Double.parseDouble(args[i+1]) : result/Double.parseDouble(args[i+1]));
					args[i+1] = "0";
					args[i-1] = "0";
					break;
				case "%": 
					result = (i==1 || !hasPriority(args[i-2]) ? result+Double.parseDouble(args[i-1])%Double.parseDouble(args[i+1]) : result%Double.parseDouble(args[i+1]));
					args[i+1] = "0";
					args[i-1] = "0";
					break;
				default:
					break;
			};		
		}
		
		for (int i = 0 ; i < args.length ; i += 2) {			
			result += Double.parseDouble(args[i]);
		}
		
		return result;
	}
	
	
	
	private static boolean check_expression(String[] args) {		
		if (args.length % 2 == 0) {
			return false;
		}		
		for (int i = 0 ; i < args.length ; i++) {
			if (i % 2 == 0) {
				if (!isDouble(args[i])) {
					return false;
				}
			}
			else {
				if (!isOperation(args[i])) {
					return false;
				}
			}
		}		
		return true;
	}

	private static boolean isDouble(String s) {
		try
		{
		Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}	
	
	private static boolean isOperation(String s) {
		String[] ops_list = {"+","-","*","/","%"};		
		for(int i = 0 ; i < ops_list.length; i++) {
        		if(s.equals(ops_list[i])) {
            			return true;
        		}
    		}
    		return false;
	}	
	
	private static boolean hasPriority(String s) {
		String[] ops_list = {"*","/","%"};		
		for(int i = 0 ; i < ops_list.length; i++) {
        		if(s.equals(ops_list[i])) {
            			return true;
        		}
    		}
    		return false;
	}
	
}
