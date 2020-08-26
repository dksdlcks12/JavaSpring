package kr.ajs.portfolio.vo;

public class BoardCartVo {
	private int cartNum;
	private int cartCount;
	private String goodsName;
	private String goodsImg;
	private int postDiscount;
	private int goodsPrice;
	private int goodsPoint;
	private String optionColor;
	private int goodsDiscountPrice;
	private int goodsAllPrice;
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
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
	public int getPostDiscount() {
		return postDiscount;
	}
	public void setPostDiscount(int postDiscount) {
		this.postDiscount = postDiscount;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
		goodsDiscountPrice = (int)(goodsPrice/100*(100-postDiscount));
		goodsAllPrice = (int)(goodsDiscountPrice*cartCount);
	}
	public int getGoodsPoint() {
		return goodsPoint;
	}
	public void setGoodsPoint(int goodsPoint) {
		this.goodsPoint = goodsPoint;
	}
	public String getOptionColor() {
		return optionColor;
	}
	public void setOptionColor(String optionColor) {
		this.optionColor = optionColor;
	}
	public int getGoodsDiscountPrice() {
		return goodsDiscountPrice;
	}
	public void setGoodsDiscountPrice(int goodsDiscountPrice) {
		this.goodsDiscountPrice = goodsDiscountPrice;
	}
	public int getGoodsAllPrice() {
		return goodsAllPrice;
	}
	public void setGoodsAllPrice(int goodsAllPrice) {
		this.goodsAllPrice = goodsAllPrice;
	}
	
	@Override
	public String toString() {
		return "BoardCartVo [cartNum=" + cartNum + ", cartCount=" + cartCount + ", goodsName=" + goodsName
				+ ", goodsImg=" + goodsImg + ", postDiscount=" + postDiscount + ", goodsPrice=" + goodsPrice
				+ ", goodsPoint=" + goodsPoint + ", optionColor=" + optionColor + ", goodsDiscountPrice="
				+ goodsDiscountPrice + ", goodsAllPrice=" + goodsAllPrice + "]";
	}
}
