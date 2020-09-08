package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecallViewVo {
	private int recallNum;
	private int orderNum;
	private String recallState;
	private Date recallDate;
	private String recallReason;
	private String recallIsChange;
	private String recallContent;
	private String recallBankName;
	private String recallAccount;
	private String goodsImg;
	private String goodsName;
	private String optionColor;
	private int goodsType;
	
	public int getRecallNum() {
		return recallNum;
	}
	public void setRecallNum(int recallNum) {
		this.recallNum = recallNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getRecallState() {
		return recallState;
	}
	public void setRecallState(String recallState) {
		this.recallState = recallState;
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
	public String getRecallReason() {
		return recallReason;
	}
	public void setRecallReason(String recallReason) {
		this.recallReason = recallReason;
	}
	public String getRecallIsChange() {
		return recallIsChange;
	}
	public void setRecallIsChange(String recallIsChange) {
		this.recallIsChange = recallIsChange;
	}
	public String getRecallContent() {
		return recallContent;
	}
	public void setRecallContent(String recallContent) {
		this.recallContent = recallContent;
	}
	public String getRecallBankName() {
		return recallBankName;
	}
	public void setRecallBankName(String recallBankName) {
		this.recallBankName = recallBankName;
	}
	public String getRecallAccount() {
		return recallAccount;
	}
	public void setRecallAccount(String recallAccount) {
		this.recallAccount = recallAccount;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getOptionColor() {
		return optionColor;
	}
	public void setOptionColor(String optionColor) {
		this.optionColor = optionColor;
	}
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	
	@Override
	public String toString() {
		return "RecallViewVo [recallNum=" + recallNum + ", orderNum=" + orderNum + ", recallState=" + recallState
				+ ", recallDate=" + recallDate + ", recallReason=" + recallReason + ", recallIsChange=" + recallIsChange
				+ ", recallContent=" + recallContent + ", recallBankName=" + recallBankName + ", recallAccount="
				+ recallAccount + ", goodsImg=" + goodsImg + ", goodsName=" + goodsName + ", optionColor=" + optionColor
				+ ", goodsType=" + goodsType + "]";
	}
}
