package kr.ajs.portfolio.vo;

public class PostDeleteVo {
	private int postNum;
	private int page;
	private int type;
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "PostDeleteVo [postNum=" + postNum + ", page=" + page + ", type=" + type + "]";
	}
}
