package kr.ajs.portfolio.vo;

public class BoardWishListVo {
	private int wishListNum;
	private int wishListCount;
	private String goodsName;
	private String goodsImg;
	private int goodsPrice;
	private String optionColor;
	
	public int getWishListNum() {
		return wishListNum;
	}
	public void setWishListNum(int wishListNum) {
		this.wishListNum = wishListNum;
	}
	public int getWishListCount() {
		return wishListCount;
	}
	public void setWishListCount(int wishListCount) {
		this.wishListCount = wishListCount;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getOptionColor() {
		return optionColor;
	}
	public void setOptionColor(String optionColor) {
		this.optionColor = optionColor;
	}
	@Override
	public String toString() {
		return "BoardWishListVo [wishListNum=" + wishListNum + ", wishListCount=" + wishListCount + ", goodsName="
				+ goodsName + ", goodsImg=" + goodsImg + ", goodsPrice=" + goodsPrice + ", optionColor=" + optionColor
				+ "]";
	}
}
