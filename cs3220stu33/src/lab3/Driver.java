package lab3;

import java.util.regex.Pattern;

public class Driver {
	public static void main(String args[]) {
		
		MyGuestBookEntry e1 = new MyGuestBookEntry("Jame", "NO");
		
		String m = e1.highLight("ReeeeRREEEE", "ee");
		
		System.out.println( Pattern.compile(Pattern.quote("ee"), Pattern.CASE_INSENSITIVE).matcher("ReeeeRREEEE"));
	
		
	}

}
