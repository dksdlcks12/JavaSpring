package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderVo {
	private int orderNum;
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
	private String orderState;
	private String orderUserId;
	private int orderGoodsCount;
	private String orderGoodsName;
	private String orderGoodsColor;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
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
		if(orderSenderTel.length()==12) {
			orderSenderTel = orderSenderTel.substring(0, 4) + "-" + orderSenderTel.substring(4, 8) + "-" + orderSenderTel.substring(8, orderSenderTel.length());
		}else if(orderSenderTel.length()==11) {
			orderSenderTel = orderSenderTel.substring(0, 3) + "-" + orderSenderTel.substring(3, 7) + "-" + orderSenderTel.substring(7, orderSenderTel.length());
		}else {
			orderSenderTel = orderSenderTel.substring(0, 3) + "-" + orderSenderTel.substring(3, 6) + "-" + orderSenderTel.substring(6, orderSenderTel.length());
		}
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
		if(orderReceiverTel.length()==12) {
			orderReceiverTel = orderReceiverTel.substring(0, 4) + "-" + orderReceiverTel.substring(4, 8) + "-" + orderReceiverTel.substring(8, orderReceiverTel.length());
		}else if(orderReceiverTel.length()==11) {
			orderReceiverTel = orderReceiverTel.substring(0, 3) + "-" + orderReceiverTel.substring(3, 7) + "-" + orderReceiverTel.substring(7, orderReceiverTel.length());
		}else if(orderReceiverTel.length()==10){
			orderReceiverTel = orderReceiverTel.substring(0, 3) + "-" + orderReceiverTel.substring(3, 6) + "-" + orderReceiverTel.substring(6, orderReceiverTel.length());
		}else {
			orderReceiverTel = orderReceiverTel.substring(0, 2) + "-" + orderReceiverTel.substring(2, 5) + "-" + orderReceiverTel.substring(5, orderReceiverTel.length());
		}
		this.orderReceiverTel = orderReceiverTel;
	}
	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public String getOrderDate() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(orderDate);
		return to;
	}
	public void setOrderDate(String orderDate) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.orderDate = transFormat.parse(orderDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getOrderUserId() {
		return orderUserId;
	}
	public void setOrderUserId(String orderUserId) {
		this.orderUserId = orderUserId;
	}
	public int getOrderGoodsCount() {
		return orderGoodsCount;
	}
	public void setOrderGoodsCount(int orderGoodsCount) {
		this.orderGoodsCount = orderGoodsCount;
	}
	public String getOrderGoodsName() {
		return orderGoodsName;
	}
	public void setOrderGoodsName(String orderGoodsName) {
		this.orderGoodsName = orderGoodsName;
	}
	public String getOrderGoodsColor() {
		return orderGoodsColor;
	}
	public void setOrderGoodsColor(String orderGoodsColor) {
		this.orderGoodsColor = orderGoodsColor;
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
				+ orderTotalPrice + ", orderDate=" + orderDate + ", orderState=" + orderState + ", orderUserId="
				+ orderUserId + ", orderGoodsCount=" + orderGoodsCount + ", orderGoodsName=" + orderGoodsName
				+ ", orderGoodsColor=" + orderGoodsColor + "]";
	}
}
