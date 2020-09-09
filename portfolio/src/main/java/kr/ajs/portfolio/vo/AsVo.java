package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsVo {
	private int asNum;
	private String asTitle;
	private String asName;
	private String asTel;
	private String asContent;
	private Date asDate;
	private String asState;
	private String asUserId;
	
	public int getAsNum() {
		return asNum;
	}
	public void setAsNum(int asNum) {
		this.asNum = asNum;
	}
	public String getAsTitle() {
		return asTitle;
	}
	public void setAsTitle(String asTitle) {
		this.asTitle = asTitle;
	}
	public String getAsName() {
		return asName;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	public String getAsTel() {
		return asTel;
	}
	public void setAsTel(String asTel) {
		if(asTel.length()==12) {
			asTel = asTel.substring(0, 4) + "-" + asTel.substring(4, 8) + "-" + asTel.substring(8, asTel.length());
		}else if(asTel.length()==11) {
			asTel = asTel.substring(0, 3) + "-" + asTel.substring(3, 7) + "-" + asTel.substring(7, asTel.length());
		}else {
			asTel = asTel.substring(0, 3) + "-" + asTel.substring(3, 6) + "-" + asTel.substring(6, asTel.length());
		}
		this.asTel = asTel;
	}
	public String getAsContent() {
		return asContent;
	}
	public void setAsContent(String asContent) {
		this.asContent = asContent;
	}
	public String getAsDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(asDate);
		return to;
	}
	public void setAsDate(String asDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.asDate = transFormat.parse(asDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getAsState() {
		return asState;
	}
	public void setAsState(String asState) {
		this.asState = asState;
	}
	public String getAsUserId() {
		return asUserId;
	}
	public void setAsUserId(String asUserId) {
		this.asUserId = asUserId;
	}
	
	@Override
	public String toString() {
		return "AsVo [asNum=" + asNum + ", asTitle=" + asTitle + ", asName=" + asName + ", asTel=" + asTel
				+ ", asContent=" + asContent + ", asDate=" + asDate + ", asState=" + asState + ", asUserId=" + asUserId
				+ "]";
	}
}
