package kr.ajs.portfolio.vo;

public class InputOptionVo {
	private String color;
	private int count;
	private String goods;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "inputOptionVo [color=" + color + ", count=" + count + ", goods=" + goods + "]";
	}

}
