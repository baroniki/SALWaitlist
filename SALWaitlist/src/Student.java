package SALWaitlist;

public class Student {
	
	private String email;
	private String name;
	private String imgURL;
	private String phoneNumber;
	
	public Student(String email, String name, String imgURL, String phoneNumber) {
		this.email = email;
		this.name = name;
		this.imgURL = imgURL;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
