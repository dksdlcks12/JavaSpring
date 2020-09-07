package kr.ajs.portfolio.vo;

import java.util.ArrayList;

public class RecallAddVo {
	private ArrayList<RecallAddVo> goodsList;
	private int orderListNum;
	private String sandNote;
	private String recallReaseon;
	private String recallChange;
	private int orderNum;
	private String recallAccount;
	private String recallBank;
	private int recallNum;
	
	public ArrayList<RecallAddVo> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList<RecallAddVo> goodsList) {
		this.goodsList = goodsList;
	}
	public int getOrderListNum() {
		return orderListNum;
	}
	public void setOrderListNum(int orderListNum) {
		this.orderListNum = orderListNum;
	}
	public String getSandNote() {
		return sandNote;
	}
	public void setSandNote(String sandNote) {
		this.sandNote = sandNote;
	}
	public String getRecallReaseon() {
		return recallReaseon;
	}
	public void setRecallReaseon(String recallReaseon) {
		this.recallReaseon = recallReaseon;
	}
	public String getRecallChange() {
		return recallChange;
	}
	public void setRecallChange(String recallChange) {
		this.recallChange = recallChange;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getRecallAccount() {
		return recallAccount;
	}
	public void setRecallAccount(String recallAccount) {
		this.recallAccount = recallAccount;
	}
	public String getRecallBank() {
		return recallBank;
	}
	public void setRecallBank(String recallBank) {
		this.recallBank = recallBank;
	}
	public int getRecallNum() {
		return recallNum;
	}
	public void setRecallNum(int recallNum) {
		this.recallNum = recallNum;
	}
	
	@Override
	public String toString() {
		return "RecallAddVo [goodsList=" + goodsList + ", orderListNum=" + orderListNum + ", sandNote=" + sandNote
				+ ", recallReaseon=" + recallReaseon + ", recallChange=" + recallChange + ", orderNum=" + orderNum
				+ ", recallAccount=" + recallAccount + ", recallBank=" + recallBank + "]";
	}
}
