package photos;

public class Photo {
	
static int count = 0;
	
	int photoID;
	private String path;
	private String name;
	
	public Photo(String path, String name) {
		super();
		this.path = path;
		this.name = name;
		this.photoID = count++;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Photo.count = count;
	}

	public int getPhotoID() {
		return photoID;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	
	
	

}
