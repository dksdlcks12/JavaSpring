package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardRecallListVo {
	private int recallNum;
	private Date recallDate;
	private String recallState;
	private String goodsName;
	private String goodsColor;
	private int goodsCount;
	
	public int getRecallNum() {
		return recallNum;
	}
	public void setRecallNum(int recallNum) {
		this.recallNum = recallNum;
	}
	public String getRecallDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(recallDate);
		return to;
	}
	public void setRecallDate(String recallDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.recallDate = transFormat.parse(recallDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getRecallState() {
		return recallState;
	}
	public void setRecallState(String recallState) {
		this.recallState = recallState;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsColor() {
		return goodsColor;
	}
	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	@Override
	public String toString() {
		return "BoardRecallListVo [recallNum=" + recallNum + ", recallDate=" + recallDate + ", recallState="
				+ recallState + ", goodsName=" + goodsName + ", goodsColor=" + goodsColor + ", goodsCount=" + goodsCount
				+ "]";
	}
}
