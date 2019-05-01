package RateMyLeturer;

import java.util.ArrayList;
import java.util.List;

public class tester {
	
	
	public static void main (String[] args) {
		
		
		 List<BookEntry> guestList = new ArrayList<BookEntry>();
	        guestList.add( new BookEntry( "John", "10am on 2/20 in ET A227", "Machine Learning") );
	        guestList.add( new BookEntry( "Jack", "10am on 2/25 in ET A332","Computer Vision") );
	        guestList.add( new BookEntry( "Jane", "3pm on 2/27 in ET A126","Machine Learning") );
	        guestList.add( new BookEntry( "May", "3:30pm on 3/11 in FA 219","Computer Science Education") );
	        
	        
	        System.out.println(guestList.get(1).raterList);
	        
	        
	        guestList.get(1).raterList.add(new RatingEntry(1.5, "dean", "good job"));
	        guestList.get(1).raterList.add(new RatingEntry(4, "dean2", "good job"));
	        
	        guestList.get(1).printRaterList();
		
	        System.out.println(guestList.get(1).getRatingAvg());
		
	}

}
