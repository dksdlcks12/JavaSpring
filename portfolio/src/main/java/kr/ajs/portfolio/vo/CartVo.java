package kr.ajs.portfolio.vo;

public class CartVo {
	private int cartNum;
	private int cart_postNum;
	private String cart_userId;
	private int cartCount;
	private int cart_optionNum;
	
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public int getCart_postNum() {
		return cart_postNum;
	}
	public void setCart_postNum(int cart_postNum) {
		this.cart_postNum = cart_postNum;
	}
	public String getCart_userId() {
		return cart_userId;
	}
	public void setCart_userId(String cart_userId) {
		this.cart_userId = cart_userId;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	public int getCart_optionNum() {
		return cart_optionNum;
	}
	public void setCart_optionNum(int cart_optionNum) {
		this.cart_optionNum = cart_optionNum;
	}
	@Override
	public String toString() {
		return "CartVo [cartNum=" + cartNum + ", cart_postNum=" + cart_postNum + ", cart_userId=" + cart_userId
				+ ", cartCount=" + cartCount + ", cart_optionNum=" + cart_optionNum + "]";
	}
}
