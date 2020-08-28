package kr.ajs.portfolio.vo;

import java.util.Date;

public class OrderVo {
	private String orderNum;
	private String orderSender;
	private String orderPw;
	private String orderSenderPostCode;
	private String orderSenderAddress;
	private String orderSenderDetailAddress;
	private String orderSenderExtraAddress;
	private String orderSenderTel;
	private String orderReceiver;
	private String orderReceiverPostCode;
	private String orderReceiverAddress;
	private String orderReceiverDetailAddress;
	private String orderReceiverExtraAddress;
	private String orderReceiverTel;
	private int orderTotalPrice;
	private Date orderDate;
	private int orderState;
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderSender() {
		return orderSender;
	}
	public void setOrderSender(String orderSender) {
		this.orderSender = orderSender;
	}
	public String getOrderPw() {
		return orderPw;
	}
	public void setOrderPw(String orderPw) {
		this.orderPw = orderPw;
	}
	public String getOrderSenderPostCode() {
		return orderSenderPostCode;
	}
	public void setOrderSenderPostCode(String orderSenderPostCode) {
		this.orderSenderPostCode = orderSenderPostCode;
	}
	public String getOrderSenderAddress() {
		return orderSenderAddress;
	}
	public void setOrderSenderAddress(String orderSenderAddress) {
		this.orderSenderAddress = orderSenderAddress;
	}
	public String getOrderSenderDetailAddress() {
		return orderSenderDetailAddress;
	}
	public void setOrderSenderDetailAddress(String orderSenderDetailAddress) {
		this.orderSenderDetailAddress = orderSenderDetailAddress;
	}
	public String getOrderSenderExtraAddress() {
		return orderSenderExtraAddress;
	}
	public void setOrderSenderExtraAddress(String orderSenderExtraAddress) {
		this.orderSenderExtraAddress = orderSenderExtraAddress;
	}
	public String getOrderSenderTel() {
		return orderSenderTel;
	}
	public void setOrderSenderTel(String orderSenderTel) {
		this.orderSenderTel = orderSenderTel;
	}
	public String getOrderReceiver() {
		return orderReceiver;
	}
	public void setOrderReceiver(String orderReceiver) {
		this.orderReceiver = orderReceiver;
	}
	public String getOrderReceiverPostCode() {
		return orderReceiverPostCode;
	}
	public void setOrderReceiverPostCode(String orderReceiverPostCode) {
		this.orderReceiverPostCode = orderReceiverPostCode;
	}
	public String getOrderReceiverAddress() {
		return orderReceiverAddress;
	}
	public void setOrderReceiverAddress(String orderReceiverAddress) {
		this.orderReceiverAddress = orderReceiverAddress;
	}
	public String getOrderReceiverDetailAddress() {
		return orderReceiverDetailAddress;
	}
	public void setOrderReceiverDetailAddress(String orderReceiverDetailAddress) {
		this.orderReceiverDetailAddress = orderReceiverDetailAddress;
	}
	public String getOrderReceiverExtraAddress() {
		return orderReceiverExtraAddress;
	}
	public void setOrderReceiverExtraAddress(String orderReceiverExtraAddress) {
		this.orderReceiverExtraAddress = orderReceiverExtraAddress;
	}
	public String getOrderReceiverTel() {
		return orderReceiverTel;
	}
	public void setOrderReceiverTel(String orderReceiverTel) {
		this.orderReceiverTel = orderReceiverTel;
	}
	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	
	@Override
	public String toString() {
		return "OrderVo [orderNum=" + orderNum + ", orderSender=" + orderSender + ", orderPw=" + orderPw
				+ ", orderSenderPostCode=" + orderSenderPostCode + ", orderSenderAddress=" + orderSenderAddress
				+ ", orderSenderDetailAddress=" + orderSenderDetailAddress + ", orderSenderExtraAddress="
				+ orderSenderExtraAddress + ", orderSenderTel=" + orderSenderTel + ", orderReceiver=" + orderReceiver
				+ ", orderReceiverPostCode=" + orderReceiverPostCode + ", orderReceiverAddress=" + orderReceiverAddress
				+ ", orderReceiverDetailAddress=" + orderReceiverDetailAddress + ", orderReceiverExtraAddress="
				+ orderReceiverExtraAddress + ", orderReceiverTel=" + orderReceiverTel + ", orderTotalPrice="
				+ orderTotalPrice + ", orderDate=" + orderDate + ", orderState=" + orderState + "]";
	}
}
