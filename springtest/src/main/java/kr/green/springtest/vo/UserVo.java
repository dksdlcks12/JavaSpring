package kr.green.springtest.vo;

public class UserVo {
	private String id;
	private String pw;
	private String email;
	private String gender;
	private String auth;
	private String iddel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getIddel() {
		return iddel;
	}
	public void setIddel(String iddel) {
		this.iddel = iddel;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", pw=" + pw + ", email=" + email + ", gender=" + gender + ", auth=" + auth
				+ ", iddel=" + iddel + "]";
	}	
}
