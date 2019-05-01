package homework2;

public class TaskEntry {

	static int count = 0;
	
	int id;
	int rank;
	String name;
	String message;
	String markedMessage;
	String markedName;
	Boolean status;
	
	public TaskEntry(String name, String message) {
		super();
		this.name = name;
		this.markedName = name;
		this.message = message;
		this.markedMessage = message;
		this.id = count++;
		this.status = false;
		this.rank = 1;
	}
	
	// enclose a subString to a <mark> tag
	public String highLight(String fullText, String subText) {
		String replacer = "<mark>" + subText + "</mark>";
		String markedText = fullText.replaceAll(subText, replacer);
		
		return markedText;
	}
	
	public void upRank() {
		if (this.rank < 3) {
		this.rank++;
		}
	}
	
	public void downRank() {
		this.rank--;
	}
	
	public void swapID(TaskEntry entry2) {
		int temp1= this.id;
		this.setId(entry2.id);
		entry2.setId(temp1);
		
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
}
