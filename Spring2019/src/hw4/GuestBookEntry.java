package hw4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestBookEntry {
	static int count = 1;

    Integer id;
    String name;
    String presentation;  
    String specialties;
	Date date;
    double rating;
    
    ArrayList<RatingEntry> raterList;
    
    public GuestBookEntry() {}
    
    public GuestBookEntry( int id, String name, String presentation, String specialties, Double rating)
    {
        this.id = id;
    	//this.id = id;
        this.name = name;
        this.presentation = presentation;
        this.specialties = specialties;
        this.rating = rating;
        this.date = new Date();
        this.raterList =  new ArrayList<RatingEntry>();
       
    }
    
    // get rating average
    public double getRatingAvg() {
    	
    	double avg = 0;
    	
    	for (RatingEntry rEntry: raterList) {
    		avg += rEntry.rating;
    		
    	}
    	
    	if (raterList.size() > 0 ) {
    		avg = avg / raterList.size();
    	}
    	
    	// format double to only have 2 decimals
    	DecimalFormat df = new DecimalFormat("#.##"); 
        avg = Double.parseDouble( df.format(avg));
   
    	return avg;
    }
    
    
    // print this entry's raterlist
    public void printRaterList() {
    	for (RatingEntry rEntry: raterList) {
    		System.out.println("" + rEntry.rating + ", " + rEntry.rater + ", " + rEntry.comment );
    	}
    	
    }
    
    
    ///////////////////////
    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getpresentation()
    {
        return presentation;
    }

    public void setpresentation( String presentation )
    {
        this.presentation = presentation;
    }
    
    public String getSpecialties()
    {
        return specialties;
    }

    public void setSpecialties( String specialties )
    {
        this.specialties = specialties;
    }
    
    public double getRating()
    {
        return rating;
    }

    public void setRating( double rating )
    {
        this.rating = rating;
    }
    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

	public ArrayList<RatingEntry> getRaterList() {
		return raterList;
	}

	public void setRaterList(ArrayList<RatingEntry> raterList) {
		this.raterList = raterList;
	}

    
}
