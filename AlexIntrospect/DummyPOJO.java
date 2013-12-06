package AlexIntrospect;

import java.sql.Date;

public class DummyPOJO {
	
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String username;
	private String mobileNo;
	private Date birthday;
	
	
	public DummyPOJO(){
		
	}
	
	public DummyPOJO(Integer id, String fname, String lname, String email,
			String password, String username, String mobileNo, Date birthday) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.username = username;
		this.mobileNo = mobileNo;
		this.birthday = birthday;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	

}
