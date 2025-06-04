package egovframework.rndp.admin.admin.service;

import java.io.Serializable;

public class AdminInfoVO implements Serializable{

	private int key = 0;
	private String id = "";
	private String name = "";
	private String email = "";
	private int level = 100;
	private String url = "";
	private int staffKey;
	private String staffClass = "";
	private String staffPosition = "";
	private String adminFlag = "F"; //추가
	private int mainGroup = 0; //admin login 화면에서  선택하고 들어온 그붋키
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStaffKey() {
		return staffKey;
	}
	public void setStaffKey(int staffKey) {
		this.staffKey = staffKey;
	}
	public String getStaffClass() {
		return staffClass;
	}
	public void setStaffClass(String staffClass) {
		this.staffClass = staffClass;
	}
	public String getStaffPosition() {
		return staffPosition;
	}
	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}
	public String getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
	public int getMainGroup() {
		return mainGroup;
	}
	public void setMainGroup(int mainGroup) {
		this.mainGroup = mainGroup;
	}
	
	
}
