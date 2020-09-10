package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QaVo {
	private int qaNum;
	private String qaTitle;
	private String qaWriter;
	private String qaIsOpen;
	private String qaPw;
	private String qaContent;
	private Date qaDate;
	private int qaOriginNum;
	
	public int getQaNum() {
		return qaNum;
	}
	public void setQaNum(int qaNum) {
		this.qaNum = qaNum;
	}
	public String getQaTitle() {
		return qaTitle;
	}
	public void setQaTitle(String qaTitle) {
		this.qaTitle = qaTitle;
	}
	public String getQaWriter() {
		return qaWriter;
	}
	public void setQaWriter(String qaWriter) {
		this.qaWriter = qaWriter;
	}
	public String getQaIsOpen() {
		return qaIsOpen;
	}
	public void setQaIsOpen(String qaIsOpen) {
		this.qaIsOpen = qaIsOpen;
	}
	public String getQaPw() {
		return qaPw;
	}
	public void setQaPw(String qaPw) {
		this.qaPw = qaPw;
	}
	public String getQaContent() {
		return qaContent;
	}
	public void setQaContent(String qaContent) {
		this.qaContent = qaContent;
	}
	public String getQaDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(qaDate);
		return to;
	}
	public void setQaDate(String qaDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.qaDate = transFormat.parse(qaDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public int getQaOriginNum() {
		return qaOriginNum;
	}
	public void setQaOriginNum(int qaOriginNum) {
		this.qaOriginNum = qaOriginNum;
	}
	
	@Override
	public String toString() {
		return "QaVo [qaNum=" + qaNum + ", qaTitle=" + qaTitle + ", qaWriter=" + qaWriter + ", qaIsOpen=" + qaIsOpen
				+ ", qaPw=" + qaPw + ", qaContent=" + qaContent + ", qaDate=" + qaDate + ", qaOriginNum=" + qaOriginNum
				+ "]";
	}
}
