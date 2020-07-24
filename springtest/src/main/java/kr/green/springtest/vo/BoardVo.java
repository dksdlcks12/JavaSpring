package kr.green.springtest.vo;

import java.text.ParseException;
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
	private int like;
	private String file;
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
	public void setRegisteredDate(String registeredDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.registeredDate = transFormat.parse(registeredDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "BoardVo [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", registeredDate=" + registeredDate + ", isdel=" + isdel + ", views=" + views + ", delDate="
				+ delDate + ", like=" + like + ", file=" + file + "]";
	}
	public String getOriFile() {
		int index = file.indexOf("_");
		return file.substring(index+1);
	}
}
