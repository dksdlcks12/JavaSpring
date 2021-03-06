package kr.ajs.portfolio.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderListVo {
	private int orderListNum;
	private int orderList_postNum;
	private int orderList_orderNum;
	private int orderList_optionNum;
	private int orderListCount;
	private int orderListPrice;
	private int orderListUsePoint;
	private String orderListIsRecall;
	private String orderListIsReview;
	private String goodsImg;
	private String goodsName;
	private String optionColor;
	private int payPrice;
	private String goodsType;
	private Date orderDate;
	public int getOrderListNum() {
		return orderListNum;
	}
	public void setOrderListNum(int orderListNum) {
		this.orderListNum = orderListNum;
	}
	public int getOrderList_postNum() {
		return orderList_postNum;
	}
	public void setOrderList_postNum(int orderList_postNum) {
		this.orderList_postNum = orderList_postNum;
	}
	public int getOrderList_orderNum() {
		return orderList_orderNum;
	}
	public void setOrderList_orderNum(int orderList_orderNum) {
		this.orderList_orderNum = orderList_orderNum;
	}
	public int getOrderList_optionNum() {
		return orderList_optionNum;
	}
	public void setOrderList_optionNum(int orderList_optionNum) {
		this.orderList_optionNum = orderList_optionNum;
	}
	public int getOrderListCount() {
		return orderListCount;
	}
	public void setOrderListCount(int orderListCount) {
		this.orderListCount = orderListCount;
	}
	public int getOrderListPrice() {
		return orderListPrice;
	}
	public void setOrderListPrice(int orderListPrice) {
		this.orderListPrice = orderListPrice;
	}
	public int getOrderListUsePoint() {
		return orderListUsePoint;
	}
	public void setOrderListUsePoint(int orderListUsePoint) {
		this.orderListUsePoint = orderListUsePoint;
		payPrice = (orderListPrice*orderListCount)-orderListUsePoint;
	}
	public String getOrderListIsRecall() {
		return orderListIsRecall;
	}
	public void setOrderListIsRecall(String orderListIsRecall) {
		this.orderListIsRecall = orderListIsRecall;
	}
	public String getOrderListIsReview() {
		return orderListIsReview;
	}
	public void setOrderListIsReview(String orderListIsReview) {
		this.orderListIsReview = orderListIsReview;
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
	public int getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
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
	
	@Override
	public String toString() {
		return "OrderListVo [orderListNum=" + orderListNum + ", orderList_postNum=" + orderList_postNum
				+ ", orderList_orderNum=" + orderList_orderNum + ", orderList_optionNum=" + orderList_optionNum
				+ ", orderListCount=" + orderListCount + ", orderListPrice=" + orderListPrice + ", orderListUsePoint="
				+ orderListUsePoint + ", orderListIsRecall=" + orderListIsRecall + ", orderListIsReview="
				+ orderListIsReview + ", goodsImg=" + goodsImg + ", goodsName=" + goodsName + ", optionColor="
				+ optionColor + ", payPrice=" + payPrice + ", goodsType=" + goodsType + ", orderDate=" + orderDate
				+ "]";
	}
}
