package egovframework.rndp.admin.admin.service;

import java.io.Serializable;

public class IntraInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int key = 0;
	protected String id = "";
	protected String name = "";
	protected String email = "";
	protected String pTel = ""; //전화
	protected String pFax = ""; //팩스
	protected String poneNum = ""; //휴대전화
	protected int level = 100;
	protected int classKey = 0;
	protected int positionKey = 0;
	protected String classRank = "";
	protected boolean counted = false;
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
	public String getpTel() {
		return pTel;
	}
	public void setpTel(String pTel) {
		this.pTel = pTel;
	}
	public String getpFax() {
		return pFax;
	}
	public void setpFax(String pFax) {
		this.pFax = pFax;
	}
	public String getPoneNum() {
		return poneNum;
	}
	public void setPoneNum(String poneNum) {
		this.poneNum = poneNum;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getClassKey() {
		return classKey;
	}
	public void setClassKey(int classKey) {
		this.classKey = classKey;
	}
	public int getPositionKey() {
		return positionKey;
	}
	public void setPositionKey(int positionKey) {
		this.positionKey = positionKey;
	}
	public String getClassRank() {
		return classRank;
	}
	public void setClassRank(String classRank) {
		this.classRank = classRank;
	}
	public boolean isCounted() {
		return counted;
	}
	public void setCounted(boolean counted) {
		this.counted = counted;
	}
	
}
