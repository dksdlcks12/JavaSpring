package kr.ajs.portfolio.vo;

public class AsAddVo {
	private String title;
	private String name;
	private String tel;
	private String sandNote;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSandNote() {
		return sandNote;
	}
	public void setSandNote(String sandNote) {
		this.sandNote = sandNote;
	}
	
	@Override
	public String toString() {
		return "AsAddVo [title=" + title + ", name=" + name + ", tel=" + tel + ", sandNote=" + sandNote + "]";
	}
}
