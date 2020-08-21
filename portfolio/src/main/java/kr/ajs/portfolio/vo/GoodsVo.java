package kr.ajs.portfolio.vo;

import java.util.Date;

public class GoodsVo {
	private int goodsNum;
	private String goodsName;
	private int goodsPrice;
	private int goodsPoint;
	private int goodsType;
	private String goodsImg;
	
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getGoodsPoint() {
		return goodsPoint;
	}
	public void setGoodsPoint(int goodsPoint) {
		this.goodsPoint = goodsPoint;
	}
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	@Override
	public String toString() {
		return "GoodsVo [goodsNum=" + goodsNum + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsPoint=" + goodsPoint + ", goodsType=" + goodsType + ", goodsImg=" + goodsImg + "]";
	}
}
