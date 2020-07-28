package kr.green.test.pagination;

public class Criteria {
	//���� ������
	private int page; 
	//�� ������ �� ������ ����
	private int perPageNum;
	private String search;
	private String type;
	//Criteria ����Ʈ ������ : ���� �������� 1��������, �� �������� 10���� ������	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 5;
	}
	//getter and setter
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		//���� ������ ��ȣ�� ������ �����Ϸ� �� ��
		if(page <= 0) {
			this.page = 1;
		}
		else
			this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		/* �� �������� ������ ���� 0�̻� 100���Ϸ� ����
		������ ������ ���� 100�κ��� ������ �� ���� */
		if(perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
		}
		else
			this.perPageNum = perPageNum;
	}
	/* ���������� limit�� ���Ǵ� �ε����� ����ϴ� getter */
	public int getPageStart() {
		return (this.page -1) * perPageNum;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", search=" + search + ", type=" + type + "]";
	}
	
}