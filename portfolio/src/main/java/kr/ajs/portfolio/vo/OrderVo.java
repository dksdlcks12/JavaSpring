package kr.ajs.portfolio.vo;

import java.util.ArrayList;

public class OrderVo {
	private ArrayList<OrderVo> orderList;
	private int orderNum;
	private String senderName;
	private int sendPostcode;
	private String sendAddress;
	private String sendDetailAddress;
	private String sendExtraAddress;
	private int sendtel;
	private int noneMemberPassword;
	private String receiverName;
	private int receivePostcode;
	private String receiveAddress;
	private String receiveDetailAddress;
	private String receiveExtraAddress;
	private int receiveTel;
	public ArrayList<OrderVo> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderVo> orderList) {
		this.orderList = orderList;
	}
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@Override
	public String toString() {
		return "OrderVo [orderList=" + orderList + ", orderNum=" + orderNum + "]";
	}
}
