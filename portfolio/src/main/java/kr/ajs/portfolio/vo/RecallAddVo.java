package kr.ajs.portfolio.vo;

import java.util.ArrayList;

public class RecallAddVo {
	private ArrayList<RecallAddVo> goodsList;
	private int orderListNum;
	private String sandNote;
	
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
	@Override
	public String toString() {
		return "RecallAddVo [goodsList=" + goodsList + ", orderListNum=" + orderListNum + ", sandNote=" + sandNote
				+ "]";
	}
	
}
