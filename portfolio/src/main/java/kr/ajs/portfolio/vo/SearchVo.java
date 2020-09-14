package kr.ajs.portfolio.vo;

public class SearchVo {
	private int goodsNum;
	private String goodsName;
	private String goodsImg;
	private int goodsPrice;
	private String search;
	private int minPrice;
	private int maxPrice;
	private int minDisCount;
	private int maxDisCount;
	
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
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = Integer.parseInt(minPrice);
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = Integer.parseInt(maxPrice);
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getMinDisCount() {
		return minDisCount;
	}
	public void setMinDisCount(String minDisCount) {
		if(minDisCount=="") {
			this.minDisCount = 0;
		}else {
			this.minDisCount = Integer.parseInt(minDisCount);
		}
	}
	public int getMaxDisCount() {
		return maxDisCount;
	}
	public void setMaxDisCount(String maxDisCount) {
		if(maxDisCount=="") {
			this.maxDisCount = 0;
		}else {
			this.maxDisCount = Integer.parseInt(maxDisCount);
		}
	}

	
	@Override
	public String toString() {
		return "SearchVo [goodsNum=" + goodsNum + ", goodsName=" + goodsName + ", goodsImg=" + goodsImg + ", goodsPrice="
				+ goodsPrice + ", search=" + search + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", minDisCount=" + minDisCount + ", maxDisCount=" + maxDisCount + "]";
	}
}
