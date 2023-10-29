public class multiHello {

	public static void main(String[] args) {
	
		final int arg_num = args.length;
		
		String s = "Hello ";
		
		if (arg_num < 2) {
			s += (arg_num==0 ? "everybody" : args[0]) + "!";
			System.out.println(s);
			return;
		}
			
		String comma = (arg_num>2 ? ", " : " ");
		
		for (int cnt = 0 ; cnt < arg_num ; cnt++) {
					
			if (cnt < arg_num-1) {
				s += args[cnt] + comma;
				continue;
			}			
			s += "and " + args[cnt] + "!";		
		}
		
		System.out.println(s);
	
	}

}
