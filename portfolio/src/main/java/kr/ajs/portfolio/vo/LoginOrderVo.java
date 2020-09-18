package kr.ajs.portfolio.vo;

public class LoginOrderVo {
	private String userId;
	private String userPw;
	private String address;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "LoginOrderVo [userId=" + userId + ", userPw=" + userPw + ", address=" + address + "]";
	}
}
