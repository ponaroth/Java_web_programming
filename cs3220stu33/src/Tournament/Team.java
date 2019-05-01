package Tournament;


public class Team{

	static int count = 0;
	
	int id;
	String name;
	String message;
	Boolean status;
	int rank;
	
	public Team(String name, String message) {
		super();
		this.name = name;
		this.message = message;
		this.id = count++;
		this.status = false;
		this.rank = 0;
	}
	
	// enclose a subString to a <mark> tag
	public String highLight(String fullText, String subText) {
		String replacer = "<mark>" + subText + "</mark>";
		String markedText = fullText.replaceAll(subText, replacer);
		
		return markedText;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}


	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
}
