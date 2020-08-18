package kr.ajs.portfolio.vo;

import java.util.Date;

public class UserVo {
	private String userId;
	private String userPw;
	private String userMail;
	private String userAuth;
	private String userIsOut;
	private Date userOutDate;
	private int userPoint;
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
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getUserIsOut() {
		return userIsOut;
	}
	public void setUserIsOut(String userIsOut) {
		this.userIsOut = userIsOut;
	}
	public Date getUserOutDate() {
		return userOutDate;
	}
	public void setUserOutDate(Date userOutDate) {
		this.userOutDate = userOutDate;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", userPw=" + userPw + ", userMail=" + userMail + ", userAuth=" + userAuth
				+ ", userIsOut=" + userIsOut + ", userOutDate=" + userOutDate + ", userPoint=" + userPoint + "]";
	}
}
