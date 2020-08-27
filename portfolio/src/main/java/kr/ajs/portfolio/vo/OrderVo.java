package kr.ajs.portfolio.vo;

import java.util.ArrayList;

public class OrderVo {
	private ArrayList<OrderVo> orderList;
	private int orderNum;

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
