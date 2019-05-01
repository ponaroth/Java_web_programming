package lab3;

public class MyGuestBookEntry {

	static int count = 0;
	
	int id;
	String name;
	String message;
	String markedMessage;
	String markedName;
	
	public MyGuestBookEntry(String name, String message) {
		super();
		this.name = name;
		this.markedName = name;
		this.message = message;
		this.markedMessage = message;
		this.id = count++;
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
	
	
	
	
	
}
