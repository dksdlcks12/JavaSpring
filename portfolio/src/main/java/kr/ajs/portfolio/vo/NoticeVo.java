package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeVo {
	private int noticeNum;
	private String notice_userId;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private String noticeIsDel;
	public int getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getNotice_userId() {
		return notice_userId;
	}
	public void setNotice_userId(String notice_userId) {
		this.notice_userId = notice_userId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(noticeDate);
		return to;
	}
	public void setNoticeDate(String noticeDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.noticeDate = transFormat.parse(noticeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getNoticeIsDel() {
		return noticeIsDel;
	}
	public void setNoticeIsDel(String noticeIsDel) {
		this.noticeIsDel = noticeIsDel;
	}
	
	@Override
	public String toString() {
		return "NoticeVo [noticeNum=" + noticeNum + ", notice_userId=" + notice_userId + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate + ", noticeIsDel=" + noticeIsDel
				+ "]";
	}
}
