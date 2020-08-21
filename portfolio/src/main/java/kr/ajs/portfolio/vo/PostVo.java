package kr.ajs.portfolio.vo;

import java.util.Date;

public class PostVo {
	private int postNum;
	private String post_userId;
	private int post_goodsNum;
	private int postDiscount;
	private String postImg;
	private String postIsDel;
	private Date postDelDate;
	
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getPost_userId() {
		return post_userId;
	}
	public void setPost_userId(String post_userId) {
		this.post_userId = post_userId;
	}
	public int getPost_goodsNum() {
		return post_goodsNum;
	}
	public void setPost_goodsNum(int post_goodsNum) {
		this.post_goodsNum = post_goodsNum;
	}
	public int getPostDiscount() {
		return postDiscount;
	}
	public void setPostDiscount(int postDiscount) {
		this.postDiscount = postDiscount;
	}
	public String getPostImg() {
		return postImg;
	}
	public void setPostImg(String postImg) {
		this.postImg = postImg;
	}
	public String getPostIsDel() {
		return postIsDel;
	}
	public void setPostIsDel(String postIsDel) {
		this.postIsDel = postIsDel;
	}
	public Date getPostDelDate() {
		return postDelDate;
	}
	public void setPostDelDate(Date postDelDate) {
		this.postDelDate = postDelDate;
	}
	
	@Override
	public String toString() {
		return "PostVo [postNum=" + postNum + ", post_userId=" + post_userId + ", post_goodsNum=" + post_goodsNum
				+ ", postDiscount=" + postDiscount + ", postImg=" + postImg + ", postIsDel=" + postIsDel
				+ ", postDelDate=" + postDelDate + "]";
	}
	
}
