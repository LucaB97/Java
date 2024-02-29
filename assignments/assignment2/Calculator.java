import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Calculator {

	public static void main(String[] args) {
	
		if (args.length != 1) {
            System.out.println("Usage: java Calculator \"expression\"");
            return;
        }
		
		ArrayList<Object> dataList = new ArrayList<>();
		if(check_expression(args[0], dataList)) {
			System.out.println(dataList);
			System.out.println("The result is: " + computation(dataList));
		}

	}
	
	
	
	private static double computation(ArrayList<Object> dataList) {
			
		double result = 0;
		ArrayList<Double> values = new ArrayList<Double>();
		double tmp;
		values.add(Double.parseDouble(String.valueOf(dataList.get(0))));
		
		for (int i = 2 ; i < dataList.size() ; i += 2) {			
			tmp = Double.parseDouble(String.valueOf(dataList.get(i)));
			if (String.valueOf(dataList.get(i-1)).equals("-")) {
				values.add(tmp*(-1));
			} else {
				values.add(tmp);
			}			
		}
		
		for (int i = 1 ; i < dataList.size() ; i += 2) {			
			switch(String.valueOf(dataList.get(i))) {
				case "*": 
					values.set(i/2+1, values.get(i/2)*values.get(i/2+1));
					values.set(i/2, 0.);				
					break;
				case "/": 
					values.set(i/2+1, values.get(i/2)/values.get(i/2+1));
					values.set(i/2, 0.);		
					break;
				case "%": 
					values.set(i/2+1, values.get(i/2)%values.get(i/2+1));
					values.set(i/2, 0.);
					break;
				default:
					break;
			};		
		}
		
		for (int i = 0 ; i < values.size() ; i++) {			
			result += values.get(i);
		}
		return result;
	}
	
	
	
	private static boolean check_expression(String arg, ArrayList<Object> dataList) {		
	
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?|[+\\-*/%]");
        Matcher matcher = pattern.matcher(arg);
		int last;
		while (matcher.find()) {
			dataList.add(matcher.group());
			last = dataList.size()-1;
			if (String.valueOf(dataList.get(last)).equals("-") && isOperation(dataList.get(last-1))) {
				if (matcher.find())
					dataList.set(last, "-" + matcher.group());
			}	
        }
				
		for (int i = 0 ; i < dataList.size() ; i++) {						
			if (i % 2 == 0) {
				if (!isDouble(dataList.get(i))) {
					return false;
				}
			}
			else {
				if (!isOperation(dataList.get(i))) {
					return false;
				}
			}
		}			
		return true;
	}

	private static boolean isDouble(Object o) {
		try
		{
		Double.parseDouble(String.valueOf(o));
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}	

	private static boolean isOperation(Object o) {
		String operations = "+-*/%";
		return operations.contains(String.valueOf(o));
	}
	
}
