package kr.green.spring.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVo {
	private int num;
	private String writer;
	private String title;
	private String content;
	private Date registeredDate;
	private char isdel;
	private int views;
	private Date delDate;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegisteredDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(registeredDate);
		return to;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public char getIsdel() {
		return isdel;
	}
	public void setIsdel(char isdel) {
		this.isdel = isdel;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	@Override
	public String toString() {
		return "BoardVo [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", registedDate=" + registeredDate + ", isdel=" + isdel + ", views=" + views + ", delDate=" + delDate
				+ "]";
	}
}
