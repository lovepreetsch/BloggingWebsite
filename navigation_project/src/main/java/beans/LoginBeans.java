package beans;

public class LoginBeans {
	private String id;
	private String email;
	private String password;
	
	public LoginBeans() {
		super();
	}
	public LoginBeans(String email, String password) {
		super();
//		this.id = id;
		this.email = email;
		this.password = password;
	}
	//id, fullName, emailid, password, contact, gender. dateOfBirth
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
