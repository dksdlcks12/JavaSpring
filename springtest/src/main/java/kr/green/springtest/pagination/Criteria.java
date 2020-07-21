package kr.green.springtest.pagination;

public class Criteria {
	private int page;
	private int perPageNum;
	private String search;
	private int type;
	public Criteria() {
		page = 1;
		perPageNum = 5;
		search ="";
		type = 0;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <=0) {
			this.page = 1;
		}else {
			this.page = page;
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		/* 한 페이지에 컨텐츠 수를 0이상 100이하로 설정
		개발자 설정에 따라 100부분을 수정할 수 있음 */
		if(perPageNum <1) {
			this.perPageNum = 10;
		}else {
			this.perPageNum = perPageNum;
		}
	}
	public int getPageStart() {
		return (page-1)*perPageNum;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		if(type<0 || type>3) {
			this.type = 0;
		}else {
			this.type = type;
		}
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", search=" + search + ", type=" + type + "]";
	}
}
