package kr.ajs.portfolio.vo;

public class WishListVo {
	private int wishListNum;
	private int wishList_postNum;
	private String wishList_userId;
	private int wishListCount;
	private int wishList_optionNum;
	public int getWishListNum() {
		return wishListNum;
	}
	public void setWishListNum(int wishListNum) {
		this.wishListNum = wishListNum;
	}
	public int getWishList_postNum() {
		return wishList_postNum;
	}
	public void setWishList_postNum(int wishList_postNum) {
		this.wishList_postNum = wishList_postNum;
	}
	public String getWishList_userId() {
		return wishList_userId;
	}
	public void setWishList_userId(String wishList_userId) {
		this.wishList_userId = wishList_userId;
	}
	public int getWishListCount() {
		return wishListCount;
	}
	public void setWishListCount(int wishListCount) {
		this.wishListCount = wishListCount;
	}
	public int getWishList_optionNum() {
		return wishList_optionNum;
	}
	public void setWishList_optionNum(int wishList_optionNum) {
		this.wishList_optionNum = wishList_optionNum;
	}
	@Override
	public String toString() {
		return "WishListVo [wishListNum=" + wishListNum + ", wishList_postNum=" + wishList_postNum
				+ ", wishList_userId=" + wishList_userId + ", wishListCount=" + wishListCount + ", wishList_optionNum="
				+ wishList_optionNum + "]";
	}
	
}
