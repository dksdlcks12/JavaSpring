package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewVo {
	private int reviewNum;
	private String review_userId;
	private int review_orderListNum;
	private String reviewTitle;
	private String reviewContent;
	private Date reviewDate;
	private String reviewIsDel;
	private Date reviewDelDate;
	public int getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}
	public String getReview_userId() {
		return review_userId;
	}
	public void setReview_userId(String review_userId) {
		this.review_userId = review_userId;
	}
	public int getReview_orderListNum() {
		return review_orderListNum;
	}
	public void setReview_orderListNum(int review_orderListNum) {
		this.review_orderListNum = review_orderListNum;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(reviewDate);
		return to;
	}
	public void setReviewDate(String reviewDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.reviewDate = transFormat.parse(reviewDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getReviewIsDel() {
		return reviewIsDel;
	}
	public void setReviewIsDel(String reviewIsDel) {
		this.reviewIsDel = reviewIsDel;
	}
	public String getReviewDelDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(reviewDelDate);
		return to;
	}
	public void setReviewDelDate(String reviewDelDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.reviewDelDate = transFormat.parse(reviewDelDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "ReviewVo [reviewNum=" + reviewNum + ", review_userId=" + review_userId + ", review_orderListNum="
				+ review_orderListNum + ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent
				+ ", reviewDate=" + reviewDate + ", reviewIsDel=" + reviewIsDel + ", reviewDelDate=" + reviewDelDate
				+ "]";
	}
}
