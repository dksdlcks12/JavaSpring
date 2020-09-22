package kr.ajs.portfolio.vo;

public class SearchVo {
	private int postNum;
	private String goodsName;
	private String goodsImg;
	private int goodsPrice;
	private String search;
	private String minPrice;
	private String maxPrice;
	private String minDisCount;
	private String maxDisCount;
	
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
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
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMinDisCount() {
		return minDisCount;
	}
	public void setMinDisCount(String minDisCount) {
		this.minDisCount = minDisCount;
	}
	public String getMaxDisCount() {
		return maxDisCount;
	}
	public void setMaxDisCount(String maxDisCount) {
		this.maxDisCount = maxDisCount;
	}
	
	@Override
	public String toString() {
		return "SearchVo [postNum=" + postNum + ", goodsName=" + goodsName + ", goodsImg=" + goodsImg + ", goodsPrice="
				+ goodsPrice + ", search=" + search + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", minDisCount=" + minDisCount + ", maxDisCount=" + maxDisCount + "]";
	}
}
