package egovframework.rndp.admin.env.service;

public class EnvHistoryVO {

	private int key;
	private int year;
	private int month;
	private String content;
	private int step;
	private String visible = "T";
	
	private int ycount;
	private int mcount;
	
	public int getYcount() {
		return ycount;
	}
	public void setYcount(int ycount) {
		this.ycount = ycount;
	}
	public int getMcount() {
		return mcount;
	}
	public void setMcount(int mcount) {
		this.mcount = mcount;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getVisibleForm(){
		if (visible.equals("T")){
			return "<font color='blue'>표시함</font>";
		}else{
			return "숨김";
		}
	}
	
}
