package hw4;

import java.util.Date;

public class RatingEntry {
	
	int count = 1;
    int id1;
    String rater;
    String comment;
	Date date;
    double rating;
    

    public RatingEntry( int id1, double rating, String rater, String comment)
    {
        this.id1 = id1;
        this.comment = comment;
        this.rating = rating;
        this.rater = rater;
        this.date = new Date();
    }

    public String getRater() {
		return rater;
	}

	public void setRater(String rater) {
		this.rater = rater;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    public Integer getId1()
    {
        return id1;
    }

    public void setId1( Integer id1 )
    {
        this.id1 = id1;
    }

    
    public double getRating()
    {
        return rating;
    }

    public void setRating( double rating )
    {
        this.rating = rating;
    }
    
//    public String getRating()
//    {
//        return rating;
//    }
//
//    public void setRating( String rating )
//    {
//        this.rating = rating;
//    }
    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

}