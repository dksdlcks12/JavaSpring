package kr.green.test.pagination;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum=4;
	private Criteria criteria;
	
	/* endPage, startPage, prev, next �� ��� */
	public void calcData() {
		/* starPage�� endPage�� ���� ������ ������ criteria�� displayPageNum�� �̿��Ͽ� ���
		 * displayPageNum�� 10�̰� ���� �������� 3�������� startPage = 1, endPage = 10�� �ǵ��� ��� */
		endPage = (int) (Math.ceil(criteria.getPage()/(double) displayPageNum)*displayPageNum);
		
		startPage = (endPage - displayPageNum)+1;
		/* �� ������ ������ �̿��Ͽ� ������ ������ ��ȣ�� ��� */
		int tempEndPage = (int)(Math.ceil(totalCount/(double)criteria.getPerPageNum()));
		
		/* ���� �������� ���� ���� ����������Ŀ�� ������ ������ ��ȣ�� ���� ������ ������ ��ȣ�� ���Ͽ�
		 * ���� ���� ������ ������ ��ȣ�� �� */
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		/* ���� ����������Ŀ�� ������������ 1�������� prev�� ����� �� */
		prev = startPage == 1 ? false : true;
		/* ���� ����������Ŀ�� ������ �������� �������� �������� ���ԵǾ� ������ next�� ����� �� */
		next = endPage * criteria.getPerPageNum() >= totalCount ? false:true;
	}
	/* getter and setter */
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	public int getLastPage() {
		return (int)(Math.ceil(totalCount/(double)criteria.getPerPageNum()));
	}
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", criteria=" + criteria + "]";
	}
}