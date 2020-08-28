package kr.ajs.portfolio.vo;

import java.util.ArrayList;

public class OrderVo {
	private ArrayList<OrderVo> goodsList;
	private ArrayList<OrderVo> orderList;
	private int orderNum;
	private String totalPrice;
	private String senderName;
	private String sendPostcode;
	private String sendAddress;
	private String sendDetailAddress;
	private String sendExtraAddress;
	private String sendtel;
	private String noneMemberPassword;
	private String receiverName;
	private String receivePostcode;
	private String receiveAddress;
	private String receiveDetailAddress;
	private String receiveExtraAddress;
	private String receiveTel;
	public ArrayList<OrderVo> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList<OrderVo> goodsList) {
		this.goodsList = goodsList;
	}
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
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSendPostcode() {
		return sendPostcode;
	}
	public void setSendPostcode(String sendPostcode) {
		this.sendPostcode = sendPostcode;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getSendDetailAddress() {
		return sendDetailAddress;
	}
	public void setSendDetailAddress(String sendDetailAddress) {
		this.sendDetailAddress = sendDetailAddress;
	}
	public String getSendExtraAddress() {
		return sendExtraAddress;
	}
	public void setSendExtraAddress(String sendExtraAddress) {
		this.sendExtraAddress = sendExtraAddress;
	}
	public String getSendtel() {
		return sendtel;
	}
	public void setSendtel(String sendtel) {
		this.sendtel = sendtel;
	}
	public String getNoneMemberPassword() {
		return noneMemberPassword;
	}
	public void setNoneMemberPassword(String noneMemberPassword) {
		this.noneMemberPassword = noneMemberPassword;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceivePostcode() {
		return receivePostcode;
	}
	public void setReceivePostcode(String receivePostcode) {
		this.receivePostcode = receivePostcode;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getReceiveDetailAddress() {
		return receiveDetailAddress;
	}
	public void setReceiveDetailAddress(String receiveDetailAddress) {
		this.receiveDetailAddress = receiveDetailAddress;
	}
	public String getReceiveExtraAddress() {
		return receiveExtraAddress;
	}
	public void setReceiveExtraAddress(String receiveExtraAddress) {
		this.receiveExtraAddress = receiveExtraAddress;
	}
	public String getReceiveTel() {
		return receiveTel;
	}
	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}
	@Override
	public String toString() {
		return "OrderVo [goodsList=" + goodsList + ", orderList=" + orderList + ", orderNum=" + orderNum
				+ ", totalPrice=" + totalPrice + ", senderName=" + senderName + ", sendPostcode=" + sendPostcode
				+ ", sendAddress=" + sendAddress + ", sendDetailAddress=" + sendDetailAddress + ", sendExtraAddress="
				+ sendExtraAddress + ", sendtel=" + sendtel + ", noneMemberPassword=" + noneMemberPassword
				+ ", receiverName=" + receiverName + ", receivePostcode=" + receivePostcode + ", receiveAddress="
				+ receiveAddress + ", receiveDetailAddress=" + receiveDetailAddress + ", receiveExtraAddress="
				+ receiveExtraAddress + ", receiveTel=" + receiveTel + "]";
	}
	
	
}
