package photos;

import java.util.ArrayList;

public class MyAlbum {

	static int count = 0;
	
	int id;
	String defaultImg;
	String name;
	String message;
	String markedMessage;
	String markedName;
	ArrayList<Photo> photoList;
	
	public MyAlbum(String name, String message) {
		super();
		this.name = name;
		this.markedName = name;
		this.message = message;
		this.markedMessage = message;
		this.id = count++;
		this.defaultImg = "/uploads/album.jpg";
		this.photoList = new ArrayList<Photo>();
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

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		MyAlbum.count = count;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public ArrayList<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(ArrayList<Photo> photoList) {
		this.photoList = photoList;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
