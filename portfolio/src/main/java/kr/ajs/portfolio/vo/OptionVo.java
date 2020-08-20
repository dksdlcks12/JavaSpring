package kr.ajs.portfolio.vo;

public class OptionVo {
	private int optionNum;
	private int option_goodsNum;
	private String optionColor;
	private int optionStock;
	
	public int getOptionNum() {
		return optionNum;
	}
	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}
	public int getOption_goodsNum() {
		return option_goodsNum;
	}
	public void setOption_goodsNum(int option_goodsNum) {
		this.option_goodsNum = option_goodsNum;
	}
	public String getOptionColor() {
		return optionColor;
	}
	public void setOptionColor(String optionColor) {
		this.optionColor = optionColor;
	}
	public int getOptionStock() {
		return optionStock;
	}
	public void setOptionStock(int optionStock) {
		this.optionStock = optionStock;
	}
	
	@Override
	public String toString() {
		return "OptionVo [optionNum=" + optionNum + ", option_goodsNum=" + option_goodsNum + ", optionColor="
				+ optionColor + ", optionStock=" + optionStock + "]";
	}
}
