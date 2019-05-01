package midterm;

public class TaskEntry2 {

	static int count = 0;
	
	int id;
	int like;
	int dislike;
	String name;
	String message;
	String markedMessage;
	String markedName;
	Boolean status;
	
	public TaskEntry2(String name, String message) {
		super();
		this.name = name;
		this.markedName = name;
		this.message = message;
		this.markedMessage = message;
		this.id = count++;
		this.status = false;
		this.like = 0;
		this.dislike = 0;
	}
	
	// enclose a subString to a <mark> tag
	public String highLight(String fullText, String subText) {
		String replacer = "<mark>" + subText + "</mark>";
		String markedText = fullText.replaceAll(subText, replacer);
		
		return markedText;
	}
	
	
	public void upLike() {
		
		this.like++;
		
	}
	
	public void upDislike() {
		this.dislike++;
	}
	
	public void swapID(TaskEntry2 entry2) {
		int temp1= this.id;
		this.setId(entry2.id);
		entry2.setId(temp1);
		
	}
	
	public void sortByLikes() {
		
	}
	
	
	public void sortByDislikes() {
		
	}
	
	
	
	
	/////////////////

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

	public String getMarkedMessage() {
		return markedMessage;
	}

	public void setMarkedMessage(String markedMessage) {
		this.markedMessage = markedMessage;
	}

	public String getMarkedName() {
		return markedName;
	}

	public void setMarkedName(String markedName) {
		this.markedName = markedName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	
	
	
	
	
	
}
